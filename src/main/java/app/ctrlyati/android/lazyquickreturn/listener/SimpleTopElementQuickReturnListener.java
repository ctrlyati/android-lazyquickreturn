package app.ctrlyati.android.lazyquickreturn.listener;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import app.ctrlyati.android.lazyquickreturn.QuickReturnHelper;
import app.ctrlyati.android.lazyquickreturn.R;

/**
 * Created by Yati on 05/14/2015.
 */
public class SimpleTopElementQuickReturnListener extends SimpleQuickReturnListener {

    public static final float SCROLL_THRESHOLD = 100f;

    private View mTarget;

    private Animation mEnterAnimation;
    private Animation mExitAnimation;

    public SimpleTopElementQuickReturnListener(@NonNull final View target, Context context) {
        super(SCROLL_THRESHOLD, QuickReturnHelper.Direction.UP);

        mTarget = target;



        mExitAnimation = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.abc_slide_out_top);
        mExitAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mTarget.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mEnterAnimation = AnimationUtils.loadAnimation(context.getApplicationContext(),
                R.anim.abc_slide_in_top);
        mEnterAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mTarget.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Override
    public void onShow() {
        mTarget.startAnimation(mEnterAnimation);
    }

    @Override
    public void onHide() {
        mTarget.startAnimation(mExitAnimation);
    }
}
