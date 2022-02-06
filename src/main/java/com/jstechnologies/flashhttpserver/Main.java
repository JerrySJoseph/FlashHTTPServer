package com.jstechnologies.flashhttpserver;

import com.jstechnologies.flashhttpserver.cli.ArgumentParser;
import com.jstechnologies.flashhttpserver.http.HttpRequest;
import com.jstechnologies.flashhttpserver.http.HttpResponse;
import com.jstechnologies.flashhttpserver.httpserver.HttpRequestHandler;
import com.jstechnologies.flashhttpserver.httpserver.HttpServer;

public class Main {

    public static void main(String[] args){

        //parse cli args
        ArgumentParser.parse(args);
        HttpServer server;
        try {
            server = new HttpServer(8080,new MyHttpRequestHandler());
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    static class MyHttpRequestHandler implements HttpRequestHandler {
        @Override
        public HttpResponse onRequest(HttpRequest httpRequest) {
            HttpResponse response=new HttpResponse();
            if(httpRequest.getMethod().equals("GET")){
                response.setStatusCode(400);
                response.setStatusMessage("Bad Request");
               // String html="<html><head><title>Test Page</title></head><body><h1>Lets dont roll it</h1><p>From the simple Http server</p></body></html>";
               // response.setBody(html);
            }
            return response;
        }
    }
}
