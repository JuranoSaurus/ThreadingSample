package com.juranoaa.threading;

import android.app.Activity;
import android.content.Intent;

import com.juranoaa.threading.background.AABackgroundSampleActivity_;
import com.juranoaa.threading.background.BackgroundSampleActivity;
import com.juranoaa.threading.uithread.AAUiThreadSampleActivity_;
import com.juranoaa.threading.uithread.UiThreadSampleActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class AAMainActivity extends Activity {
    private Intent intent;

    @Click(R.id.btn_aa_background_sample_activity_call)
    void btnAABackgroundSampleActivityCallClicked() {
        intent = new Intent(this, AABackgroundSampleActivity_.class);
        startActivity(intent);
    }

    @Click
    void btnBackgroundSampleActivityCallClicked() {
        intent = new Intent(this, BackgroundSampleActivity.class);
        startActivity(intent);
    }

    @Click(R.id.btn_aa_ui_thread_sample_activity_call)
    void btnAAUiThreadSampleActivityCallClicked() {
        intent = new Intent(this, AAUiThreadSampleActivity_.class);
        startActivity(intent);
    }

    @Click
    void btnUiThreadSampleActivityCallClicked() {
        intent = new Intent(this, UiThreadSampleActivity.class);
        startActivity(intent);
    }

}
