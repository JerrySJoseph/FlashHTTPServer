package com.jstechnologies.flashhttpserver.httpserver;

import com.jstechnologies.flashhttpserver.base.ConnectionHandler;
import com.jstechnologies.flashhttpserver.base.Server;
import com.jstechnologies.flashhttpserver.httpconfig.HttpConfiguration;

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

    public HttpServer(int port) throws IOException {
        super(port);
    }

    public HttpServer() throws IOException {
        super(0);
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
        HttpConnectionHandler httpConnectionHandler= new HttpConnectionHandler(socket);
        httpConnectionHandler.start();
    }
}
