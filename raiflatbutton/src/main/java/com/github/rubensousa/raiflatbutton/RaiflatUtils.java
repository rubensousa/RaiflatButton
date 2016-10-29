package com.github.rubensousa.raiflatbutton;


import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

public class RaiflatUtils {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void setupRaiflat(View view) {
        StateListAnimator stateListAnimator
                = AnimatorInflater.loadStateListAnimator(view.getContext(),
                R.drawable.raiflatbutton_statelistanimator);
        view.setStateListAnimator(stateListAnimator);
    }
}
