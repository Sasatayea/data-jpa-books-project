package com.global.book.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled" , matchIfMissing = true)
@EnableAsync
@EnableSchedulerLock(defaultLockAtMostFor = "10m")
public class SchedulerConfig {
	
	  @Bean
	  public LockProvider lockProvider(DataSource dataSource) {
	    return new JdbcTemplateLockProvider(
	        JdbcTemplateLockProvider.Configuration.builder()
	        .withJdbcTemplate(new JdbcTemplate(dataSource))
	        .usingDbTime() // Works on Postgres, MySQL, MariaDb, MS SQL, Oracle, DB2, HSQL and H2
	        .build()
	    );
	  }

}
