package com.github.rubensousa.raiflatbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;

/**
 * An ImageButton that lowers down to 0dp of elevation when it's pressed
 */
public class RaiflatImageButton extends AppCompatImageButton {

    private RaiflatDelegate mDelegate;

    public RaiflatImageButton(Context context) {
        this(context, null);
    }

    public RaiflatImageButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RaiflatImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mDelegate = new RaiflatDelegate(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RaiflatButton);
            mDelegate.setup(typedArray.getBoolean(R.styleable.RaiflatButton_flatten, true));
            typedArray.recycle();
        }
    }

    public void setFlatEnabled(boolean flatten) {
        mDelegate.setFlatEnabled(flatten);
    }

    public boolean isFlatEnabled() {
        return mDelegate.isFlatEnabled();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        return mDelegate.onSaveInstanceState(super.onSaveInstanceState());
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(mDelegate.onRestoreInstanceState(state));
    }
}
