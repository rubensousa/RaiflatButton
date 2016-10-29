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
