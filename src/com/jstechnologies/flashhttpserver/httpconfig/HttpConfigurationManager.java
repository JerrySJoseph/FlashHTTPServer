package com.jstechnologies.flashhttpserver.httpconfig;

/**
 * Configuration Manager
 * Singleton for managing current server Configurations
 *
 * */
public class HttpConfigurationManager {

    private HttpConfiguration mHttpConfiguration;
    private static HttpConfigurationManager mInstance;

    //Get a singleton instance of Configuration Manager
    public static HttpConfigurationManager getInstance() throws HttpConfigurationException {
        if(mInstance==null)
            throw new HttpConfigurationException("Configuration Manager has not been initialized. Please make sure you initialize with a configuration file or object");
        return  mInstance;
    }

    public static boolean init(HttpConfiguration httpConfiguration)throws HttpConfigurationException{
        if(validateConfiguration(httpConfiguration)) {
            mInstance = new HttpConfigurationManager(httpConfiguration);
            return true;
        }
        return  false;
    }

    public static boolean init(String configurationFilePath)throws HttpConfigurationException {
        HttpConfiguration httpConfiguration =fetchConfigurationFromFile(configurationFilePath);
        return init(httpConfiguration);
    }

    private HttpConfigurationManager(HttpConfiguration httpConfiguration){
        this.mHttpConfiguration = httpConfiguration;
    }

    private static HttpConfiguration fetchConfigurationFromFile(String filePath)throws HttpConfigurationException {
        try{
            //TODO: Load file and parse
        }catch (Exception e){
            throw new HttpConfigurationException(e.getMessage());
        }
        return null;
    }

    private static boolean validateConfiguration(HttpConfiguration httpConfiguration) throws HttpConfigurationException{
        if(httpConfiguration ==null)
            throw new HttpConfigurationException("No valid configuration detected.");
        return true;
    }

    public HttpConfiguration getHttpConfiguration(){
        return this.mHttpConfiguration;
    }
}
