package net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ServerDemo {

    /**
     * 一个完整的nio的例子
     * @throws IOException
     */
    public void selector() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(2 * 1024);
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        //这里为什么需要用socket对象来绑定监听，而没有用ServerSocket来绑定
        serverSocketChannel.socket().bind(new InetSocketAddress(55333));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            int selectionNum = selector.select();
            if (selectionNum <= 0) {
                continue;
            }
            Set<SelectionKey> set = selector.selectedKeys();
            Iterator<SelectionKey> iterator = set.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //说明服务器socket已经接受
                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    //存在继承的关系，所以可以直接转型
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    //这里已经得到了服务器的channel下一步是为读数据作准备，这里的连接表示服务器
                    //已经取得了客户端的连接socket。为下一步监听socket的read作准备，所以还需要向轮询器注册。
                    SocketChannel sc = ssc.accept();
                    //开始读取数据
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                    iterator.remove();
                }
                //表示已经做好了读的准备，这里也是单线程处理的模型，可以将数据处理部分抽离出来，用线程池解决。
                else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    while (true) {
                        buffer.clear();
                        int count = sc.read(buffer);
                        if (count < 0) {
                            break;
                        }
                        buffer.flip();
                        //这里应该对buffer做相应的操作。
                    }
                    iterator.remove();
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("等待客户连接");
        Socket socket = serverSocket.accept();
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println("hello world");
        printWriter.close();
        serverSocket.close();


        AsynchronousServerSocketChannel asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
        //accept()不会阻塞，只是会在数据准备完成后调用
        Future<AsynchronousSocketChannel> socketChannelFuture = asynchronousServerSocketChannel.accept();
        try {
            AsynchronousSocketChannel socketChannel = socketChannelFuture.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
