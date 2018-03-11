/*
Brooke Porter
xsadrithx@yahoo.com
2/27/18

Treehouse-Pomodoro

CS 17.11, Section 6991: Sean Kirkpatrick


*/

package treehouse.model;

public enum AttemptKind
{
    FOCUS(25 * 60, "Focus Time"),
    BREAK(5 * 60, "Break Time");

    private int mTotalSeconds;
    private String mDisplayName;

    AttemptKind(int mTotalSeconds, String displayName)
    {
        this.mTotalSeconds = mTotalSeconds;
        mDisplayName = displayName;
    }

    public int getTotalSeconds()
    {
        return mTotalSeconds;
    }

    public String getDisplayName()
    {
        return mDisplayName;
    }
}
