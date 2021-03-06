package com.jstechnologies.flashhttpserver.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpParser {


    public HttpRequest parse(InputStream inputStream) throws Exception{

        InputStreamReader inputStreamReader= new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        BufferedReader reader=new BufferedReader(inputStreamReader);
        HttpRequest request= new HttpRequest();

        //begin parsing request
        parseRequestLine(reader,request);
        parseHeaders(reader,request);

        //SKip parsing Body for GET Request
        if(!request.getMethod().equals("GET")){
            parseBody(reader,request);
        }

        return request;
    }

    private void parseBody(BufferedReader reader,HttpRequest request) throws IOException {
        int _byte;
        while((_byte=reader.read())>-1){
            request.addToBody((byte)_byte);
        }

    }

    private void parseHeaders(BufferedReader reader,HttpRequest request)  throws IOException {
        String line;
        while((line=reader.readLine())!=null){

            if(line.isEmpty())
                return;
            //split headers into key value pairs
            String[] pairs=line.split(":",2);
            if(pairs[0]!=null && pairs[1]!=null)
                request.addHeader(pairs[0],pairs[1]);

        }

    }

    private void parseRequestLine(BufferedReader reader,HttpRequest request) throws Exception {

        String line;
        while ((line = reader.readLine())!=null) {

            try{
                if(line==null || line.isEmpty())
                    return;
                String[] parsed=line.split(" ");
                if(parsed.length!=3)
                    throw new Exception("Invalid Request Line");
                request.setMethod(parsed[0]);
                var target=parsed[1].split("\\?");
                request.setTarget(target[0]);
                request.setQueryString(target.length>1?target[1]:"");
                request.setVersion(parsed[2]);
                request.setRequestLine(line);
                return;
            }catch (Exception e){
                throw new IOException("BAD request");
            }

        }

    }

    void print(Object obj){
        System.out.println(obj);
    }
}
