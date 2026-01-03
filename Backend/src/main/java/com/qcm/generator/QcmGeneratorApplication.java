package com.qcm.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

// Bloqué l'utilisation de posgreSql tmp
@SpringBootApplication(
        exclude = {
                DataSourceAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class
        }
)
public class QcmGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(QcmGeneratorApplication.class, args);
	}

}
