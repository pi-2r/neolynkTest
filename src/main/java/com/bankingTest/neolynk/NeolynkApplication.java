package com.bankingTest.neolynk;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class NeolynkApplication {

    @Value("${my.swaggerTitle}")
    private String swaggerTitle;
    @Value("${my.swaggerResume}")
    private String swaggerResume;
    @Value("${my.swaggerVersion}")
    private String swaggerVersion;
    @Value("${my.swaggerTOS}")
    private String swaggerTOS;
    @Value("${my.swaggerContactName}")
    private String swaggerContactName;
    @Value("${my.swaggerContacUrl}")
    private String swaggerContacUrl;
    @Value("${my.swaggerContacEmail}")
    private String swaggerContacEmail;
    @Value("${my.swaggerLicence}")
    private String swaggerLicence;
    @Value("${my.swaggerLicenceUrl}")
    private String swaggerLicenceUrl;


	public static void main(String[] args) {
		SpringApplication.run(NeolynkApplication.class, args);
	}


	@Bean
	public Docket swaggerSettings() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaData())
				.pathMapping("");
	}

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo(
                swaggerTitle,
                swaggerResume,
                swaggerVersion,
                swaggerTOS,
				new Contact(swaggerContactName, swaggerContacUrl, swaggerContacEmail),
                swaggerLicence,
                swaggerLicenceUrl);
		return apiInfo;
	}


}
