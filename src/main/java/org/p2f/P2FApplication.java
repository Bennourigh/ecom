package org.p2f;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class P2FApplication {

    public static void main(String[] args) {
        SpringApplication.run(P2FApplication.class, args);
    }
    @Bean
    InitializingBean initializingBean(ObjectMapper objectMapper){
        return ()-> objectMapper.activateDefaultTyping((
                objectMapper.getPolymorphicTypeValidator()),
                ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT
                );

    }

}
