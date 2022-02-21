package org.ty.strongerme;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class StrongermeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StrongermeeApplication.class, args);
	}
	
	@Bean
	public Docket swaggerConfigure() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.ty.strongerme"))
				.build();
				/*.apiInfo(apiInformation());*/
	}
	/*
	 * private ApiInfo apiInformation() { return new ApiInfo("Strongerme",
	 * "three apis", "1.0", "free to use this", new
	 * springfox.documentation.service.Contact("shahid", "http://google.in",
	 * "aman.ak@"), "API linec", "http://URLaddress.com"); }
	 */
	
	//Push Notification firebase intialization and configuration
		@Bean("messageinsta")
		FirebaseMessaging firebaseMessaging() throws IOException {
			
		    GoogleCredentials googleCredentials = GoogleCredentials
		            .fromStream(new ClassPathResource("strongerme-20bc3-firebase-adminsdk-3fxmh-ce7c840d42.json").getInputStream());
		    FirebaseOptions firebaseOptions = FirebaseOptions
		            .builder()
		            .setCredentials(googleCredentials)
		            .build();
		    FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "StrongerMe");
		    return FirebaseMessaging.getInstance(app);
		}
}
