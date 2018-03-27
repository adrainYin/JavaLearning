package net;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",9999);
        Scanner scanner = new Scanner(socket.getInputStream());
        while (scanner.hasNext()){
            System.out.println(scanner.next());
        }
        scanner.close();
        socket.close();
    }
}
