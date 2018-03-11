/*
Brooke Porter
xsadrithx@yahoo.com
2/27/18

Treehouse-Pomodoro

CS 17.11, Section 6991: Sean Kirkpatrick


*/

package treehouse.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import treehouse.model.Attempt;
import treehouse.model.AttemptKind;

public class Home
{
    private final AudioClip myApplause;
    @FXML
    private VBox container;

    @FXML
    private Label title;

    @FXML
    private TextArea message;

    private Attempt mCurrentAttempt;
    private StringProperty mTimerText;
    private Timeline mTimeLine;

    public Home()
    {
        mTimerText = new SimpleStringProperty();
        setTimerText(0);
        myApplause = new AudioClip(getClass().getResource("/sounds/applause.mp3").toExternalForm());
    }

    public String getTimerText()
    {
        return mTimerText.get();
    }

    public StringProperty timerTextProperty()
    {
        return mTimerText;
    }

    public void setTimerText(String mTimerText)
    {
        this.mTimerText.set(mTimerText);
    }

    public void setTimerText(int remainingSeconds)
    {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        setTimerText(String.format("%02d:%02d", minutes, seconds));
    }

    private void prepareAttempt(AttemptKind kind)
    {
        reset();
        mCurrentAttempt = new Attempt("", kind);
        addAttemptStyle(kind);
        title.setText(kind.getDisplayName());
        setTimerText(mCurrentAttempt.getmRemainingSeconds());

        mTimeLine = new Timeline();
        mTimeLine.setCycleCount(kind.getTotalSeconds());
        mTimeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(1), (e) ->
        {
            mCurrentAttempt.tick();
            setTimerText(mCurrentAttempt.getmRemainingSeconds());
        }));
        mTimeLine.setOnFinished(e ->
        {
            saveCurrentAttempt();
            myApplause.play();
            prepareAttempt(mCurrentAttempt.getmKind() == AttemptKind.FOCUS ?
                    AttemptKind.BREAK : AttemptKind.FOCUS);
        });
    }

    private void saveCurrentAttempt()
    {
        mCurrentAttempt.setmMessage(message.getText());
        mCurrentAttempt.save();
    }

    private void reset()
    {
        clearAttemptStyles();
        if (mTimeLine != null && mTimeLine.getStatus() == Animation.Status.RUNNING)
        {
            mTimeLine.stop();
        }
    }

    public void playTimer()
    {
        container.getStyleClass().add("playing");
        mTimeLine.play();
    }

    public void pauseTimer()
    {
        container.getStyleClass().remove("playing");
        mTimeLine.pause();
    }

    private void addAttemptStyle(AttemptKind kind)
    {
        container.getStyleClass().add(kind.toString().toLowerCase());
    }

    private void clearAttemptStyles()
    {
        container.getStyleClass().remove("playing");
        for (AttemptKind kind : AttemptKind.values())
        {
            container.getStyleClass().remove(kind.toString().toLowerCase());
        }
    }

    public void handleRestart(ActionEvent actionEvent)
    {
        prepareAttempt(AttemptKind.FOCUS);
        playTimer();
    }

    public void handlePause(ActionEvent actionEvent)
    {
        pauseTimer();
    }

    public void handlePlay(ActionEvent actionEvent)
    {
        if (mCurrentAttempt == null)
        {
            handleRestart(actionEvent);
        }
        else
        {
            playTimer();
        }
    }
}
