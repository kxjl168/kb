package com.kxjl.web.todo.service;

import java.util.List;

import org.json.JSONObject;

import com.kxjl.web.autodata.model.ToDo;


public interface ToDoService {

	int deleteByPrimaryKey(Integer id);

	int insert(ToDo record);

	int insertSelective(ToDo record);

	ToDo selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ToDo record);

	int updateByPrimaryKey(ToDo record);

	/**
	 * 条件查询
	 * 
	 * @param role
	 * @return
	 * @author zj
	 * @date 2018年5月9日
	 */
	List<ToDo> getTodoList(ToDo query);



}
