package com.jstechnologies.flashhttpserver.httpserver;

import com.jstechnologies.flashhttpserver.annotations.Method;
import com.jstechnologies.flashhttpserver.base.ConnectionHandler;
import com.jstechnologies.flashhttpserver.base.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP Server
 * Create an HTTP Server on a new Thread and keep it listening on the
 * given port
 * */
public class HttpServer extends Server {

    Class<?> srcClass;

    public HttpServer(int port,Class<?> srcClass) throws IOException {
        super(port);
        this.srcClass=srcClass;
    }

    public HttpServer(Class<?> srcClass) throws IOException {
        super(0);
        this.srcClass=srcClass;
    }

    @Override
    public void onStart(int port) {
        System.out.println("Started server on PORT: "+port);
    }

    @Override
    public void onStop() {
        System.out.println("Stopping Server");
    }

    @Override
    public void onNewConnection(Socket socket) {
        HttpConnectionHandler httpConnectionHandler= new HttpConnectionHandler(socket,this.srcClass);
        httpConnectionHandler.start();
    }
}
