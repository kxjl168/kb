
package com.kxjl.admin.util;

/**
 * 常量定义
 * 
 * @author havery
 *
 */
public interface Constants {

	//默认版本
	String DEFULT_VERSION="1.0.0";
	
	/**
	 * 子库默认版本号
	 */
	String DEFULT_SUBLIB_VERSION = "0.0.1-快照";

	/**
	 * 子库默认排序
	 */
	String DEFULT_SORT_NUM = "0";

	/**
	 * 树形结构根节点ID
	 */
	String TREE_ROOT_ID = "-1";

	/**
	 * 超级用户
	 */
	String SUPER_USER = "999001";

	/**
	 * 默认年度
	 */
	String DEFAULT_BIZ_YEAR = "2019";

	/**
	 * 默认部门编码
	 */
	String DEFAULT_REG_CODE = "WZ9999";
	
	/**
	 * 管理员角色id
	 */
	String DEFAULT_ADMIN_ROLEID="kg-admin";

	/**
	 * 符号常量
	 * 
	 * @author havery
	 *
	 */
	interface Symbols {
		/**
		 * 逗号
		 */
		String COMMA = ",";
	}

	/**
	 * 子库状态
	 * 
	 * @author havery
	 *
	 */
	interface LibStatus {
		/**
		 * 子库状态：维护中
		 */
		int MAINTENANCE = 0;
		/**
		 * 子库状态：已发布
		 */
		int PUBLISHED = 1;
//		/**
//		 * 子库状态：已上线
//		 */
//		int ONLINE = 2;
		/**
		 * 子库状态：已关闭
		 */
		int CLOSED = 2;
	}

	/**
	 * 有效性
	 * 
	 * @author havery
	 *
	 */
	interface Enabled {
		/**
		 * 有效性:是效
		 */
		String YES = "1";
		/**
		 * 有效性:无效
		 */
		String NO = "0";
	}

	/**
	 * 激活状态
	 * 
	 * @author havery
	 *
	 */
	interface ActivationStatus {
		/**
		 * 激活状态:激活
		 */
		String YES = "1";
		/**
		 * 激活状态:未激活
		 */
		String NO = "0";
	}

	/**
	 * 节点类型
	 * 
	 * @author havery
	 *
	 */
	interface NodeType {
		/**
		 * 领域
		 */
		String DOMAIN = "0";
		/**
		 * 职能
		 */
		String FUNCTION = "1";
		/**
		 * 活动
		 */
		String FUNC_ACT = "2";

		/**
		 * 指标
		 */
		String INDEX = "3";
		
		/**
		 * 部门
		 */
		String ORG = "3";
		/**
		 * 用户
		 */
		String UESR = "4";
	}


	/**
	 * 节点颜色
	 * 
	 * @author havery
	 *
	 */
	interface NodeColor {
		/**
		 * 领域
		 */
		String DOMAIN = "#3098D2";
		/**
		 * 职能
		 */
		String FUNCTION = "#F81212";
		/**
		 * 活动
		 */
		String FUNC_ACT = "#3BED05";

		/**
		 * 指标
		 */
		String INDEX = "#0525ED";

		/**
		 * 默认
		 */
		String DEFAULT = "#D27830";
	}
	
	/**
	 * 失败编码
	 * 
	 * @author havery
	 *
	 */
	interface ErrorCode {
		/**
		 * 失败编码：参数校验失败
		 */
		String F001 = "F001";
		/**
		 * 失败编码：目录编码校验失败
		 */
		String F002 = "F002";

		/**
		 * 失败编码：数据不整合
		 */
		String F003 = "F003";

		/**
		 * 失败编码：原密码不正确
		 */
		String F004 = "F004";
	}

	/**
	 * 失败消息
	 * 
	 * @author havery
	 *
	 */
	interface ErrorMsg {
		/**
		 * 失败消息：参数校验失败
		 */
		String F001 = "参数校验失败";
		/**
		 * 失败消息：目录编码校验失败
		 */
		String F002 = "目录编码校验失败";

		/**
		 * 失败编码：数据不整合
		 */
		String F003 = "数据不整合";

		/**
		 * 失败编码：原密码不正确
		 */
		String F004 = "原密码不正确";
	}

	/**
	 * 数据操作类型
	 * 
	 * @author havery
	 *
	 */
	interface OperType {
		/**
		 * 数据操作类型：新增
		 */
		String NEW = "1";
		/**
		 * 数据操作类型：修改
		 */
		String MODIFY = "2";
		/**
		 * 数据操作类型：删除
		 */
		String DELETE = "0";
		/**
		 * 数据操作类型：未变化
		 */
		String NO_CHANGE = "3";
	}

	/**
	 * 来源
	 * 
	 * @author havery
	 *
	 */
	interface DataSourceOrigin {
		String FROM_MASTER_LIB = "0";
		String CREATE = "1";
	}

	/**
	 * 领域职能活动类型
	 * 
	 * @author havery
	 *
	 */
	interface DomainFuncActType {
		String DOMAIN = "1";
		String FUNCTION = "2";
		String ACTION = "3";
	}
	
	/**
	 * 指标适用层级
	 * 
	 * @author havery
	 *
	 */
	interface IndexHierarchyCode {
		/**
		 * 指标适用层级：中央
		 */
		String CENTER = "1";
		/**
		 * 指标适用层级：省
		 */
		String PROVINCE = "2";
		/**
		 * 指标适用层级：市
		 */
		String CITY = "3";
		/**
		 * 指标适用层级：区县
		 */
		String REGION = "4";
		/**
		 * 指标适用层级：乡镇
		 */
		String TOWN = "5";
	}
}
