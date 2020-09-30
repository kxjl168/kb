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
package com.kxjl.admin.util;

import java.util.List;

import com.kxjl.admin.common.Pagination;


/**
 * 
 * 分页对象
 * 
 * @date 2019年7月24日 下午5:25:12
 * @author havery
 */
public class Page<T> {

	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 页码
	 */
	private int pageNo = 1;
	/**
	 * 页大小
	 */
	private int pageSize = 10;
	/**
	 * 总记录数
	 */
	private long totalRecord;
	/**
	 * 结果集
	 */
	private List<T> results;

	/**
	 * 查询条件
	 */
	private Object condition;

	public Pagination getPageInfo() {
		Pagination page = new Pagination();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setTotalCount((int)totalRecord);
		return page;
	}

	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage
	 *            the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo
	 *            the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the totalRecord
	 */
	public long getTotalRecord() {
		return totalRecord;
	}

	/**
	 * @param totalRecord
	 *            the totalRecord to set
	 */
	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}

	/**
	 * @return the results
	 */
	public List<T> getResults() {
		return results;
	}

	/**
	 * @param results
	 *            the results to set
	 */
	public void setResults(List<T> results) {
		this.results = results;
	}

	/**
	 * @return the condition
	 */
	public Object getCondition() {
		return condition;
	}

	/**
	 * @param condition
	 *            the condition to set
	 */
	public void setCondition(Object condition) {
		this.condition = condition;
	}
}
