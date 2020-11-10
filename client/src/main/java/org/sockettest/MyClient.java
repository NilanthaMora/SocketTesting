package org.sockettest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
public class MyClient {
    public static void main(String[] args) {
        try{
            JSONObject obj = new JSONObject();
            JSONParser parser = new JSONParser();
            String serverTime = "";


            Socket s=new Socket("localhost",6666);
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            DataInputStream dis=new DataInputStream(s.getInputStream());

            obj.put("query-type", "time");
            dout.writeUTF(obj.toJSONString());

            String  str=(String)dis.readUTF();
            JSONObject json = (JSONObject) parser.parse(str);
            serverTime = json.get("time").toString();
            System.out.println("server time = "+serverTime);


            dout.flush();
            dout.close();
            s.close();
        }catch(Exception e){System.out.println(e);}
    }
}
