/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.jstechnologies.flashhttpserver.httpserver;

import com.jstechnologies.flashhttpserver.annotations.Method;
import com.jstechnologies.flashhttpserver.http.ContentType;
import com.jstechnologies.flashhttpserver.http.HttpException;
import com.jstechnologies.flashhttpserver.http.HttpRequest;
import com.jstechnologies.flashhttpserver.http.HttpResponse;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//Default Http Request Handler looks for files with target url
public class DefaultHttpRequestHandler implements HttpRequestHandler {

    private File rootDir;

    public DefaultHttpRequestHandler(File rootDir) throws Exception {
        this.rootDir=rootDir;
        if(!this.rootDir.isDirectory())
            throw new Exception("Root is not a Directory");
    }
    public DefaultHttpRequestHandler(String rootDir) throws Exception {
        this(new File(rootDir));
    }

    private String targetUrlToPath(String targetUrl)  {
        System.out.println("target Url is ->"+targetUrl);
        Path p= Path.of(this.rootDir.getAbsolutePath(),targetUrl);
        return p.toString();
    }


    private HttpResponse prepareResponse(String path){
        File file=new File(path);
        HttpResponse response=new HttpResponse();
        try {
            if(!file.exists()){
                response.setStatusCode(401);
                response.setStatusMessage("NOT Found");
                String message="Resource not found in url: "+path;
                response.setBody(message.getBytes(StandardCharsets.UTF_8));
                return response;
            }

            if(file.isDirectory())
                return prepareResponse(Path.of(path,"index.html").toString());
            else if(file.getName().endsWith(".html")){
                response.setContentType(ContentType.TEXT_HTML);
            }
            else if(file.getName().endsWith(".js")){
                response.setContentType(ContentType.TEXT_JAVASCRIPT);
            }
            else if(file.getName().endsWith(".css")){
                response.setContentType(ContentType.TEXT_CSS);
            }
            else if(file.getName().endsWith(".png")){
                response.setContentType(ContentType.IMAGE_PNG);
            }
            else if(file.getName().endsWith(".woff")){
                response.setContentType(ContentType.FONT_WOFF);
            }
            else if(file.getName().endsWith(".woff2")){
                response.setContentType(ContentType.FONT_WOFF2);
            }
            else if(file.getName().endsWith(".ttf")){
                response.setContentType(ContentType.FONT_TTF);
            }
            else if(file.getName().endsWith(".jpg") || file.getName().endsWith(".jpg")){
                response.setContentType(ContentType.IMAGE_JPEG);
            }
            else if(file.getName().endsWith(".svg")){
                response.setContentType(ContentType.IMAGE_JPEG);
            }
            response.setStatusCode(200);
            response.setStatusMessage("Success");
            var body=Files.readAllBytes(Path.of(path));
            response.setBody(body);
            return response;

        }catch (IOException e){
            response.setStatusCode(401);
            response.setStatusMessage("Failed to load Resource");
            response.setBody(e.getMessage().getBytes(StandardCharsets.UTF_8));
            return response;
        }

    }

    @Override
    public HttpResponse onRequest(HttpRequest httpRequest) {
        String absolutePath=targetUrlToPath(httpRequest.getTarget());
        return prepareResponse(absolutePath);

    }
}
