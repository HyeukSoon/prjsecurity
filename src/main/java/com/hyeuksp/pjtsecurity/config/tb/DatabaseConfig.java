package com.hyeuksp.pjtsecurity.config.tb;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource(HikariConfig hikariConfig) {
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }

}

/**
 * 4-2) DatabaseConfig 구성요소 알아보기
 *
 * @Configuration
 * 이 어노테이션이 선언된 클래스를 Java 기반의 설정 파일로 지정합니다. 설정 파일은 Spring IoC 컨테이너에 의해 관리되며, 빈(Bean) 구성에 사용됩니다.
 *
 * @Bean
 * 이 어노테이션이 선언된 메서드를 Spring IoC 컨테이너에 의해 관리되는 빈(Bean)으로 등록합니다.
 *
 * @ConfigurationProperties
 * applicant.yml에서 "spring.datasource.hikari" 접두사를 가진 설정 정보를 읽어오기 위한 어노테이션으로,
 * 읽어온 정보를 hikariConfig( ) 메서드에 매핑(바인딩)합니다.
 * hikariConfig( )
 * @ConfigurationProperties를 이용하여 읽어온 정보를 통해 HikariConfig 객체를 생성합니다.
 * dataSource( )
 * HikariConfig 객체를 통해 HikariDataSource를 생성합니다. 이 데이터 소스는 HikariCP를 사용하여 데이터베이스 연결을 관리하는 데 사용됩니다.
 * HikariCP는 커넥션 풀(Connection Pool) 라이브러리의 한 종류입니다.
 */