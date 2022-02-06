package com.jstechnologies.flashhttpserver.httpserver;

import com.jstechnologies.flashhttpserver.base.ConnectionHandler;
import com.jstechnologies.flashhttpserver.http.HttpParser;
import com.jstechnologies.flashhttpserver.http.HttpRequest;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

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

            HttpParser parser=new HttpParser();
            HttpRequest request=parser.parse(inputStream);

            System.out.println("Request method: "+request.getMethod());
            System.out.println("Request version: "+request.getVersion());
            for(Map.Entry<String,String>entry:request.getHeaders().entrySet()){
                System.out.println(entry.getKey()+": "+entry.getValue());
            }
            System.out.println(new String(request.getBody()));

            String CLRF="\n";
            String html="<html><head><title>WebPage</title></head><body><h1>This is a website</h1><p>From the simple Http server</p></body></html>";
            String response="HTTP/1.1 200 OK"+CLRF+
                            "Content-Length: "+html.getBytes().length+CLRF+CLRF+
                    html+CLRF+CLRF;
          //  Thread.sleep(200);
            outputStream.write(response.getBytes());
            outputStream.close();
            inputStream.close();
        }  catch (Exception e) {
            System.out.println(e);
            outputStream.close();
            inputStream.close();

        }finally {

            return true;
        }

    }

}
