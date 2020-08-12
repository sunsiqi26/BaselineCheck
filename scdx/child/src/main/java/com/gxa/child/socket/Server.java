package com.gxa.child.socket;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings("all")
public class Server {

    public Server() throws IOException{
        Integer port = 8008;//端口号
        //注册服务端socket对象
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("服务器端启动");

//        Socket accept = serverSocket.accept();
//        InetAddress inetAddress = accept.getInetAddress();
//        String ipAddress = inetAddress.getHostAddress();
//        System.out.println("客户端ip:"+ipAddress+"-连接成功!");

//        DataInputStream dataInputStream = new DataInputStream(accept.getInputStream());
//        DataOutputStream dataOutputStream = new DataOutputStream(accept.getOutputStream());
//        String msg = dataInputStream.readUTF();//读
//        System.out.println("客户端:"+msg);
//        dataOutputStream.writeUTF("来自服务器的一条消息");//写
//
//        // 释放资源
//        dataInputStream.close();
//        dataOutputStream.close();
//        accept.close();
//        serverSocket.close();
        while (true){
            Socket accept = serverSocket.accept();
            // 使用线程解决多客户端问题
            new ServerThread(accept).start();
        }
    }
}
