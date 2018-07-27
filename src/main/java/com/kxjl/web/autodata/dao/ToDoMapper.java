package com.kxjl.web.autodata.dao;

import java.util.List;

import com.kxjl.web.autodata.model.ToDo;
import com.kxjl.web.phoneaccount.model.PhoneAccount;

public interface ToDoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ToDo record);

    int insertSelective(ToDo record);

    ToDo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ToDo record);

    int updateByPrimaryKey(ToDo record);
    
	/**
	 * 分页获取banner列表
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<ToDo> getTodoList(ToDo query);

}