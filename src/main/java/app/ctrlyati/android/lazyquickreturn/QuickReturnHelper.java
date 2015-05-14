package app.ctrlyati.android.lazyquickreturn;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import app.ctrlyati.android.lazyquickreturn.listener.QuickReturnListener;

/**
 * Created by Yati on 05/13/2015.
 */
public class QuickReturnHelper {

    private Context mContext;
    private View mObserveView;
    private View mTargetView;

    private ArrayList<QuickReturnAction> mActions = new ArrayList<>();

    public enum Direction {
        UP,
        DOWN,
        NONE
    }

    public QuickReturnHelper(Context context, View observeView) {
        mContext = context;
        mObserveView = observeView;

        mObserveView.setOnTouchListener(new View.OnTouchListener() {

            private MotionEvent.PointerCoords position = new MotionEvent.PointerCoords();

            float lastY = Float.MIN_VALUE;
            float up = 0f;
            float down = 0f;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                event.getPointerId(event.getPointerCount() - 1);

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    lastY = Float.MIN_VALUE;
                    up = 0f;
                    down = 0f;
                    return false;
                }

                float y = event.getY();

                if (lastY == Float.MIN_VALUE) {
                    lastY = y;
                }

                if (lastY > y) {
                    up = 0f;
                    down += lastY - y;

                    notifyQuickReturn(Direction.DOWN, down);
                } else if (lastY < y) {
                    down = 0f;
                    up += y - lastY;

                    notifyQuickReturn(Direction.UP, up);
                }

                lastY = y;

                return false;
            }
        });
    }

    public void registerAction(QuickReturnListener listener) {
        mActions.add(new QuickReturnAction(listener));
    }

    private static class QuickReturnAction {

        private QuickReturnListener quickReturnActionListener;

        public QuickReturnAction(QuickReturnListener quickReturnActionListener) {
            this.quickReturnActionListener = quickReturnActionListener;
        }

    }

    public void notifyQuickReturn(Direction direction, float scrollY) {
        for (QuickReturnAction action : mActions) {
            action.quickReturnActionListener.onScroll(direction, scrollY);
        }
    }
}
