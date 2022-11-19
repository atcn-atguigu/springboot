package com.atguigu.boot.javaReadPropertiesFileSample;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// Ref: https://mkyong.com/java/java-properties-file-examples/
public class Java2_LoadAPropertiesFile {

    public static void main(String[] args) {

        try (InputStream input = new FileInputStream("/Users/WenjieYang/work/jetbrain/intellij/atguigu/springboot/06_springboot_ConfigurationProperties/src/main/resources/db.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("db.url"));
            System.out.println(prop.getProperty("db.user"));
            System.out.println(prop.getProperty("db.password"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}