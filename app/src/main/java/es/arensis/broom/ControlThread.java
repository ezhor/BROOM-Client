package es.arensis.broom;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

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
                outputStream.write(BroomStatus.getInstance().toString().getBytes());
                /*Log.e("BroomStatus",
                        BroomStatus.getInstance().toString().substring(0, 4)
                        + "|" + BroomStatus.getInstance().toString().substring(4, 8)
                        + "|" + BroomStatus.getInstance().toString().substring(8, 12)
                        + "|" + BroomStatus.getInstance().toString().substring(12, 16)
                        + "|" + BroomStatus.getInstance().toString().substring(16, 17)
                );*/
                Thread.sleep(DELAY);
            }
            socket.close();

        } catch (IOException | InterruptedException e) {
            Log.e("CONNECTION", e.toString());
        }


    }
}
