package com.example.application.task;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.TaskService;

import org.springframework.stereotype.Component;

@Component
@RestController
@RequestMapping(value = "/api/task")
public class TaskController {
    
    TaskService taskService = new TaskService();
    
    @GetMapping
    public List<Map<String, Object>> index(@RequestParam(defaultValue = "") String title) {
        List<Map<String, Object>> list = null;
        try {
            list = taskService.search(title);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @PostMapping
    public boolean create(@RequestBody TaskCreateForm form) {
        try {
            taskService.create(form.getTitle(), form.getDetail());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    @PutMapping("{id}")
    public boolean updateComplete(@PathVariable String id, @RequestBody TaskUpdateForm form) {
        try {
            taskService.updateComplete(id, form.isCompleted());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}