package com.example.yujin.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Logger.Log;

public class MainActivity extends AppCompatActivity {
Button btnMap, btnSearch, btnPublicMap, btn;
    TextView tvLocation;
    String lol="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MoimListviewActivity.class);
                startActivity(intent);
            }
        });



        btnMap=(Button)findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(intent);
            }
        });

        btnSearch = (Button)findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettongLocation.class);
                startActivity(intent);
            }
        });

        btnPublicMap = (Button)findViewById(R.id.btnPublicMap);
        btnPublicMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PublicMap.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences test = getSharedPreferences("주소", MODE_PRIVATE);
        lol = test.getString("주소", "empty");

        if(lol.equals("empty")){
            Log.i("가져온정보", "없음");
        }else{
            Message msg1 = handler.obtainMessage();
            msg1.what=1;
            handler.sendMessage(msg1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences pref = getSharedPreferences("주소", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove("주소");
        editor.commit();


    }


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 1:
                    tvLocation = (TextView)findViewById(R.id.tvLocation);
                    tvLocation.setText(lol);
                    break;
            }
        }
    };
}
