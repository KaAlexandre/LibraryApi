package io.github.cursodsousa.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value(("${spring.datasource.url}"))
    String url;
    @Value(("${spring.datasource.username}"))
    String username;
    @Value(("${spring.datasource.password}"))
    String password;
    @Value(("${spring.datasource.driver-class-name}"))
    String driver;
    /*@Bean
    public DataSource datasource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        return ds;
    }*/

    @Bean
    public DataSource hikariDataSource(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);

        config.setMaximumPoolSize(10); // maximo de conexoes liberadas
        config.setMinimumIdle(1); // tamanho inicial do pool
        config.setPoolName("Library-db-pool"); // nome do pool
        config.setMaxLifetime(600000); // tempo de vida da conexao 600 mil ms = 10 min
        config.setConnectionTimeout(100000); // tempo de espera para conexao 100 mil ms = 1 min e 40 seg
        config.setConnectionTestQuery("SELECT 1"); // query para testar o banco de dados
        return new HikariDataSource(config);
    }
}
