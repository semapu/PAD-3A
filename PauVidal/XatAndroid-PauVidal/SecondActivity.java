package com.example.pau.myapplication;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Pau on 12/4/16.
 */

public class SecondActivity extends AppCompatActivity {
    protected MySocket s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        s = MainActivity.s;
        TextView outputText = (TextView) findViewById(R.id.outputChat);
        outputText.setMovementMethod(new ScrollingMovementMethod());
        Thread t = new Thread() {
            public void run() {
                String line;
                while((line = s.readLine()) != null) {
                    final TextView text = (TextView) findViewById(R.id.outputChat);
                    final String l = line;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            text.append(l+"\n");
                        }
                    });
                }
            }
        };
        t.start();
        Button btn = (Button) findViewById(R.id.enviar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.message);
                if(et.getText().toString().equals("")==false){
                    s.println(et.getText().toString());
                    et.setText("");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Thread t = new Thread() {
            public void run() {
                s.close();
            }
        };
        t.start();
        finish();
    }
}
