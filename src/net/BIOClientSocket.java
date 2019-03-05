package net;

import com.sun.tools.internal.ws.wsdl.document.Output;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.concurrent.CountDownLatch;

public class BIOClientSocket {
    public static void main(String[] args) {
        Integer num = 20;
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < num; i++, countDownLatch.countDown()) {
            BIOClientSocketThread thread = new BIOClientSocketThread(countDownLatch, num);
            new Thread(thread).start();
        }
    }
}

class BIOClientSocketThread implements Runnable {

    private CountDownLatch countDownLatch;
    private Integer clientIndex;

    public BIOClientSocketThread(CountDownLatch countDownLatch, Integer clientIndex) {
        this.countDownLatch = countDownLatch;
        this.clientIndex = clientIndex;
    }

    @Override
    public void run() {
        Socket socket = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;

        try {
            //这里在建立连接的时候会发生阻塞
            socket = new Socket("localhost", 55333);
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();

            this.countDownLatch.await();
            outputStream.write(("这是第" + this.clientIndex + "个客户的请求，内容").getBytes());
            outputStream.flush();

            int MAX_CAPACITY = 1024;
            byte[] bytes = new byte[MAX_CAPACITY];
            int count;
            StringBuffer sb = new StringBuffer();
            //这里会发生阻塞
            while ((count = inputStream.read(bytes, 0, MAX_CAPACITY)) != -1) {
                sb.append(new String(bytes, 0, MAX_CAPACITY));
            }
            String message = URLDecoder.decode(sb.toString(), "UTF-8");
            System.out.println(message);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
