package com.kina.oneline_intent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_1;
    private Button button_1_1;
    private Button button_1_2;
    private Button button_1_3;
    private Button button_quit;
    private Button button_put_data;
    private Button button_back_data;
    private TextView text_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        button_1 = ((Button) findViewById(R.id.button_1));
//        button_1_1 = ((Button) findViewById(R.id.button_1_1));
        button_1_2 = ((Button) findViewById(R.id.button_1_2));
        button_1_3 = ((Button) findViewById(R.id.button_1_3));
        button_put_data = ((Button) findViewById(R.id.button_put_data));
        button_back_data = ((Button) findViewById(R.id.button_back_data));
        text_back = ((TextView) findViewById(R.id.text_back));
        button_quit = ((Button) findViewById(R.id.button_quit));

        button_1.setOnClickListener(this);
//        button_1_1.setOnClickListener(this);
        button_1_2.setOnClickListener(this);
        button_1_3.setOnClickListener(this);
        button_put_data.setOnClickListener(this);
        button_back_data.setOnClickListener(this);
        button_quit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_1:

                //1、显式Intent
                //构建一个Intent
                //传入 FirstActivity.this 作为上下文，传入 SecondActivity.class作为目标活动
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
                break;

//            case R.id.button_1_1:
//
//                //2、隐式Intent
//                Intent intent_1 = new Intent("com.kina.activitytest.ACTION_START");
//                //此处category的值要与AndroidManifest.xml中的category的name要一致
//                intent_1.addCategory("com.kina.activitytest.MY_CATEGORY");
//                //通过 startActivity()方法来执行这个 Intent。
//                startActivity(intent_1);
//                break;

            case R.id.button_1_2:

                //调用系统的浏览器打开网页
                Intent intent_view = new Intent(Intent.ACTION_VIEW);
                intent_view.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent_view);

                break;

            case R.id.button_1_3:

                //调用系统拨号界面
                Intent intent_dialog = new Intent(Intent.ACTION_DIAL);
                intent_dialog.setData(Uri.parse("tel:10086"));
                startActivity(intent_dialog);

                break;

            case R.id.button_put_data:

                //向下一个活动传递数据
                String data = "Hello SecondActivity";
                Intent intent_put = new Intent(FirstActivity.this, SecondActivity.class);
                intent_put.putExtra("extra_data", data);
                startActivity(intent_put);

                break;

            case R.id.button_back_data:

                //接收给上一个Activity返回的数据
                Intent intent_back = new Intent(FirstActivity.this, SecondActivity.class);
                startActivityForResult(intent_back, 1);

                break;

            case R.id.button_quit:

                //销毁当前活动，效果和按下Back键是一样的
                finish();

                break;
        }
    }

    /**
     * setResult()方法接收两个参数，
     * 第一个参数用于向上一个活动返回处理结果，一般只使用 RESULT_OK 或 RESULT_CANCELED 这两个值，
     * 第二个参数则是把带有数据的 Intent 传递回去， 然后调用了 finish()方法来销毁当前活动。
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Log.d("FirstActivity", returnedData);
                    text_back.setText("接收返回的数据：" + returnedData);
                }
                break;
            default:
        }
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //调用双击退出函数
            exitBy2Click();
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }
}
