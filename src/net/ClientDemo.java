package net;

import thread.Client;

import java.io.IOException;
import java.net.Socket;
import java.nio.channels.*;
import java.util.Scanner;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("59.78.194.236",55333);
        Scanner scanner = new Scanner(socket.getInputStream());
        while (scanner.hasNext()){
            System.out.println(scanner.next());
        }
        scanner.close();
        socket.close();


        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        //将管道设置成非阻塞
        socketChannel.configureBlocking(false);
        int interest = SelectionKey.OP_ACCEPT | SelectionKey.OP_CONNECT;
        /**
         * selectionKey对象提供了channel和selector的一种绑定的状态
         */
        SelectionKey key = socketChannel.register(selector, interest);
        //这个是一个阻塞方法，如果没有同道处于已经就绪的状态，那么线程会阻塞到该方法上。
        selector.select();

        CompletionHandler completionHandler;

    }
}
