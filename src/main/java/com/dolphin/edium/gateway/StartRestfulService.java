package com.dolphin.edium.gateway;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class StartRestfulService {

    static Server jettyServer;

    public static void main(String[] args) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.GZIP);
        context.setContextPath("/");

        jettyServer = new Server(Integer.parseInt(System.getProperty("server.port")));
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/rest/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages",
                "com.dolphin.edium.gateway.restful");

        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            //jettyServer.destroy();
        }
    }

    protected static void stop() {
        if (jettyServer != null && jettyServer.isRunning()) {
            jettyServer.destroy();
        }
    }
}
