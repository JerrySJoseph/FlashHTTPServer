/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.jstechnologies.flashhttpserver.httpserver;

import com.jstechnologies.flashhttpserver.annotations.Route;
import com.jstechnologies.flashhttpserver.base.ConnectionHandler;
import com.jstechnologies.flashhttpserver.http.HttpParser;
import com.jstechnologies.flashhttpserver.http.HttpRequest;
import com.jstechnologies.flashhttpserver.http.HttpResponse;
import com.jstechnologies.flashhttpserver.sample.MyGetRequestHandler;
import org.reflections.Reflections;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class HttpConnectionHandler extends ConnectionHandler {

    InputStream inputStream=null;
    OutputStream outputStream=null;
    Class<?> srcClass;

    public HttpConnectionHandler(Socket socket,Class<?>srcClass){
        super(socket);
        this.srcClass=srcClass;
    }


    private HttpRequestHandler getHandler(HttpRequest request){

        switch (request.getMethod()){
            case "GET":return getAnnotatedClass("GET");
        }

        return new MyGetRequestHandler();
    }

    HttpRequestHandler getAnnotatedClass(String method){

        Reflections ref = new Reflections(this.srcClass.getPackageName());

        for (Class<?> cl : ref.getTypesAnnotatedWith(Route.class)) {
            var findable = cl.getAnnotation(Route.class);
            System.out.printf("Found class: %s, with meta name: %s%n",
                    cl.getPackageName(), findable.Url());
        }
        return new MyGetRequestHandler();
    }


    @Override
    public boolean handleConnection(Socket mSocket) {
        try {
            inputStream=mSocket.getInputStream();
            outputStream=mSocket.getOutputStream();

            HttpParser parser=new HttpParser();
            HttpRequest request=parser.parse(inputStream);

            HttpRequestHandler handler=new DefaultHttpRequestHandler("C:\\Users\\JERRY\\Desktop\\html");

            //default handler
            HttpResponse response=handler.onRequest(request);

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
