package com.yoprogramo.jokestartedservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButton();
        setOnClickListener();
    }

    private void setOnClickListener() {
        button.setOnClickListener(clickListener);
    }


    private void initButton() {
        button = ((Button) findViewById(R.id.button));

    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,DelayedMessageService.class);
            intent.putExtra(EXTRA_MESSAGE, "Message from beyond....");
            startService(intent);
        }
    };
}
