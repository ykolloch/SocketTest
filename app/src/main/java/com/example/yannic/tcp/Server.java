package com.example.yannic.tcp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Yannic on 05.03.2017.
 */

public class Server extends AsyncTask<Void, Void, String> {


    private ServerSocket serverSocket;
    private MainActivity mainActivity;
    private String nmea_string = "";

    public Server(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    @Override
    protected String doInBackground(Void... voids) {

        try {
            serverSocket = new ServerSocket(8288);
            Log.v("Server", "ServerSocket offen");
            //mainActivity.openServerSocket(true);
            Socket socket = serverSocket.accept();
            Log.v("Server", "Client accepted");
            //mainActivity.acceptClient(true);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            while (true) {
                byte[] b = new byte[1];
                dataInputStream.read(b);
                String cha = new String(b);
                if(cha.equals("$")) {
                    Log.v("Server", nmea_string);
                    nmea_string = "";
                } else {
                    if(!cha.equals("\n"))
                        nmea_string = nmea_string.concat(cha);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
