package app.ctrlyati.android.lazyquickreturn.listener;

import android.support.v4.view.ViewCompat;
import android.view.View;

import app.ctrlyati.android.lazyquickreturn.QuickReturnHelper;

/**
 * Created by Yati on 05/14/2015.
 *
 * don't forget to call setShowing to true when target is showing and setShowing to false when the
 * target is not showing
 */
public abstract class SimpleQuickReturnListener implements QuickReturnListener {

    private float mScrollThreshold;

    private QuickReturnHelper.Direction mCurrentDirection = QuickReturnHelper.Direction.NONE;
    private QuickReturnHelper.Direction mShowDirection = QuickReturnHelper.Direction.NONE;

    private boolean mIsShowing = true;

    public SimpleQuickReturnListener(float scrollThreshold,
            QuickReturnHelper.Direction showDirection) {
        mScrollThreshold = scrollThreshold;
        mShowDirection = showDirection;
    }

    public SimpleQuickReturnListener(float scrollThreshold,
            QuickReturnHelper.Direction showDirection, boolean isShowing) {
        mScrollThreshold = scrollThreshold;
        mShowDirection = showDirection;
        mIsShowing = isShowing;
    }

    @Override
    public void onScroll(View view, QuickReturnHelper.Direction direction, float scrollY) {

        if (!ViewCompat.canScrollVertically(view,
                (int) mScrollThreshold) || !ViewCompat.canScrollVertically(view,
                -(int) mScrollThreshold)) {
            if (!mIsShowing) {
                onShow();
                mIsShowing = true;
            }
            return;
        }

        if (direction == mShowDirection && scrollY > mScrollThreshold &&
                mCurrentDirection != direction && !mIsShowing) {

            onShow();
            mIsShowing = true;
            mCurrentDirection = direction;

        } else if (direction == QuickReturnHelper.Direction.DOWN && scrollY > mScrollThreshold &&
                mCurrentDirection != direction && mIsShowing) {

            onHide();
            mIsShowing = false;
            mCurrentDirection = direction;
        }
    }

    public abstract void onShow();

    public abstract void onHide();

    public void setShowing(boolean isShowing) {
        mIsShowing = isShowing;
    }

    public boolean isShowing() {
        return mIsShowing;
    }

}
