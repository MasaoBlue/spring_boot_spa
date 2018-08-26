package com.example.infrastructure;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.domain.User;

public class UserRepository {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    
    public List<Map<String, Object>> search(String name) throws Exception {
        String searchName = "%" + name.toLowerCase() + "%";
        String sql = "SELECT * FROM users WHERE LOWER(name) LIKE :name";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name", searchName);

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, param);
        System.out.println(String.format("user search[%s] => result count:%s", name, list.size()));
        return list;
    }

    public int create(User user) throws Exception {
        String sql = "INSERT INTO users(name, email) VALUES (:name, :email)";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name", user.getName());
        param.addValue("email", user.getEmail());

        int result = jdbcTemplate.update(sql, param);
        System.out.println(String.format("user create => result count:%s", result));
        return result;
    }
}
