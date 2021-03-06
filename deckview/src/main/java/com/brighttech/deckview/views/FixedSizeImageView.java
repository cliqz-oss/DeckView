package com.brighttech.deckview.views;

/**
 * Created by Vikram on 01/04/2015.
 */

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * This is an optimized ImageView that does not trigger a requestLayout() or invalidate() when
 * setting the image to Null.
 */
public class FixedSizeImageView extends ImageView {

    boolean mAllowRelayout = true;
    boolean mAllowInvalidate = true;

    public FixedSizeImageView(Context context) {
        this(context, null);
    }

    public FixedSizeImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FixedSizeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public FixedSizeImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void requestLayout() {
        if (mAllowRelayout) {
            super.requestLayout();
        }
    }

    @Override
    public void invalidate() {
        if (mAllowInvalidate) {
            super.invalidate();
        }
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        boolean isNullBitmapDrawable = (drawable instanceof BitmapDrawable) &&
                (((BitmapDrawable) drawable).getBitmap() == null);
        if (drawable == null || isNullBitmapDrawable) {
            mAllowRelayout = false;
            mAllowInvalidate = false;
        }
        super.setImageDrawable(drawable);
        mAllowRelayout = true;
        mAllowInvalidate = true;
    }
}
