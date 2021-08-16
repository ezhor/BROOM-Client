package es.arensis;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ControlThread extends Thread {

    private static final int PORT = 2727;
    private static final int DELAY = 50;

    private final String ip;
    private final BroomStatus broomStatus;
    private boolean active = true;

    public ControlThread(String ip, BroomStatus broomStatus) {
        this.ip = ip;
        this.broomStatus = broomStatus;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void run() {

        try {
            Socket socket = new Socket(ip, PORT);
            OutputStream outputStream = socket.getOutputStream();

            while (active) {
                outputStream.write(broomStatus.toString().concat("\n").getBytes());
                Log.e("asd", broomStatus.toString().concat("\n"));
                Thread.sleep(DELAY);
            }
            socket.close();

        } catch (IOException | InterruptedException e) {
            Log.e("CONNECTION", e.toString());
        }


    }
}
