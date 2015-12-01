package com.juranoaa.threading.uithread;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import com.juranoaa.threading.R;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.BackgroundExecutor;

@EActivity(R.layout.activity_ui_thread_sample)
public class AAUiThreadSampleActivity extends Activity {

    private static int count = 0;
    private static final String TASK_ID = "task_id";

    @ViewById
    TextView textView;

    @Override
    protected void onStart() {
        super.onStart();

        executeTask();
    }

    @Background(id = TASK_ID)
    void executeTask() {
        boolean loopFlag = true;

        while(loopFlag) {
            try {
                count++;
                Log.d("TAG", "count: " + count);

                uiTask();

                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                Log.d("TAG", "InterruptedException occurred! stop loop");
                loopFlag = false; //탈출
            }
        }
    }

    @UiThread
    void uiTask() {
        textView.setText("count: " + count);
    }

    @Click
    void btnStopTaskClicked() {
        Log.d("TAG", "btnStopTask() called");
        BackgroundExecutor.cancelAll(TASK_ID, true); //해당 인자가 false면 작업이 완료될 때까지 기다리며, true면 InterruptedException을 발생시킨다.
    }

}
