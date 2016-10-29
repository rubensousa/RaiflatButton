package com.github.rubensousa.raiflatbutton;


import android.os.Parcelable;

interface RaiflatView {

    Parcelable onSaveInstanceState(Parcelable state);

    Parcelable onRestoreInstanceState(Parcelable state);

    void setFlatEnabled(boolean enable);

    boolean isFlatEnabled();
}
