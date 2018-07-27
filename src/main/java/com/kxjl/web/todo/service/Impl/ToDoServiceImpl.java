package com.kxjl.web.todo.service.Impl;


import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.kxjl.web.autodata.dao.ToDoMapper;
import com.kxjl.web.autodata.model.ToDo;
import com.kxjl.web.todo.service.ToDoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ToDoServiceImpl implements ToDoService {

	@Autowired
	private ToDoMapper todoMapper;


	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int deleteByPrimaryKey(Integer id) {
		return todoMapper.deleteByPrimaryKey(id);
	}

	public int insert(ToDo record) {
		return todoMapper.insert(record);
	}

	@Transactional
	public int insertSelective(ToDo record) {

		return todoMapper.insertSelective(record);

	}

	public ToDo selectByPrimaryKey(Integer id) {
		return todoMapper.selectByPrimaryKey(id);
	}

	@Transactional
	public int updateByPrimaryKeySelective(ToDo record) {
		return 	todoMapper.updateByPrimaryKeySelective(record);


	}

	public int updateByPrimaryKey(ToDo record) {
		return todoMapper.updateByPrimaryKey(record);
	}

	/**
	 * 条件查询
	 * 
	 * @param optionDef
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	public List<ToDo> getTodoList(ToDo optionDef) {
		return todoMapper.getTodoList(optionDef);
	}



}
