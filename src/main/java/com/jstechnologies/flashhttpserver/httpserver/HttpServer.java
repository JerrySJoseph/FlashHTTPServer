package com.jstechnologies.flashhttpserver.httpserver;

import com.jstechnologies.flashhttpserver.base.ConnectionHandler;
import com.jstechnologies.flashhttpserver.base.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * HTTP Server
 * Create an HTTP Server on a new Thread and keep it listening on the
 * given port
 * */
public class HttpServer extends Server {

    int HTTP_PORT=8080;
    HttpRequestHandler handler;
    public HttpServer(int port,HttpRequestHandler handler) throws IOException {
        super(port);
        this.handler=handler;
    }

    public HttpServer(HttpRequestHandler handler) throws IOException {
        super(0);
        this.handler=handler;
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
        HttpConnectionHandler httpConnectionHandler= new HttpConnectionHandler(socket,this.handler);
        httpConnectionHandler.start();
    }
}
