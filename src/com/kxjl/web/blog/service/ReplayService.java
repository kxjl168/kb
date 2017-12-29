package com.kxjl.web.blog.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;




import com.kxjl.web.blog.model.Replay;


public interface ReplayService {
	


	/**
	 * 分页获取banner列表
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public List<Replay> getReplayPageList(Replay query);

	/**
	 * 获取总条数
	 * @param query
	 * @return
	 * @date 2016-8-4
	 */
	public int getReplayPageListCount(Replay query);
	/**
	 * 添加Replay
	 * @param Replay
	 * @return
	 */
	public int addReplay(Replay Replay);
	
	/**
	 * 删除Replay
	 * @param id
	 * @return
	 */
	public int deleteReplay(@Param(value="id")Integer id);

	
}
