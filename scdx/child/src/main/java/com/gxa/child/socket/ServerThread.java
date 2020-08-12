package com.gxa.child.socket;

import com.gxa.child.service.impl.ProcessDataService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class ServerThread extends Thread{

    private Socket socket;//socket对象
    private InputStreamReader inputStreamReader;//输入流
    private ProcessDataService processDataService;//处理数据的对象

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public ServerThread(Socket socket, ProcessDataService processDataService) {
        this.socket = socket;
        this.processDataService = processDataService;
    }

    @Override
    public void run(){
        try {
            InetAddress inetAddress = socket.getInetAddress();
            String ipAddress = inetAddress.getHostAddress();
            System.out.println("客户端ip:"+ipAddress+",连接成功!");

            //对接python客户端
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            String msg = getData(inputStreamReader);
            System.out.println("客户端:"+msg);

            //处理数据
            processDataService.process(msg);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeResources(inputStreamReader,socket);
        }
    }


    private String getData(InputStreamReader inputStreamReader) throws IOException{
        char[] chars = new char[1024];
        int read;//一次读取的量
        //用StringBuilder拼接字符串
        StringBuilder stringBuilder = new StringBuilder();
        while ((read = inputStreamReader.read(chars)) != -1){
            System.out.println("读取了:"+read+"个字符!");
            stringBuilder.append(new String(chars, 0, read));
        }
        //转换成字符串
        return stringBuilder.toString();
    }
    /**
     * 销毁资源
     * @param inputStreamReader
     * @param socket
     */
    private void closeResources(InputStreamReader inputStreamReader, Socket socket){
        try {
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
