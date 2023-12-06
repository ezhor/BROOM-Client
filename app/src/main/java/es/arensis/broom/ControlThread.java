package es.arensis.broom;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ControlThread extends Thread {

    private static final int PORT = 2727;
    private static final int DELAY = 20;

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
            InetAddress broomAddress = InetAddress.getByName(ip);
            DatagramSocket datagramSocket = new DatagramSocket();
            byte[] buffer;

            while (active) {
                buffer = BroomStatus.getInstance().toString().getBytes();
                datagramSocket.send(new DatagramPacket(buffer, buffer.length, broomAddress, PORT));

                /*Log.e("BroomStatus",
                        BroomStatus.getInstance().toString().substring(0, 4)
                        + "|" + BroomStatus.getInstance().toString().substring(4, 8)
                        + "|" + BroomStatus.getInstance().toString().substring(8, 12)
                        + "|" + BroomStatus.getInstance().toString().substring(12, 16)
                        + "|" + BroomStatus.getInstance().toString().substring(16, 17)
                );*/
                Thread.sleep(DELAY);
            }
            datagramSocket.close();

        } catch (IOException | InterruptedException e) {
            Log.e("CONNECTION", e.toString());
        }


    }
}
