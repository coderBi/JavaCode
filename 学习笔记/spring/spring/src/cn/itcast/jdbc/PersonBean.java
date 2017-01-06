/**
 *
 */
package cn.itcast.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @author Edwin
 * @since 2016-7-19
 */
@Service
public class PersonBean {
	private Integer id;
	private String name;
	JdbcTemplate jdbcTemplate;

	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		// set方法注入的同时进行jdbc模板的初始化
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void add(String name) {
		String sql = "insert into t_person(name) value(?)";
		jdbcTemplate.update(sql, new Object[] { name },
				new int[] { java.sql.Types.VARCHAR });
	}

	public PersonBean getById(Integer id) {
		String sql = "select id,name from t_person where id=?";
		List<?> rs = jdbcTemplate.query(sql, new Object[] { id },
				new int[] { java.sql.Types.VARCHAR }, new RowMapper() {
					public Object mapRow(ResultSet rs, int index)
							throws SQLException {
						// if (!rs.next())
						// return null; //这个函数调用一次之前已经有next
						PersonBean person = new PersonBean();
						person.setId(((Number) rs.getObject("id")).intValue());
						person.setName((String) rs.getObject("name"));
						return person;
					}
				});
		if (rs.isEmpty())
			return null;
		return (PersonBean) rs.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<PersonBean> getAll() {
		String sql = "select id,name from t_person";
		return (List<PersonBean>) jdbcTemplate.query(sql,
				new ResultSetExtractor() {
					public Object extractData(ResultSet res)
							throws SQLException, DataAccessException {
						List<PersonBean> persons = new ArrayList<PersonBean>();
						while (res.next()) {
							PersonBean person = new PersonBean();
							person.setId(((Number) res.getObject("id"))
									.intValue());
							person.setName((String) res.getObject("name"));
							persons.add(person);
						}
						return persons;
					}
				});
	}

	public void update(PersonBean person) {
		String sql = "update t_person set name=? where id=?";
		jdbcTemplate.update(sql,
				new Object[] { person.getName(), person.getId() }, new int[] {
						java.sql.Types.VARCHAR, java.sql.Types.INTEGER });
	}

	public void delete(PersonBean person) {
		String sql = "delete from t_person where id=?";
		jdbcTemplate.update(sql, new Object[] { person.id });
	}

	/**
	 * 这个方法存在一个问题，如果没有查找到一条记录会报出异常
	 * 
	 * @param id
	 * @return
	 */
	public PersonBean getById1(Integer id) {
		String sql = "select id,name from t_person where id=?";
		return (PersonBean) jdbcTemplate.queryForObject(sql,
				new Object[] { id }, new int[] { java.sql.Types.VARCHAR },
				new RowMapper() {
					public Object mapRow(ResultSet rs, int index)
							throws SQLException {
						// if (!rs.next())
						// return null; //这个函数调用一次之前已经有next
						PersonBean person = new PersonBean();
						person.setId(((Number) rs.getObject("id")).intValue());
						person.setName((String) rs.getObject("name"));
						return person;
					}
				});
	}
}
