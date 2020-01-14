package com.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.library.entity.Classification;

@Repository
public class ClassificationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final static String ADD_CLASSIFICATION = "INSERT INTO class VALUES(?,?,?)";
	private final static String DELETE_CLASSIFICATION = "delete from class where id = ?  ";
	private final static String UPDATE_CLASSIFICATION = "update class set class_index= ? ,class_name= ? where book_id= ? ;";
	private final static String SELECT_ALL_CLASSIFICATION = "SELECT * FROM class ";
	private final static String SELECT_CLASSIFICATION_BY_ID = "SELECT * FROM class WHERE id = ?   ";

	/**
	 * 新增分类
	 * 
	 * @param book
	 * @return
	 */
	public int createClassification(Classification clf) {
		String id = UUID.randomUUID().toString().replaceAll("-", "");

		String index = clf.getIndex();
		String name = clf.getClassName();

		return jdbcTemplate.update(ADD_CLASSIFICATION, new Object[] { id,index, name });
	}

	/**
	 * 删除分类
	 * 
	 * @param clfId
	 * @return
	 */
	public int deleteClassification(String clfId) {
		return jdbcTemplate.update(DELETE_CLASSIFICATION, clfId);
	}

	/**
	 * 更新分类
	 * 
	 * @param clf
	 * @return
	 */
	public int updateClassification(Classification clf) {
		String index = clf.getIndex();
		String name = clf.getClassName();
		String id = clf.getId();
		return jdbcTemplate.update(UPDATE_CLASSIFICATION, new Object[] { index, name ,id});
	}

	/**
	 * id获取分类
	 * 
	 * @param id
	 * @return
	 */
	public Classification getClassificationById(String id) {
		Classification clf = new Classification();
		
		jdbcTemplate.query(SELECT_CLASSIFICATION_BY_ID, new Object[] { id }, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					clf.setId(resultSet.getString("id"));
					clf.setClassName(resultSet.getString("class_Name"));
					clf.setIndex(resultSet.getString("class_index"));
				}
			}
		});
		return clf;
	}

	

	/**
	 * 获取全部分类列表
	 * 
	 * @return
	 */
	public ArrayList<Classification> getClassificationList() {
		final ArrayList<Classification> clfs = new ArrayList<Classification>();

		jdbcTemplate.query(SELECT_ALL_CLASSIFICATION, new RowCallbackHandler() {
			public void processRow(ResultSet resultSet) throws SQLException {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					Classification clf = new Classification();
					clf.setId(resultSet.getString("id"));
					clf.setClassName(resultSet.getString("class_Name"));
					clf.setIndex(resultSet.getString("class_index"));
					clfs.add(clf);
				}
			}
		});
		return clfs;

	}

}
