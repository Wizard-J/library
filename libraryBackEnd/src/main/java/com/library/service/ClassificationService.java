package com.library.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.dao.ClassificationDao;
import com.library.entity.Classification;

@Service
@Transactional(readOnly = false)
public class ClassificationService {
	@Autowired
	private ClassificationDao classificationDao;

	/**
	 * 新增分类
	 * 
	 * @param clf
	 * @return
	 */
	public boolean createClassification(Classification clf) {
		return classificationDao.createClassification(clf) > 0;
	}

	/**
	 * 删除分类
	 * 
	 * @param clfId
	 * @return
	 */
	public int deleteClassification(String clfId) {
		return classificationDao.deleteClassification(clfId);
	}

	/**
	 * 更新分类
	 * 
	 * @param clf
	 * @return
	 */
	public boolean updateClassification(Classification clf) {
		Classification targetClf = getClassificationById(clf.getId());
		if (clf.getId() == null) {
			clf.setId(targetClf.getId());
		}
		if (clf.getClassName() == null) {
			clf.setClassName(targetClf.getClassName());
		}
		if (clf.getIndex()== null) {
			clf.setIndex(targetClf.getIndex());
		}
		return classificationDao.updateClassification(clf) > 0;
	}

	/**
	 * id查找分类
	 * 
	 * @param id
	 * @return
	 */
	public Classification getClassificationById(String id) {
		return classificationDao.getClassificationById(id);
	}

	/**
	 * 查找全部图书列表
	 * 
	 * @return
	 */
	public ArrayList<Classification> getClassificationList() {
		return classificationDao.getClassificationList();
	}

}
