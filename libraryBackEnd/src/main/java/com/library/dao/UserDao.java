package com.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.library.entity.User;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final static String DELETE_USER_SQL = "DELETE FROM user where id = ? ";
	private final static String GET_USER_SQL = "SELECT * FROM user where id = ? ";
	private final static String UPDATE_USER_SQL = "UPDATE user set account = ? ,passwd = ? ,name = ? ,sex = ? ,birthday = ? ,address = ? ,number = ? where id = ? ";
	private final static String ALL_USER_SQL = "SELECT * FROM user";
	private final static String ADD_USER_SQL = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?)";

	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */
	public int createUser(User user) {
		String id = UUID.randomUUID().toString().replaceAll("-", "");

		String account = user.getAccount();
		String passwd = user.getPasswd();
		String name = user.getName();
		String sex = user.getSex();
		Date birthday = user.getBirthday();
		String address = user.getAddress();
		String number = user.getNumber();

		return jdbcTemplate.update(ADD_USER_SQL,
				new Object[] { id, account, passwd, name, sex, birthday, address, number });
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	public int deleteUser(String id) {
		return jdbcTemplate.update(DELETE_USER_SQL, id);
	}

	/**
	 * 更新用户
	 * 
	 * @param reader
	 * @return
	 */
	public int updateUser(User user) {
		String id = user.getId();

		String account = user.getAccount();
		String passwd = user.getPasswd();
		String name = user.getName();
		String sex = user.getSex();
		Date birthday = user.getBirthday();
		String address = user.getAddress();
		String number = user.getNumber();

		return jdbcTemplate.update(UPDATE_USER_SQL,
				new Object[] { account, passwd, name, sex, birthday, address, number, id });
	}

	/**
	 * 获取全部用户信息
	 * 
	 * @return
	 */
	public ArrayList<User> getAllUserInfo() {
		ArrayList<User> userList = new ArrayList<>();
		jdbcTemplate.query(ALL_USER_SQL, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					User user = new User();
					user.setId(resultSet.getString("id"));
					user.setAccount(resultSet.getString("account"));
					user.setPasswd(resultSet.getString("passwd"));
					user.setName(resultSet.getString("name"));
					user.setSex(resultSet.getString("sex"));
					user.setBirthday(resultSet.getDate("birthday"));
					user.setAddress(resultSet.getString("address"));
					user.setNumber(resultSet.getString("number"));
					user.setAdmin(!resultSet.getString("is_admin").equals(""));
					userList.add(user);
				}
			}
		});
		return userList;
	}

	/**
	 * 查找单个用户信息
	 * 
	 * @param readerId
	 * @return
	 */
	public User selectUserById(String readerId) {
		User user = new User();
		jdbcTemplate.query(GET_USER_SQL, new Object[] { readerId }, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					user.setId(resultSet.getString("id"));
					user.setAccount(resultSet.getString("account"));
					user.setPasswd(resultSet.getString("passwd"));
					user.setName(resultSet.getString("name"));
					user.setSex(resultSet.getString("sex"));
					user.setBirthday(resultSet.getDate("birthday"));
					user.setAddress(resultSet.getString("address"));
					user.setNumber(resultSet.getString("number"));
					user.setAdmin(!resultSet.getString("is_admin").equals(""));
				}
			}
		});
		return user;
	}

}
