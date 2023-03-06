package com.proyecto.retail.Infraestructura;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

@Component
public class ExecuteProcedure {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private SimpleJdbcCall simpleJdbcCall;
	
	
	public List<HashMap<String, Object>> executeProcedure(String sql, Map<String, Object> p_parameters) {
		try {
			
			Connection connect = jdbcTemplate.getDataSource().getConnection();
			
			PreparedStatement proc = connect.prepareStatement(sql);
			int index_param = 1;
			for(Map.Entry<String, Object> entry: p_parameters.entrySet()) {
				proc.setObject(index_param, entry.getValue());
				index_param++;
			}
			ResultSet rs = proc.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();
			List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
			while(rs.next()) {
				HashMap<String,Object> row = new HashMap<String, Object>(columns);
				for(int i=1; i<=columns; ++i) {
		            row.put(md.getColumnName(i),rs.getObject(i));
		        }
				list.add(row);
			}
			rs.close();
			connect.close();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	  }
}
