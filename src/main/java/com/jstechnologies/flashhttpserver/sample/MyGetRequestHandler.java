/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.jstechnologies.flashhttpserver.sample;

import com.jstechnologies.flashhttpserver.annotations.Method;
import com.jstechnologies.flashhttpserver.annotations.Route;
import com.jstechnologies.flashhttpserver.http.HttpRequest;
import com.jstechnologies.flashhttpserver.http.HttpResponse;
import com.jstechnologies.flashhttpserver.httpserver.HttpRequestHandler;

import java.nio.charset.StandardCharsets;

@Route(Url = "/jerkin",Method = Method.GET)
public class MyGetRequestHandler implements HttpRequestHandler {
    @Override
    public HttpResponse onRequest(HttpRequest httpRequest) {
        System.out.println(httpRequest.getTarget());
        HttpResponse response=new HttpResponse();
        response.setStatusCode(200);
        response.setStatusMessage("OK");
        response.setBody("Thank you".getBytes(StandardCharsets.UTF_8));
        return response;
    }
}
