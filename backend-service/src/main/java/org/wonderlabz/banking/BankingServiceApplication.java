package org.wonderlabz.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableSwagger2
public class BankingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingServiceApplication.class, args);
	}

//	@Bean
//	public Docket productApi() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("org.wonderlabz.banking")).build();
//	}

}