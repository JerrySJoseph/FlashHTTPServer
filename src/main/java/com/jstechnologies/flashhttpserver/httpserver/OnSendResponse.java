package com.jstechnologies.flashhttpserver.httpserver;

import com.jstechnologies.flashhttpserver.http.HttpResponse;

public interface OnSendResponse {
    void sendResponse(HttpResponse response);
}
