package com.jstechnologies.flashhttpserver;

public class ApplicationManager {

    private static ApplicationManager mInstance;

    //If server is running
    private static boolean HttpServerActive=false;

    //Execution Modes
    public static final byte DEBUG = 1;
    public static final byte PRODUCTION = 2;

    //By default set into Debug mode
    private byte executionMode=DEBUG;

    public static ApplicationManager getInstance(){
        if(mInstance==null)
            mInstance=new ApplicationManager();
        return  mInstance;
    }

    public void setExecutionMode(final byte executionMode){
        this.executionMode=executionMode;
    }

    public byte getExecutionMode(){
        return this.executionMode;
    }

}
