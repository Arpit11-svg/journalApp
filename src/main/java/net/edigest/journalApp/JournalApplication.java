package net.edigest.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.convert.MongoDatabaseFactoryReferenceLoader;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args) {SpringApplication.run(JournalApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager anything(MongoDatabaseFactory dbfactory){
		return new MongoTransactionManager(dbfactory);
	}
	// Above method is like this==> PlatformTransactionManager anything=new MongoTransactionManager(factory)

	@Bean
	public RestTemplate restTemplate(){
		return  new RestTemplate();
	}
//	this instance will inject in WeatherService.java

}
