package com.gxa.child.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void Client() throws IOException {
        Integer port = 8008;
        String ipAddress = "127.0.0.1";
        Socket socket = new Socket(ipAddress,port);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

        dataOutputStream.writeUTF("来自客户端的一条消息");
        String msg = dataInputStream.readUTF();
        System.out.println("服务端:"+msg);

        // 释放资源
        dataInputStream.close();
        dataOutputStream.close();
        socket.close();
    }

}
