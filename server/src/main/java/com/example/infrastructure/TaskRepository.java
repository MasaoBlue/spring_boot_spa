package com.example.infrastructure;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Task;

@Repository
public class TaskRepository {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    
    public List<Map<String, Object>> search(String title) throws Exception {
        String searchTitle = "%" + title.toLowerCase() + "%";
        String sql = "SELECT * FROM tasks WHERE LOWER(title) LIKE :title";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("title", searchTitle);

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, param);
        System.out.println(String.format("search[%s] => result count:%s", title, list.size()));
        return list;
    }

    public int create(Task task) throws Exception {
        String sql = "INSERT INTO tasks(title, detail) VALUES (:title, :detail)";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("title", task.getTitle());
        param.addValue("detail", task.getDetail());

        int result = jdbcTemplate.update(sql, param);
        System.out.println(String.format("create task => result count:%s", result));
        return result;
    }
}
