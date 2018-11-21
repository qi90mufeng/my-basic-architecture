package com.my.config;

import java.net.URL;

/**
 * @version $Id: FirstMainTest.java, v 0.1 2017-12-13 Exp $$
 */
public class FirstMainTest {

    public static void main(String[] args){
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }
    }

}
