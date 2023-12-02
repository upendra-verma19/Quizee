package com.quizee;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class InfoDialog extends Dialog implements android.view.View.OnClickListener {

    public Activity activity;

    public InfoDialog() {
        super(null);
    }

    public InfoDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.info_dialog);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

}