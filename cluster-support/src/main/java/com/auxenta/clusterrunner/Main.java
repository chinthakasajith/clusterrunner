package com.auxenta.clusterrunner;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.util.Optional;

/**
 * Created by Sudeera Harshanath on 23/06/2017.
 */
public class Main {

    public static final Optional<String> PORT = Optional.ofNullable(System.getenv("PORT"));
    public static final Optional<String> HOSTNAME = Optional.ofNullable(System.getenv("HOSTNAME"));

    public static void main(String[] args) throws Exception {
        String contextPath = "/";
        String appBase = ".";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.valueOf(PORT.orElse("8080")));
        tomcat.setHostname(HOSTNAME.orElse("localhost"));
        tomcat.getHost().setAppBase(appBase);
        Context appContext = tomcat.addWebapp(contextPath, appBase);

        tomcat.getHost().setAutoDeploy(true);
        tomcat.getHost().setDeployOnStartup(true);

        tomcat.start();
        tomcat.getServer().await();
    }
}
