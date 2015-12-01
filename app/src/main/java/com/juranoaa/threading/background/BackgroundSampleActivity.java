package com.juranoaa.threading.background;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.juranoaa.threading.R;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.BackgroundExecutor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BackgroundSampleActivity extends Activity {

    private static int count = 0;
    private static final String TASK_ID = "task_id";

    private Thread thread = null;
    /** AA에서는 List를 이용하여 pool 관리를 하지만 편의상 Map으로 구현함 */
    private Map<String, Thread> threadPool = null;

    private Button btnStopTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_background_sample);

        btnStopTask = (Button) findViewById(R.id.btn_stop_task);
        btnStopTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStopTaskClicked();
            }
        });

        threadPool = new HashMap<>();
        thread = new Thread(new MyThreadTask());

        threadPool.put(TASK_ID, thread); //풀에 등록
        thread.start();
    }

    void executeTask() {
        boolean loopFlag = true;

        while(loopFlag) {
            try {
                count++;
                Log.d("TAG", "count: " + count);
                //textView.setText("count: " + count); //UiThread가 아니므로 본 구문 실행불가!

                Thread.sleep(500);
            } catch (InterruptedException e) {
                Log.d("TAG", "InterruptedException occured! stop loop");
                loopFlag = false; //탈출
            }
        }
    }

    void btnStopTaskClicked() {
        Log.d("TAG", "btnStopTask() called");

        Thread thread = threadPool.get(TASK_ID);
        thread.interrupt();
    }

    private class MyThreadTask implements Runnable {
        @Override
        public void run() {
            executeTask();
        }
    }
}
