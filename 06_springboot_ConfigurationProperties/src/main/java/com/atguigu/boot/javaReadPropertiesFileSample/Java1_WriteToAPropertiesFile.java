package com.atguigu.boot.javaReadPropertiesFileSample;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

// Ref: https://mkyong.com/java/java-properties-file-examples/
public class Java1_WriteToAPropertiesFile {

    public static void main(String[] args) {

        try (OutputStream output = new FileOutputStream("/Users/WenjieYang/work/jetbrain/intellij/atguigu/springboot/06_springboot_ConfigurationProperties/src/main/resources/db.properties")) {

            Properties prop = new Properties();

            // set the properties value
            prop.setProperty("db.url", "localhost");
            prop.setProperty("db.user", "mkyong");
            prop.setProperty("db.password", "password");

            // save properties to project root folder
            prop.store(output, "Hello Properties File");

            System.out.println(prop);

        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}