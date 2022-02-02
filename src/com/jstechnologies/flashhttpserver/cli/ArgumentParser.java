package com.jstechnologies.flashhttpserver.cli;

import com.jstechnologies.flashhttpserver.ApplicationManager;


public class ArgumentParser {

    public static void parse(String[] args){
        if(args==null || args.length==0)
            return;

        for(int i=0;i<args.length;i++){
            switch (args[i]){
                case "-debug":ApplicationManager.getInstance().setExecutionMode(ApplicationManager.DEBUG);break;
                case "-production":ApplicationManager.getInstance().setExecutionMode(ApplicationManager.PRODUCTION);break;
                default:break;
            }
        }

    }

}
