# Library API

Esta é uma API de Biblioteca construída com Java e Spring Boot. O projeto está estruturado para seguir a progressão de um curso, com cada aula sendo implementada em uma branch separada. À medida que o curso avança, novos recursos e melhorias serão adicionados à API.

## Estrutura do Projeto

- **Java**: A linguagem de programação principal utilizada.
- **Spring Boot**: O framework utilizado para construir a API.
- **Maven**: A ferramenta de automação de build utilizada para gerenciar dependências e construir o projeto.

## Branches

Cada aula do curso é implementada em uma branch separada. A branch atual é `Aula-52`. Você pode alternar entre as branches para ver a implementação de diferentes aulas.

## Configuração

A configuração do banco de dados é gerenciada usando **HikariCP** para pooling de conexões. Os detalhes da configuração estão especificados no arquivo `application.yml`.

### `application.yml`

```yaml
spring:
  application:
    name: libraryapi
  datasource:
    url: jdbc:postgresql://localhost:5432/library
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
```

## Configuração do Banco de Dados

A classe `DatabaseConfiguration` configura a fonte de dados HikariCP:

```java
package io.github.cursodsousa.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    private String url;
    
    @Value("${spring.datasource.username}")
    private String username;
    
    @Value("${spring.datasource.password}")
    private String password;
    
    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Bean
    public DataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        config.setMaximumPoolSize(10); // Máximo de conexões liberadas
        config.setMinimumIdle(1); // Tamanho inicial do pool
        config.setPoolName("Library-db-pool"); // Nome do pool
        config.setMaxLifetime(600000); // Tempo de vida da conexão (10 min)
        config.setConnectionTimeout(100000); // Tempo de espera para conexão (1 min e 40 seg)
        config.setConnectionTestQuery("SELECT 1"); // Query para testar o banco de dados
        return new HikariDataSource(config);
    }
}
```

## Executando a Aplicação

Para executar a aplicação, utilize o seguinte comando:

```sh
mvn spring-boot:run
```

## Classe Principal

A classe principal para executar a aplicação Spring Boot é `LibraryapiApplication`:

```java
package io.github.cursodsousa.libraryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryapiApplication.class, args);
    }
}
```

## Atualizações Futuras

Esta API está em andamento e será atualizada conforme o curso progride. Cada nova aula introduzirá novos recursos e melhorias, que serão implementados em branches separadas.
