package com.sungkyu.catchlion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button_start_single,button_start_multi;
    static allValus valus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valus = (allValus)getApplication();

        button_start_single = (Button)findViewById(R.id.btn_start_single);
        //button_start_single.setOnClickListener(this);
        button_start_multi = (Button)findViewById(R.id.btn_start_multi);
        button_start_multi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        if( view.getId() == R.id.btn_start_single) {
//            Intent intent = new Intent(this, game_play.class);
//            valus.setGame_mode(0);
//            startActivity(intent);
//        }
        if( view.getId() == R.id.btn_start_multi) {
            Intent intent = new Intent(this, game_play.class);
            valus.setGame_mode(1);
            startActivity(intent);
        }

    }
}
