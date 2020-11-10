package org.sockettest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class MyServer {
    public static void main(String[] args){
        try{
            JSONObject obj = new JSONObject();
            JSONParser parser = new JSONParser();
            String messageType = "";

            ServerSocket ss=new ServerSocket(6666);
            Socket s=ss.accept();//establishes connection
            DataInputStream dis=new DataInputStream(s.getInputStream());
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());


            String  str=(String)dis.readUTF();
            JSONObject json = (JSONObject) parser.parse(str);
            messageType = json.get("query-type").toString();
            System.out.println("messageType = "+messageType);

            if(messageType.equals("time")){
                obj.put("time", new Date().toString());
            } else {
                obj.put("time", "No return time");
            }
            System.out.println("return server time = "+obj.get("time"));
            dout.writeUTF(obj.toJSONString());


            dout.flush();
            dout.close();
            ss.close();

        }catch(Exception e){System.out.println(e);}
    }
}
