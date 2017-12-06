package edu.npu.utils;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 
 * http 代理程序 
 * @author lulaijun 
 * 
 */  
public class SocketProxy {  
      
    static final int listenPort=10086;  
    static final SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    public static void main(String[] args) throws Exception {  
    	StringBuilder sbu  = new StringBuilder();
        ServerSocket serverSocket = new ServerSocket(listenPort);  
        final ExecutorService tpe=Executors.newCachedThreadPool();  
        //获取本机的InetAddress实例
  		InetAddress address =InetAddress.getLocalHost();
  		 //添加请求日志信息
  		sbu.append("Proxy Server StartTime：" + sdf.format(new Date()));
  		sbu.append("\r\n").append("Proxy Server Name：" + address.getHostName());
  		sbu.append("\r\n").append("Proxy Server IP	：" + address.getHostAddress());
  		sbu.append("\r\n").append("Proxy Server Port：" + listenPort);
  		System.out.println(String.valueOf(sbu));
        while (true) {  
            Socket socket = null;  
            try {  
                socket = serverSocket.accept();  
                socket.setKeepAlive(true);  
                //加入任务列表，等待处理  
                tpe.execute(new ProxyTask(socket));  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
      
}  