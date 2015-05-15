package app.ctrlyati.android.lazyquickreturn.listener;

import android.view.View;

import app.ctrlyati.android.lazyquickreturn.QuickReturnHelper;

/**
 * Created by Yati on 05/14/2015.
 */
public interface QuickReturnListener {
    void onScroll(View view, QuickReturnHelper.Direction direction, float scrollY);
}
