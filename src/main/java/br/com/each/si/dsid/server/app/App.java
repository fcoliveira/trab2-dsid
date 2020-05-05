package br.com.each.si.dsid.server.app;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import br.com.each.si.dsid.server.conf.Config;
import br.com.each.si.dsid.server.controller.ServerController;
import br.com.each.si.dsid.server.http.handler.JsonPlaceholderHttpHandler;
import br.com.each.si.dsid.server.http.handler.OpenSkyHttpHandler;
import br.com.each.si.dsid.server.rest.resources.JsonPlaceholderResources;
import br.com.each.si.dsid.server.rest.resources.OpenSkyResources;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class App extends Application<Config> {
	
	public static void main(final String[] args) throws Exception {
		final String dir = System.getProperty("user.dir");
		
		String[] serverConfig = new String[2];
		serverConfig[0] = "server";
		serverConfig[1] = dir + "/conf.yml";
		
		new App().run(serverConfig);
	}
	
	@Override
	public String getName() {
		return "Atividade 2 - Parte 1 - DSID";
	}

	@Override
	public void initialize(final Bootstrap<Config> bootstrap) {
		
		bootstrap.addBundle(new SwaggerBundle<Config>() {
			@Override
			protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(Config configuration) {
				return configuration.swaggerBundleConfiguration;
			}
		});
		
	}

	@Override
	public void run(Config configuration, Environment environment) throws Exception {

		final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
		cors.setInitParameter("allowedOrigins", "*"); 
		cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
		cors.setInitParameter("allowedMethods", "GET,PUT,POST,DELETE");
		cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
		
		ServerController controller = new ServerController(new JsonPlaceholderHttpHandler(), new OpenSkyHttpHandler());
        final JsonPlaceholderResources tpResources = new JsonPlaceholderResources(controller);
        environment.jersey().register(tpResources);
		
        final OpenSkyResources openSkyResources = new OpenSkyResources(controller);
        environment.jersey().register(openSkyResources);
        
	}
}
