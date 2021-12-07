package com.Utilities;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;


public class ConfigurationReader {
    public static void main(String[] args) throws IOException {
        ConfigurationReader.printConfiguration();
        System.out.println(ConfigurationReader.isKey_Check("driver_password"));
        ConfigurationReader.insert("driver_password","User");
        ConfigurationReader.insert("browser","chrome");
    }

    private static Properties properties;

    static {

        try {
            String path = "configuration.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);

            input.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static String get(String keyName) {

        return properties.getProperty(keyName);
    }
    public static void printConfiguration(){
        Set<String> keys=properties.stringPropertyNames();
        for (String key : keys) {
            System.out.println(key+" :"+properties.get(key));
        }
    }
    public static boolean isKey_Check(String key){
        Set<String> keys=properties.stringPropertyNames();

        return keys.contains(key);
    }
    public static void insert(String key,String value) throws IOException {
        FileWriter fileWriter=new FileWriter("configuration.properties",true);
        Properties p=new Properties();
        if(!ConfigurationReader.isKey_Check(key)) {
            p.setProperty(key, value);
        }else if(ConfigurationReader.isKey_Check(key)) {
            System.out.println(key + " is in the configuration.properties already");
        }
        p.store(fileWriter,null);
    }
    public static void insert(String fileName,String key,String value) throws IOException {
        FileWriter fileWriter=new FileWriter(fileName,true);
        Properties p=new Properties();
        if(!ConfigurationReader.isKey_Check(key)) {
            p.setProperty(key, value);
        }else if(ConfigurationReader.isKey_Check(key)) {
            System.out.println(key + " is in the configuration.properties already");
        }        p.store(fileWriter,null);
    }

}