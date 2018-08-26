package com.example.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.application.config.AppConfig;
import com.example.infrastructure.UserRepository;

public class UserService {
    @Autowired
    UserRepository userRepository;
    
    public UserService() {
        try(ConfigurableApplicationContext appCtx = new AnnotationConfigApplicationContext(AppConfig.class)) {
            userRepository = appCtx.getBean(UserRepository.class);
        }
    }
    
    public List<Map<String, Object>> search(String title) throws Exception {
        return userRepository.search(title);
    }

    public int create(String name, String email) throws Exception {
        // TODO factory定義
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return userRepository.create(user);
    }
}
