/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.jstechnologies.flashhttpserver.http;

public class HttpException extends RuntimeException{
    private int statuscode;
    private String statusMessage;
    private String statusbody;

    public HttpException(int statusCode,String message,String body) {
        super(message);
        this.statuscode=statusCode;
        this.statusbody=body;
        this.statusMessage=message;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getStatusbody() {
        return statusbody;
    }
}
