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

    public Server(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    @Override
    protected String doInBackground(Void... voids) {

        try {
            serverSocket = new ServerSocket(8288);
            mainActivity.openServerSocket(true);
            Socket socket = serverSocket.accept();
            mainActivity.acceptClient(true);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            while (true) {
                String s = dataInputStream.readUTF();
                Log.v("Server: ", s);
                mainActivity.getData(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
