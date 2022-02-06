package com.jstechnologies.flashhttpserver.cli;

import com.jstechnologies.flashhttpserver.ApplicationManager;


public class ArgumentParser {

    public static void parse(String[] args){
        if(args==null || args.length==0)
            return;

        for (String arg : args) {
            switch (arg) {
                case "-debug" -> ApplicationManager.getInstance().setExecutionMode(ApplicationManager.DEBUG);
                case "-production" -> ApplicationManager.getInstance().setExecutionMode(ApplicationManager.PRODUCTION);
                default -> {
                }
            }
        }

    }

}
