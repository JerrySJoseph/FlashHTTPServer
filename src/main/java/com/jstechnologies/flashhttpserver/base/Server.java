package com.jstechnologies.flashhttpserver.base;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Server extends Thread {

    private int PORT=0;
    private ServerSocket mServerSocket;
    boolean isRunning=false;

    //Run on custom port
    public Server(int port) throws IOException {
       this.PORT=port;
    }
    public void onStart(int port){};
    public void onStop(){};

    //Run on free port (automatic)
    public Server() throws IOException {
    }

    protected abstract void onNewConnection(Socket socket);

    @Override
    public void interrupt() {
        closeServer();
    }

    private void closeServer(){
        try {
            mServerSocket.close();
            isRunning=false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        onStop();
    }

    public boolean isRunning(){
        return this.isRunning;
    }

    @Override
    public void run() {
        try {
            this.mServerSocket=new ServerSocket(this.PORT);
            onStart(mServerSocket.getLocalPort());

            //Accept connection till port is bound and is not closed
            while(mServerSocket.isBound() && !mServerSocket.isClosed()){
                isRunning=true;
                Socket requestSocket = null;
                requestSocket=mServerSocket.accept();
                onNewConnection(requestSocket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
