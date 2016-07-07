package com.kina.oneline_intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by g-emall on 2016/7/7.
 */
public class SecondActivity extends Activity {

    private Button button_2;
    private Button button_2_back;
    private TextView receive_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        button_2 = ((Button) findViewById(R.id.button_2));
        button_2_back = ((Button) findViewById(R.id.button_2_back));
        receive_data = ((TextView) findViewById(R.id.receive_data));

        //接收从FirstActivity传递过来的数据
        Intent intent_data = getIntent();
        String data = intent_data.getStringExtra("extra_data");
        Log.d("SecondActivity", data);
        receive_data.setText("接收传递过来的数据：" + data);

        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到第三个Activity
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

        button_2_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到第一个Activity
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("data_return", "Hello FirstActivity");
        setResult(RESULT_OK, intent);
        finish();
    }
}
