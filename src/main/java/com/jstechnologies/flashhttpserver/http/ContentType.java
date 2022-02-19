/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.jstechnologies.flashhttpserver.http;

public enum ContentType{
    APPLICATION_JAVASCRIPT("application/javascript"),
    APPLICATION_ECMASCRIPT("application/ecmascript"),
    APPLICATION_X_JAVASCRIPT("application/x-javascript"),
    TEXT_PLAIN("text/plain"),
    TEXT_HTML("text/html"),
    TEXT_CSS("text/css"),
    TEXT_JAVASCRIPT("text/javascript"),
    TEXT_ECMASCRIPT("text/ecmascript"),
    TEXT_JAVASCRIPT_1_0("text/javascript1.0"),
    TEXT_JAVASCRIPT_1_1("text/javascript1.1"),
    TEXT_JAVASCRIPT_1_2("text/javascript1.2"),
    TEXT_JAVASCRIPT_1_3("text/javascript1.3"),
    TEXT_JAVASCRIPT_1_4("text/javascript1.4"),
    TEXT_JAVASCRIPT_1_5("text/javascript1.5"),
    TEXT_JSCRIPT("text/jscript"),
    TEXT_LIVESCRIPT("text/livescript"),
    TEXT_X_ECMASCRIPT("text/x-ecmascript"),
    TEXT_X_JAVASCRIPT("text/x-javascript"),
    IMAGE_APNG("image/apng"),
    IMAGE_AVIF("image/avif"),
    IMAGE_GIF("image/gif"),
    IMAGE_JPEG("image/jpeg"),
    IMAGE_PNG("image/png"),
    IMAGE_SVG_XML("image/svg+xml"),
    IMAGE_WEBP("image/webp"),
    FONT_COLLECTION("font/collection"),
    FONT_OTF("font/otf"),
    FONT_TTF("font/ttf"),
    FONT_WOFF("font/woff"),
    FONT_WOFF2("font/woff2"),
    FONT_SFNT("font/sfnt");

    //TODO: add other MIME Types

    private String type;
    ContentType(String type){
        this.type=type;
    }
    public String getType(){
        return this.type;
    }
}
