package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.spi.AsynchronousChannelProvider;

public class BIOServerSocket {

    public static void main(String[] args) throws IOException {

        //这里用的是阻塞io的方式进行。
        AsynchronousChannelProvider provider;
        AsynchronousServerSocketChannel asynchronousServerSocketChannel;
        ServerSocket serverSocket = null;
        serverSocket = new ServerSocket(55333);
        System.out.println("开始监听端口");
        while (true) {
            Socket socket = serverSocket.accept();
            BIOSocketServerThread thread = new BIOSocketServerThread(socket);
            new Thread(thread).start();
        }
    }
}

class BIOSocketServerThread implements Runnable {

    private static final int MAX_CAPITARY = 1024;
    private Socket socket;

    public BIOSocketServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        int port = socket.getPort();
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            byte[] bytes = new byte[MAX_CAPITARY];
            StringBuffer sb = new StringBuffer();
            int count;
            while ((count = inputStream.read(bytes, 0, MAX_CAPITARY)) != -1) {
                sb.append(new String(bytes));
            }
            System.out.println("服务器接收了来自" + port + "端口的信息" + sb);
            outputStream.write("信息收到".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                inputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}