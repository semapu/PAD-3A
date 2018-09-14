package com.example.pau.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public static MySocket s = new MySocket();
    private GoogleApiClient client;
    protected Boolean connectat = false;

    PopupWindow popUp;
    LinearLayout layout;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        //POP UP:
        popUp = new PopupWindow(this);
        layout = new LinearLayout(this);
        tv = new TextView(this);
        tv.setTextSize(24);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        tv.setText("Aplicació feta per l'assignatura PAD.");
        layout.addView(tv);
        popUp.setContentView(layout);
}

    public void iniciarClient() {
        final int port = 12345;
        Log.e("iniciarClient()", "Application started");
        EditText et = (EditText) findViewById(R.id.ipField);
        final CharSequence cs = et.getText();
        Thread t = new Thread() {
            public void run() {
                Log.e("iniciarClient()", "Application started1");
                s.connect(port,cs.toString());
                Log.e("iniciarClient()", "Application started2");
                String ll;

                if((ll = s.readLine()).equals("connected")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            connectat = true;
                            Button b = (Button) findViewById(R.id.connect);
                            b.setVisibility(Button.INVISIBLE);
                            EditText et = (EditText) findViewById(R.id.ipField);
                            et.setKeyListener(null);
                            final TextView text = (TextView) findViewById(R.id.connectant);
                            text.setText("Connectat  ");
                            ProgressBar pb = (ProgressBar) findViewById(R.id.pbLoading);
                            pb.setVisibility(ProgressBar.INVISIBLE);

                            CheckBox cb = (CheckBox) findViewById(R.id.checkBox);
                            cb.setVisibility(View.VISIBLE);


                        }
                    });
                } else {
                    Log.e("iniciarClient()", "No s'ha pogut connectar.");
                    finish();
                }
            }
        };
        t.start();
    }


    public void sendNick(View view) {
        if(connectat) {
            TextView tw = (TextView) findViewById(R.id.nick);
            CharSequence cs = tw.getText();
            if(cs.equals("") == false) {
                s.println(cs.toString());
                //Iniciar segona activitat
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                //acabar primera activitat
                finish();
            }
        }
    }

    public static boolean ip(String text) {
        Pattern p = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
        Matcher m = p.matcher(text);
        return m.find();
    }

    public void botoConnectar(View view) {
        EditText et = (EditText) findViewById(R.id.ipField);
        CharSequence cs = et.getText();
        if(ip(cs.toString())){
            TextView tw = (TextView) findViewById(R.id.connectant);
            tw.setVisibility(TextView.VISIBLE);
            ProgressBar pb = (ProgressBar) findViewById(R.id.pbLoading);
            pb.setVisibility(ProgressBar.VISIBLE);
            iniciarClient();
        }
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
        ViewGroup view = (ViewGroup)getWindow().getDecorView();
        LinearLayout content = (LinearLayout)view.getChildAt(0);

        if (id == R.id.about) {
            popUp.showAtLocation(content, Gravity.CENTER, 0, 0);
            popUp.update(600, 200);
            popUp.setOutsideTouchable(false);
            View.OnTouchListener l = new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    popUp.dismiss();
                    return false;
                }
            };
            popUp.setTouchInterceptor(l);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.pau.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.pau.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
