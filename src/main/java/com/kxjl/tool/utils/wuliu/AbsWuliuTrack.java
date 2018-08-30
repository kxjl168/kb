package com.kxjl.tool.utils.wuliu;


public interface  AbsWuliuTrack {

	
	
	/**
	 * 查询物流信息通用接口，其他各个地方查询的结果都归一化如下格式
	 * <br>
	 * <pre>
	 * {
		"mailNo": "70442311422394",
		"errCode": 0,  //0正常   ，其他错误
		"message": "",
		"expSpellName": "huitong",
		"expTextName": "百世快递",
		"tel": "95320",
		"url": "http://www.sf-express.com/",
		"data": [
			{
				"time": "2018-06-08 14:52",
				"context": "大连市【大连龙王塘分部】，【吴昊】已揽收"
			},
			{
				"time": "2018-06-08 20:14",
				"context": "到大连市【大连沙河口区集货点】"
			},
			{
				"time": "2018-06-09 18:47",
				"context": "到大连市【大连转运中心】"
			}
		
		],
		"ord": "ASC"
	}
	 * </pre>
	 * @param expCode
	 * @param expNo
	 * @return
	 * @throws Exception
	 * @author zj
	 * @date 2018年8月22日
	 */
	public  String getOrderTracesByJson(String expCode, String expNo) throws Exception;

}