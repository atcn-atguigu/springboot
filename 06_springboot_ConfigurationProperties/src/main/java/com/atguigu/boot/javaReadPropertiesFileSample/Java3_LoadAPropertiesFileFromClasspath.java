package com.atguigu.boot.javaReadPropertiesFileSample;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// Ref: https://mkyong.com/java/java-properties-file-examples/
public class Java3_LoadAPropertiesFileFromClasspath {

    public static void main(String[] args) {

        try (InputStream input = Java3_LoadAPropertiesFileFromClasspath.class.getClassLoader().getResourceAsStream("db.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
            System.out.println(prop.getProperty("db.url"));
            System.out.println(prop.getProperty("db.user"));
            System.out.println(prop.getProperty("db.password"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}