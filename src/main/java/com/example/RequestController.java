package com.example;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

@RestController
public class RequestController{
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    
    public List<Map<String, Object>> run(String... strings) throws Exception {
        String sql = "select * from customer";
        SqlParameterSource param = new MapSqlParameterSource();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, param);
        System.out.println("result =" + list);
        return list;
    }
    
    @RequestMapping(value="/")
    public List<Map<String, Object>> index() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("index.html");
        List<Map<String, Object>> list = null;
        try {
            list = run("a", "b");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
}