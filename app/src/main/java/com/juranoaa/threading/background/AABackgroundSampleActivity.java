package com.juranoaa.threading.background;

import android.app.Activity;
import android.util.Log;

import com.juranoaa.threading.R;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.api.BackgroundExecutor;

@EActivity(R.layout.activity_background_sample)
public class AABackgroundSampleActivity extends Activity {

    private static int count = 0;
    private static final String TASK_ID = "task_id";

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
                //textView.setText("count: " + count); //UiThread가 아니므로 본 구문 실행불가!

                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                Log.d("TAG", "InterruptedException occurred! stop loop");
                loopFlag = false; //탈출
            }
        }
    }

    @Click
    void btnStopTaskClicked() {
        Log.d("TAG", "btnStopTask() called");
        BackgroundExecutor.cancelAll(TASK_ID, true); //해당 인자가 false면 작업이 완료될 때까지 기다리며, true면 InterruptedException을 발생시킨다.
    }

}
