package com.jstechnologies.flashhttpserver;


import com.jstechnologies.flashhttpserver.httpserver.HttpServer;
import com.jstechnologies.flashhttpserver.sample.MyGetRequestHandler;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        HttpServer server=new HttpServer(8080,Main.class);
        server.start();
    }
}
