package com.kxjl.web.blog.service.Impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.kxjl.web.system.dao.SystemParamsDao;
import com.kxjl.web.blog.dao.ReplayDao;
import com.kxjl.web.blog.model.Replay;
import com.kxjl.web.blog.service.ReplayService;

@Service(value="ReplayService")
public class ReplayServiceImpl implements ReplayService{
	
	@Autowired
	ReplayDao blogDao;
	
	@Autowired
	SystemParamsDao sysDao;

	
	
	
	/**
	 * 分页获取banner列表
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	@Override
	public List<Replay> getReplayPageList(Replay query) {
		return blogDao.getReplayPageList(query);
	}
	
	/**
	 * 获取总条数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getReplayPageListCount(Replay query){
		return blogDao.getReplayPageListCount(query);
	}
	
	@Override
	public int addReplay(Replay Replay) {
		return blogDao.addReplay(Replay);
	}

	@Override
	public int deleteReplay(@Param("id") Integer id) {
		return blogDao.deleteReplay(id);
	}

	
	
}
