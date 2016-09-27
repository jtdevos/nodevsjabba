package launch;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by JAMES on 9/24/2016.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        File webappDir = Paths.get("src/main/webapp/").toFile();
        Tomcat tomcat = new Tomcat();
        String port = System.getenv("PORT");

        Path basedir = Paths.get("target/tomcatbase");
        tomcat.setBaseDir(basedir.toFile().getAbsolutePath());

        if(port == null) {
            port = "8080";
        }

        tomcat.setPort(Integer.valueOf(port));
        Context ctx = tomcat.addWebapp("/", webappDir.getAbsolutePath());
        System.out.println("configuring app with basedir:" + webappDir.getAbsolutePath() );


        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();
    }


}
