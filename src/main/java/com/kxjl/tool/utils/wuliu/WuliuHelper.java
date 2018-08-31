package com.kxjl.tool.utils.wuliu;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.kxjl.tool.utils.wuliu.impl.ICKDWuliuTrack;
import com.kxjl.tool.utils.wuliu.impl.K100Track;


@Service
public class WuliuHelper {

	@Autowired
	ICKDWuliuTrack iCKDWuliuTrack;

	@Autowired
	K100Track k100Track;


	/**
	 * 物流查询
	 * 
	 * @param num
	 * @return
	 * @author zj
	 * @date 2018年8月22日
	 */
	public String getWuliuInfo(String num) {
		String rst = "";

		try {

			String r1 = iCKDWuliuTrack.getOrderTracesByJson("", num);

			JSONObject j1 = new JSONObject(r1);
			if (j1.optString("errCode").equals("0"))
				return r1;

			r1 = k100Track.getOrderTracesByJson("", num);
			j1 = new JSONObject(r1);
			if (j1.optString("errCode").equals("0"))
				return r1;

			/*r1 = k100TrackOfficial.getOrderTracesByJson("", num);
			j1 = new JSONObject(r1);
			if (j1.optString("errCode").equals("0"))
				return r1;
*/
			try {
				JSONObject jerror = new JSONObject();
				jerror.put("data", "");
				jerror.put("errCode", "-1");

				rst = jerror.toString();
			} catch (Exception e2) {
				// TODO: handle exception
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());

			try {
				JSONObject jerror = new JSONObject();
				jerror.put("data", "");
				jerror.put("errCode", "-1");

				rst = jerror.toString();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

		return rst;
	}
}
