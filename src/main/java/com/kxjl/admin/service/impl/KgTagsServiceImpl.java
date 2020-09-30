/**
 * Copyright (c) 2019 kxjl All Rights Reserved.
 * 
 * This software license agreement (the "Agreement") is a legal agreement between the user 
 * ("You" or the "User") and kxjl ("kxjl") 
 * for the software products (the "Software") and related services (the "Service") that 
 * accompanies this Agreement, as may be updated or replaced by feature enhancements, 
 * updates or maintenance releases and any services that may be provided by kxjl under this Agreement. 
 * You are not allowed to download, install or use the Software or to use Services unless 
 * you accept all the terms and conditions of this Agreement. Your downloading, 
 * installation and use of the Software shall be regarded as your acceptance of the Agreement 
 * and your agreement to be bound by all the terms and conditions of this Agreement.
 * 
 * The above notice shall be included in all copies or substantial portions of the Software.
 * 
 * The software is provided "as is", without warranty of any kind, express or implied, 
 * including but not limited to the warranties of merchantability, fitness for a particular 
 * purpose and noninfringement. In no event shall the authors or copyright holders be 
 * liable for any claim, damages or other liability, whether in an action of contract, 
 * tort or otherwise, arising from, out of or in connection with the software or the use 
 * or other dealings in the software.
 */
package com.kxjl.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kxjl.admin.common.Pagination;
import com.kxjl.base.util.UUIDUtil;
import com.kxjl.admin.util.Constants;
import com.kxjl.admin.util.Page;
import com.kxjl.admin.util.PageUtil;
import com.kxjl.admin.persistence.adapter.dao.KgTagsMapperAdapter;
import com.kxjl.admin.persistence.entity.KgClass;
import com.kxjl.admin.persistence.entity.KgSubGroup;
import com.kxjl.admin.persistence.entity.KgTags;
import com.kxjl.admin.persistence.entity.KgTagsExample;
import com.kxjl.admin.persistence.entity.KgTagsKey;
import com.kxjl.admin.service.KgTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 具体开发人员请在此补充上类描述说明
 * 
 * @date 2020年06月16日 16:31:39
 * @author 具体开发人员请在此补充上本人名称拼音
 */
@Service
public class KgTagsServiceImpl implements KgTagsService {

	@Autowired
	KgTagsMapperAdapter kgTagsMapper;

	/**
	 * <p>
	 * New Info
	 * </p>
	 * 
	 * @param kgTags
	 * @return
	 */
	@Transactional
	public Boolean add(KgTags kgTags) {

		if (kgTags.getId() == null || kgTags.getId().equals(""))
			kgTags.setId(UUIDUtil.get32UUID());

		if (kgTags.getVersionId() == null || kgTags.getVersionId().equals(""))
			kgTags.setVersionId("1.0.0");

		return kgTagsMapper.insertSelective(kgTags) > 0;
	}

	/**
	 * <p>
	 * Modify Info
	 * </p>
	 * 
	 * @param kgTags
	 * @return
	 */
	@Transactional
	public Boolean modify(KgTags kgTags) {
		return kgTagsMapper.updateByPrimaryKeySelective(kgTags) > 0;
	}

	/**
	 * <p>
	 * Delete
	 * </p>
	 * 
	 * @return
	 */
	@Transactional
	public Boolean delete(String id, String versionId) {
		KgTagsKey key = new KgTagsKey();
		// 设置key值
		key.setId(id);
		key.setVersionId(versionId);
		return kgTagsMapper.deleteByPrimaryKey(key) > 0;
	}

	/**
	 * <p>
	 * query info by id
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public KgTags getOne(String id, String versionId) {
		KgTagsKey key = new KgTagsKey();
		// 设置key值
		key.setId(id);
		key.setVersionId(versionId);
		return kgTagsMapper.selectByPrimaryKey(key);
	}

	/**
	 * <p>
	 * query all info
	 * </p>
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<KgTags> getAll() {
		KgTagsExample example = new KgTagsExample();
		return kgTagsMapper.selectByExample(example);
	}

	/**
	 * 检查返回的tag中，是否有新增数据，直接新增tag
	 * 
	 * @param data
	 * @return
	 * @author:kxjl
	 * @date 2020年6月23日
	 */
	public Boolean SaveList(String data) {
		Boolean rst = false;
		if (data != null && !data.equals("")) {

			JSONObject jo=JSON.parseObject(data);
			JSONArray jarray = jo.getJSONArray("data");
			
			//JSONArray jarray = JSON.parseArray(data);
			for (int i = 0; i < jarray.size(); i++) {

				JSONObject jobj = jarray.getJSONObject(i);

				KgTags tag = new KgTags();
				tag.setName(jobj.getString("label"));

				KgTags tp = kgTagsMapper.selectByName(tag);
				if (tp == null) {
					add(tag);
				}

			}

		}

		return rst;
	}
	
	public Boolean addSameNameTag(KgClass kgClass) {
		KgTags tag = new KgTags();
		tag.setName(kgClass.getClsName());
		tag.setSourceClsId(kgClass.getId());
		
		KgTags tp = kgTagsMapper.selectByName(tag);
		if (tp == null) {
			add(tag);
		}
		return true;
	}

	/**
	 * 
	 * @param example
	 * @return
	 * @author:kxjl
	 * @date 2020年6月9日
	 */
	public Page<KgTags> selectList(KgTags query, Pagination pageCondition) {
		com.github.pagehelper.Page pageinfo = PageUtil.getPage(pageCondition);

		List<KgTags> lst = kgTagsMapper.selectList(query);

		Page<KgTags> page = new Page<KgTags>();
		page.setResults(lst);
		page.setPageNo(pageinfo.getPageNum());
		page.setPageSize(pageinfo.getPageSize());
		page.setTotalRecord((int) pageinfo.getTotal());

		return page;
	}
}