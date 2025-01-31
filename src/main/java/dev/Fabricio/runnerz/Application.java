package dev.Fabricio.runnerz;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import dev.Fabricio.runnerz.user.UserHttpClient;


@SpringBootApplication
public class Application {

	private final static Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {SpringApplication.run(Application.class, args);}


	@Bean
	UserHttpClient userHttpClient(){
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
		HttpServiceProxyFactory factory =  HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		return factory.createClient(UserHttpClient.class);
	}


	@Bean
	CommandLineRunner runner(UserHttpClient client){
		return args -> {
			log.info("Listing all users");
			client.findAll().forEach(user -> log.info(user.toString()));
			log.info("Fetching user by id");
			log.info(client.findById(1).toString());
		};
	}



}
