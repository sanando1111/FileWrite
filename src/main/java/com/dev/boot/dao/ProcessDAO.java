package com.dev.boot.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import com.dev.boot.entity.ProcessFeds;

public class ProcessDAO {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProcessDAO.class);
	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String USERNAME = "dataadmin";
	public static final String PASSWORD = "dataadmin";

	public  List<ProcessFeds> getAllEmployees() {
		logger.info("Start getAllEmployees.");
		List<ProcessFeds> empList = new ArrayList<ProcessFeds>();
		// JDBC Code - Start
		String query = "select a_file,b_file from processfeds";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

		logger.info("Starting getting content");
		List<Map<String, Object>> empRows = jdbcTemplate.queryForList(query);

		for (Map<String, Object> empRow : empRows) {
			ProcessFeds f = new ProcessFeds();
			/// if(empRow.get("a_file").toString())

			if (empRow.get("a_file")!=null||empRow.get("a_file").toString()!="") {
				f.setA_file(empRow.get("a_file").toString());
			}
			if (empRow.get("b_file")!=null||empRow.get("b_file").toString()!="") {
				f.setB_file(empRow.get("b_file").toString());
			}
			
			empList.add(f);
		}

		return empList;
	}

	private static DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(ProcessDAO.DRIVER);
		dataSource.setUrl(ProcessDAO.JDBC_URL);
		dataSource.setUsername(ProcessDAO.USERNAME);
		dataSource.setPassword(ProcessDAO.PASSWORD);
	
		return dataSource;
	}

}
