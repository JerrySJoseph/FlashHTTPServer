package com.jstechnologies.flashhttpserver;

import com.jstechnologies.flashhttpserver.cli.ArgumentParser;
import com.jstechnologies.flashhttpserver.httpconfig.HttpConfiguration;
import com.jstechnologies.flashhttpserver.httpserver.HttpServer;

import java.io.IOException;

public class Main {

    public static void main(String[] args){

        //parse cli args
        ArgumentParser.parse(args);
        HttpServer server;
        try {
            server = new HttpServer(8080);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
