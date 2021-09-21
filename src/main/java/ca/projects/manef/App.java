package ca.projects.manef;


import ca.projects.manef.context.ServerContext;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class App implements Runnable
{
    private static final int PORT = 8282;

    public static void main( String[] args )
    {

        new App().run();
    }

    public void run() {
        String portFromEnv = System.getenv("PORT");
        int port = PORT;
        if (portFromEnv != null){
            port = Integer.valueOf(portFromEnv).intValue();
        }
        Server server = new Server(port);

        ServletContextHandler contextHandler = new ServletContextHandler(server, "/");
        ResourceConfig resourceConfig = new ResourceConfig().packages("ca.projects.manef");
        resourceConfig.register(MultiPartFeature.class);
        ServletContainer container = new ServletContainer(resourceConfig);
        ServletHolder servletHolder = new ServletHolder(container);

        contextHandler.addServlet(servletHolder, "/*");
        new ServerContext().applyServerContext();


        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server.isRunning()) {
                server.destroy();
            }
        }
    }
}
