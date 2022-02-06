package com.jstechnologies.flashhttpserver.httpserver;

import com.jstechnologies.flashhttpserver.http.HttpRequest;
import com.jstechnologies.flashhttpserver.http.HttpResponse;

public interface HttpRequestHandler {
    HttpResponse onRequest(HttpRequest httpRequest);
}
