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
package com.kxjl.admin.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 微服务响应结果实体对象.
 * 
 * @date 2019年7月9日 下午6:07:22
 * @author havery
 */
public class WZResponseEntity<T> extends BaseEntity {

	/**
	 * 请求是否成功处理：默认是
	 */
	private Boolean isSuccess = true;

	/**
	 * 错误编码，处理失败时给值
	 */
	@JsonInclude(value = Include.NON_NULL)
	private String errorCode;

	/**
	 * 错误信息，处理失败时给值
	 */
	@JsonInclude(value = Include.NON_NULL)
	private String errorMsg;

	/**
	 * 响应消息体
	 */
	@JsonInclude(value = Include.NON_NULL)
	private T body;

	/**
	 * 响应消息体
	 */
	@JsonInclude(value = Include.NON_NULL)
	private Pagination pagination;

	/**
	 * 空构造
	 */
	public WZResponseEntity() {

	}

	/**
	 * 带结果的构造
	 * 
	 * @param isSuccess
	 */
	public WZResponseEntity(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * 带消息体的构造
	 * 
	 * @param isSuccess
	 */
	public WZResponseEntity(T body) {
		this.body = body;
	}

	/**
	 * 获取请求结果(true:成功；false:失败)
	 * 
	 * @return 请求结果
	 */
	public Boolean getIsSuccess() {
		return isSuccess;
	}

	/**
	 * 设置请求结果(true:成功；false:失败)
	 * 
	 * @param isSuccess 请求结果(true:成功；false:失败)
	 */
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * 获取错误编码，处理失败时给值
	 * 
	 * @return 错误编码
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * 设置错误编码，处理失败时给值
	 * 
	 * @param errorCode 错误编码
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 获取错误信息，处理失败时给值
	 * 
	 * @return 错误信息
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * 设置错误信息，处理失败时给值
	 * 
	 * @param errorMsg 错误信息
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * 获取响应消息体，处理成功时给值
	 * 
	 * @return 响应消息体
	 */
	public T getBody() {
		return body;
	}

	/**
	 * 设置响应消息体，处理成功时给值
	 * 
	 * @param body 响应消息体
	 */
	public void setBody(T body) {
		this.body = body;
	}

	/**
	 * @return the pagination
	 */
	public Pagination getPagination() {
		return pagination;
	}

	/**
	 * @param pagination the pagination to set
	 */
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	/**
	 * @param pagination the pagination to set
	 */

	/**
	 * 设置分页信息
	 * 
	 * @param totalCount 总数
	 * @param pageSize   页大小
	 * @param pageNo     页码
	 */
	public void setPagination(int totalCount, int pageSize, int pageNo) {
		Pagination pagination = new Pagination();
		pagination.setPageNo(pageNo);
		pagination.setPageSize(pageSize);
		pagination.setTotalCount(totalCount);
		this.pagination = pagination;
	}
}
