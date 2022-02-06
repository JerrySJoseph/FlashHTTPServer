/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.jstechnologies.flashhttpserver.httpserver;

import com.jstechnologies.flashhttpserver.base.ConnectionHandler;
import com.jstechnologies.flashhttpserver.http.HttpParser;
import com.jstechnologies.flashhttpserver.http.HttpRequest;
import com.jstechnologies.flashhttpserver.http.HttpResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class HttpConnectionHandler extends ConnectionHandler {

    InputStream inputStream=null;
    OutputStream outputStream=null;
    HttpRequestHandler handler;

    public HttpConnectionHandler(Socket socket,HttpRequestHandler handler){
        super(socket);
        this.handler=handler;
    }

    @Override
    public boolean handleConnection(Socket mSocket) {
        System.out.println("Handling Http Connection");
        try {
            inputStream=mSocket.getInputStream();
            outputStream=mSocket.getOutputStream();

            HttpParser parser=new HttpParser();
            HttpRequest request=parser.parse(inputStream);

            HttpResponse response=this.handler.onRequest(request);

            outputStream.write(response.buildResponse());
            outputStream.close();
            inputStream.close();
        }  catch (Exception e) {
            System.out.println(e);

        }finally {

            return true;
        }



    }


}
