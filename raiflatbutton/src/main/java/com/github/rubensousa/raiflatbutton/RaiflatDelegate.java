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


import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

class RaiflatDelegate implements RaiflatView {

    private View mView;
    private StateListAnimator mFlatStateListAnimator;
    private StateListAnimator mOriginalStateListAnimator;
    private boolean mFlatEnabled;

    public RaiflatDelegate(View view) {
        mView = view;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setup(boolean flatten) {

        mFlatEnabled = flatten;


        if (mView.getElevation() == 0) {
            mView.setElevation(mView.getContext().getResources()
                    .getDimensionPixelSize(R.dimen.button_elevation));
        }

        mOriginalStateListAnimator = mView.getStateListAnimator();

        if (flatten) {
            setupStateListAnimator();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupStateListAnimator() {
        mFlatStateListAnimator = new StateListAnimator();

        ObjectAnimator pressed = ObjectAnimator.ofFloat(this, "elevation", 0)
                .setDuration(mView.getContext().getResources()
                        .getInteger(android.R.integer.config_shortAnimTime));

        ObjectAnimator notPressed = ObjectAnimator.ofFloat(this, "elevation", mView.getElevation()
                + mView.getTranslationZ()).setDuration(mView.getContext().getResources()
                .getInteger(android.R.integer.config_shortAnimTime));


        notPressed.setStartDelay(100);

        mFlatStateListAnimator.addState(new int[]{android.R.attr.state_pressed,
                android.R.attr.state_enabled}, pressed);

        mFlatStateListAnimator.addState(new int[]{android.R.attr.state_enabled}, notPressed);

        mFlatStateListAnimator.addState(new int[]{},
                ObjectAnimator.ofFloat(this, "elevation", 0).setDuration(0));

        mView.setStateListAnimator(mFlatStateListAnimator);
    }

    @Override
    public void setFlatEnabled(boolean flatten) {
        mFlatEnabled = flatten;
        if (mFlatEnabled) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mView.setStateListAnimator(mFlatStateListAnimator);
            }
        } else {
            mView.setPressed(false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mView.setStateListAnimator(mOriginalStateListAnimator);
            }
        }
    }

    @Override
    public boolean isFlatEnabled() {
        return mFlatEnabled;
    }

    @Override
    public Parcelable onSaveInstanceState(Parcelable state) {
        SavedState savedState = new SavedState(state);
        savedState.canFlatten = mFlatEnabled;
        return state;
    }

    @Override
    public Parcelable onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            return state;
        }

        SavedState ss = (SavedState) state;
        mFlatEnabled = ss.canFlatten;
        return ss.getSuperState();
    }

    public static class SavedState extends View.BaseSavedState {

        public boolean canFlatten;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public SavedState(Parcel source) {
            super(source);
            canFlatten = source.readByte() == 0x01;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeByte((byte) (canFlatten ? 0x01 : 0x00));
        }

        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
