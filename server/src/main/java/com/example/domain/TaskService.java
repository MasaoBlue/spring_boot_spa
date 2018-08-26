package com.example.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.example.application.config.AppConfig;
import com.example.infrastructure.TaskRepository;

@Component
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    
    public TaskService() {
        try(ConfigurableApplicationContext appCtx = new AnnotationConfigApplicationContext(AppConfig.class)) {
            taskRepository = appCtx.getBean(TaskRepository.class);
        }
    }
    
    public List<Map<String, Object>> search(String title) throws Exception {
        return taskRepository.search(title);
    }

    public int create(String title, String detail) throws Exception {
        // TODO factory定義
        Task task = new Task();
        task.setTitle(title);
        task.setDetail(detail);
        return taskRepository.create(task);
    }
    
    public int updateComplete(String id, boolean completed) throws Exception {
        return taskRepository.updateComplete(id, completed);
    }
}
