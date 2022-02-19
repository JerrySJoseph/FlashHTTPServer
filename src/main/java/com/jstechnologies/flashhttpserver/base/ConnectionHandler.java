package com.jstechnologies.flashhttpserver.base;

import java.io.IOException;
import java.net.Socket;

public abstract class ConnectionHandler extends Thread {
    protected Socket mSocket;

    protected ConnectionHandler(Socket connectionSocket){
        this.mSocket=connectionSocket;
    }

    /*
    * Handle Connection Protocol implementation with the socket
    * return True to dispose the socket
    * */
    public abstract boolean handleConnection(Socket mSocket);

    @Override
    public void run() {
        boolean handled=handleConnection(this.mSocket);
        if(handled)
            disposeSocket();
    }

    /**
     * Manually dispose the socket object*/
    protected void disposeSocket(){
        try {
            this.mSocket.close();
        } catch (Exception e) {}

    }

}
