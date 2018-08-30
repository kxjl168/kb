package com.kxjl.tool.utils.wuliu;

import java.util.Iterator;

import org.json.JSONObject;

/**
 * 物流公司名字查询
 * @author zj
 * @date 2018年8月22日
 *
 */
public class WuliuUtil {

	private static JSONObject jsonCompany = new JSONObject();

	public static void init() {
		try {

			jsonCompany=new JSONObject("{" + 
				"		    auto: {" + 
				"		        spell: \"auto\"," + 
				"		        name: \"自动识别\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"\"," + 
				"		        url: \"#\"" + 
				"		    }," + 
				"		    ems: {" + 
				"		        spell: \"ems\"," + 
				"		        name: \"EMS快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"11183\"," + 
				"		        url: \"http://www.ems.com.cn/\"" + 
				"		    }," + 
				"		    shentong: {" + 
				"		        spell: \"shentong\"," + 
				"		        name: \"申通快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"95543\"," + 
				"		        url: \"http://www.sto.cn/\"" + 
				"		    }," + 
				"		    shunfeng: {" + 
				"		        spell: \"shunfeng\"," + 
				"		        name: \"顺丰快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"95338\"," + 
				"		        url: \"http://www.sf-express.com/\"" + 
				"		    }," + 
				"		    yuantong: {" + 
				"		        spell: \"yuantong\"," + 
				"		        name: \"圆通快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"95554\"," + 
				"		        url: \"http://www.yto.net.cn/\"" + 
				"		    }," + 
				"		    yunda: {" + 
				"		        spell: \"yunda\"," + 
				"		        name: \"韵达快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"95546\"," + 
				"		        url: \"http://www.yundaex.com/\"" + 
				"		    }," + 
				"		    huitong: {" + 
				"		        spell: \"huitong\"," + 
				"		        name: \"百世汇通快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"95320\"," + 
				"		        url: \"http://www.800bestex.com/\"" + 
				"		    }," + 
				"		    tiantian: {" + 
				"		        spell: \"tiantian\"," + 
				"		        name: \"天天快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4001-888-888\"," + 
				"		        url: \"http://www.ttkdex.com/\"" + 
				"		    }," + 
				"		    zhongtong: {" + 
				"		        spell: \"zhongtong\"," + 
				"		        name: \"中通快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"95311\"," + 
				"		        url: \"http://www.zto.com/\"" + 
				"		    }," + 
				"		    zhaijisong: {" + 
				"		        spell: \"zhaijisong\"," + 
				"		        name: \"宅急送快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4006-789-000\"," + 
				"		        url: \"http://www.zjs.com.cn/\"" + 
				"		    }," + 
				"		    pingyou: {" + 
				"		        spell: \"pingyou\"," + 
				"		        name: \"中国邮政\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"11185\"," + 
				"		        url: \"http://www.chinapost.com.cn\"" + 
				"		    }," + 
				"		    quanfeng: {" + 
				"		        spell: \"quanfeng\"," + 
				"		        name: \"全峰快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4001-000-001\"," + 
				"		        url: \"http://www.qfkd.com.cn\"" + 
				"		    }," + 
				"		    guotong: {" + 
				"		        spell: \"guotong\"," + 
				"		        name: \"国通快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"95327\"," + 
				"		        url: \"http://www.gto365.com\"" + 
				"		    }," + 
				"		    jingdong: {" + 
				"		        spell: \"jingdong\"," + 
				"		        name: \"京东快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4006-033-600\"," + 
				"		        url: \"http://www.jdwl.com\"" + 
				"		    }," + 
				"		    sure: {" + 
				"		        spell: \"sure\"," + 
				"		        name: \"速尔快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4001-589-888\"," + 
				"		        url: \"http://www.sure56.com/\"" + 
				"		    }," + 
				"		    kuaijie: {" + 
				"		        spell: \"kuaijie\"," + 
				"		        name: \"快捷快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008-333-666\"," + 
				"		        url: \"http://www.fastexpress.com.cn/\"" + 
				"		    }," + 
				"		    ririshun: {" + 
				"		        spell: \"ririshun\"," + 
				"		        name: \"日日顺物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-800-9999\"," + 
				"		        url: \"http://www.rrs.com/\"" + 
				"		    }," + 
				"		    zhongtie: {" + 
				"		        spell: \"zhongtie\"," + 
				"		        name: \"中铁快运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"95572\"," + 
				"		        url: \"http://www.cre.cn/\"" + 
				"		    }," + 
				"		    yousu: {" + 
				"		        spell: \"yousu\"," + 
				"		        name: \"优速快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4001-111-119\"," + 
				"		        url: \"http://www.uc56.com/\"" + 
				"		    }," + 
				"		    longbang: {" + 
				"		        spell: \"longbang\"," + 
				"		        name: \"龙邦快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"021-5921 8889\"," + 
				"		        url: \"http://www.lbex.com.cn/\"" + 
				"		    }," + 
				"		    debang: {" + 
				"		        spell: \"debang\"," + 
				"		        name: \"德邦物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"95353\"," + 
				"		        url: \"http://www.deppon.com/\"" + 
				"		    }," + 
				"		    rufeng: {" + 
				"		        spell: \"rufeng\"," + 
				"		        name: \"如风达快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-010-6660\"," + 
				"		        url: \"http://www.rufengda.com/\"" + 
				"		    }," + 
				"		    lianhaotong: {" + 
				"		        spell: \"lianhaotong\"," + 
				"		        name: \"联昊通快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400 888 8887\"," + 
				"		        url: \"http://www.lhtex.com.cn\"" + 
				"		    }," + 
				"		    fedex: {" + 
				"		        spell: \"fedex\"," + 
				"		        name: \"国际Fedex\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008-861-888\"," + 
				"		        url: \"http://fedex.com/cn/\"" + 
				"		    }," + 
				"		    fedexcn: {" + 
				"		        spell: \"fedexcn\"," + 
				"		        name: \"Fedex国内\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008-891-888\"," + 
				"		        url: \"http://cndxp.apac.fedex.com\"" + 
				"		    }," + 
				"		    dhl: {" + 
				"		        spell: \"dhl\"," + 
				"		        name: \"DHL快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"8008-108-000\"," + 
				"		        url: \"http://www.cn.dhl.com/\"" + 
				"		    }," + 
				"		    xinfeng: {" + 
				"		        spell: \"xinfeng\"," + 
				"		        name: \"信丰快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008 306 333/0769-81518333\"," + 
				"		        url: \"http://www.xf-express.com.cn/\"" + 
				"		    }," + 
				"		    eyoubao: {" + 
				"		        spell: \"eyoubao\"," + 
				"		        name: \"E邮宝\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"11185\"," + 
				"		        url: \"http://www.ems.com.cn\"" + 
				"		    }," + 
				"		    zhongxinda: {" + 
				"		        spell: \"zhongxinda\"," + 
				"		        name: \"忠信达快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-646-6665\"," + 
				"		        url: \"http://www.zhongxind.cn\"" + 
				"		    }," + 
				"		    changtong: {" + 
				"		        spell: \"changtong\"," + 
				"		        name: \"长通物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"(0371)66520111 66520222\"," + 
				"		        url: \"http://www.hnct56.com/\"" + 
				"		    }," + 
				"		    usps: {" + 
				"		        spell: \"usps\"," + 
				"		        name: \"USPS快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"+1 800-275-8777\"," + 
				"		        url: \"https://www.usps.com/\"" + 
				"		    }," + 
				"		    huaqi: {" + 
				"		        spell: \"huaqi\"," + 
				"		        name: \"华企快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-806-8111\"," + 
				"		        url: \"http://www.hqkd.cn\"" + 
				"		    }," + 
				"		    zhima: {" + 
				"		        spell: \"zhima\"," + 
				"		        name: \"芝麻开门快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4001-056-056,88056056\"," + 
				"		        url: \"http://www.zmkmex.com/\"" + 
				"		    }," + 
				"		    upu: {" + 
				"		        spell: \"upu\"," + 
				"		        name: \"UPU-Universal Postal Union\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.upu.int\"" + 
				"		    }," + 
				"		    gnxb: {" + 
				"		        spell: \"gnxb\"," + 
				"		        name: \"邮政小包\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"11183\"," + 
				"		        url: \"http://yjcx.chinapost.com.cn/\"" + 
				"		    }," + 
				"		    nell: {" + 
				"		        spell: \"nell\"," + 
				"		        name: \"尼尔快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400 000 5611\"," + 
				"		        url: \"http://www.nell.cn/\"" + 
				"		    }," + 
				"		    zengyi: {" + 
				"		        spell: \"zengyi\"," + 
				"		        name: \"增益快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008-456-789\"," + 
				"		        url: \"http://www.zeny-express.com/\"" + 
				"		    }," + 
				"		    yuxin: {" + 
				"		        spell: \"yuxin\"," + 
				"		        name: \"宇鑫物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0371-66378798/99\"," + 
				"		        url: \"http://www.yx56.cn/\"" + 
				"		    }," + 
				"		    xingchengzhaipei: {" + 
				"		        spell: \"xingchengzhaipei\"," + 
				"		        name: \"星程宅配快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"028-66009696\"," + 
				"		        url: \"http://www.sccod.com/\"" + 
				"		    }," + 
				"		    anneng: {" + 
				"		        spell: \"anneng\"," + 
				"		        name: \"安能物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-1040-0888\"," + 
				"		        url: \"http://www.ane56.com/\"" + 
				"		    }," + 
				"		    dada: {" + 
				"		        spell: \"dada\"," + 
				"		        name: \"大达物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-098-5656\"," + 
				"		        url: \"http://www.idada56.com/\"" + 
				"		    }," + 
				"		    tongzhishu: {" + 
				"		        spell: \"tongzhishu\"," + 
				"		        name: \"高考录取通知书\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"11185\"," + 
				"		        url: \"http://www.ems.com.cn\"" + 
				"		    }," + 
				"		    aol: {" + 
				"		        spell: \"aol\"," + 
				"		        name: \"AOL快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"020-36413533\"," + 
				"		        url: \"http://www.aol-au.com\"" + 
				"		    }," + 
				"		    dongjun: {" + 
				"		        spell: \"dongjun\"," + 
				"		        name: \"成都东骏快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"028-8553 8888\"," + 
				"		        url: \"http://www.dj56.cc/\"" + 
				"		    }," + 
				"		    suning: {" + 
				"		        spell: \"suning\"," + 
				"		        name: \"苏宁快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"95315\"," + 
				"		        url: \"http://wuliu.suning.com/\"" + 
				"		    }," + 
				"		    quanyi: {" + 
				"		        spell: \"quanyi\"," + 
				"		        name: \"全一快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-678-1515\"," + 
				"		        url: \"http://www.unitop-apex.com/\"" + 
				"		    }," + 
				"		    huayu: {" + 
				"		        spell: \"huayu\"," + 
				"		        name: \"华宇物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-808-6666\"," + 
				"		        url: \"http://www.hoau.net/\"" + 
				"		    }," + 
				"		    quanritong: {" + 
				"		        spell: \"quanritong\"," + 
				"		        name: \"全日通快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"020-86298999\"," + 
				"		        url: \"http://www.at-express.com/\"" + 
				"		    }," + 
				"		    ane66: {" + 
				"		        spell: \"ane66\"," + 
				"		        name: \"安能小包\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"40010-40088\"," + 
				"		        url: \"http://www.ane66.com\"" + 
				"		    }," + 
				"		    \"800best\": {" + 
				"		        spell: \"800best\"," + 
				"		        name: \"百世快运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-8856-561\"," + 
				"		        url: \"http://www.800best.com/\"" + 
				"		    }," + 
				"		    fengcheng: {" + 
				"		        spell: \"fengcheng\"," + 
				"		        name: \"丰程物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-1676-555\"," + 
				"		        url: \"http://www.sccod.com/\"" + 
				"		    }," + 
				"		    minhang: {" + 
				"		        spell: \"minhang\"," + 
				"		        name: \"民航快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008-17-4008\"," + 
				"		        url: \"http://www.cae.com.cn\"" + 
				"		    }," + 
				"		    zhongyou: {" + 
				"		        spell: \"zhongyou\"," + 
				"		        name: \"中邮物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"11185\"," + 
				"		        url: \"http://www.cnpl.com.cn/\"" + 
				"		    }," + 
				"		    wanjia: {" + 
				"		        spell: \"wanjia\"," + 
				"		        name: \"万家物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"021-51592929\"," + 
				"		        url: \"http://www.manco-logistics.com\"" + 
				"		    }," + 
				"		    jiaji: {" + 
				"		        spell: \"jiaji\"," + 
				"		        name: \"佳吉快运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-820-5566\"," + 
				"		        url: \"http://www.jiaji.com/\"" + 
				"		    }," + 
				"		    wanxiang: {" + 
				"		        spell: \"wanxiang\"," + 
				"		        name: \"万象物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-820-8088\"," + 
				"		        url: \"http://www.ewinshine.com\"" + 
				"		    }," + 
				"		    zqlwl: {" + 
				"		        spell: \"zqlwl\"," + 
				"		        name: \"青旅物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400 086 5656\"," + 
				"		        url: \"http://www.zqlwl.com\"" + 
				"		    }," + 
				"		    beihai: {" + 
				"		        spell: \"beihai\"," + 
				"		        name: \"贝海国际快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.xlobo.com/\"" + 
				"		    }," + 
				"		    junda: {" + 
				"		        spell: \"junda\"," + 
				"		        name: \"骏达快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"1-626-699-0009\"," + 
				"		        url: \"http://www.jdexpressusa.com/\"" + 
				"		    }," + 
				"		    quanxintong: {" + 
				"		        spell: \"quanxintong\"," + 
				"		        name: \"全信通快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-882-6886\"," + 
				"		        url: \"http://www.all-express.com.cn/\"" + 
				"		    }," + 
				"		    correos: {" + 
				"		        spell: \"correos\"," + 
				"		        name: \"西班牙邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.correos.es/\"" + 
				"		    }," + 
				"		    ynztsy: {" + 
				"		        spell: \"ynztsy\"," + 
				"		        name: \"纵通速运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008-788-744\"," + 
				"		        url: \"http://www.ynztsy.com/\"" + 
				"		    }," + 
				"		    postnl: {" + 
				"		        spell: \"postnl\"," + 
				"		        name: \"荷兰邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.postnl.com/\"" + 
				"		    }," + 
				"		    \"cosco-eglobal\": {" + 
				"		        spell: \"cosco-eglobal\"," + 
				"		        name: \"中远e环球\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"020-83330879\"," + 
				"		        url: \"http://www.cosco-eglobal.com/\"" + 
				"		    }," + 
				"		    thailand: {" + 
				"		        spell: \"thailand\"," + 
				"		        name: \"泰国邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.thailandpost.com/\"" + 
				"		    }," + 
				"		    yifankd: {" + 
				"		        spell: \"yifankd\"," + 
				"		        name: \"艺凡快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"021-59882268\"," + 
				"		        url: \"http://www.yifankd.com/\"" + 
				"		    }," + 
				"		    bjems: {" + 
				"		        spell: \"bjems\"," + 
				"		        name: \"北京邮政快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"010-64563396/64566270\"," + 
				"		        url: \"http://www.bj-cnpl.com/\"" + 
				"		    }," + 
				"		    yimidida: {" + 
				"		        spell: \"yimidida\"," + 
				"		        name: \"壹米滴答\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008311311\"," + 
				"		        url: \"http://www.yimidida.com\"" + 
				"		    }," + 
				"		    canpost: {" + 
				"		        spell: \"canpost\"," + 
				"		        name: \"加拿大邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"1-866-607-6301\"," + 
				"		        url: \"https://www.canadapost.ca/\"" + 
				"		    }," + 
				"		    yzs56: {" + 
				"		        spell: \"yzs56\"," + 
				"		        name: \"亚洲顺物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0755-86006052\"," + 
				"		        url: \"http://www.yzs56.net.cn/\"" + 
				"		    }," + 
				"		    postdanmark: {" + 
				"		        spell: \"postdanmark\"," + 
				"		        name: \"丹麦邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+45 70 70 70 30\"," + 
				"		        url: \"http://www.postdanmark.dk/en/\"" + 
				"		    }," + 
				"		    yatfai: {" + 
				"		        spell: \"yatfai\"," + 
				"		        name: \"一辉物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0755-82631108-839\"," + 
				"		        url: \"http://www.yatfai.com/\"" + 
				"		    }," + 
				"		    ruston: {" + 
				"		        spell: \"ruston\"," + 
				"		        name: \"俄速通快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"0451-5192 2298\"," + 
				"		        url: \"http://www.ruston.cc/\"" + 
				"		    }," + 
				"		    \"u-ex\": {" + 
				"		        spell: \"u-ex\"," + 
				"		        name: \"邮来速递\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"18768082162\"," + 
				"		        url: \"http://www.u-ex.cn/\"" + 
				"		    }," + 
				"		    pochta: {" + 
				"		        spell: \"pochta\"," + 
				"		        name: \"俄罗斯邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"8-800-2005-888\"," + 
				"		        url: \"https://www.pochta.ru/\"" + 
				"		    }," + 
				"		    eta100: {" + 
				"		        spell: \"eta100\"," + 
				"		        name: \"易达国际速递\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"13688034855\"," + 
				"		        url: \"http://www.eta100.com/\"" + 
				"		    }," + 
				"		    ukrposhta: {" + 
				"		        spell: \"ukrposhta\"," + 
				"		        name: \"乌克兰邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://ukrposhta.ua/en/\"" + 
				"		    }," + 
				"		    posmy: {" + 
				"		        spell: \"posmy\"," + 
				"		        name: \"马来西亚邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"603-2727-9100\"," + 
				"		        url: \"http://www.pos.com.my/\"" + 
				"		    }," + 
				"		    bsht: {" + 
				"		        spell: \"bsht\"," + 
				"		        name: \"百事亨通\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-185-6666\"," + 
				"		        url: \"http://www.bsht-express.com\"" + 
				"		    }," + 
				"		    postifi: {" + 
				"		        spell: \"postifi\"," + 
				"		        name: \"芬兰邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.posti.fi/\"" + 
				"		    }," + 
				"		    bosind: {" + 
				"		        spell: \"bosind\"," + 
				"		        name: \"堡昕德速递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4006787807\"," + 
				"		        url: \"http://www.bosind.com/ \"" + 
				"		    }," + 
				"		    indiapost: {" + 
				"		        spell: \"indiapost\"," + 
				"		        name: \"印度邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.indiapost.gov.in/\"" + 
				"		    }," + 
				"		    polarisexpress: {" + 
				"		        spell: \"polarisexpress\"," + 
				"		        name: \"北极星快运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"31 88 888 9345\"," + 
				"		        url: \"http://www.polarisexpress.com/ \"" + 
				"		    }," + 
				"		    pocztex: {" + 
				"		        spell: \"pocztex\"," + 
				"		        name: \"波兰邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+48 43 842 0 842\"," + 
				"		        url: \"http://www.pocztex.pl/\"" + 
				"		    }," + 
				"		    europe8: {" + 
				"		        spell: \"europe8\"," + 
				"		        name: \"败欧洲\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"+49 7251 72358\"," + 
				"		        url: \"http://www.8europe.com/\"" + 
				"		    }," + 
				"		    fanjie: {" + 
				"		        spell: \"fanjie\"," + 
				"		        name: \"泛捷国际快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"400-808-3929\"," + 
				"		        url: \"http://www.epanex.com/\"" + 
				"		    }," + 
				"		    comexpress: {" + 
				"		        spell: \"comexpress\"," + 
				"		        name: \"邦通国际\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0411-39550451\"," + 
				"		        url: \"http://comexpress.ca/index.aspx\"" + 
				"		    }," + 
				"		    cloudexpress: {" + 
				"		        spell: \"cloudexpress\"," + 
				"		        name: \"CE易欧通国际速递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"+31 367851419，+31 6280884\"," + 
				"		        url: \"\"" + 
				"		    }," + 
				"		    ups: {" + 
				"		        spell: \"ups\"," + 
				"		        name: \"UPS快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"800-820-8388/400-820-8388\"," + 
				"		        url: \"http://www.ups.com/cn\"" + 
				"		    }," + 
				"		    yikeman: {" + 
				"		        spell: \"yikeman\"," + 
				"		        name: \"易客满快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"400-086-1756\"," + 
				"		        url: \"http://www.ecmsglobal.com/\"" + 
				"		    }," + 
				"		    cex: {" + 
				"		        spell: \"cex\"," + 
				"		        name: \"城铁速递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4000-800-601\"," + 
				"		        url: \"http://www.c-ex.cn\"" + 
				"		    }," + 
				"		    tnt: {" + 
				"		        spell: \"tnt\"," + 
				"		        name: \"TNT快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"800 820 9868/400 820 9868\"," + 
				"		        url: \"http://www.tnt.com/express/zh_cn/site/home.html\"" + 
				"		    }," + 
				"		    fastgo: {" + 
				"		        spell: \"fastgo\"," + 
				"		        name: \"速派快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"400 886 3278\"," + 
				"		        url: \"http://www.fastgo.com.au/\"" + 
				"		    }," + 
				"		    spring56: {" + 
				"		        spell: \"spring56\"," + 
				"		        name: \"春风物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"021-62967908\"," + 
				"		        url: \"http://www.spring56.com\"" + 
				"		    }," + 
				"		    cnpex: {" + 
				"		        spell: \"cnpex\"," + 
				"		        name: \"中邮快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"400 007 9988\"," + 
				"		        url: \"http://www.cnpex.com.au/\"" + 
				"		    }," + 
				"		    longvast: {" + 
				"		        spell: \"longvast\"," + 
				"		        name: \"长风物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0769-85340156\"," + 
				"		        url: \"http://www.longvast.com.cn/\"" + 
				"		    }," + 
				"		    auexpress: {" + 
				"		        spell: \"auexpress\"," + 
				"		        name: \"澳邮中国快运\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"130 007 9988, +612 9644 9588\"," + 
				"		        url: \"http://www.auexpress.com.au/\"" + 
				"		    }," + 
				"		    parcelchina: {" + 
				"		        spell: \"parcelchina\"," + 
				"		        name: \"诚一物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"(+44)01332419100\"," + 
				"		        url: \"http://parcelchina.co.uk/\"" + 
				"		    }," + 
				"		    pca: {" + 
				"		        spell: \"pca\"," + 
				"		        name: \"PCA Express\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"1800 518 000\"," + 
				"		        url: \"http://www.pcaexpress.com.au/\"" + 
				"		    }," + 
				"		    easyexpress: {" + 
				"		        spell: \"easyexpress\"," + 
				"		        name: \"EASYEXPRESS国际速递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.easyexpress.com.au/\"" + 
				"		    }," + 
				"		    adp: {" + 
				"		        spell: \"adp\"," + 
				"		        name: \"韩国ADP快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"1588-1330\"," + 
				"		        url: \"http://www.adpair.co.kr/ch/\"" + 
				"		    }," + 
				"		    ecallturn: {" + 
				"		        spell: \"ecallturn\"," + 
				"		        name: \"E跨通\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"+66-899368856\"," + 
				"		        url: \"http://www.ecallturn.com/\"" + 
				"		    }," + 
				"		    a2u: {" + 
				"		        spell: \"a2u\"," + 
				"		        name: \"A2U快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"03 98774330/04 04616906\"," + 
				"		        url: \"http://www.a2u.com.au/\"" + 
				"		    }," + 
				"		    crazyexpress: {" + 
				"		        spell: \"crazyexpress\"," + 
				"		        name: \"疯狂快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.crazyexpress.ca\"" + 
				"		    }," + 
				"		    fedroad: {" + 
				"		        spell: \"fedroad\"," + 
				"		        name: \"FedRoad 联邦转运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4007-280-360 \"," + 
				"		        url: \"http://www.fedroad.com \"" + 
				"		    }," + 
				"		    omniva: {" + 
				"		        spell: \"omniva\"," + 
				"		        name: \"爱沙尼亚邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"661 6616\"," + 
				"		        url: \"https://www.omniva.ee/\"" + 
				"		    }," + 
				"		    freakyquick: {" + 
				"		        spell: \"freakyquick\"," + 
				"		        name: \"FQ狂派速递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"15369147269\"," + 
				"		        url: \"http://www.freakyquick.com.au/\"" + 
				"		    }," + 
				"		    laposte: {" + 
				"		        spell: \"laposte\"," + 
				"		        name: \"突尼斯邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.poste.tn/\"" + 
				"		    }," + 
				"		    fecobv: {" + 
				"		        spell: \"fecobv\"," + 
				"		        name: \"丰客物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"18067277845\"," + 
				"		        url: \"http://www.fecobv.com/\"" + 
				"		    }," + 
				"		    swisspost: {" + 
				"		        spell: \"swisspost\"," + 
				"		        name: \"瑞士邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+41 (0) 848 888 888\"," + 
				"		        url: \"https://www.post.ch/en/\"" + 
				"		    }," + 
				"		    beebird: {" + 
				"		        spell: \"beebird\"," + 
				"		        name: \"锋鸟物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"+61 08 9417 8950\"," + 
				"		        url: \"https://beebird.com.au\"" + 
				"		    }," + 
				"		    ada: {" + 
				"		        spell: \"ada\"," + 
				"		        name: \"明大快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"1300-721-688/中国热线 95040-444-16\"," + 
				"		        url: \"http://adaexpress.com.au/\"" + 
				"		    }," + 
				"		    guosong: {" + 
				"		        spell: \"guosong\"," + 
				"		        name: \"国送快运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4000008408\"," + 
				"		        url: \"http://guosong365.com/\"" + 
				"		    }," + 
				"		    stous: {" + 
				"		        spell: \"stous\"," + 
				"		        name: \"美国申通快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"18884481288\"," + 
				"		        url: \"http://www.stoexpress.us/\"" + 
				"		    }," + 
				"		    airgtc: {" + 
				"		        spell: \"airgtc\"," + 
				"		        name: \"航空快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"18640151012 \"," + 
				"		        url: \"http://air-gtc.com/\"" + 
				"		    }," + 
				"		    quantium: {" + 
				"		        spell: \"quantium\"," + 
				"		        name: \"Quantium\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"(852) 2318 1213\"," + 
				"		        url: \"http://www.quantiumsolutions.com/\"" + 
				"		    }," + 
				"		    ccd: {" + 
				"		        spell: \"ccd\"," + 
				"		        name: \"河南次晨达\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-003-3506 \"," + 
				"		        url: \"http://www.ccd56.com \"" + 
				"		    }," + 
				"		    ontrac: {" + 
				"		        spell: \"ontrac\"," + 
				"		        name: \"OnTrac快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"800 334 5000\"," + 
				"		        url: \"http://www.ontrac.com/\"" + 
				"		    }," + 
				"		    httx56: {" + 
				"		        spell: \"httx56\"," + 
				"		        name: \"汇通天下物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0755-23060295\"," + 
				"		        url: \"http://www.httx56.com/ \"" + 
				"		    }," + 
				"		    eltacourier: {" + 
				"		        spell: \"eltacourier\"," + 
				"		        name: \"希腊邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+30 210 6073 000\"," + 
				"		        url: \"http://www.elta-courier.gr/\"" + 
				"		    }," + 
				"		    hivewms: {" + 
				"		        spell: \"hivewms\"," + 
				"		        name: \"海沧无忧\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"+49 7251 72332\"," + 
				"		        url: \"http://www.hivewms.com/\"" + 
				"		    }," + 
				"		    hnht56: {" + 
				"		        spell: \"hnht56\"," + 
				"		        name: \"鸿泰物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-8607777\"," + 
				"		        url: \"http://hnht56.com/\"" + 
				"		    }," + 
				"		    shenghui: {" + 
				"		        spell: \"shenghui\"," + 
				"		        name: \"盛辉物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008-222-222\"," + 
				"		        url: \"http://www.shenghui56.com/\"" + 
				"		    }," + 
				"		    tntuk: {" + 
				"		        spell: \"tntuk\"," + 
				"		        name: \"TNT UK\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.tnt.com/content/express/en_gb/site/home.html\"" + 
				"		    }," + 
				"		    hltop: {" + 
				"		        spell: \"hltop\"," + 
				"		        name: \"海联快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"021-33886089\"," + 
				"		        url: \"http://www.hltop.com/\"" + 
				"		    }," + 
				"		    jiuyescm: {" + 
				"		        spell: \"jiuyescm\"," + 
				"		        name: \"九曳供应链\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4006-199-939\"," + 
				"		        url: \"http://jiuyescm.com\"" + 
				"		    }," + 
				"		    jcex: {" + 
				"		        spell: \"jcex\"," + 
				"		        name: \"佳成国际快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"400-889-1083\"," + 
				"		        url: \"http://www.jcex.com\"" + 
				"		    }," + 
				"		    jieanda: {" + 
				"		        spell: \"jieanda\"," + 
				"		        name: \"捷安达国际速递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"03 9544 8304\"," + 
				"		        url: \"http://www.giantpost.com.au/\"" + 
				"		    }," + 
				"		    efspost: {" + 
				"		        spell: \"efspost\"," + 
				"		        name: \"EFS快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"0773-2308246\"," + 
				"		        url: \"http://www.efspost.com/\"" + 
				"		    }," + 
				"		    newsway: {" + 
				"		        spell: \"newsway\"," + 
				"		        name: \"家家通快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"15040133730\"," + 
				"		        url: \"http://www.newsway.ca/\"" + 
				"		    }," + 
				"		    ctt: {" + 
				"		        spell: \"ctt\"," + 
				"		        name: \"葡萄牙邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+351 707 26 26 26\"," + 
				"		        url: \"http://www.ctt.pt\"" + 
				"		    }," + 
				"		    jiazhoumao: {" + 
				"		        spell: \"jiazhoumao\"," + 
				"		        name: \"加州猫速递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"13390977117\"," + 
				"		        url: \"http://www.jiazhoumao.com/tracking/htl\"" + 
				"		    }," + 
				"		    gls: {" + 
				"		        spell: \"gls\"," + 
				"		        name: \"GLS快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"877-914-5465\"," + 
				"		        url: \"https://gls-group.eu/\"" + 
				"		    }," + 
				"		    jieborne: {" + 
				"		        spell: \"jieborne\"," + 
				"		        name: \"捷邦物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-9006956\"," + 
				"		        url: \"http://www.jieborne.com/\"" + 
				"		    }," + 
				"		    gaticn: {" + 
				"		        spell: \"gaticn\"," + 
				"		        name: \"GATI快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"4000-804-284\"," + 
				"		        url: \"http://www.gaticn.com/\"" + 
				"		    }," + 
				"		    kfwnet: {" + 
				"		        spell: \"kfwnet\"," + 
				"		        name: \"快服务\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-068-0101\"," + 
				"		        url: \"http://www.kfw.net/index.html\"" + 
				"		    }," + 
				"		    southafricanpost: {" + 
				"		        spell: \"southafricanpost\"," + 
				"		        name: \"南非邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+27 0860 111 502\"," + 
				"		        url: \"http://www.postoffice.co.za\"" + 
				"		    }," + 
				"		    ltexp: {" + 
				"		        spell: \"ltexp\"," + 
				"		        name: \"乐天速递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"021-62269059 \"," + 
				"		        url: \"http://www.ltexp.com.cn \"" + 
				"		    }," + 
				"		    yafeng: {" + 
				"		        spell: \"yafeng\"," + 
				"		        name: \"亚风快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4001 000 002\"," + 
				"		        url: \"http://www.airfex.net/\"" + 
				"		    }," + 
				"		    gatikwe: {" + 
				"		        spell: \"gatikwe\"," + 
				"		        name: \"Gati-KWE快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+91 1800-180-4284\"," + 
				"		        url: \"http://www.gatikwe.com/\"" + 
				"		    }," + 
				"		    szuem: {" + 
				"		        spell: \"szuem\"," + 
				"		        name: \"联运通物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008988008\"," + 
				"		        url: \"http://www.szuem.com/ \"" + 
				"		    }," + 
				"		    gsm: {" + 
				"		        spell: \"gsm\"," + 
				"		        name: \"GSM快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"021-6465-6011\"," + 
				"		        url: \"http://www.gsmnton.com \"" + 
				"		    }," + 
				"		    mchy: {" + 
				"		        spell: \"mchy\"," + 
				"		        name: \"木春货运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-6359-800 \"," + 
				"		        url: \"http://www.mcchina-express.com/ \"" + 
				"		    }," + 
				"		    iparcel: {" + 
				"		        spell: \"iparcel\"," + 
				"		        name: \"UPS i-parcel\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"400-078-1183\"," + 
				"		        url: \"http://www.i-parcel.com/en/\"" + 
				"		    }," + 
				"		    valueway: {" + 
				"		        spell: \"valueway\"," + 
				"		        name: \"美通快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"905-766-1111\"," + 
				"		        url: \"http://www.valueway.net/\"" + 
				"		    }," + 
				"		    brazilcorreios: {" + 
				"		        spell: \"brazilcorreios\"," + 
				"		        name: \"巴西邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+55 61 3003 0100\"," + 
				"		        url: \"http://www.correios.com.br/ \"" + 
				"		    }," + 
				"		    zsmhwl: {" + 
				"		        spell: \"zsmhwl\"," + 
				"		        name: \"明辉物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"\"," + 
				"		        url: \"\"" + 
				"		    }," + 
				"		    saudipost: {" + 
				"		        spell: \"saudipost\"," + 
				"		        name: \"沙特邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+966 9200 05700\"," + 
				"		        url: \"http://www.sp.com.sa/\"" + 
				"		    }," + 
				"		    mantoo: {" + 
				"		        spell: \"mantoo\"," + 
				"		        name: \"优能物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"02885154290\"," + 
				"		        url: \"http://www.ef.mantoo.com.cn/\"" + 
				"		    }," + 
				"		    dsu: {" + 
				"		        spell: \"dsu\"," + 
				"		        name: \"D速快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0531-87927796/97/98/99\"," + 
				"		        url: \"http://www.d-exp.cn/\"" + 
				"		    }," + 
				"		    cambodiapost: {" + 
				"		        spell: \"cambodiapost\"," + 
				"		        name: \"柬埔寨邮政_Cambodia Post\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+855 23 723 51\"," + 
				"		        url: \"http://www.ems.com.kh/\"" + 
				"		    }," + 
				"		    noll: {" + 
				"		        spell: \"noll\"," + 
				"		        name: \"诺尔国际物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.noll-logistics.com/\"" + 
				"		    }," + 
				"		    datian: {" + 
				"		        spell: \"datian\"," + 
				"		        name: \"大田物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-626-1166\"," + 
				"		        url: \"http://www.dtw.com.cn/\"" + 
				"		    }," + 
				"		    vnpost: {" + 
				"		        spell: \"vnpost\"," + 
				"		        name: \"越南邮政_Vietnam Post\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+84 1900 54 54 81\"," + 
				"		        url: \"http://www.vnpost.vn/\"" + 
				"		    }," + 
				"		    ndwl: {" + 
				"		        spell: \"ndwl\"," + 
				"		        name: \"南方传媒物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008-921-921\"," + 
				"		        url: \"http://nfcmwl.com/\"" + 
				"		    }," + 
				"		    jiayi: {" + 
				"		        spell: \"jiayi\"," + 
				"		        name: \"佳怡物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400 631 9999\"," + 
				"		        url: \"http://www.jiayi56.com/\"" + 
				"		    }," + 
				"		    \"chronopost-portugal\": {" + 
				"		        spell: \"chronopost-portugal\"," + 
				"		        name: \"Chronopost Portugal\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+351 707 20 28 28\"," + 
				"		        url: \"http://chronopost.pt/\"" + 
				"		    }," + 
				"		    canhold: {" + 
				"		        spell: \"canhold\"," + 
				"		        name: \"能装能送\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4001891756\"," + 
				"		        url: \"http://www.canhold.com.cn/\"" + 
				"		    }," + 
				"		    jiayunmei: {" + 
				"		        spell: \"jiayunmei\"," + 
				"		        name: \"加运美快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0769-85515555、85166425 投诉电话：0769-85515555\"," + 
				"		        url: \"http://www.jym56.cn\"" + 
				"		    }," + 
				"		    buylogic: {" + 
				"		        spell: \"buylogic\"," + 
				"		        name: \"捷买送(Buy Logic)快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+86-755-81455122\"," + 
				"		        url: \"http://www.buylogic.cc/\"" + 
				"		    }," + 
				"		    euasia: {" + 
				"		        spell: \"euasia\"," + 
				"		        name: \"欧亚专线\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"+31 88 668 1277 \"," + 
				"		        url: \"http://www.euasia.eu \"" + 
				"		    }," + 
				"		    quanchen: {" + 
				"		        spell: \"quanchen\"," + 
				"		        name: \"全晨快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0769-89033666-801/803\"," + 
				"		        url: \"http://www.qckd.net/\"" + 
				"		    }," + 
				"		    \"taiwan-post\": {" + 
				"		        spell: \"taiwan-post\"," + 
				"		        name: \"台湾邮政(Taiwan Post)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+886 (02)2703-7527\"," + 
				"		        url: \"http://www.post.gov.tw/\"" + 
				"		    }," + 
				"		    qbexpress: {" + 
				"		        spell: \"qbexpress\"," + 
				"		        name: \"秦邦快运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"\"," + 
				"		        url: \"\"" + 
				"		    }," + 
				"		    ocs: {" + 
				"		        spell: \"ocs\"," + 
				"		        name: \"OCS快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-1188-588/010-85819966-112\"," + 
				"		        url: \"http://www.ocs.co.jp/\"" + 
				"		    }," + 
				"		    speedpost: {" + 
				"		        spell: \"speedpost\"," + 
				"		        name: \"Speed Post\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+65 6222 5777\"," + 
				"		        url: \"http://www.speedpost.com.sg/\"" + 
				"		    }," + 
				"		    linkglobalexpress: {" + 
				"		        spell: \"linkglobalexpress\"," + 
				"		        name: \"全速通国际快递\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"1300 166 666\"," + 
				"		        url: \"http://www.linkglobalexpress.com/\"" + 
				"		    }," + 
				"		    shengfeng: {" + 
				"		        spell: \"shengfeng\"," + 
				"		        name: \"盛丰物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0591-83621111 / 83625777\"," + 
				"		        url: \"http://www.sfwl.com.cn/\"" + 
				"		    }," + 
				"		    laopost: {" + 
				"		        spell: \"laopost\"," + 
				"		        name: \"老挝邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.laopostvte.com/\"" + 
				"		    }," + 
				"		    qinyuan: {" + 
				"		        spell: \"qinyuan\"," + 
				"		        name: \"秦远物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"09-8372888\"," + 
				"		        url: \"http://www.chinz56.co.nz/\"" + 
				"		    }," + 
				"		    \"emirates-post\": {" + 
				"		        spell: \"emirates-post\"," + 
				"		        name: \"阿联酋邮政(Emirates Post)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+971 4 230 3000 \"," + 
				"		        url: \"https://www.epg.ae/\"" + 
				"		    }," + 
				"		    qichen: {" + 
				"		        spell: \"qichen\"," + 
				"		        name: \"启辰国际物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"00852-31166708\"," + 
				"		        url: \"http://www.qichen.hk/\"" + 
				"		    }," + 
				"		    xinbang: {" + 
				"		        spell: \"xinbang\"," + 
				"		        name: \"新邦物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008-000-222\"," + 
				"		        url: \"http://www.xbwl.cn/\"" + 
				"		    }," + 
				"		    cuckoo: {" + 
				"		        spell: \"cuckoo\"," + 
				"		        name: \"Cuckoo布谷鸟快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"4001000533\"," + 
				"		        url: \"http://www.cuckooexpress.com/\"" + 
				"		    }," + 
				"		    chengguang: {" + 
				"		        spell: \"chengguang\"," + 
				"		        name: \"程光快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0064 9 948 2780\"," + 
				"		        url: \"http://www.flywayex.com\"" + 
				"		    }," + 
				"		    \"dhl-global-mail\": {" + 
				"		        spell: \"dhl-global-mail\"," + 
				"		        name: \"DHL eCommerce\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+1 317 554 5191\"," + 
				"		        url: \"http://webtrack.dhlglobalmail.com/\"" + 
				"		    }," + 
				"		    qskdyxgs: {" + 
				"		        spell: \"qskdyxgs\"," + 
				"		        name: \"千顺快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4000-444-668\"," + 
				"		        url: \"http://www.qskdyxgs.com\"" + 
				"		    }," + 
				"		    fengda: {" + 
				"		        spell: \"fengda\"," + 
				"		        name: \"丰达快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-113-6666\"," + 
				"		        url: \"http://www.fd168.com.cn/\"" + 
				"		    }," + 
				"		    \"asendia-usa\": {" + 
				"		        spell: \"asendia-usa\"," + 
				"		        name: \"Asendia USA\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+1 610 461 3661\"," + 
				"		        url: \"http://www.brokersworldwide.com/\"" + 
				"		    }," + 
				"		    guexp: {" + 
				"		        spell: \"guexp\"," + 
				"		        name: \"全联速运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008908809\"," + 
				"		        url: \"http://www.guexp.com/\"" + 
				"		    }," + 
				"		    \"yamato-japan\": {" + 
				"		        spell: \"yamato-japan\"," + 
				"		        name: \"日本黑猫宅急便(Yamato Japan)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+81 0120-17-9625\"," + 
				"		        url: \"http://www.kuronekoyamato.co.jp/en/\"" + 
				"		    }," + 
				"		    bjqywl: {" + 
				"		        spell: \"bjqywl\"," + 
				"		        name: \"青云物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"010-60344320\"," + 
				"		        url: \"http://www.bjqywl.com/\"" + 
				"		    }," + 
				"		    feihang: {" + 
				"		        spell: \"feihang\"," + 
				"		        name: \"原飞航物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0769-87001100-8225\"," + 
				"		        url: \"http://www.yfhex.com/\"" + 
				"		    }," + 
				"		    \"china-post\": {" + 
				"		        spell: \"china-post\"," + 
				"		        name: \"China Post\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+86 11183\"," + 
				"		        url: \"http://www.chinapost.com.cn\"" + 
				"		    }," + 
				"		    subida: {" + 
				"		        spell: \"subida\"," + 
				"		        name: \"速必达物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4006005656\"," + 
				"		        url: \"http://www.cjspd.com/\"" + 
				"		    }," + 
				"		    jinyue: {" + 
				"		        spell: \"jinyue\"," + 
				"		        name: \"晋越快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"台北总部：+886-2-26591819 广东：0769-85158039\"," + 
				"		        url: \"http://www.byondex.com/\"" + 
				"		    }," + 
				"		    \"royal-mail\": {" + 
				"		        spell: \"royal-mail\"," + 
				"		        name: \"英国皇家邮政(Royal Mail)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+44 1752387112\"," + 
				"		        url: \"http://www.royalmail.com/\"" + 
				"		    }," + 
				"		    sufast: {" + 
				"		        spell: \"sufast\"," + 
				"		        name: \"速方国际物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-820-1220\"," + 
				"		        url: \"http://www.sufast.net/\"" + 
				"		    }," + 
				"		    \"parcel-force\": {" + 
				"		        spell: \"parcel-force\"," + 
				"		        name: \"Parcel Force\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+44 844 800 44 66\"," + 
				"		        url: \"http://www.parcelforce.com/\"" + 
				"		    }," + 
				"		    sihaiet: {" + 
				"		        spell: \"sihaiet\"," + 
				"		        name: \"四海快递\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"400-6666-337 \"," + 
				"		        url: \"http://www.sihaiet.com/ \"" + 
				"		    }," + 
				"		    \"dpd-ireland\": {" + 
				"		        spell: \"dpd-ireland\"," + 
				"		        name: \"DPD Ireland\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+353 (0)90 64 20500\"," + 
				"		        url: \"http://www.dpd.ie/\"" + 
				"		    }," + 
				"		    sendtochina: {" + 
				"		        spell: \"sendtochina\"," + 
				"		        name: \"速递中国\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"(+44)1293537035\"," + 
				"		        url: \"http://send2china.com\"" + 
				"		    }," + 
				"		    yuefeng: {" + 
				"		        spell: \"yuefeng\"," + 
				"		        name: \"越丰快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0755-27517588(深圳)/0852-23909969(香港)\"," + 
				"		        url: \"http://www.yfexpress.com.hk\"" + 
				"		    }," + 
				"		    \"dpd-de\": {" + 
				"		        spell: \"dpd-de\"," + 
				"		        name: \"DPD Germany\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+49 01806 373 200\"," + 
				"		        url: \"https://www.dpd.com/de_privatkunden/\"" + 
				"		    }," + 
				"		    \"czech-post\": {" + 
				"		        spell: \"czech-post\"," + 
				"		        name: \"捷克邮政(Czech Post - Česká pošta)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+420 954 292 102\"," + 
				"		        url: \"https://www.postaonline.cz/\"" + 
				"		    }," + 
				"		    superb: {" + 
				"		        spell: \"superb\"," + 
				"		        name: \"Superb Grace\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"\"," + 
				"		        url: \"https://www.superb-grace.com/\"" + 
				"		    }," + 
				"		    anjie: {" + 
				"		        spell: \"anjie\"," + 
				"		        name: \"安捷快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400565656\"," + 
				"		        url: \"http://www.anjelex.com/\"" + 
				"		    }," + 
				"		    gofly: {" + 
				"		        spell: \"gofly\"," + 
				"		        name: \"Go Fly\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+86 13266937871\"," + 
				"		        url: \"http://goflyi.com/\"" + 
				"		    }," + 
				"		    aae: {" + 
				"		        spell: \"aae\"," + 
				"		        name: \"AAE快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-6100-400\"," + 
				"		        url: \"http://cn.aaeweb.com\"" + 
				"		    }," + 
				"		    \"chronopost-france\": {" + 
				"		        spell: \"chronopost-france\"," + 
				"		        name: \"Chronopost France\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+33 (0) 969 391 391\"," + 
				"		        url: \"http://chronopost.fr\"" + 
				"		    }," + 
				"		    s2c: {" + 
				"		        spell: \"s2c\"," + 
				"		        name: \"S2C\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"0755-82269110\"," + 
				"		        url: \"http://www.s2c.com.au/\"" + 
				"		    }," + 
				"		    yuntong: {" + 
				"		        spell: \"yuntong\"," + 
				"		        name: \"运通中港快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0769-81156999\"," + 
				"		        url: \"http://www.ytkd168.com/\"" + 
				"		    }," + 
				"		    \"laposte-france\": {" + 
				"		        spell: \"laposte-france\"," + 
				"		        name: \"法国邮政(La Poste)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+33 3631\"," + 
				"		        url: \"http://www.csuivi.courrier.laposte.fr/\"" + 
				"		    }," + 
				"		    \"correos-chile\": {" + 
				"		        spell: \"correos-chile\"," + 
				"		        name: \"智利邮政(Correos Chile)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+562 600 950 2020\"," + 
				"		        url: \"http://www.correos.cl/\"" + 
				"		    }," + 
				"		    shunshid: {" + 
				"		        spell: \"shunshid\"," + 
				"		        name: \"顺士达速运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0319-3292789\"," + 
				"		        url: \"http://www.shunshid.com/\"" + 
				"		    }," + 
				"		    jc56: {" + 
				"		        spell: \"jc56\"," + 
				"		        name: \"锦程国际物流\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"400-020-5556\"," + 
				"		        url: \"http://www.jc56.com/\"" + 
				"		    }," + 
				"		    synship: {" + 
				"		        spell: \"synship\"," + 
				"		        name: \"SYNSHIP快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"13029809802\"," + 
				"		        url: \"http://www.synship.com/\"" + 
				"		    }," + 
				"		    \"posta-romana\": {" + 
				"		        spell: \"posta-romana\"," + 
				"		        name: \"罗马尼亚邮政(Poșta Română)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+40 021 9393 111\"," + 
				"		        url: \"http://www.posta-romana.ro/\"" + 
				"		    }," + 
				"		    sdsy888: {" + 
				"		        spell: \"sdsy888\"," + 
				"		        name: \"首达速运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0769-89951888\"," + 
				"		        url: \"http://www.sdsy888.cn\"" + 
				"		    }," + 
				"		    belpost: {" + 
				"		        spell: \"belpost\"," + 
				"		        name: \"白俄罗斯邮政(Belpost)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+375 17 293 59 10\"," + 
				"		        url: \"http://www.belpost.by/\"" + 
				"		    }," + 
				"		    sxexpress: {" + 
				"		        spell: \"sxexpress\"," + 
				"		        name: \"三象速递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"+61 420319144\"," + 
				"		        url: \"http://www.sxexpress.com.au/ \"" + 
				"		    }," + 
				"		    \"ptt-posta\": {" + 
				"		        spell: \"ptt-posta\"," + 
				"		        name: \"土耳其邮政(PTT Posta)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+90 444 1 788\"," + 
				"		        url: \"http://www.ptt.gov.tr/\"" + 
				"		    }," + 
				"		    shangqiao56: {" + 
				"		        spell: \"shangqiao56\"," + 
				"		        name: \"商桥物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008 518 666\"," + 
				"		        url: \"http://www.shangqiao56.com/\"" + 
				"		    }," + 
				"		    dpex: {" + 
				"		        spell: \"dpex\"," + 
				"		        name: \"DPEX快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"021-64659883\"," + 
				"		        url: \"http://www.szdpex.com.cn/\"" + 
				"		    }," + 
				"		    bgpost: {" + 
				"		        spell: \"bgpost\"," + 
				"		        name: \"保加利亚邮政(Bulgarian Posts)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+3592/949 3280\"," + 
				"		        url: \"http://www.bgpost.bg/\"" + 
				"		    }," + 
				"		    shd56: {" + 
				"		        spell: \"shd56\"," + 
				"		        name: \"商海德物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"021-58350669\"," + 
				"		        url: \"http://www.shd56.com/\"" + 
				"		    }," + 
				"		    yuancheng: {" + 
				"		        spell: \"yuancheng\"," + 
				"		        name: \"远成物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-820-1646\"," + 
				"		        url: \"http://www.ycgwl.com/\"" + 
				"		    }," + 
				"		    \"iceland-post\": {" + 
				"		        spell: \"iceland-post\"," + 
				"		        name: \"冰岛邮政(Iceland Post)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+354 580 1200\"," + 
				"		        url: \"http://www.postur.is/\"" + 
				"		    }," + 
				"		    shenma: {" + 
				"		        spell: \"shenma\"," + 
				"		        name: \"神马快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008-633-929\"," + 
				"		        url: \"http://www.shenma-express.com/\"" + 
				"		    }," + 
				"		    \"lietuvos-pastas\": {" + 
				"		        spell: \"lietuvos-pastas\"," + 
				"		        name: \"立陶宛邮政(Lietuvos Paštas)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+370 8 700 55 400\"," + 
				"		        url: \"http://www.post.lt/\"" + 
				"		    }," + 
				"		    superoz: {" + 
				"		        spell: \"superoz\"," + 
				"		        name: \"速配鸥翼\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"+61 07 31942954\"," + 
				"		        url: \"http://www.superoz.com.au/\"" + 
				"		    }," + 
				"		    \"cyprus-post\": {" + 
				"		        spell: \"cyprus-post\"," + 
				"		        name: \"塞浦路斯邮政(Cyprus Post)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+357 22805802\"," + 
				"		        url: \"http://www.mcw.gov.cy/mcw/postal/dps.nsf/index_en/index_en\"" + 
				"		    }," + 
				"		    zjstky: {" + 
				"		        spell: \"zjstky\"," + 
				"		        name: \"苏通快运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-991-1185\"," + 
				"		        url: \"http://www.zjstky.com/ \"" + 
				"		    }," + 
				"		    \"magyar-posta\": {" + 
				"		        spell: \"magyar-posta\"," + 
				"		        name: \"匈牙利邮政(Magyar Posta)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+36 6 4046 4646\"," + 
				"		        url: \"http://www.posta.hu/\"" + 
				"		    }," + 
				"		    lntjs: {" + 
				"		        spell: \"lntjs\"," + 
				"		        name: \"特急送\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-6688-2222 \"," + 
				"		        url: \"http://tjsexpress.xicp.net/web_index/index.aspx\"" + 
				"		    }," + 
				"		    \"hrvatska-posta\": {" + 
				"		        spell: \"hrvatska-posta\"," + 
				"		        name: \"克罗地亚邮政(Hrvatska Pošta)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+385 0800 303 304\"," + 
				"		        url: \"http://www.posta.hr/\"" + 
				"		    }," + 
				"		    surpassgo: {" + 
				"		        spell: \"surpassgo\"," + 
				"		        name: \"天越物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"18621666966\"," + 
				"		        url: \"http://www.surpass-go.com/\"" + 
				"		    }," + 
				"		    postnord: {" + 
				"		        spell: \"postnord\"," + 
				"		        name: \"瑞典邮政(PostNord)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+46 771 33 33 10\"," + 
				"		        url: \"http://www.postnord.se/\"" + 
				"		    }," + 
				"		    tianxiang: {" + 
				"		        spell: \"tianxiang\"," + 
				"		        name: \"天翔快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-1818-900\"," + 
				"		        url: \"http://www.52tx.com.cn/ \"" + 
				"		    }," + 
				"		    gdyz: {" + 
				"		        spell: \"gdyz\"," + 
				"		        name: \"广东邮政物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"020-38181677\"," + 
				"		        url: \"http://www.ickd.cn\"" + 
				"		    }," + 
				"		    \"macau-post\": {" + 
				"		        spell: \"macau-post\"," + 
				"		        name: \"澳门邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.macaupost.gov.mo/\"" + 
				"		    }," + 
				"		    shpost: {" + 
				"		        spell: \"shpost\"," + 
				"		        name: \"同城快寄\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"(021)63936666\\r\\n\"," + 
				"		        url: \"http://www.shpost.com.cn/\"" + 
				"		    }," + 
				"		    aramex: {" + 
				"		        spell: \"aramex\"," + 
				"		        name: \"Aramex国际快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"+86 (0571) 85092777\"," + 
				"		        url: \"http://www.aramex.com/\"" + 
				"		    }," + 
				"		    \"israel-post\": {" + 
				"		        spell: \"israel-post\"," + 
				"		        name: \"以色列邮政(Israel Post)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+972 2 629 0691\"," + 
				"		        url: \"http://www.israelpost.co.il/\"" + 
				"		    }," + 
				"		    uex: {" + 
				"		        spell: \"uex\"," + 
				"		        name: \"UEX国际物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-807-3330\"," + 
				"		        url: \"http://www.uex.co.jp/\"" + 
				"		    }," + 
				"		    intmail: {" + 
				"		        spell: \"intmail\"," + 
				"		        name: \"国际邮政快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"11183\"," + 
				"		        url: \"http://intmail.183.com.cn\"" + 
				"		    }," + 
				"		    \"correo-argentino\": {" + 
				"		        spell: \"correo-argentino\"," + 
				"		        name: \"阿根廷邮政(Correo Argentino)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+54 11 4891-9191\"," + 
				"		        url: \"http://www.correoargentino.com.ar/\"" + 
				"		    }," + 
				"		    utaoscm: {" + 
				"		        spell: \"utaoscm\"," + 
				"		        name: \"UTAO 优到\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4001-5050-82\"," + 
				"		        url: \"http://www.utao-scm.com/\"" + 
				"		    }," + 
				"		    ytfh: {" + 
				"		        spell: \"ytfh\"," + 
				"		        name: \"北京一统飞鸿快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"010-87308631、674972/15/75\"," + 
				"		        url: \"http://218.97.241.58:8080/yitongfeihongweb/common?action=toindex\"" + 
				"		    }," + 
				"		    \"nigeria-post\": {" + 
				"		        spell: \"nigeria-post\"," + 
				"		        name: \"尼日利亚邮政(NiPost)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+234 09-3149531\"," + 
				"		        url: \"http://www.emsng.com/\"" + 
				"		    }," + 
				"		    wtdchina: {" + 
				"		        spell: \"wtdchina\"," + 
				"		        name: \"威时沛运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"(020)86767799 \"," + 
				"		        url: \"http://www.wtdchina.com/\"" + 
				"		    }," + 
				"		    \"pakistan-post\": {" + 
				"		        spell: \"pakistan-post\"," + 
				"		        name: \"巴基斯坦邮政(Pakistan Post)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+92 51 926 00 37\"," + 
				"		        url: \"http://ep.gov.pk/\"" + 
				"		    }," + 
				"		    gswtkd: {" + 
				"		        spell: \"gswtkd\"," + 
				"		        name: \"万通快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4000061888\"," + 
				"		        url: \"http://www.wtoykd.com\"" + 
				"		    }," + 
				"		    santai: {" + 
				"		        spell: \"santai\"," + 
				"		        name: \"三态速递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-881-8106\"," + 
				"		        url: \"http://www.sfcservice.com/\"" + 
				"		    }," + 
				"		    \"latvijas-pasts\": {" + 
				"		        spell: \"latvijas-pasts\"," + 
				"		        name: \"拉脱维亚邮政(Latvijas Pasts)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+371 67008001\"," + 
				"		        url: \"http://www.pasts.lv/\"" + 
				"		    }," + 
				"		    wotu: {" + 
				"		        spell: \"wotu\"," + 
				"		        name: \"渥途国际速运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"004917656935818\"," + 
				"		        url: \"http://www.wotu.eu \"" + 
				"		    }," + 
				"		    \"q-post\": {" + 
				"		        spell: \"q-post\"," + 
				"		        name: \"卡塔尔邮政(Q Post)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+974 44464000\"," + 
				"		        url: \"http://www.qpost.com.qa/\"" + 
				"		    }," + 
				"		    wandougongzhu: {" + 
				"		        spell: \"wandougongzhu\"," + 
				"		        name: \"豌豆物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4001183939\"," + 
				"		        url: \"http://wandougongzhu.cn\"" + 
				"		    }," + 
				"		    gongsuda: {" + 
				"		        spell: \"gongsuda\"," + 
				"		        name: \"共速达物流|快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-111-0005\"," + 
				"		        url: \"http://www.gongsuda.com\"" + 
				"		    }," + 
				"		    \"pos-indonesia-int\": {" + 
				"		        spell: \"pos-indonesia-int\"," + 
				"		        name: \"印度尼西亚邮政(Pos Indonesia)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+62 21 161\"," + 
				"		        url: \"http://www.posindonesia.co.id/\"" + 
				"		    }," + 
				"		    wdm: {" + 
				"		        spell: \"wdm\"," + 
				"		        name: \"万达美\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"028-61677165\"," + 
				"		        url: \"http://www.wdm-logistics.com/\"" + 
				"		    }," + 
				"		    ees: {" + 
				"		        spell: \"ees\"," + 
				"		        name: \"百福东方物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"010-57169000\"," + 
				"		        url: \"http://www.ees.com.cn\"" + 
				"		    }," + 
				"		    \"afghan-post\": {" + 
				"		        spell: \"afghan-post\"," + 
				"		        name: \"阿富汗邮政(Afghan Post)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+93 20 2104075\"," + 
				"		        url: \"http://track.afghanpost.gov.af/\"" + 
				"		    }," + 
				"		    wto56kj: {" + 
				"		        spell: \"wto56kj\"," + 
				"		        name: \"温通物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0577-86165656 \"," + 
				"		        url: \"http://www.wto56kj.com\"" + 
				"		    }," + 
				"		    scs: {" + 
				"		        spell: \"scs\"," + 
				"		        name: \"伟邦(SCS)快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"021-64212098/62629520\"," + 
				"		        url: \"http://www.scsexpress.com/?char_set=gb/\"" + 
				"		    }," + 
				"		    \"nova-poshta\": {" + 
				"		        spell: \"nova-poshta\"," + 
				"		        name: \"Nova Poshta\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+7 (0) 800 500 609\"," + 
				"		        url: \"http://novaposhta.ua/\"" + 
				"		    }," + 
				"		    sunspeedy: {" + 
				"		        spell: \"sunspeedy\"," + 
				"		        name: \"新速航\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"852-64797448\"," + 
				"		        url: \"http://www.sunspeedy.hk \"" + 
				"		    }," + 
				"		    \"liban-post\": {" + 
				"		        spell: \"liban-post\"," + 
				"		        name: \"黎巴嫩邮政(Liban Post)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+961 1 629628\"," + 
				"		        url: \"https://www.libanpost.com/\"" + 
				"		    }," + 
				"		    sunjex: {" + 
				"		        spell: \"sunjex\"," + 
				"		        name: \"新杰物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-620-2828\"," + 
				"		        url: \"http://www.sunjex.com/\"" + 
				"		    }," + 
				"		    \"post-serbia\": {" + 
				"		        spell: \"post-serbia\"," + 
				"		        name: \"塞尔维亚邮政(POŠTA SRBIJE)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+381 700 100 300\"," + 
				"		        url: \"http://www.posta.rs/\"" + 
				"		    }," + 
				"		    westwing: {" + 
				"		        spell: \"westwing\"," + 
				"		        name: \"西翼物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"+1 909-321-5963\"," + 
				"		        url: \"http://www.westwingexpress.com/\"" + 
				"		    }," + 
				"		    pinganda: {" + 
				"		        spell: \"pinganda\"," + 
				"		        name: \"平安达\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400 999 0998\"," + 
				"		        url: \"http://www.padtf.com/\"" + 
				"		    }," + 
				"		    \"iran-post\": {" + 
				"		        spell: \"iran-post\"," + 
				"		        name: \"伊朗邮政(Iran Post)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+98 88532387\"," + 
				"		        url: \"http://www.post.ir/\"" + 
				"		    }," + 
				"		    littlebearbear: {" + 
				"		        spell: \"littlebearbear\"," + 
				"		        name: \"小熊物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"\"," + 
				"		        url: \"\"" + 
				"		    }," + 
				"		    yad: {" + 
				"		        spell: \"yad\"," + 
				"		        name: \"源安达快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0769-85021875\"," + 
				"		        url: \"http://www.yadex.com.cn/\"" + 
				"		    }," + 
				"		    \"slovenska-posta\": {" + 
				"		        spell: \"slovenska-posta\"," + 
				"		        name: \"斯洛伐克邮政(Slovenská pošta)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+421 48 437 87 77\"," + 
				"		        url: \"http://www.posta.sk/\"" + 
				"		    }," + 
				"		    wlwex: {" + 
				"		        spell: \"wlwex\"," + 
				"		        name: \"星空国际\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"021-66115507\"," + 
				"		        url: \"http://www.wlwex.com/\"" + 
				"		    }," + 
				"		    disifang: {" + 
				"		        spell: \"disifang\"," + 
				"		        name: \"递四方速递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0755-33933895\"," + 
				"		        url: \"http://www.4px.com/\"" + 
				"		    }," + 
				"		    tianfeng: {" + 
				"		        spell: \"tianfeng\"," + 
				"		        name: \"天峰国际物流\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.tflcn.com/\"" + 
				"		    }," + 
				"		    yingchao: {" + 
				"		        spell: \"yingchao\"," + 
				"		        name: \"英超物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"（+44）01213680088 \"," + 
				"		        url: \"http://www.51parcel.com/\"" + 
				"		    }," + 
				"		    yinjie: {" + 
				"		        spell: \"yinjie\"," + 
				"		        name: \"顺捷丰达快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0755-89808666/88250666\"," + 
				"		        url: \"http://www.sjfd-express.com/\"" + 
				"		    }," + 
				"		    \"one-world\": {" + 
				"		        spell: \"one-world\"," + 
				"		        name: \"One World 物流\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+86-755-8653 6663\"," + 
				"		        url: \"http://www.oneworldexpress.cn/\"" + 
				"		    }," + 
				"		    edlogistics: {" + 
				"		        spell: \"edlogistics\"," + 
				"		        name: \"益递物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"021-64050106\"," + 
				"		        url: \"http://www.ed-logistics.net/\"" + 
				"		    }," + 
				"		    yunexpress: {" + 
				"		        spell: \"yunexpress\"," + 
				"		        name: \"云途物流\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+86-400-0262-126\"," + 
				"		        url: \"http://www.yunexpress.com/\"" + 
				"		    }," + 
				"		    \"1hcang\": {" + 
				"		        spell: \"1hcang\"," + 
				"		        name: \"一号仓\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0755-89391959，0755-893913\"," + 
				"		        url: \"http://www.1hcang.com\"" + 
				"		    }," + 
				"		    jldt: {" + 
				"		        spell: \"jldt\"," + 
				"		        name: \"嘉里大通物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"800-810-3188/400-610-3188\"," + 
				"		        url: \"http://www.kerryeas.com\"" + 
				"		    }," + 
				"		    \"bangladesh-post\": {" + 
				"		        spell: \"bangladesh-post\"," + 
				"		        name: \"孟加拉国邮政(Bangladesh Post)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+880-9558006\"," + 
				"		        url: \"http://www.bangladeshpost.gov.bd/\"" + 
				"		    }," + 
				"		    lineone: {" + 
				"		        spell: \"lineone\"," + 
				"		        name: \"一号线\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"08 6113 7357 \"," + 
				"		        url: \"http://www.line-1.com.au/\"" + 
				"		    }," + 
				"		    \"uk-mail\": {" + 
				"		        spell: \"uk-mail\"," + 
				"		        name: \"UK MAIL\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+44 8451 554 455\"," + 
				"		        url: \"https://www.ukmail.com/\"" + 
				"		    }," + 
				"		    skynet: {" + 
				"		        spell: \"skynet\"," + 
				"		        name: \"Sky Net\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+60 3- 56239090\"," + 
				"		        url: \"http://www.skynet.com.my/\"" + 
				"		    }," + 
				"		    yyqc56: {" + 
				"		        spell: \"yyqc56\"," + 
				"		        name: \"一运全成物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-635-5566 \"," + 
				"		        url: \"http://www.yyqc56.com \"" + 
				"		    }," + 
				"		    coe: {" + 
				"		        spell: \"coe\"," + 
				"		        name: \"东方快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0755-83575000\"," + 
				"		        url: \"http://www.coe.com.hk\"" + 
				"		    }," + 
				"		    colissimo: {" + 
				"		        spell: \"colissimo\"," + 
				"		        name: \"法国Colissimo\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+33 3631\"," + 
				"		        url: \"http://www.colissimo.fr/\"" + 
				"		    }," + 
				"		    iyoungspeed: {" + 
				"		        spell: \"iyoungspeed\"," + 
				"		        name: \"驿扬国际速运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"+4961057178778 \"," + 
				"		        url: \"http://iyoungspeed.com \"" + 
				"		    }," + 
				"		    phlpost: {" + 
				"		        spell: \"phlpost\"," + 
				"		        name: \"菲律宾邮政（Philippine Post）\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"https://www.phlpost.gov.ph/\"" + 
				"		    }," + 
				"		    zgyzt: {" + 
				"		        spell: \"zgyzt\"," + 
				"		        name: \"一站通快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4000-181818\"," + 
				"		        url: \"http://www.zgyzt.com\"" + 
				"		    }," + 
				"		    \"egypt-post\": {" + 
				"		        spell: \"egypt-post\"," + 
				"		        name: \"埃及邮政(Egypt Post)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+20 16789\"," + 
				"		        url: \"http://www.egyptpost.org\"" + 
				"		    }," + 
				"		    eupackage: {" + 
				"		        spell: \"eupackage\"," + 
				"		        name: \"易优包裹\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"+49-6196-9549480\"," + 
				"		        url: \"http://www.eupackage.com\"" + 
				"		    }," + 
				"		    chuanxi: {" + 
				"		        spell: \"chuanxi\"," + 
				"		        name: \"传喜快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-777-5656\"," + 
				"		        url: \"http://www.cxcod.com/\"" + 
				"		    }," + 
				"		    \"fastway-nz\": {" + 
				"		        spell: \"fastway-nz\"," + 
				"		        name: \"新西兰Fast Way 快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+64 (09) 634 3704\"," + 
				"		        url: \"http://www.fastway.co.nz/\"" + 
				"		    }," + 
				"		    yangbaoguo: {" + 
				"		        spell: \"yangbaoguo\"," + 
				"		        name: \"洋包裹\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-004-6616\"," + 
				"		        url: \"http://www.yangbaoguo.com/ \"" + 
				"		    }," + 
				"		    \"fastway-au\": {" + 
				"		        spell: \"fastway-au\"," + 
				"		        name: \"澳大利亚Fast Way 快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.fastway.com.au/\"" + 
				"		    }," + 
				"		    uluckex: {" + 
				"		        spell: \"uluckex\"," + 
				"		        name: \"优联吉运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"010-58496570\"," + 
				"		        url: \"http://www.uluckex.com/ \"" + 
				"		    }," + 
				"		    feibao: {" + 
				"		        spell: \"feibao\"," + 
				"		        name: \"飞豹快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"025-52433090\"," + 
				"		        url: \"http://www.ztky.com/\"" + 
				"		    }," + 
				"		    courierpost: {" + 
				"		        spell: \"courierpost\"," + 
				"		        name: \"新西兰CourierPost快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+64 9 367 9710\"," + 
				"		        url: \"http://www.courierpost.co.nz/\"" + 
				"		    }," + 
				"		    ubonex: {" + 
				"		        spell: \"ubonex\"," + 
				"		        name: \"优邦速运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-830-6050\"," + 
				"		        url: \"http://www.ubonex.com/f/list-2.html\"" + 
				"		    }," + 
				"		    \"fastway-ie\": {" + 
				"		        spell: \"fastway-ie\"," + 
				"		        name: \"爱尔兰Fastway快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+353 1 4242 900\"," + 
				"		        url: \"http://www.fastway.ie/\"" + 
				"		    }," + 
				"		    yue777: {" + 
				"		        spell: \"yue777\"," + 
				"		        name: \"玥玛速运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"(831)337 4444\"," + 
				"		        url: \"http://www.yue777.com/\"" + 
				"		    }," + 
				"		    jingguang: {" + 
				"		        spell: \"jingguang\"," + 
				"		        name: \"京广快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400 882 3878\"," + 
				"		        url: \"http://www.szkke.com\"" + 
				"		    }," + 
				"		    \"austrian-post\": {" + 
				"		        spell: \"austrian-post\"," + 
				"		        name: \"奥地利邮政(Austrian Post)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+43 810 010 100\"," + 
				"		        url: \"http://www.post.at/\"" + 
				"		    }," + 
				"		    yongbangwuliu: {" + 
				"		        spell: \"yongbangwuliu\"," + 
				"		        name: \"永邦国际物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0638599377\"," + 
				"		        url: \"http://www.yongbangwuliu.com/\"" + 
				"		    }," + 
				"		    feiyuan: {" + 
				"		        spell: \"feiyuan\"," + 
				"		        name: \"飞远物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400 703 1313\"," + 
				"		        url: \"http://www.fyps.cn\"" + 
				"		    }," + 
				"		    yyox: {" + 
				"		        spell: \"yyox\"," + 
				"		        name: \"邮客全球速递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008636658\"," + 
				"		        url: \"http://www.yyox.com/\"" + 
				"		    }," + 
				"		    cszx: {" + 
				"		        spell: \"cszx\"," + 
				"		        name: \"城市之星\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-1188-666\"," + 
				"		        url: \"http://www.soclgz.com\"" + 
				"		    }," + 
				"		    qexpress: {" + 
				"		        spell: \"qexpress\"," + 
				"		        name: \"易达通快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"0064 9 838 8681\"," + 
				"		        url: \"http://www.qexpress.co.nz\"" + 
				"		    }," + 
				"		    youyou: {" + 
				"		        spell: \"youyou\"," + 
				"		        name: \"优优速递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-0167687\"," + 
				"		        url: \"http://www.2uex.com\"" + 
				"		    }," + 
				"		    cfe: {" + 
				"		        spell: \"cfe\"," + 
				"		        name: \"CFE 超峰快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"0800-658-999\"," + 
				"		        url: \"http://www.express.com.tw/\"" + 
				"		    }," + 
				"		    euguoji: {" + 
				"		        spell: \"euguoji\"," + 
				"		        name: \"易邮国际\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0881-888-999\"," + 
				"		        url: \"http://www.eu-ex.com/ \"" + 
				"		    }," + 
				"		    yisu: {" + 
				"		        spell: \"yisu\"," + 
				"		        name: \"翼速物流\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"021-64650010\"," + 
				"		        url: \"http://www.zce.net.cn/\"" + 
				"		    }," + 
				"		    uscbexpress: {" + 
				"		        spell: \"uscbexpress\"," + 
				"		        name: \"易境达国际物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"020-22091198\"," + 
				"		        url: \"http://www.uscbexpress.com/ \"" + 
				"		    }," + 
				"		    yfsuyun: {" + 
				"		        spell: \"yfsuyun\"," + 
				"		        name: \"驭丰速运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"95105353\"," + 
				"		        url: \"http://www.yfsuyun.com/\"" + 
				"		    }," + 
				"		    suchi: {" + 
				"		        spell: \"suchi\"," + 
				"		        name: \"苏驰物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400 025 9666\"," + 
				"		        url: \"http://www.sc56.cc/\"" + 
				"		    }," + 
				"		    youban: {" + 
				"		        spell: \"youban\"," + 
				"		        name: \"邮邦国际\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"02203-8095414\"," + 
				"		        url: \"http://www.youban.de/\"" + 
				"		    }," + 
				"		    shunda: {" + 
				"		        spell: \"shunda\"," + 
				"		        name: \"顺达快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"718-886-5508\"," + 
				"		        url: \"http://www.sd-ex.com/\"" + 
				"		    }," + 
				"		    zhichengtongda: {" + 
				"		        spell: \"zhichengtongda\"," + 
				"		        name: \"至诚通达快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-151-8918 \"," + 
				"		        url: \"http://www.zctdky.com/ \"" + 
				"		    }," + 
				"		    rpx: {" + 
				"		        spell: \"rpx\"," + 
				"		        name: \"RPX保时达\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.rpx.com.cn/\"" + 
				"		    }," + 
				"		    arkexpress: {" + 
				"		        spell: \"arkexpress\"," + 
				"		        name: \"方舟快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+61 3 9008 9668\"," + 
				"		        url: \"http://www.arkexpress.com.au/\"" + 
				"		    }," + 
				"		    esdex: {" + 
				"		        spell: \"esdex\"," + 
				"		        name: \"卓志速运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"020-62262811\"," + 
				"		        url: \"http://www.esdex.com/\"" + 
				"		    }," + 
				"		    changjiang: {" + 
				"		        spell: \"changjiang\"," + 
				"		        name: \"长江国际速递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"(03)8838 2723\"," + 
				"		        url: \"http://www.changjiangexpress.com/\"" + 
				"		    }," + 
				"		    \"365zf\": {" + 
				"		        spell: \"365zf\"," + 
				"		        name: \"珠峰速运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4000-99-8848\"," + 
				"		        url: \"http://www.365-zf.com/\"" + 
				"		    }," + 
				"		    citylink: {" + 
				"		        spell: \"citylink\"," + 
				"		        name: \"CityLinkExpress\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.citylinkexpress.com\"" + 
				"		    }," + 
				"		    fox: {" + 
				"		        spell: \"fox\"," + 
				"		        name: \"FOX快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+86 21 6167 2118\"," + 
				"		        url: \"http://www.foxglobal.nl/foxcn/\"" + 
				"		    }," + 
				"		    auvanda: {" + 
				"		        spell: \"auvanda\"," + 
				"		        name: \"中联速递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"61892775686\"," + 
				"		        url: \"http://www.auvanda.com/ \"" + 
				"		    }," + 
				"		    chengshi100: {" + 
				"		        spell: \"chengshi100\"," + 
				"		        name: \"城市100\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"010-52932760/1/2/3\"," + 
				"		        url: \"http://www.bjcs100.com/\"" + 
				"		    }," + 
				"		    yixiang: {" + 
				"		        spell: \"yixiang\"," + 
				"		        name: \"亿翔快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4006 123 878\"," + 
				"		        url: \"http://www.yx-express.cn/\"" + 
				"		    }," + 
				"		    lijisong: {" + 
				"		        spell: \"lijisong\"," + 
				"		        name: \"成都立即送快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-028-5666\"," + 
				"		        url: \"http://www.cdljs.com/\"" + 
				"		    }," + 
				"		    feiying: {" + 
				"		        spell: \"feiying\"," + 
				"		        name: \"飞鹰物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-6291-666\"," + 
				"		        url: \"http://www.hnfy56.com/\"" + 
				"		    }," + 
				"		    balunzhi: {" + 
				"		        spell: \"balunzhi\"," + 
				"		        name: \"巴伦支\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-636-1516\"," + 
				"		        url: \"http://cnbd.hendari.com/\"" + 
				"		    }," + 
				"		    \"post-luxembourg\": {" + 
				"		        spell: \"post-luxembourg\"," + 
				"		        name: \"卢森堡邮政(Post Luxembourg)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"https://www.post.lu/\"" + 
				"		    }," + 
				"		    dayang: {" + 
				"		        spell: \"dayang\"," + 
				"		        name: \"大洋物流快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-820-0088\"," + 
				"		        url: \"http://www.dayang365.cn/\"" + 
				"		    }," + 
				"		    maltapost: {" + 
				"		        spell: \"maltapost\"," + 
				"		        name: \"马耳他邮政(Malta Post)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.maltapost.com/\"" + 
				"		    }," + 
				"		    \"correo-uruguayo\": {" + 
				"		        spell: \"correo-uruguayo\"," + 
				"		        name: \"乌拉圭邮政(Correo Uruguayo)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.correo.com.uy/\"" + 
				"		    }," + 
				"		    poslaju: {" + 
				"		        spell: \"poslaju\"," + 
				"		        name: \"Pos Laju\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.poslaju.com.my/\"" + 
				"		    }," + 
				"		    \"tanzania-post\": {" + 
				"		        spell: \"tanzania-post\"," + 
				"		        name: \"坦桑尼亚邮政(TANZANIA POSTA)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.posta.co.tz/\"" + 
				"		    }," + 
				"		    fanyu: {" + 
				"		        spell: \"fanyu\"," + 
				"		        name: \"凡宇快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4006-580-358\"," + 
				"		        url: \"http://www.fanyu56.com.cn/\"" + 
				"		    }," + 
				"		    \"bermuda-post\": {" + 
				"		        spell: \"bermuda-post\"," + 
				"		        name: \"百慕大邮政(Bermuda post)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"441.297.7802\"," + 
				"		        url: \"http://www.bpo.bm/\"" + 
				"		    }," + 
				"		    haosheng: {" + 
				"		        spell: \"haosheng\"," + 
				"		        name: \"昊盛物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-186-5566\"," + 
				"		        url: \"\"" + 
				"		    }," + 
				"		    \"peru-post\": {" + 
				"		        spell: \"peru-post\"," + 
				"		        name: \"秘鲁邮政(Peru Post)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.serpost.com.pe/\"" + 
				"		    }," + 
				"		    \"venezuela-post\": {" + 
				"		        spell: \"venezuela-post\"," + 
				"		        name: \"委内瑞拉邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"（0800）476-78-35\"," + 
				"		        url: \"http://www.ipostel.gob.ve/\"" + 
				"		    }," + 
				"		    hebeijianhua: {" + 
				"		        spell: \"hebeijianhua\"," + 
				"		        name: \"河北建华快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0311-86123186\"," + 
				"		        url: \"http://116.255.133.172/hebeiwebsite/index.jsp\"" + 
				"		    }," + 
				"		    \"colombia-post\": {" + 
				"		        spell: \"colombia-post\"," + 
				"		        name: \"哥伦比亚邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"(57-1) 4722000\"," + 
				"		        url: \"http://www.4-72.com.co/\"" + 
				"		    }," + 
				"		    \"correos-mexico\": {" + 
				"		        spell: \"correos-mexico\"," + 
				"		        name: \"墨西哥邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"(55) 5340 3300\"," + 
				"		        url: \"http://www.correosdemexico.gob.mx/\"" + 
				"		    }," + 
				"		    jixianda: {" + 
				"		        spell: \"jixianda\"," + 
				"		        name: \"急先达物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-694-1256\"," + 
				"		        url: \"http://www.joust.net.cn/\"" + 
				"		    }," + 
				"		    \"albania-post\": {" + 
				"		        spell: \"albania-post\"," + 
				"		        name: \"阿尔巴尼亚邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.en.postashqiptare.al/\"" + 
				"		    }," + 
				"		    \"antilles-post\": {" + 
				"		        spell: \"antilles-post\"," + 
				"		        name: \"荷属安的列斯邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.cpostinternational.com/\"" + 
				"		    }," + 
				"		    suijia: {" + 
				"		        spell: \"suijia\"," + 
				"		        name: \"穗佳物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-880-9771\"," + 
				"		        url: \"http://www.suijiawl.com\"" + 
				"		    }," + 
				"		    \"armenia-post\": {" + 
				"		        spell: \"armenia-post\"," + 
				"		        name: \"亚美尼亚邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"https://www.haypost.am/\"" + 
				"		    }," + 
				"		    shengan: {" + 
				"		        spell: \"shengan\"," + 
				"		        name: \"圣安物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4006-618-169\"," + 
				"		        url: \"http://www.sa56.net\"" + 
				"		    }," + 
				"		    \"ecuador-post\": {" + 
				"		        spell: \"ecuador-post\"," + 
				"		        name: \"厄瓜多尔邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.correosdelecuador.gob.ec/\"" + 
				"		    }," + 
				"		    \"rwanda-post\": {" + 
				"		        spell: \"rwanda-post\"," + 
				"		        name: \"卢旺达邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://i-posita.rw/\"" + 
				"		    }," + 
				"		    \"aruba-post\": {" + 
				"		        spell: \"aruba-post\"," + 
				"		        name: \"阿鲁巴邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.postaruba.com/\"" + 
				"		    }," + 
				"		    weitepai: {" + 
				"		        spell: \"weitepai\"," + 
				"		        name: \"微特派\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400 6363 000\"," + 
				"		        url: \"http://www.vtepai.com/\"" + 
				"		    }," + 
				"		    \"el-salvador-post\": {" + 
				"		        spell: \"el-salvador-post\"," + 
				"		        name: \"萨尔瓦多邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.correos.gob.sv/\"" + 
				"		    }," + 
				"		    \"senegal-post\": {" + 
				"		        spell: \"senegal-post\"," + 
				"		        name: \"塞内加尔邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.laposte.sn/\"" + 
				"		    }," + 
				"		    \"macedonia-post\": {" + 
				"		        spell: \"macedonia-post\"," + 
				"		        name: \"马其顿邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.mp.com.mk/\"" + 
				"		    }," + 
				"		    \"ethiopia-post\": {" + 
				"		        spell: \"ethiopia-post\"," + 
				"		        name: \"埃塞俄比亚邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.ethiopostal.com/\"" + 
				"		    }," + 
				"		    fardar: {" + 
				"		        spell: \"fardar\"," + 
				"		        name: \"Fardar\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0755-27332618\"," + 
				"		        url: \"http://www.fardar.com/\"" + 
				"		    }," + 
				"		    \"madagascar-post\": {" + 
				"		        spell: \"madagascar-post\"," + 
				"		        name: \"马达加斯加邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.mtpc.gov.mg/\"" + 
				"		    }," + 
				"		    henglu: {" + 
				"		        spell: \"henglu\"," + 
				"		        name: \"恒路物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-182-6666\"," + 
				"		        url: \"http://www.e-henglu.com\"" + 
				"		    }," + 
				"		    hwhq: {" + 
				"		        spell: \"hwhq\"," + 
				"		        name: \"海外环球快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"010-59790107\"," + 
				"		        url: \"http://www.haiwaihuanqiu.com/\"" + 
				"		    }," + 
				"		    \"azerbaijan-post\": {" + 
				"		        spell: \"azerbaijan-post\"," + 
				"		        name: \"阿塞拜疆邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.azerpost.az/\"" + 
				"		    }," + 
				"		    jinda: {" + 
				"		        spell: \"jinda\"," + 
				"		        name: \"金大物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0755-82262209\"," + 
				"		        url: \"http://www.szkingdom.com.cn\"" + 
				"		    }," + 
				"		    \"maldives-post\": {" + 
				"		        spell: \"maldives-post\"," + 
				"		        name: \"马尔代夫邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"https://www.maldivespost.com/\"" + 
				"		    }," + 
				"		    \"slovenia-post\": {" + 
				"		        spell: \"slovenia-post\"," + 
				"		        name: \"斯洛文尼亚邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.posta.si/\"" + 
				"		    }," + 
				"		    kuayue: {" + 
				"		        spell: \"kuayue\"," + 
				"		        name: \"跨越速运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008-098-098\"," + 
				"		        url: \"http://www.ky-express.com/\"" + 
				"		    }," + 
				"		    \"bahamas-post\": {" + 
				"		        spell: \"bahamas-post\"," + 
				"		        name: \"巴哈马邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.bahamas.gov.bs\"" + 
				"		    }," + 
				"		    kcs: {" + 
				"		        spell: \"kcs\"," + 
				"		        name: \"顺鑫(KCS)快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"800-858-5590\"," + 
				"		        url: \"http://www.kcs56.com\"" + 
				"		    }," + 
				"		    \"mali-post\": {" + 
				"		        spell: \"mali-post\"," + 
				"		        name: \"马里邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.laposte.ml/\"" + 
				"		    }," + 
				"		    mingliang: {" + 
				"		        spell: \"mingliang\"," + 
				"		        name: \"明亮物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-035-6568\"," + 
				"		        url: \"http://www.szml56.com/\"" + 
				"		    }," + 
				"		    \"solomon-islands-post\": {" + 
				"		        spell: \"solomon-islands-post\"," + 
				"		        name: \"所罗门群岛邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.solomonpost.com.sb\"" + 
				"		    }," + 
				"		    minbang: {" + 
				"		        spell: \"minbang\"," + 
				"		        name: \"民邦快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0769-81515303\"," + 
				"		        url: \"http://www.mbex168.com/\"" + 
				"		    }," + 
				"		    \"barbados-post\": {" + 
				"		        spell: \"barbados-post\"," + 
				"		        name: \"巴巴多斯邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://barbadospostal.com/\"" + 
				"		    }," + 
				"		    minsheng: {" + 
				"		        spell: \"minsheng\"," + 
				"		        name: \"闽盛快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0592-3725988\"," + 
				"		        url: \"http://www.xmms-express.com/\"" + 
				"		    }," + 
				"		    \"gabon-post\": {" + 
				"		        spell: \"gabon-post\"," + 
				"		        name: \"加蓬邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.lapostedugabon.org/\"" + 
				"		    }," + 
				"		    \"georgia-post\": {" + 
				"		        spell: \"georgia-post\"," + 
				"		        name: \"格鲁吉亚邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.georgianpost.ge\"" + 
				"		    }," + 
				"		    \"mauritania-post\": {" + 
				"		        spell: \"mauritania-post\"," + 
				"		        name: \"毛里塔尼亚邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.mauripost.mr\"" + 
				"		    }," + 
				"		    xianglong: {" + 
				"		        spell: \"xianglong\"," + 
				"		        name: \"祥龙运通快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008 908 908\"," + 
				"		        url: \"http://www.ldl.com.cn\"" + 
				"		    }," + 
				"		    \"mauritius-post\": {" + 
				"		        spell: \"mauritius-post\"," + 
				"		        name: \"毛里求斯邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.mauritiuspost.mu\"" + 
				"		    }," + 
				"		    yishunhang: {" + 
				"		        spell: \"yishunhang\"," + 
				"		        name: \"亿顺航快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4006-018-268\"," + 
				"		        url: \"http://www.igoex.com/\"" + 
				"		    }," + 
				"		    \"benin-post\": {" + 
				"		        spell: \"benin-post\"," + 
				"		        name: \"贝宁邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.laposte.bj\"" + 
				"		    }," + 
				"		    benteng: {" + 
				"		        spell: \"benteng\"," + 
				"		        name: \"成都奔腾国际快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"028-66700599\"," + 
				"		        url: \"http://www.benteng.org\"" + 
				"		    }," + 
				"		    \"ghana-post\": {" + 
				"		        spell: \"ghana-post\"," + 
				"		        name: \"加纳邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+ 030 2- 668138\"," + 
				"		        url: \"http://www.ghanapost.com.gh/\"" + 
				"		    }," + 
				"		    \"moldova-post\": {" + 
				"		        spell: \"moldova-post\"," + 
				"		        name: \"摩尔多瓦邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.posta.md/\"" + 
				"		    }," + 
				"		    zhengzhoujianhua: {" + 
				"		        spell: \"zhengzhoujianhua\"," + 
				"		        name: \"郑州建华快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0371-86115566/5588\"," + 
				"		        url: \"http://www.zzjhtd.com/\"" + 
				"		    }," + 
				"		    \"bhutan-post\": {" + 
				"		        spell: \"bhutan-post\"," + 
				"		        name: \"不丹邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.bhutanpost.bt/\"" + 
				"		    }," + 
				"		    \"mongolia-post\": {" + 
				"		        spell: \"mongolia-post\"," + 
				"		        name: \"蒙古邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.mongolpost.mn/\"" + 
				"		    }," + 
				"		    feite: {" + 
				"		        spell: \"feite\"," + 
				"		        name: \"飞特物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400 888 4003\"," + 
				"		        url: \"http://www.flytexpress.com/\"" + 
				"		    }," + 
				"		    \"bosnia-herzegovina-post\": {" + 
				"		        spell: \"bosnia-herzegovina-post\"," + 
				"		        name: \"波黑邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.posta.ba/\"" + 
				"		    }," + 
				"		    huahan: {" + 
				"		        spell: \"huahan\"," + 
				"		        name: \"华翰物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4000-156-080\"," + 
				"		        url: \"http://www.hh-exp.com/\"" + 
				"		    }," + 
				"		    \"guyana-post\": {" + 
				"		        spell: \"guyana-post\"," + 
				"		        name: \"圭亚那邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://guypost.gy/\"" + 
				"		    }," + 
				"		    baotongda: {" + 
				"		        spell: \"baotongda\"," + 
				"		        name: \"宝通达\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"86-0755-83261581\"," + 
				"		        url: \"http://www.btdair.com/\"" + 
				"		    }," + 
				"		    \"montenegro-post\": {" + 
				"		        spell: \"montenegro-post\"," + 
				"		        name: \"黑山邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.postacg.me/\"" + 
				"		    }," + 
				"		    chukouyi: {" + 
				"		        spell: \"chukouyi\"," + 
				"		        name: \"出口易物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4006 988 223\"," + 
				"		        url: \"http://www.chukou1.com/\"" + 
				"		    }," + 
				"		    \"botswana-post\": {" + 
				"		        spell: \"botswana-post\"," + 
				"		        name: \"博茨瓦纳邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.botspost.co.bw\"" + 
				"		    }," + 
				"		    \"mozambique-post\": {" + 
				"		        spell: \"mozambique-post\"," + 
				"		        name: \"莫桑比克邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.correios.co.mz\"" + 
				"		    }," + 
				"		    yumeijie: {" + 
				"		        spell: \"yumeijie\"," + 
				"		        name: \"誉美捷快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0351-5668811\"," + 
				"		        url: \"http://www.5668811.cn/\"" + 
				"		    }," + 
				"		    \"myanmar-post\": {" + 
				"		        spell: \"myanmar-post\"," + 
				"		        name: \"缅甸邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.myanmaposts.net.mm/\"" + 
				"		    }," + 
				"		    \"uganda-post\": {" + 
				"		        spell: \"uganda-post\"," + 
				"		        name: \"乌干达邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.ugapost.co.ug/\"" + 
				"		    }," + 
				"		    \"nepal-post\": {" + 
				"		        spell: \"nepal-post\"," + 
				"		        name: \"尼泊尔邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.nepalpost.gov.np/\"" + 
				"		    }," + 
				"		    \"cayman-islands-post\": {" + 
				"		        spell: \"cayman-islands-post\"," + 
				"		        name: \"开曼群岛邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.caymanpost.gov.ky/\"" + 
				"		    }," + 
				"		    nanbei: {" + 
				"		        spell: \"nanbei\"," + 
				"		        name: \"南北快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400 007 3337\"," + 
				"		        url: \"http://www.nbky.com.cn/\"" + 
				"		    }," + 
				"		    \"uzbekistan-post\": {" + 
				"		        spell: \"uzbekistan-post\"," + 
				"		        name: \"乌兹别克斯坦邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.pochta.uz/\"" + 
				"		    }," + 
				"		    wanbo: {" + 
				"		        spell: \"wanbo\"," + 
				"		        name: \"万博快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0311-83035553\"," + 
				"		        url: \"http://www.wanboex.cn/\"" + 
				"		    }," + 
				"		    \"ivory-coast-post\": {" + 
				"		        spell: \"ivory-coast-post\"," + 
				"		        name: \"科特迪瓦邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.laposte.ci\"" + 
				"		    }," + 
				"		    \"papua-new-guinea-post\": {" + 
				"		        spell: \"papua-new-guinea-post\"," + 
				"		        name: \"巴布亚新几内亚邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.postpng.com.pg/\"" + 
				"		    }," + 
				"		    \"vanuatu-post\": {" + 
				"		        spell: \"vanuatu-post\"," + 
				"		        name: \"瓦努阿图邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.vanuatupost.vu/\"" + 
				"		    }," + 
				"		    suchengzhaipei: {" + 
				"		        spell: \"suchengzhaipei\"," + 
				"		        name: \"速呈宅配\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0991-3656861\"," + 
				"		        url: \"http://www.sczpds.com/\"" + 
				"		    }," + 
				"		    \"jamaica-post\": {" + 
				"		        spell: \"jamaica-post\"," + 
				"		        name: \"牙买加邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://jamaicapost.gov.jm/\"" + 
				"		    }," + 
				"		    \"paraguay-post\": {" + 
				"		        spell: \"paraguay-post\"," + 
				"		        name: \"巴拉圭邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.correoparaguayo.gov.py\"" + 
				"		    }," + 
				"		    shengbang: {" + 
				"		        spell: \"shengbang\"," + 
				"		        name: \"晟邦物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4006-666-066\"," + 
				"		        url: \"http://www.3856.cc/\"" + 
				"		    }," + 
				"		    \"costa-rica-post\": {" + 
				"		        spell: \"costa-rica-post\"," + 
				"		        name: \"哥斯达黎加邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"https://www.correos.go.cr/\"" + 
				"		    }," + 
				"		    baiqian: {" + 
				"		        spell: \"baiqian\"," + 
				"		        name: \"百千诚国际物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0755-2222 2232\"," + 
				"		        url: \"http://www.1001000.com\"" + 
				"		    }," + 
				"		    \"jordan-post\": {" + 
				"		        spell: \"jordan-post\"," + 
				"		        name: \"约旦邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.jordanpost.com.jo/\"" + 
				"		    }," + 
				"		    \"zambia-post\": {" + 
				"		        spell: \"zambia-post\"," + 
				"		        name: \"赞比亚邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.zampost.com.zm\"" + 
				"		    }," + 
				"		    \"cuba-post\": {" + 
				"		        spell: \"cuba-post\"," + 
				"		        name: \"古巴邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://cubapostal.com\"" + 
				"		    }," + 
				"		    guanda: {" + 
				"		        spell: \"guanda\"," + 
				"		        name: \"冠达快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-990-0088\"," + 
				"		        url: \"http://www.gda-e.com.cn/\"" + 
				"		    }," + 
				"		    \"kazakhstan-post\": {" + 
				"		        spell: \"kazakhstan-post\"," + 
				"		        name: \"哈萨克斯坦邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.kazpost.kz\"" + 
				"		    }," + 
				"		    haolaiyun: {" + 
				"		        spell: \"haolaiyun\"," + 
				"		        name: \"好来运快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"020-86293333\"," + 
				"		        url: \"http://www.hlyex.com/\"" + 
				"		    }," + 
				"		    \"zimbabwe-post\": {" + 
				"		        spell: \"zimbabwe-post\"," + 
				"		        name: \"津巴布韦邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.zimpost.co.zw\"" + 
				"		    }," + 
				"		    hutong: {" + 
				"		        spell: \"hutong\"," + 
				"		        name: \"户通物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-060-1656\"," + 
				"		        url: \"http://www.cnhtwl.com/\"" + 
				"		    }," + 
				"		    \"kenya-post\": {" + 
				"		        spell: \"kenya-post\"," + 
				"		        name: \"肯尼亚邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.posta.co.ke\"" + 
				"		    }," + 
				"		    huahang: {" + 
				"		        spell: \"huahang\"," + 
				"		        name: \"华航快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-0235-004\"," + 
				"		        url: \"http://www.hz3pl.com\"" + 
				"		    }," + 
				"		    \"morocco-post\": {" + 
				"		        spell: \"morocco-post\"," + 
				"		        name: \"摩洛哥邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.poste.ma/\"" + 
				"		    }," + 
				"		    huangmajia: {" + 
				"		        spell: \"huangmajia\"," + 
				"		        name: \"黄马甲快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"029-96128\"," + 
				"		        url: \"http://www.huangmajia.com/\"" + 
				"		    }," + 
				"		    \"brunei-post\": {" + 
				"		        spell: \"brunei-post\"," + 
				"		        name: \"文莱邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.post.gov.bn/\"" + 
				"		    }," + 
				"		    ucs: {" + 
				"		        spell: \"ucs\"," + 
				"		        name: \"合众速递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"(714) 522-2210\"," + 
				"		        url: \"http://www.ucsus.com/\"" + 
				"		    }," + 
				"		    \"dominican-post\": {" + 
				"		        spell: \"dominican-post\"," + 
				"		        name: \"多米尼加邮政(INPOSDOM)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.inposdom.gob.do/\"" + 
				"		    }," + 
				"		    anlexpress: {" + 
				"		        spell: \"anlexpress\"," + 
				"		        name: \"新干线快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+1 (212) 947-3219\"," + 
				"		        url: \"http://www.anlexpress.com/\"" + 
				"		    }," + 
				"		    jiuyi: {" + 
				"		        spell: \"jiuyi\"," + 
				"		        name: \"久易快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"021-64206088\"," + 
				"		        url: \"http://www.jiuyicn.com/\"" + 
				"		    }," + 
				"		    expressplus: {" + 
				"		        spell: \"expressplus\"," + 
				"		        name: \"澳洲新干线快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"0061-2-8069 3411\"," + 
				"		        url: \"http://www.expressplus.com.au/\"" + 
				"		    }," + 
				"		    ewe: {" + 
				"		        spell: \"ewe\"," + 
				"		        name: \"EWE快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+61 2 9644 2648\"," + 
				"		        url: \"https://www.everfast.com.au/\"" + 
				"		    }," + 
				"		    zhunshi: {" + 
				"		        spell: \"zhunshi\"," + 
				"		        name: \"准实快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4000568962\"," + 
				"		        url: \"http://www.zsky123.com/\"" + 
				"		    }," + 
				"		    menduimen: {" + 
				"		        spell: \"menduimen\"," + 
				"		        name: \"门对门快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-700-7676\"," + 
				"		        url: \"http://www.szdod.com/\"" + 
				"		    }," + 
				"		    jgwl: {" + 
				"		        spell: \"jgwl\"," + 
				"		        name: \"景光物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-700-1682\"," + 
				"		        url: \"http://www.jgwl.cn/\"" + 
				"		    }," + 
				"		    \"qq-ex\": {" + 
				"		        spell: \"qq-ex\"," + 
				"		        name: \"QQ-EX快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"717-394-3043\"," + 
				"		        url: \"http://www.qq-ex.com/\"" + 
				"		    }," + 
				"		    riyu: {" + 
				"		        spell: \"riyu\"," + 
				"		        name: \"日昱物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-882-0800\"," + 
				"		        url: \"http://www.rywl.cn/\"" + 
				"		    }," + 
				"		    lindao: {" + 
				"		        spell: \"lindao\"," + 
				"		        name: \"上海林道货运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008-200-112\"," + 
				"		        url: \"http://www.ldxpress.com/\"" + 
				"		    }," + 
				"		    \"srilanka-post\": {" + 
				"		        spell: \"srilanka-post\"," + 
				"		        name: \"斯里兰卡邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.slpost.gov.lk\"" + 
				"		    }," + 
				"		    fengtong: {" + 
				"		        spell: \"fengtong\"," + 
				"		        name: \"丰通快运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0571-56507333\"," + 
				"		        url: \"http://www.ftky365.com/\"" + 
				"		    }," + 
				"		    duoduo: {" + 
				"		        spell: \"duoduo\"," + 
				"		        name: \"多多快运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-921-9988\"," + 
				"		        url: \"http://www.ddkykj.com/\"" + 
				"		    }," + 
				"		    nsf: {" + 
				"		        spell: \"nsf\"," + 
				"		        name: \"新顺丰（NSF）快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0064-9-2818966\"," + 
				"		        url: \"http://www.nsf.co.nz/\"" + 
				"		    }," + 
				"		    dajin: {" + 
				"		        spell: \"dajin\"," + 
				"		        name: \"大金物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"021-34307932\"," + 
				"		        url: \"http://www.dajin.com.cn/\"" + 
				"		    }," + 
				"		    zhixing: {" + 
				"		        spell: \"zhixing\"," + 
				"		        name: \"志行物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-86-56568\"," + 
				"		        url: \"http://www.hnzxkd.com/\"" + 
				"		    }," + 
				"		    coscon: {" + 
				"		        spell: \"coscon\"," + 
				"		        name: \"中国远洋运输(COSCON)\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.coscon.com/\"" + 
				"		    }," + 
				"		    happylink: {" + 
				"		        spell: \"happylink\"," + 
				"		        name: \"HappyLink快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.happylink.com.au/\"" + 
				"		    }," + 
				"		    yuhong: {" + 
				"		        spell: \"yuhong\"," + 
				"		        name: \"宇宏物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"021-5447 7777\"," + 
				"		        url: \"http://www.yhl.cn/\"" + 
				"		    }," + 
				"		    \"360zebra\": {" + 
				"		        spell: \"360zebra\"," + 
				"		        name: \"斑马物联网\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"400-7200-400\"," + 
				"		        url: \"http://www.360zebra.com/global/zh-CN.html\"" + 
				"		    }," + 
				"		    ezhuanyun: {" + 
				"		        spell: \"ezhuanyun\"," + 
				"		        name: \"易转运\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"400-966-3136\"," + 
				"		        url: \"http://www.ezhuanyun.cn/\"" + 
				"		    }," + 
				"		    jixiangyou: {" + 
				"		        spell: \"jixiangyou\"," + 
				"		        name: \"吉祥邮\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"021-69950109\"," + 
				"		        url: \"http://www.jixiangyou.com/\"" + 
				"		    }," + 
				"		    easytocn: {" + 
				"		        spell: \"easytocn\"," + 
				"		        name: \"运淘美国\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.easytocn.com/\"" + 
				"		    }," + 
				"		    gangkuai: {" + 
				"		        spell: \"gangkuai\"," + 
				"		        name: \"港快速递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4001-133-333\"," + 
				"		        url: \"http://www.gksd.cn/\"" + 
				"		    }," + 
				"		    shlexp: {" + 
				"		        spell: \"shlexp\"," + 
				"		        name: \"畅灵国际物流\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"400-098-5066\"," + 
				"		        url: \"http://www.shlexp.com/\"" + 
				"		    }," + 
				"		    hermes: {" + 
				"		        spell: \"hermes\"," + 
				"		        name: \"Hermes快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"https://www.hermesworld.com/\"" + 
				"		    }," + 
				"		    postelbe: {" + 
				"		        spell: \"postelbe\"," + 
				"		        name: \"PostElbe快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+49 1726576999\"," + 
				"		        url: \"http://www.postelbe.com/\"" + 
				"		    }," + 
				"		    sutong: {" + 
				"		        spell: \"sutong\"," + 
				"		        name: \"速通物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0510-88551183\"," + 
				"		        url: \"http://www.sut56.com/\"" + 
				"		    }," + 
				"		    \"hermes-de\": {" + 
				"		        spell: \"hermes-de\"," + 
				"		        name: \"德国Hermes快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"https://www.myhermes.de/\"" + 
				"		    }," + 
				"		    anxun: {" + 
				"		        spell: \"anxun\"," + 
				"		        name: \"安迅物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"010-5928 8730\"," + 
				"		        url: \"http://www.anxl.com.cn/\"" + 
				"		    }," + 
				"		    \"hermes-uk\": {" + 
				"		        spell: \"hermes-uk\"," + 
				"		        name: \"英国Hermes快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"https://www.myhermes.co.uk/\"" + 
				"		    }," + 
				"		    hkpost: {" + 
				"		        spell: \"hkpost\"," + 
				"		        name: \"香港邮政\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"(852) 2921 2222\"," + 
				"		        url: \"http://www.hongkongpost.hk/\"" + 
				"		    }," + 
				"		    tianma: {" + 
				"		        spell: \"tianma\"," + 
				"		        name: \"天马迅达快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"626-855-3088\"," + 
				"		        url: \"http://www.expresstochina.com/\"" + 
				"		    }," + 
				"		    jppost: {" + 
				"		        spell: \"jppost\"," + 
				"		        name: \"日本邮政\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0081-0570-046111\"," + 
				"		        url: \"http://www.post.japanpost.jp/\"" + 
				"		    }," + 
				"		    apkex: {" + 
				"		        spell: \"apkex\"," + 
				"		        name: \"重点物流 APK Logistics\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.apkex.com/\"" + 
				"		    }," + 
				"		    singpost: {" + 
				"		        spell: \"singpost\"," + 
				"		        name: \"新加坡邮政(Sing Post)\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.singpost.com/\"" + 
				"		    }," + 
				"		    otobv: {" + 
				"		        spell: \"otobv\"," + 
				"		        name: \"中欧快运\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"088-188-8989\"," + 
				"		        url: \"http://www.otobv.com\"" + 
				"		    }," + 
				"		    ztwl: {" + 
				"		        spell: \"ztwl\"," + 
				"		        name: \"中铁物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-000-5566\"," + 
				"		        url: \"http://www.ztky.com/\"" + 
				"		    }," + 
				"		    asiax: {" + 
				"		        spell: \"asiax\"," + 
				"		        name: \"ASIAX快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"0120-205-609\"," + 
				"		        url: \"http://www.asiax.jp/\"" + 
				"		    }," + 
				"		    ppbyb: {" + 
				"		        spell: \"ppbyb\"," + 
				"		        name: \"贝邮宝\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008-206-207\"," + 
				"		        url: \"http://www.ppbyb.com/\"" + 
				"		    }," + 
				"		    scorejp: {" + 
				"		        spell: \"scorejp\"," + 
				"		        name: \"中国流通王\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"86-755-2977-8850\"," + 
				"		        url: \"http://www.scorejp.com.cn\"" + 
				"		    }," + 
				"		    yanwen: {" + 
				"		        spell: \"yanwen\"," + 
				"		        name: \"燕文物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4008-900-156\"," + 
				"		        url: \"http://www.yw56.com.cn/\"" + 
				"		    }," + 
				"		    feiyang: {" + 
				"		        spell: \"feiyang\"," + 
				"		        name: \"飞洋快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"+86-10-87785733 +1-877-387-9799\"," + 
				"		        url: \"http://express.shipgce.com/\"" + 
				"		    }," + 
				"		    amazon: {" + 
				"		        spell: \"amazon\"," + 
				"		        name: \"亚马逊物流\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"400-910-5669\"," + 
				"		        url: \"https://www.z-exp.com/\"" + 
				"		    }," + 
				"		    zuochuan: {" + 
				"		        spell: \"zuochuan\"," + 
				"		        name: \"佐川急便\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0120-222-372\"," + 
				"		        url: \"http://www.sagawa-exp.co.jp/\"" + 
				"		    }," + 
				"		    itl: {" + 
				"		        spell: \"itl\"," + 
				"		        name: \"科捷物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-628-0056\"," + 
				"		        url: \"http://www.itl.cn/\"" + 
				"		    }," + 
				"		    hengyu: {" + 
				"		        spell: \"hengyu\"," + 
				"		        name: \"恒宇运通\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"010-62237700\"," + 
				"		        url: \"http://www.hyytes.cn/\"" + 
				"		    }," + 
				"		    mengsu: {" + 
				"		        spell: \"mengsu\"," + 
				"		        name: \"蒙速快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-0471-565\"," + 
				"		        url: \"http://www.mengsu.net/\"" + 
				"		    }," + 
				"		    jiahuier: {" + 
				"		        spell: \"jiahuier\"," + 
				"		        name: \"佳惠尔快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4000-139-907\"," + 
				"		        url: \"http://www.jhekd.com/\"" + 
				"		    }," + 
				"		    polarexpress: {" + 
				"		        spell: \"polarexpress\"," + 
				"		        name: \"极地快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+61 03 9468 5253\"," + 
				"		        url: \"http://www.polarexpress.com.au/\"" + 
				"		    }," + 
				"		    ftd: {" + 
				"		        spell: \"ftd\"," + 
				"		        name: \"富腾达快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"09-4432342\"," + 
				"		        url: \"http://www.ftd.nz/\"" + 
				"		    }," + 
				"		    ande: {" + 
				"		        spell: \"ande\"," + 
				"		        name: \"安得物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-000-0056\"," + 
				"		        url: \"http://www.annto.com/\"" + 
				"		    }," + 
				"		    rtexpress: {" + 
				"		        spell: \"rtexpress\"," + 
				"		        name: \"荣腾快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"(+86)025-58863284\"," + 
				"		        url: \"http://www.rtexpress.com/\"" + 
				"		    }," + 
				"		    rongqing: {" + 
				"		        spell: \"rongqing\"," + 
				"		        name: \"荣庆物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-000-5656\"," + 
				"		        url: \"http://www.rokin.cn/\"" + 
				"		    }," + 
				"		    tykd: {" + 
				"		        spell: \"tykd\"," + 
				"		        name: \"天翼快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"400-000-3131\"," + 
				"		        url: \"http://www.tykd.com/\"" + 
				"		    }," + 
				"		    dashun: {" + 
				"		        spell: \"dashun\"," + 
				"		        name: \"大顺物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0755-29459328 29459338\"," + 
				"		        url: \"\"" + 
				"		    }," + 
				"		    ause: {" + 
				"		        spell: \"ause\"," + 
				"		        name: \"澳世快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"400-117-6088\"," + 
				"		        url: \"http://www.aus-express.com/\"" + 
				"		    }," + 
				"		    fangfangda: {" + 
				"		        spell: \"fangfangda\"," + 
				"		        name: \"方方达物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-020-0056\"," + 
				"		        url: \"http://www.gzffd.com/\"" + 
				"		    }," + 
				"		    astexpress: {" + 
				"		        spell: \"astexpress\"," + 
				"		        name: \"安世通快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"40083 87987\"," + 
				"		        url: \"http://www.astexpress.com/\"" + 
				"		    }," + 
				"		    wykjt: {" + 
				"		        spell: \"wykjt\"," + 
				"		        name: \"51跨境通\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"\"," + 
				"		        url: \"https://www.wy-kjt.com/\"" + 
				"		    }," + 
				"		    huiwen: {" + 
				"		        spell: \"huiwen\"," + 
				"		        name: \"汇文快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-0000-266\"," + 
				"		        url: \"http://www.hefeihuiwen.com/\"" + 
				"		    }," + 
				"		    \"6ls\": {" + 
				"		        spell: \"6ls\"," + 
				"		        name: \"6LS EXPRESS\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.6ls.cc/\"" + 
				"		    }," + 
				"		    sujie: {" + 
				"		        spell: \"sujie\"," + 
				"		        name: \"速捷快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0371-66612770\"," + 
				"		        url: \"http://www.sujievip.com/\"" + 
				"		    }," + 
				"		    qdants: {" + 
				"		        spell: \"qdants\"," + 
				"		        name: \"ANTS EXPRESS\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.qdants.com\"" + 
				"		    }," + 
				"		    dhlde: {" + 
				"		        spell: \"dhlde\"," + 
				"		        name: \"德国DHL快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.dhl.de/\"" + 
				"		    }," + 
				"		    aplusex: {" + 
				"		        spell: \"aplusex\"," + 
				"		        name: \"Aplus物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.aplusex.com/\"" + 
				"		    }," + 
				"		    baiteng: {" + 
				"		        spell: \"baiteng\"," + 
				"		        name: \"百腾物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-9989-256\"," + 
				"		        url: \"http://www.beteng.com/\"" + 
				"		    }," + 
				"		    bcwelt: {" + 
				"		        spell: \"bcwelt\"," + 
				"		        name: \"BCWELT\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.bcwelt.com/\"" + 
				"		    }," + 
				"		    cncexp: {" + 
				"		        spell: \"cncexp\"," + 
				"		        name: \"C&C国际速递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"(03) 9558 9351\"," + 
				"		        url: \"http://www.cncexp.com.au/\"" + 
				"		    }," + 
				"		    cnair: {" + 
				"		        spell: \"cnair\"," + 
				"		        name: \"CNAIR快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"+61(02)8739 2375\"," + 
				"		        url: \"http://www.cnair.com.au\"" + 
				"		    }," + 
				"		    dpd: {" + 
				"		        spell: \"dpd\"," + 
				"		        name: \"DPD快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"+44 845 9 300 350\"," + 
				"		        url: \"http://www.dpd.co.uk/\"" + 
				"		    }," + 
				"		    jxfex: {" + 
				"		        spell: \"jxfex\"," + 
				"		        name: \"集先锋快递\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"13380443157\"," + 
				"		        url: \"http://www.jxf-ex.com/\"" + 
				"		    }," + 
				"		    xinhua: {" + 
				"		        spell: \"xinhua\"," + 
				"		        name: \"新华快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"1-888-300-7288\"," + 
				"		        url: \"http://www.xheusa.com/\"" + 
				"		    }," + 
				"		    tengxunda: {" + 
				"		        spell: \"tengxunda\"," + 
				"		        name: \"腾迅达物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"010-5242 9369\"," + 
				"		        url: \"http://www.tasinda.com/\"" + 
				"		    }," + 
				"		    thunderex: {" + 
				"		        spell: \"thunderex\"," + 
				"		        name: \"风雷快递\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"86- 0631-6688877\"," + 
				"		        url: \"http://www.thunderex.com/\"" + 
				"		    }," + 
				"		    pinjun: {" + 
				"		        spell: \"pinjun\"," + 
				"		        name: \"品骏快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4009 789 888\"," + 
				"		        url: \"http://www.pjbest.com/\"" + 
				"		    }," + 
				"		    skydexpress: {" + 
				"		        spell: \"skydexpress\"," + 
				"		        name: \"天地快递\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"0451-57780834\"," + 
				"		        url: \"https://www.skydexpress.com/\"" + 
				"		    }," + 
				"		    xiyoug: {" + 
				"		        spell: \"xiyoug\"," + 
				"		        name: \"西游寄快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"+86 21-6221 5372\"," + 
				"		        url: \"http://www.xiyoug.com/\"" + 
				"		    }," + 
				"		    taohuaex: {" + 
				"		        spell: \"taohuaex\"," + 
				"		        name: \"桃花运快递\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.taohuaex.com/\"" + 
				"		    }," + 
				"		    wzhuanyun: {" + 
				"		        spell: \"wzhuanyun\"," + 
				"		        name: \"微转运快递\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"4008699273\"," + 
				"		        url: \"http://www.wzhuanyun.com/\"" + 
				"		    }," + 
				"		    bse: {" + 
				"		        spell: \"bse\"," + 
				"		        name: \"蓝天快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.blueskyexpress.com.au/\"" + 
				"		    }," + 
				"		    u2c: {" + 
				"		        spell: \"u2c\"," + 
				"		        name: \"U2C转运\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.u2cex.com/\"" + 
				"		    }," + 
				"		    cgeusa: {" + 
				"		        spell: \"cgeusa\"," + 
				"		        name: \"绿洲快递\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.cgeusa.com/\"" + 
				"		    }," + 
				"		    ueq: {" + 
				"		        spell: \"ueq\"," + 
				"		        name: \"UEQ快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"020-87780288\"," + 
				"		        url: \"http://www.ueq.com/\"" + 
				"		    }," + 
				"		    \"52hbl\": {" + 
				"		        spell: \"52hbl\"," + 
				"		        name: \"小羚羊转运\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.52hbl.com/\"" + 
				"		    }," + 
				"		    xru: {" + 
				"		        spell: \"xru\"," + 
				"		        name: \"XRU快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-688-0611\"," + 
				"		        url: \"http://www.xru.com/\"" + 
				"		    }," + 
				"		    feigex: {" + 
				"		        spell: \"feigex\"," + 
				"		        name: \"飞鸽快递\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.feigex.com/\"" + 
				"		    }," + 
				"		    ycky: {" + 
				"		        spell: \"ycky\"," + 
				"		        name: \"远成快运\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-168-1111\"," + 
				"		        url: \"http://www.ycgky.com/\"" + 
				"		    }," + 
				"		    zhonghuanus: {" + 
				"		        spell: \"zhonghuanus\"," + 
				"		        name: \"中环转运\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.zhonghuanus.com/\"" + 
				"		    }," + 
				"		    anpost: {" + 
				"		        spell: \"anpost\"," + 
				"		        name: \"爱尔兰邮政(AN POST)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.anpost.ie/\"" + 
				"		    }," + 
				"		    ipehua: {" + 
				"		        spell: \"ipehua\"," + 
				"		        name: \"鹏华转运\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"0755-23078290\"," + 
				"		        url: \"http://www.ipehua.com/\"" + 
				"		    }," + 
				"		    koreapost: {" + 
				"		        spell: \"koreapost\"," + 
				"		        name: \"韩国邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+0082 2 2195 1114\"," + 
				"		        url: \"http://www.koreapost.go.kr/\"" + 
				"		    }," + 
				"		    haidaibao: {" + 
				"		        spell: \"haidaibao\"," + 
				"		        name: \"海带宝转运\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"400-825-8585\"," + 
				"		        url: \"http://www.haidaibao.com/\"" + 
				"		    }," + 
				"		    posten: {" + 
				"		        spell: \"posten\"," + 
				"		        name: \"挪威邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.posten.no/\"" + 
				"		    }," + 
				"		    atians: {" + 
				"		        spell: \"atians\"," + 
				"		        name: \"天鸟转运\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"18098904559\"," + 
				"		        url: \"http://www.atians.com/\"" + 
				"		    }," + 
				"		    auspost: {" + 
				"		        spell: \"auspost\"," + 
				"		        name: \"澳大利亚邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://auspost.com.au/\"" + 
				"		    }," + 
				"		    \"59express\": {" + 
				"		        spell: \"59express\"," + 
				"		        name: \"59转运\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"3022762771\"," + 
				"		        url: \"http://59express.com/\"" + 
				"		    }," + 
				"		    posteit: {" + 
				"		        spell: \"posteit\"," + 
				"		        name: \"意大利邮政\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"http://www.poste.it/\"" + 
				"		    }," + 
				"		    nzpost: {" + 
				"		        spell: \"nzpost\"," + 
				"		        name: \"新西兰邮政(NZPost)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"\"," + 
				"		        url: \"https://www.nzpost.co.nz/\"" + 
				"		    }," + 
				"		    datongex: {" + 
				"		        spell: \"datongex\"," + 
				"		        name: \"大通速递\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"+1 （302）397-4251\"," + 
				"		        url: \"https://www.datongex.com/\"" + 
				"		    }," + 
				"		    bpost: {" + 
				"		        spell: \"bpost\"," + 
				"		        name: \"比利时邮政(BPost)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"0032-02-278-50-90\"," + 
				"		        url: \"http://landmarkglobal.com/zh/\"" + 
				"		    }," + 
				"		    belsexp: {" + 
				"		        spell: \"belsexp\"," + 
				"		        name: \"贝斯转运\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"+1 503-8628029\"," + 
				"		        url: \"https://belsexp.com/\"" + 
				"		    }," + 
				"		    depost: {" + 
				"		        spell: \"depost\"," + 
				"		        name: \"德国邮政(Deutsche Post)\"," + 
				"		        dir: \"global\"," + 
				"		        tel: \"+49 (0) 180 2 000221\"," + 
				"		        url: \"https://www.deutschepost.de/\"" + 
				"		    }," + 
				"		    \"chn-exp\": {" + 
				"		        spell: \"chn-exp\"," + 
				"		        name: \"中翼国际物流\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"400-900-8687\"," + 
				"		        url: \"http://www.chn-exp.com\"" + 
				"		    }," + 
				"		    cccc58: {" + 
				"		        spell: \"cccc58\"," + 
				"		        name: \"中集冷云\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-6507-168\"," + 
				"		        url: \"http://www.cccc58.com/\"" + 
				"		    }," + 
				"		    ruifeng: {" + 
				"		        spell: \"ruifeng\"," + 
				"		        name: \"瑞丰速递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4006-261-667\"," + 
				"		        url: \"http://www.rfsd88.com/\"" + 
				"		    }," + 
				"		    suteng: {" + 
				"		        spell: \"suteng\"," + 
				"		        name: \"速腾快递\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"0769-33503999\"," + 
				"		        url: \"http://www.ste56.com/\"" + 
				"		    }," + 
				"		    zongxing: {" + 
				"		        spell: \"zongxing\"," + 
				"		        name: \"纵行物流\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"400-8808-994\"," + 
				"		        url: \"http://www.156zx.com/\"" + 
				"		    }," + 
				"		    yuetu: {" + 
				"		        spell: \"yuetu\"," + 
				"		        name: \"悦途转运\"," + 
				"		        dir: \"transit\"," + 
				"		        tel: \"18526485013\"," + 
				"		        url: \"http://www.tt-yuetu.com/\"" + 
				"		    }," + 
				"		    exfresh: {" + 
				"		        spell: \"exfresh\"," + 
				"		        name: \"安鲜达\"," + 
				"		        dir: \"\"," + 
				"		        tel: \"4009201156\"," + 
				"		        url: \"http://exfresh.com.cn/\"" + 
				"		    }" + 
				"		}");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void setComInfo(String enName ,JSONObject jsonobj) {
		Express info= WuliuUtil.getComInfo(enName);
	/*
		"expSpellName": "huitong",
		"expTextName": "百世快递",
		"tel": "95320",
		"url": "http://www.sf-express.com/",*/
		try {
			
	
		jsonobj.put("expTextName", info.name);
		jsonobj.put("expSpellName", info.spell);
		jsonobj.put("tel", info.tel);
		jsonobj.put("url", info.url);
		
	} catch (Exception e) {
		// TODO: handle exception
	}
 }
	
		
	/**
	 * 根据代码获取物流公司名字
	 * @param enName
	 * @return
	 * @author zj
	 * @date 2018年8月22日
	 */
	public  static Express getComInfo(String enName) {
		String name=enName;
		Express express=new Express();
	
		if(!jsonCompany.has("ems"))
			init();
		
		Iterator<String> keys= jsonCompany.keys();
		while(keys.hasNext()) {
			String key=keys.next();
			if(enName.contains(key)) {
				express.spell= jsonCompany.optJSONObject(key).optString("spell");
				express.name= jsonCompany.optJSONObject(key).optString("name");
				express.tel= jsonCompany.optJSONObject(key).optString("tel");
				express.url= jsonCompany.optJSONObject(key).optString("url");
			}
		}
		
		
		return express;
	}
	
	public static void main(String [] args) {
		String name=getComInfo("shentong").name;
		System.out.println(name);
	}
	
	
}
