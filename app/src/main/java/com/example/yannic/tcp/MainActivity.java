package com.example.yannic.tcp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tfServer, tfClient, tfData;
    private String LOG = String.valueOf(this.getClass());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.iniAll();
        new Server(this).execute();
    }

    public void openServerSocket(final Boolean b) {
        if(b)
            Log.v(LOG, "ServerSocket open");
            tfServer.setText("OK");
    }

    public void acceptClient(final Boolean b) {
        if(b)
            Log.v(LOG, "Client accept");
            tfClient.setText("OK");
    }

    public void getData(final Boolean b) {
        if(b)
            Log.v(LOG, "Data inc");
            tfData.setText("OK");
    }

    private void iniAll() {
        tfServer = (TextView) findViewById(R.id.tfServerStatus);
        tfClient = (TextView) findViewById(R.id.tfClientStatus);
        tfData = (TextView) findViewById(R.id.tfDataStatus);
    }
}
