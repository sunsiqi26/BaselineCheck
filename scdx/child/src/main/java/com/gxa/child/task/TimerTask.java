package com.gxa.child.task;

import com.gxa.child.service.impl.ProcessDataService;
import com.gxa.child.socket.ServerThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class TimerTask {

    @Autowired
    private ProcessDataService processDataService;

    @Scheduled(fixedDelay = 4000L)
    public void task01(){
        try {
            Integer port = 8008;//端口号
            //注册服务端socket对象
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务器端启动");
            while (true) {
                Socket accept = serverSocket.accept();
                // 使用线程解决多客户端问题
                new ServerThread(accept,processDataService).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
