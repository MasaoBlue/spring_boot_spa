package com.example;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@RestController
@RequestMapping("/api/user")
public class UserController{
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    
    public List<Map<String, Object>> execSearch(String name) throws Exception {
        String searchName= "%" + name.toLowerCase() + "%";
        String sql = "SELECT * FROM users WHERE LOWER(name) LIKE :name";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name", searchName);

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, param);
        System.out.println(String.format("search[%s] => result count:%s", name, list.size()));
        return list;
    }
    
    public int execCreate(UserForm userForm) throws Exception {
        String sql = "insert into users(name, email) values (:name, :email)";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name", userForm.getName());
        param.addValue("email", userForm.getEmail());

        int result = jdbcTemplate.update(sql, param);
        System.out.println(String.format("create => result count:%s", result));
        return result;
    }
    
    @GetMapping(value="/")
    public List<Map<String, Object>> index(@RequestParam String name) {
        List<Map<String, Object>> list = null;
        try {
            list = execSearch(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @PostMapping(value="/")
    public boolean create(@RequestBody UserForm userForm) {
        try {
            execCreate(userForm);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}