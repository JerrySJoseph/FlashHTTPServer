package com.jstechnologies.flashhttpserver.http;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpParserTest {

    HttpParser parser;
    @BeforeAll
    void setup(){

        parser=new HttpParser();
    }

    @Test
    void parseGETRequestWithCRLF() throws Exception {

        HttpRequest request;
        request=parser.parse(generateGetRequestCRLF());
        assertEquals(request.getMethod(),"GET");
        assertEquals(request.getMethod(),"GET");
        assertEquals(request.getTarget(),"/");
        assertEquals(request.getVersion(),"HTTP/1.1");
        
    }
    @Test
    void parseGETRequestWithLF() throws Exception {

        HttpRequest request;
        request=parser.parse(generateGetRequestLF());
        assertEquals(request.getMethod(),"GET");
        assertEquals(request.getTarget(),"/");
        assertEquals(request.getVersion(),"HTTP/1.1");

    }
    @Test
    void parsePOSTRequestWithLF() throws Exception {

        HttpRequest request;
        request=parser.parse(generatePostRequestLF());
        assertEquals(request.getMethod(),"POST");
        assertEquals(request.getTarget(),"");
        assertEquals(request.getVersion(),"HTTP/1.1");

    }

    @Test
    void parsePOSTRequestWithNoHeader()throws Exception{
        HttpRequest request;
        request=parser.parse(generatePostWithNoHeader());
        assertEquals(request.getMethod(),"POST");
        assertEquals(request.getTarget(),"/");
        assertEquals(request.getVersion(),"HTTP/1.1");
        assertEquals(request.getHeaders().size(),0);
    }
    @Test
    void parsePOSTRequestWithNoTarget()throws Exception{
        HttpRequest request;
        request=parser.parse(generatePostWithNoTarget());
        assertEquals(request.getMethod(),"POST");
        assertEquals(request.getTarget(),"");
        assertEquals(request.getVersion(),"HTTP/1.1");

    }
    @Test
    void parsePOSTRequestWithNoHeaderButBody()throws Exception{
        HttpRequest request;
        request=parser.parse(generatePostWithNoHeaderButBody());
        assertEquals(request.getMethod(),"POST");
        assertEquals(request.getTarget(),"/");
        assertEquals(request.getVersion(),"HTTP/1.1");
        assertEquals(request.getHeaders().size(),0);
        assertEquals(new String(request.getBody()),"{\n" +
                "\t\"name\":\"jerin\"\n" +
                "}");

    }


    //Input data
    InputStream generateGetRequestCRLF(){
        String request="GET / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "sec-ch-ua: \" Not;A Brand\";v=\"99\", \"Google Chrome\";v=\"97\", \"Chromium\";v=\"97\"\r\n" +
                "sec-ch-ua-mobile: ?0\r\n" +
                "sec-ch-ua-platform: \"Windows\"\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n" +
                "Purpose: prefetch\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br\r\n" +
                "Accept-Language: en-US,en;q=0.9\r\n" +
                "Cookie: _ga=GA1.1.1564361586.1631729435; _ga_3V32TNJKJY=GS1.1.1631810732.3.0.1631810732.0; _ga_Z5Q9WLXD2Q=GS1.1.1632233048.20.0.1632233048.0; _ga_F4DHQZBM01=GS1.1.1641018753.2.0.1641018753.0\r\n\r\n";
        return new ByteArrayInputStream(request.getBytes(StandardCharsets.US_ASCII));
    }
    InputStream generateGetRequestLF(){
        String request="GET / HTTP/1.1\n" +
                "Host: localhost:8080\n" +
                "Connection: keep-alive\n" +
                "sec-ch-ua: \" Not;A Brand\";v=\"99\", \"Google Chrome\";v=\"97\", \"Chromium\";v=\"97\"\n" +
                "sec-ch-ua-mobile: ?0\n" +
                "sec-ch-ua-platform: \"Windows\"\n" +
                "Upgrade-Insecure-Requests: 1\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\n" +
                "Purpose: prefetch\n" +
                "Sec-Fetch-Site: none\n" +
                "Sec-Fetch-Mode: navigate\n" +
                "Sec-Fetch-User: ?1\n" +
                "Sec-Fetch-Dest: document\n" +
                "Accept-Encoding: gzip, deflate, br\n" +
                "Accept-Language: en-US,en;q=0.9\n" +
                "Cookie: _ga=GA1.1.1564361586.1631729435; _ga_3V32TNJKJY=GS1.1.1631810732.3.0.1631810732.0; _ga_Z5Q9WLXD2Q=GS1.1.1632233048.20.0.1632233048.0; _ga_F4DHQZBM01=GS1.1.1641018753.2.0.1641018753.0\n\n";
        return new ByteArrayInputStream(request.getBytes(StandardCharsets.US_ASCII));
    }
    InputStream generatePostRequestLF(){
        String request="POST  HTTP/1.1\n" +
                "Host: localhost:8080\n" +
                "Content-Type: application/json\n" +
                "Cache-Control: no-cache\n" +
                "Postman-Token: 00e32bf5-edf9-d221-6d7f-c9edd2e1ca24\n" +
                "\n" +
                "{\n" +
                "\t\"name\":\"jerin\"\n" +
                "}";
        return new ByteArrayInputStream(request.getBytes(StandardCharsets.US_ASCII));
    }
    InputStream generatePostWithNoHeader(){
        String request="POST / HTTP/1.1\n";
        return new ByteArrayInputStream(request.getBytes(StandardCharsets.US_ASCII));
    }
    InputStream generatePostWithNoTarget(){
        String request="POST  HTTP/1.1\n" +
                "Host: localhost:8080\n" +
                "Content-Type: application/json\n" +
                "Cache-Control: no-cache\n" +
                "Postman-Token: 3f63ff4d-d889-8b00-f531-cb6b7e48665a\n" +
                "\n" +
                "{\n" +
                "\t\"name\":\"jerin\"\n" +
                "}";
        return new ByteArrayInputStream(request.getBytes(StandardCharsets.US_ASCII));
    }
    InputStream generatePostWithNoHeaderButBody(){
        String request="POST / HTTP/1.1\n"+
                "\n" +
                "{\n" +
                "\t\"name\":\"jerin\"\n" +
                "}";
        return new ByteArrayInputStream(request.getBytes(StandardCharsets.US_ASCII));
    }


}