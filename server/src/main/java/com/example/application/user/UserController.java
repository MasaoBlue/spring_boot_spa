package com.example.application.user;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.task.TaskCreateForm;
import com.example.domain.TaskService;
import com.example.domain.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@RestController
@RequestMapping("/api/user")
public class UserController{
    UserService userService = new UserService();
    
    @GetMapping
    public List<Map<String, Object>> index(@RequestParam(defaultValue = "") String name) {
        List<Map<String, Object>> list = null;
        try {
            list = userService.search(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @PostMapping
    public boolean create(@RequestBody UserCreateForm form) {
        try {
            userService.create(form.getName(), form.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}