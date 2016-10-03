package com.gowhich.androidhandlerandmessagequeue;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private Button button1;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            System.out.println("========> what = " + msg.what);
            System.out.println("========> arg1 = " + msg.arg1);
            System.out.println("========> arg2 = " + msg.arg2);
            System.out.println("========> obj = " + msg.obj);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) this.findViewById(R.id.button);
        button1 = (Button) this.findViewById(R.id.button2);

        button.setOnClickListener(this);
        button1.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(3);
                        handler.sendEmptyMessageAtTime(33, 10000);
                        handler.sendEmptyMessageDelayed(44, 10000);

//                        Message message = Message.obtain();
//                        message.arg1 = 1;
//                        message.arg2 = 2;
//                        message.obj = "tom";

                        Message message = handler.obtainMessage(33, 1, 2, "tom");
                        handler.sendMessage(message);

                    }
                }).start();

                break;
            case R.id.button2:
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Message message = handler.obtainMessage(33, 1, 2, "tom2");
                        handler.sendMessage(message);
                    }
                });
        }
    }
}
