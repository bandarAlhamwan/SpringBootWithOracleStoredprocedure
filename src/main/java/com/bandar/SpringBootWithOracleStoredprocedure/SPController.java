package com.bandar.SpringBootWithOracleStoredprocedure;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SPController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private SimpleJdbcCall simpleJdbcCall;
	
	
	// this code only for reference  
	@GetMapping("SP")
	public Map<String, Object> callSP() {
		
		jdbcTemplate.setResultsMapCaseInsensitive(false);
		simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withSchemaName("SchemaName").withProcedureName("ProcedureName");
		
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("paramterNameINProcedure", "Value")
				.addValue("paramterNameINProcedure", 100);
		Map<String, Object> execute = simpleJdbcCall.execute(in);
		
		return execute;
	}
}
