package com.cybertek.library.utilities.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Environment {

    //1- we created properties object
    private static Properties properties;

    static {
        //2- get the path and store in String, or directly pass into fileInputStream obj
        String path = "configuration.properties";

        try {
            //3- we need to open the file
            FileInputStream file = new FileInputStream(path);
            //4- we need to load the file to properties object
            properties = new Properties();
            properties.load(file);


            //5- Load environment properties
            if(System.getProperty("environment") != null) {
                path = "src/test/resources/environments/" + System.getProperty("environment") + ".properties";
            } else {
                path = "src/test/resources/environments/"+properties.getProperty("environment")+".properties";
            }

            file = new FileInputStream(path);
            properties.load(file);
            //6- close.file
            file.close();

        } catch (IOException e) {
            System.out.println("Properties file not found.");
            e.getCause();
        }
    }

    //Our own custom method to read and get values from configuration.properties file
    public static String getProperty(String keyWord) {
        return properties.getProperty(keyWord);
    }
}


