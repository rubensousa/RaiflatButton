/*
 * Copyright 2016 Rúben Sousa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.rubensousa.raiflatbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
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
        super(context, attrs);
        init(context, attrs);
    }

    public RaiflatImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mDelegate = new RaiflatDelegate(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && attrs != null) {
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
