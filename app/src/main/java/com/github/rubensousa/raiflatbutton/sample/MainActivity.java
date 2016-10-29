package com.github.rubensousa.raiflatbutton.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.github.rubensousa.raiflatbutton.RaiflatButton;
import com.github.rubensousa.raiflatbutton.RaiflatImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private RaiflatButton mNormalButton;
    private RaiflatButton mNoRippleButton;
    private RaiflatButton mMergeRippleButton;
    private RaiflatImageButton mImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNormalButton = (RaiflatButton) findViewById(R.id.normalButton);
        mNoRippleButton = (RaiflatButton) findViewById(R.id.noRippleButton);
        mMergeRippleButton = (RaiflatButton) findViewById(R.id.mergeRippleButton);
        mImageButton = (RaiflatImageButton) findViewById(R.id.imageButton);
        findViewById(R.id.flatAllBtn).setOnClickListener(this);
        findViewById(R.id.raiseAllBtn).setOnClickListener(this);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.flatAllBtn) {
            onCheckedChanged(null, true);
            mNormalButton.setPressed(true);
            mNoRippleButton.setPressed(true);
            mMergeRippleButton.setPressed(true);
            mImageButton.setPressed(true);
        } else if (v.getId() == R.id.raiseAllBtn) {
            mNormalButton.setPressed(false);
            mNoRippleButton.setPressed(false);
            mMergeRippleButton.setPressed(false);
            mImageButton.setPressed(false);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mNormalButton.setFlatEnabled(isChecked);
        mNoRippleButton.setFlatEnabled(isChecked);
        mMergeRippleButton.setFlatEnabled(isChecked);
        mImageButton.setFlatEnabled(isChecked);
    }
}
