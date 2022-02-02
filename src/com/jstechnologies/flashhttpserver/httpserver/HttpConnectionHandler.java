package com.jstechnologies.flashhttpserver.httpserver;

import com.jstechnologies.flashhttpserver.base.ConnectionHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionHandler extends ConnectionHandler {

    InputStream inputStream=null;
    OutputStream outputStream=null;

    public HttpConnectionHandler(Socket socket){
        super(socket);
    }

    @Override
    public boolean handleConnection(Socket mSocket) {
        System.out.println("Handling Http Connection");
        try {
            inputStream=mSocket.getInputStream();
            outputStream=mSocket.getOutputStream();

            int _byte;
            while((_byte=inputStream.read())>-1){
                System.out.print((char) _byte);
            }

            String CLRF="\n\r";
            String html="<html><head><title>WebPage</title></head><body><h1>This is a website</h1><p>From the simple Http server</p></body></html>";
            String response="HTTP/1.1 200 OK"+CLRF+
                            "Content-Length: "+html.getBytes().length+CLRF+CLRF+
                    html+CLRF+CLRF;
            Thread.sleep(5000);
            outputStream.write(response.getBytes());
            outputStream.close();
            inputStream.close();

        }  catch (Exception e) {
            System.out.println(e);
        }finally {
            return true;
        }

    }

}
