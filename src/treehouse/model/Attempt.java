/*
Brooke Porter
xsadrithx@yahoo.com
2/27/18

Treehouse-Pomodoro

CS 17.11, Section 6991: Sean Kirkpatrick


*/

package treehouse.model;

public class Attempt
{
    private String mMessage;
    private int mRemainingSeconds;
    private AttemptKind mKind;

    public Attempt(String mMessage, AttemptKind mKind)
    {
        this.mMessage = mMessage;
        this.mKind = mKind;
        mRemainingSeconds = mKind.getTotalSeconds();
    }

    public AttemptKind getmKind()
    {
        return mKind;
    }

    public String getmMessage()
    {
        return mMessage;
    }

    public int getmRemainingSeconds()
    {
        return mRemainingSeconds;
    }

    public void setmMessage(String mMessage)
    {
        this.mMessage = mMessage;
    }

    public void tick()
    {
        mRemainingSeconds--;
    }

    @Override
    public String toString()
    {
        return "Attempt{" +
                "mMessage='" + mMessage + '\'' +
                ", mRemainingSeconds=" + mRemainingSeconds +
                ", mKind=" + mKind +
                '}';
    }

    public void save()
    {
        System.out.printf("Saving: %s %n", this);
    }
}
