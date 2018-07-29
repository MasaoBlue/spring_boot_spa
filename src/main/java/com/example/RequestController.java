package com.example;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Controller;

@Controller
public class RequestController {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    
    public void run(String... strings) throws Exception {

        String sql = "select id from customer";
        SqlParameterSource param = new MapSqlParameterSource();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, param);
        System.out.println("result =" + list);
    }
    
    @RequestMapping(value="/")
    private String hello(){
        try {
            run("a", "b");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "index";
    }
}