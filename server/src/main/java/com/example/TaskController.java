package com.example;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@RestController
@RequestMapping(value="/api/task")
public class TaskController{
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    
    public List<Map<String, Object>> execSearch(String title) throws Exception {
        String searchTitle= "%" + title.toLowerCase() + "%";
        String sql = "SELECT * FROM tasks WHERE LOWER(title) LIKE :title";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("title", searchTitle);

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, param);
        System.out.println(String.format("search[%s] => result count:%s", title, list.size()));
        return list;
    }
    
    public int execCreate(TaskForm taskForm) throws Exception {
        String sql = "insert into tasks(title, detail) values (:title, :detail)";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("title", taskForm.getTitle());
        param.addValue("detail", taskForm.getDetail());

        int result = jdbcTemplate.update(sql, param);
        System.out.println(String.format("create task => result count:%s", result));
        return result;
    }
    
    @GetMapping
    public List<Map<String, Object>> index(@RequestParam(defaultValue = "") String name) {
        List<Map<String, Object>> list = null;
        try {
            list = execSearch(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @PostMapping
    public boolean create(@RequestBody TaskForm taskForm) {
        try {
            execCreate(taskForm);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}