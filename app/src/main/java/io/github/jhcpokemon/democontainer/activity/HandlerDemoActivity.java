package io.github.jhcpokemon.democontainer.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.jhcpokemon.democontainer.R;

public class HandlerDemoActivity extends AppCompatActivity {

    @Bind(R.id.text_view1)
    TextView textView1;
    @Bind(R.id.text_view2)
    TextView textView2;
    @Bind(R.id.button1)
    Button button1;
    @Bind(R.id.button2)
    Button button2;
    @Bind(R.id.button3)
    Button button3;
    @Bind(R.id.button4)
    Button button4;
    @Bind(R.id.button5)
    Button button5;
    private MyHandler handler1;
    private MyHandler2 handler2;
    private Handler handler3 = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_demo);
        ButterKnife.bind(this);
        handler1 = new MyHandler();
        button1.setOnClickListener(new ButtonListener());
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = handler2.obtainMessage();
                msg.obj = "From MainThread!";
                msg.sendToTarget();
                Log.i("Main", Thread.currentThread().getName());
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyThread2 thread2 = new MyThread2();
                thread2.start();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyThread3 myThread3 = new MyThread3();
                myThread3.run();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandlerThread thread = new HandlerThread("My handler thread");
                thread.start();
                Handler handler = new Handler(thread.getLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        Toast.makeText(getApplicationContext(), msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    }
                };
                Message message = handler.obtainMessage();
                message.obj = thread.getName();
                message.sendToTarget();
            }
        });
        MyThread mt = new MyThread();
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mt.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Message msg = handler1.obtainMessage();
            msg.what = 0;
            handler1.sendMessage(msg);
        }
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            int result = msg.what;
            textView1.setText("result" + result);
        }
    }

    class MyHandler2 extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Log.i("from_work_thread", msg.obj.toString());
            Log.i("Thread", Thread.currentThread().getName());
        }
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            Looper.prepare();
            handler2 = new MyHandler2();
            Looper.loop();
        }
    }

    class MyThread2 extends Thread {
        @Override
        public void run() {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    textView2.setText("From post");
                }
            };
            handler3.post(runnable);
        }
    }

    class MyThread3 extends Thread {
        @Override
        public void run() {
            Handler handler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    if (msg.what == 1) {
                        Toast.makeText(getApplication(), "From callback", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    return false;
                }
            }) {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Toast.makeText(getApplication(), "From handler", Toast.LENGTH_SHORT).show();
                }
            };
            Message msg = handler.obtainMessage();
            msg.what = 1;
            msg.sendToTarget();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textView2.setText("Delay");
                }
            }, 3000);
        }
    }
}
