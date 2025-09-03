package com.bibek.accounts;

import com.bibek.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts API",
				version = "1.0",
				description = "Accounts API",
				contact = @Contact(
						name = "Bibek Regmi",
						email = "bibek@gmail.com",
						url = "https://github.com/bibekregmi9"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://github.com/bibekregmi9"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Accounts API Documentation",
				url = "https://github.com/bibekregmi9"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}
}
