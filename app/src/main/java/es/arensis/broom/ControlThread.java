package es.arensis.broom;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ControlThread extends Thread {

    private static final int PORT = 2727;
    private static final int DELAY = 50;

    private final String ip;
    private boolean active = true;

    public ControlThread(String ip) {
        this.ip = ip;
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
                outputStream.write(BroomStatus.getInstance().toString().concat("\n").getBytes());
                Log.e("asd", BroomStatus.getInstance().toString().concat("\n"));
                Thread.sleep(DELAY);
            }
            socket.close();

        } catch (IOException | InterruptedException e) {
            Log.e("CONNECTION", e.toString());
        }


    }
}
