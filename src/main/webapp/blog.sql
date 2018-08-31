/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50718
Source Host           : 192.168.2.191:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-12-29 17:40:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog_info
-- ----------------------------
DROP TABLE IF EXISTS `blog_info`;
CREATE TABLE `blog_info` (
  `recordid` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `title` varchar(256) DEFAULT NULL,
  `blog_type` int(11) DEFAULT '0' COMMENT '分类',
  `content` text,
  `tags` varchar(1000) DEFAULT NULL COMMENT 'tag',
  `view_nums` int(11) DEFAULT '0' COMMENT '查看次数',
  `replay_nums` int(11) DEFAULT '0' COMMENT '评论次数',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `imei` varchar(128) DEFAULT NULL COMMENT '唯一号',
  KEY `Index_1` (`recordid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='blog';

-- ----------------------------
-- Records of blog_info
-- ----------------------------
INSERT INTO `blog_info` VALUES ('1', 'test2', '2', '%3Cp%3E%u6211%u4EEC%u77E5%u9053%u5728%u5E94%u7528%u7684%u9875%u9762%u6216%u8005%u7EC4%u4EF6%u9700%u8981%u52A0%u8F7D%u6570%u636E%u65F6%uFF0C%u6D4F%u89C8%u5668%u548Cangular%u6E32%u67D3%u9875%u9762%u90FD%u9700%u8981%u6D88%u8017%u4E00%u5B9A%u7684%u65F6%u95F4%u3002%u8FD9%u91CC%u7684%u95F4%u9694%u53EF%u80FD%u5F88%u5C0F%uFF0C%u751A%u81F3%u8BA9%u4EBA%u611F%u89C9%u4E0D%u5230%u533A%u522B%uFF1B%u4F46%u4E5F%u53EF%u80FD%u5F88%u957F%uFF0C%u8FD9%u6837%u4F1A%u5BFC%u81F4%u8BA9%u6211%u4EEC%u7684%u7528%u6237%u770B%u5230%u4E86%u6CA1%u6709%u88AB%u6E32%u67D3%u8FC7%u7684%u9875%u9762%u3002%3C/p%3E%0A%0A%3Cp%3E%u8FD9%u79CD%u60C5%u51B5%u88AB%u53EB%u505AFlash%20Of%20Unrendered%20Content%20%28FOUC%29%uFF08K%uFF09%uFF1Fand%20is%20always%20unwanted.%u4E0B%u9762%u6211%u4EEC%u5C06%u8981%u4ECB%u7ECD%u51E0%u4E2A%u4E0D%u540C%u7684%u65B9%u5F0F%u9632%u6B62%u8FD9%u79CD%u60C5%u51B5%u53D1%u751F%u5728%u6211%u4EEC%u7684%u7528%u6237%u8EAB%u4E0A%u3002%3C/p%3E%0A%0A%3Cp%3E%26nbsp%3B%3C/p%3E%0A%0A%3Cp%3E%26nbsp%3B%3C/p%3E%0A%0A%3Cp%3E%26nbsp%3B%3C/p%3E%0A', 'test,java', '9', '0', '2017-11-25 15:50:58', '2017-12-26 15:04:23', '13892f60-8e5f-48b9-b479-a2a5097a0150');
INSERT INTO `blog_info` VALUES ('2', '测试日志11111', '3', '%3Cp%3E%3Cstrong%3EPython%20%u57FA%u7840%u6559%u7A0B%3C/strong%3E%3C/p%3E%0A%0A%3Cp%3E%26nbsp%3B%3C/p%3E%0A%0A%3Cpre%3E%0A%3Ccode%20class%3D%22language-javascript%22%3Evar%20d%3D1%3B%0Afunction%20test%28%29%7B%0A%0Ad++%3B%0Aalert%28d%29%3B%0A%0A%7D%3C/code%3E%3C/pre%3E%0A%0A%3Cp%3E%26nbsp%3B%3C/p%3E%0A%0A%3Cp%3E%3Cstrong%3E%3Cimg%20alt%3D%22%22%20src%3D%22http%3A//127.0.0.1%3A8080/kb/file/pic/201712/0dadf049-c34a-4d3e-8e9e-c302e1bd82be.jpg%22%20style%3D%22height%3A250px%3B%20width%3A800px%22%20/%3E%3C/strong%3E%3Cbr%20/%3E%0A%26nbsp%3B%3C/p%3E%0A%0A%3Cp%3EPython%u662F%u4E00%u79CD%u89E3%u91CA%u578B%u3001%u9762%u5411%u5BF9%u8C61%u3001%u52A8%u6001%u6570%u636E%u7C7B%u578B%u7684%u9AD8%u7EA7%u7A0B%u5E8F%u8BBE%u8BA1%u8BED%u8A00%u3002%3C/p%3E%0A%0A%3Cp%3E111%3C/p%3E%0A%0A%3Cp%3E%26nbsp%3B%3C/p%3E%0A%0A%3Cpre%3E%0APython%u7531Guido%20van%20Rossum%u4E8E1989%u5E74%u5E95%u53D1%u660E%uFF0C%u7B2C%u4E00%u4E2A%u516C%u5F00%u53D1%u884C%u7248%u53D1%u884C%u4E8E1991%u5E74%u3002%3C/pre%3E%0A%0A%3Col%3E%0A%09%3Cli%3E1111%3C/li%3E%0A%09%3Cli%3E1231%3C/li%3E%0A%09%3Cli%3E3%3C/li%3E%0A%09%3Cli%3E123123%3C/li%3E%0A%3C/ol%3E%0A%0A%3Cp%3E%26nbsp%3B%3C/p%3E%0A%0A%3Cul%3E%0A%09%3Cli%3E12312%3C/li%3E%0A%09%3Cli%3E123123%3C/li%3E%0A%09%3Cli%3E123123%3C/li%3E%0A%3C/ul%3E%0A%0A%3Ch2%20style%3D%22font-style%3Aitalic%22%3Esdfsdfsdfsdfs%26Oacute%3B%3C/h2%3E%0A%0A%3Cp%3E%u50CFPerl%u8BED%u8A00%u4E00%u6837%2C%20Python%20%u6E90%u4EE3%u7801%u540C%u6837%u9075%u5FAA%20GPL%28GNU%20General%20Public%20License%29%u534F%u8BAE%u3002%3C/p%3E%0A%0A%3Cp%3E%u672C%u6559%u7A0B%u4E3B%u8981%u9488%u5BF9Python%202.x%u7248%u672C%u7684%u5B66%u4E60%uFF0C%u5982%u679C%u4F60%u4F7F%u7528%u7684%u662FPython%203.x%u7248%u672C%u8BF7%u79FB%u6B65%u81F3Python%203.X%u7248%u672C%u7684%u6559%u7A0B%u3002%3C/p%3E%0A%0A%3Cp%3E%u8C01%u9002%u5408%u9605%u8BFB%u672C%u6559%u7A0B%uFF1F%3C/p%3E%0A%0A%3Cblockquote%3E%0A%3Cp%3E%u672C%u6559%u7A0B%u9002%u5408%u60F3%u4ECE%u96F6%u5F00%u59CB%u5B66%u4E60Python%u7F16%u7A0B%u8BED%u8A00%u7684%u5F00%u53D1%u4EBA%u5458%u3002%u5F53%u7136%u672C%u6559%u7A0B%u4E5F%u4F1A%u5BF9%u4E00%u4E9B%u6A21%u5757%u8FDB%u884C%u6DF1%u5165%uFF0C%u8BA9%u4F60%u66F4%u597D%u7684%u4E86%u89E3Python%u7684%u5E94%u7528%u3002%3C/p%3E%0A%3C/blockquote%3E%0A%0A%3Cp%3E%u5B66%u4E60%u672C%u6559%u7A0B%u524D%u4F60%u9700%u8981%u4E86%u89E3%3C/p%3E%0A%0A%3Cp%3E%u5728%u7EE7%u7EED%u672C%u6559%u7A0B%u4E4B%u524D%uFF0C%u4F60%u5E94%u8BE5%u4E86%u89E3%u4E00%u4E9B%u57FA%u672C%u7684%u8BA1%u7B97%u673A%u7F16%u7A0B%u672F%u8BED%u3002%u5982%u679C%u4F60%u5B66%u4E60%u8FC7PHP%uFF0CASP%u7B49%u7F16%u7A0B%u8BED%u8A00%uFF0C%u5C06%u6709%u52A9%u4E8E%u4F60%u66F4%u5FEB%u7684%u4E86%u89E3Python%u7F16%u7A0B%u3002%3C/p%3E%0A%0A%3Cp%3E%26nbsp%3B%3C/p%3E%0A', 'test,问题', '50', '0', '2017-10-25 19:59:59', '2017-12-27 13:46:14', '6b9ff6df-5655-4f96-b72c-db6e646612fb');
INSERT INTO `blog_info` VALUES ('3', 'ckeditor工具栏编辑问题', '1', '%3Cp%3ECkeditor%20%u5DE5%u5177%u680F%u7F16%u8F91%u3001%u627E%u5230%u5B98%u7F51%u6587%u6863%u3001%u76F4%u63A5%u8BBF%u95EE%u672C%u5730ckeditor%u76EE%u5F55%u4E0B%u7684samples/index.html%u6587%u4EF6%u3001%u6253%u5F00%u5982%u4E0B%u754C%u9762%u3001%u4E4B%u5B9A%u4E49%u914D%u7F6E%u5DE5%u5177%u680F%u3001%u83B7%u53D6%u5230js%u914D%u7F6E%u540E%uFF0C%3C/p%3E%0A%0A%3Cp%3E%u81EA%u5DF1%u590D%u5236%u5230config.js%u6587%u4EF6%u4E2D%u6307%u5B9A%uFF01%3C/p%3E%0A%0A%3Cp%3E%u6682%u65F6%u5148%u8FD9%u6837%u5427%3Cimg%20alt%3D%22indecision%22%20src%3D%22http%3A//127.0.0.1%3A8080/kb/js/plugin/ckeditor4.5.11/plugins/smiley/images/whatchutalkingabout_smile.png%22%20style%3D%22height%3A23px%3B%20width%3A23px%22%20title%3D%22indecision%22%20/%3E%3C/p%3E%0A%0A%3Cp%3E%3Cimg%20alt%3D%22%22%20src%3D%22http%3A//127.0.0.1%3A8080/kb/file/pic/201712/77d3c993-cb53-40ec-8d1b-c510d38ec765.PNG%22%20style%3D%22height%3A434px%3B%20width%3A851px%22%20/%3E%3C/p%3E%0A', 'ckeditor', '48', '21', '2017-12-26 09:14:05', '2017-12-27 13:45:40', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b');
INSERT INTO `blog_info` VALUES ('4', 'Mysql存储过程', '4', '%3Cp%3E%u6BCF%u6B21%u5199%u5B58%u50A8%u8FC7%u7A0B%u90FD%u662F%u4E2A%u6311%u6218%uFF0C%u4E0D%u8FC7%u6BCF%u6B21%u5199%u5B8C%uFF0C%u5199%u597D%u4E86%uFF0C%u611F%u89C9%u90FD%u86EE%u4E0D%u9519%u7684%u3002%3C/p%3E%0A%0A%3Cp%3E%u563F%u563F%uFF0C%u770B%u770B%uFF0C%u611F%u89C9%u826F%u597D%u3002%3C/p%3E%0A%0A%3Cp%3E%26nbsp%3B%3C/p%3E%0A%0A%3Cp%3E%u5176%u5B9E%u6709%u65F6%u5019%uFF0C%u6211%u611F%u89C9%u4E0D%u4E00%u5B9A%u975E%u5F97%u7528%u5B58%u50A8%u8FC7%u7A0B%uFF0C%u4E0D%u8FC7%u65E2%u7136%u8001%u5927%u8981%u6C42%uFF0C%u90A3%u4E5F%u6CA1%u529E%u6CD5%u5462%uFF0C%u505A%u5462%u3002%3C/p%3E%0A%0A%3Cp%3E%u5F53%u7136%uFF0C%u8FD9%u4E2A%u786E%u5B9E%u662F%u53EF%u4EE5%u4F7F%u7528%u5B58%u50A8%u8FC7%u7A0B%u7684%u5462%u3002%3C/p%3E%0A%0A%3Cp%3E%26nbsp%3B%3C/p%3E%0A%0A%3Cpre%3E%0A%3Ccode%20class%3D%22language-sql%22%3EDROP%20PROCEDURE%20IF%20EXISTS%20%60clean_off_route%60%3B%0ADELIMITER%20%3B%3B%0ACREATE%20DEFINER%3D%60root%60@%60%25%60%20PROCEDURE%20%60clean_off_route%60%28IN%20psvrid%20varchar%2830%29%2CIN%20savedata%20varchar%2830%29%2Cout%20routeids%20varchar%2830%29%29%20COMMENT%20%27%u6E05%u9664%u8D85%u65F6%u6216%u8005%u4F7F%u7528%u5B8C%u6210%u7684%u8DEF%u7531%uFF0C%u5E76%u4E0B%u7EBF%27%0Abegin%20%0A%0A%20%20declare%20maxnum%20INTEGER%20DEFAULT%206%3B%20--%20%u8DEF%u7531%u516C%u53F8%u6700%u5927%u4F7F%u7528%u6B21%u6570%uFF0C%u8D85%u8FC7%u8BE5%u6B21%u6570%uFF0C%u4E0B%u7EBF%0Adeclare%20maxnousehour%20INTEGER%20DEFAULT%203%3B%20--%20%u8DEF%u7531%u6700%u5927%u5927%u65E0%u4F7F%u7528%u65F6%u95F4%uFF0C%u5927%u4E8E%u8BE5%u65F6%u95F4%u3001%u4E0B%u7EBF%0A%0Adeclare%20v_finished%20INTEGER%20DEFAULT%200%3B%0Adeclare%20pid%20varchar%2830%29%20default%20%22%22%3B%0A%20declare%20rid%20varchar%2830%29%20default%20%22%22%3B%0A%20declare%20rtype%20varchar%2830%29%20default%20%22%22%3B%0A%0A%0A%0A%0A%20DECLARE%20_Cur%20CURSOR%20for%0Aselect%20proxyserver_id%2Crouteid%2Ctype%20from%20%28%0A--%20%u8FC7%u6EE4%u4E2D%u8F6C%u670D%u52A1%u5668%u3001%u5728%u7EBF%u72B6%u6001%0Aselect%20*%20from%20%28%0A--%20%u5168%u91CF1%u6761%u4EF6%u8FC7%u6EE4%0Aselect%20*%20from%20%28%0A--%20%u5168%u91CF1--%u8DEF%u7531%u516C%u53F8%u4F7F%u7528%u6B21%u6570---%0Aselect%20r.proxyserver_id%2Cr.flag%2Cr.free%2Crcnums.*%2C%27maxuse%27%20type%20from%20route_info%20r%20left%20join%0A%28%0Aselect%20routeid%2Ccompanyid%2Ccount%28*%29%20num%20from%20%28%0A%0A--%20%u5173%u8054%u8DEF%u7531%u4F7F%u7528%u8BB0%u5F55-%u516C%u53F8%0Aselect%20rus.*%2C%20pcpsvr.companyid%20from%20route_use_log%20%20rus%0Aleft%20JOIN%0A%28%0A--%20%u624B%u673A%u8D26%u53F7-%u516C%u53F8%20%u4E2D%u8F6C%u670D%u52A1%u5668-%uFF0C%uFF0C%u5BF9%u5E94%u5173%u7CFB%0Aselect%20cphone.phoneip%2Ccphone.accountid%2Ccpsvr.*%20from%20%0A%28%0Aselect%20%0Asubstring_index%28p.ip%20%2C%20%27.%27%2C3%29%20phone_ippre%2Cp.accountid%0A%2Ccast%28substring_index%28p.ip%20%2C%20%27.%27%2C-1%29%20%20as%20SIGNED%20INTEGER%29%20phone_iplast%2C%0Ac.accountid%20companyid%2Cc.company_name%2Cp.accountid%20phoneaccount%2Cp.city%20phonecity%2Cp.ip%20phoneip%20from%20phoneaccount_info%20p%20LEFT%20JOIN%20company_info%20c%20on%20c.accountid%3Dp.company_userid%0Aorder%20by%20c.accountid%0A%29%20cphone%0Aleft%20join%0A%28%0Aselect%20substring_index%28substring_index%28cp.ipdesc%2C%20%27-%27%2C1%29%2C%20%27.%27%2C3%29%20ippre%0A%2C%20cast%28%20substring_index%28substring_index%28cp.ipdesc%2C%20%27-%27%2C1%29%2C%20%27.%27%2C-1%29%20as%20SIGNED%20INTEGER%29%20ipstart%0A%2Ccast%28%20substring_index%28substring_index%28cp.ipdesc%2C%20%27-%27%2C-1%29%2C%20%27.%27%2C-1%29%20%20as%20SIGNED%20INTEGER%29%20ipend%0A%2C%20psvr.id%20proxyserver_id%20%2Cpsvr.assignphones%2Cpsvr.maxphones%2Ccp.companyid%2Ccp.ipdesc%20from%20proxyserver_info%20psvr%20left%20join%20company_proxyserver%20cp%20on%20psvr.id%3Dcp.proxyserver_id%0A%29%20cpsvr%20%0A%20on%20%28cpsvr.companyid%3Dcphone.companyid%0A%20and%20cphone.phone_ippre%3Dcpsvr.ippre%20%0A%20and%20cphone.phone_iplast%26gt%3B%3Dcpsvr.ipstart%0A%20and%20cphone.phone_iplast%26lt%3B%3Dcpsvr.ipend%20%0A%29%20%0A%20--%20%u624B%u673A%u8D26%u53F7-%u516C%u53F8%20%u4E2D%u8F6C%u670D%u52A1%u5668-%uFF0C%uFF0C%u5BF9%u5E94%u5173%u7CFB--end%0A%29%20pcpsvr%20on%20rus.phoneip%3Dpcpsvr.phoneip%0A%0A%29%20rtcmp%20%20where%20time%26gt%3BDATE_SUB%28now%28%29%2CINTERVAL%2024%20HOUR%29%20%20group%20by%20companyid%20%2Crouteid%20order%20by%20count%28*%29%20desc%0A%29%20rcnums%20on%20r.routeid%3Drcnums.routeid%0A--%20%u5168%u91CF1--%u8DEF%u7531%u516C%u53F8%u4F7F%u7528%u6B21%u6570---%20end%0A%29%20tmax%20where%20tmax.num%26gt%3Bmaxnum%20%0A%0Aunion%0A%28%0A--%20%u67E5%u8BE2%0Aselect%20*%20from%20%28%0A--%20%u5168%u91CF2%uFF0Cxx%u5C0F%u65F6%u5185%u6CA1%u6709%u65F6%u95F4%u8BB0%u5F55%0Aselect%20r.proxyserver_id%2Cr.flag%2Cr.free%2Cr.routeid%2Crcnums.num%2C%27%27%20companyid%2C%27nouse%27%20type%20from%20route_info%20r%20left%20join%0A%28%0A%0Aselect%20routeid%2Ccount%28*%29%20num%20%20from%20route_use_log%20where%0A%20time%26gt%3BDATE_SUB%28now%28%29%2CINTERVAL%20maxnousehour%20HOUR%29%20%20group%20by%20routeid%20%0A%29%20rcnums%20on%20r.routeid%3Drcnums.routeid%0A--%20%u5168%u91CF2%uFF0Cxx%u5C0F%u65F6%u5185%u6CA1%u6709%u65F6%u95F4%u8BB0%u5F55--end%0A%29%20tuse%20where%20tuse.num%20is%20null%0A%29%0A%0A%29%20tall%20where%20proxyserver_id%3Dpsvrid%20and%20flag%3D2%0A%29%20tall2%3B%0A%0ADECLARE%20CONTINUE%20HANDLER%20%0AFOR%20NOT%20FOUND%20SET%20v_finished%20%3D%201%3B%0A%0A%0Aset%20routeids%20%3D%20%22xxxx%22%3B%0A%0A%0Aset%20v_finished%20%3D%200%3B%0A--%20%u5FAA%u73AF%u5904%u7406%0AOPEN%20_Cur%3B%0Adowork%3A%20LOOP%0A%20FETCH%20_Cur%20INTO%20%20pid%2Crid%2Crtype%3B%0A%0A%20IF%20v_finished%20%3D%201%20THEN%20%0A%20LEAVE%20dowork%3B%0A%20END%20IF%3B%0A%0A%0A--%20insert%20into%20%20mysql_pro_log%20%20%20values%28concat%28pid%2Crid%2Crtype%2Cdate_format%28now%28%29%2C%27%25Y-%25m-%25d%20%25H%27%29%2C%27%20%27%29%2Cnow%28%29%29%3B%0A%0A%20%20--%20%u64CD%u4F5C%0A%20%20--%20%u5904%u7406%u8FD4%u56DE%0A%20%20%20set%20routeids%3D%20concat%28routeids%2C%22%2C%22%2Crid%29%20%3B%0A%0Aif%28savedata%3D%27true%27%29%20then%0A%0A%20%20--%20%u8868%u72B6%u6001%0A%20select%20routeid%20into%20rid%20from%20route_info%20where%20proxyserver_id%3Dpsvrid%20and%20routeid%3Drid%20and%20flag%3D2%3B%0A%20if%28rid%20is%20not%20null%29%20then%20%20%20%20--%20%u8FC7%u6EE4%0A%20%20%20--%20%20%u8DEF%u7531%u72B6%u6001%0A%20%20%20update%20route_info%20set%20flag%3D0%2Cfree%3D0%2Cofflinetime%3Dnow%28%29%20%2Cproxyserver_id%3D%22%22%20where%20proxyserver_id%3Dpsvrid%20and%20routeid%3Drid%3B%0A%20%20%20%20--%20%u4E0B%u7EBF%u65F6%u95F4%0A%09%20update%20route_log_Info%20set%20offlinetime%3Dnow%28%29%2Cofflinetype%3D%220%22%20where%20offlinetime%20is%20null%20and%20routeid%3Drid%20and%20proxyserver_id%3Dpid%3B%0A%0A%20%20%20%20%0A%20%20%20%20if%28rtype%20%3D%20%27maxuse%27%29%20then%0A%20%20%20%20%20--%20%20%u670D%u52A1%u5B8C%u6210%0A%20%20%20%20insert%20into%20server_complete_route%20values%28pid%2Crid%2Cnow%28%29%29%3B%0A%20%20%20%20end%20if%3B%0A%20end%20if%3B%0Aend%20if%3B%0A%0A%0A%20%0AEND%20LOOP%20dowork%3B%0ACLOSE%20_Cur%3B%0A%0A%0Aend%0A%3B%3B%0ADELIMITER%20%3B%0A%3C/code%3E%3C/pre%3E%0A%0A%3Cp%3E%26nbsp%3B%3C/p%3E%0A', 'mysql,存储过程', '108', '22', '2017-12-28 10:44:20', null, 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab');

-- ----------------------------
-- Table structure for dict_info
-- ----------------------------
DROP TABLE IF EXISTS `dict_info`;
CREATE TABLE `dict_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `dict_type` varchar(256) DEFAULT NULL COMMENT 'type',
  `dict_key` varchar(64) DEFAULT NULL COMMENT 'key',
  `dict_name` varchar(32) DEFAULT NULL COMMENT 'val',
  `sort` int(11) DEFAULT '0' COMMENT 'sort',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `val1` varchar(512) DEFAULT '' COMMENT ' 备用',
  `desc_info` varchar(128) DEFAULT NULL COMMENT '描述',
  KEY `Index_1` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of dict_info
-- ----------------------------
INSERT INTO `dict_info` VALUES ('1', 'blog_type', '1', '日常', '3', '2017-12-25 15:43:29', '2017-12-26 15:00:24', 'apk/201712/a2e5d433-06f7-4a2c-ad18-fa9a26f1c8ea.png', '');
INSERT INTO `dict_info` VALUES ('2', 'blog_type', '2', 'JAVA', '2', '2017-12-25 15:44:43', '2017-12-26 14:56:44', 'apk/201712/3021af2f-54c0-43a9-96a9-43c13d1282c5.png', '');
INSERT INTO `dict_info` VALUES ('3', 'blog_type', '3', 'Python', '3', '2017-12-25 16:08:16', '2017-12-26 15:02:53', 'apk/201712/a42626e2-1572-4ede-832f-d874af343a1a.ico', 'py');
INSERT INTO `dict_info` VALUES ('4', 'blog_type', '4', 'MYSQL', '4', '2017-12-28 10:42:01', null, 'apk/201712/fa12031b-6c73-467e-a996-5ef8760f9f44.png', 'mysql');

-- ----------------------------
-- Table structure for file_svr_info
-- ----------------------------
DROP TABLE IF EXISTS `file_svr_info`;
CREATE TABLE `file_svr_info` (
  `id` int(11) DEFAULT NULL,
  `old_name` varchar(100) DEFAULT NULL COMMENT '文件原始名称',
  `save_name` varchar(150) DEFAULT NULL COMMENT '文件存储名称',
  `full_path` varchar(400) DEFAULT NULL COMMENT '文件本地存储全路径',
  `http_relative_path` varchar(500) DEFAULT NULL COMMENT '文件访问相对路径  (不带http://xxxx/)',
  `http_down_url` varchar(500) DEFAULT NULL COMMENT '文件下载路径.action-计算下载次数(不带http://xxxx/)',
  `file_size` int(11) DEFAULT NULL,
  `save_date` varchar(50) DEFAULT NULL,
  `file_md5` varchar(100) DEFAULT NULL,
  `down_nums` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件服务器使用的记录表，记录上传的文件存储信息';

-- ----------------------------
-- Records of file_svr_info
-- ----------------------------

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `managerid` varchar(30) DEFAULT NULL,
  `managername` varchar(50) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `annotation` varchar(50) DEFAULT NULL,
  `createby` varchar(30) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `updatedby` varchar(30) DEFAULT NULL,
  `updateddate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('root', 'root', 'C8837B23FF8AAA8A2DDE915473CE0991', '0', '0', '2016-10-17 18:52:07', '', '2016-05-12 15:38:55');

-- ----------------------------
-- Table structure for manager_role
-- ----------------------------
DROP TABLE IF EXISTS `manager_role`;
CREATE TABLE `manager_role` (
  `manager_id` varchar(50) NOT NULL,
  `role_id` varchar(50) NOT NULL,
  PRIMARY KEY (`manager_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员-角色对应关系';

-- ----------------------------
-- Records of manager_role
-- ----------------------------
INSERT INTO `manager_role` VALUES ('root', 'root');

-- ----------------------------
-- Table structure for menu_info
-- ----------------------------
DROP TABLE IF EXISTS `menu_info`;
CREATE TABLE `menu_info` (
  `menu_id` varchar(20) NOT NULL,
  `menu_orderid` varchar(10) DEFAULT NULL,
  `menu_parentid` varchar(20) DEFAULT NULL,
  `menu_name` varchar(30) DEFAULT NULL,
  `menu_url` varchar(120) DEFAULT NULL,
  `menu_ico` varchar(100) DEFAULT NULL,
  `menu_group` varchar(50) DEFAULT NULL COMMENT '菜单分组名称-同组的列在一个下拉分组',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_info
-- ----------------------------
INSERT INTO `menu_info` VALUES ('blog', '2', 'p1', '文章管理', 'page/blog/', null, null);
INSERT INTO `menu_info` VALUES ('blogindex', '3', null, '文章首页', 'public/index', null, null);
INSERT INTO `menu_info` VALUES ('blogtype', '1', 'p1', '文章类型管理', 'page/btype/', null, null);
INSERT INTO `menu_info` VALUES ('p1', '1', null, '文章管理', null, null, null);
INSERT INTO `menu_info` VALUES ('p2', '0', null, '访问统计', null, null, null);
INSERT INTO `menu_info` VALUES ('p3', '2', null, '用户管理', null, null, null);
INSERT INTO `menu_info` VALUES ('p4', '3', null, '系统', null, null, null);
INSERT INTO `menu_info` VALUES ('privilege', '2', 'p4', '角色管理', 'page/privilege/', null, '');
INSERT INTO `menu_info` VALUES ('stastic', '1', 'p2', '页面访问统计', 'page/stastic/', null, null);
INSERT INTO `menu_info` VALUES ('user', '3', 'p3', '用户管理', 'page/user', null, null);

-- ----------------------------
-- Table structure for mysql_pro_log
-- ----------------------------
DROP TABLE IF EXISTS `mysql_pro_log`;
CREATE TABLE `mysql_pro_log` (
  `log` varchar(200) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储mysql统计存储过程的执行日志';

-- ----------------------------
-- Records of mysql_pro_log
-- ----------------------------
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-28 17 start...', '2017-12-28 17:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-28 start...', '2017-12-28 17:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-28 done...', '2017-12-28 17:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-28 start...', '2017-12-28 18:49:21');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-28 done...', '2017-12-28 18:49:22');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-28 18 start...', '2017-12-28 18:49:40');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-28 18 done...', '2017-12-28 18:49:41');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-28 18 start...', '2017-12-28 18:52:11');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-28 18 done...', '2017-12-28 18:52:12');
INSERT INTO `mysql_pro_log` VALUES ('***month-2017-12 start...', '2017-12-28 18:52:49');
INSERT INTO `mysql_pro_log` VALUES ('***month-2017-12 done...', '2017-12-28 18:52:49');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-28 18 start...', '2017-12-28 18:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-28 18 done...', '2017-12-28 18:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-28 start...', '2017-12-28 18:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-28 done...', '2017-12-28 18:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-28 19 start...', '2017-12-28 19:01:45');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-28 19 done...', '2017-12-28 19:01:46');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-28 19 start...', '2017-12-28 19:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-28 19 done...', '2017-12-28 19:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-28 start...', '2017-12-28 19:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-28 done...', '2017-12-28 19:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-28 20 start...', '2017-12-28 20:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-28 20 done...', '2017-12-28 20:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-28 start...', '2017-12-28 20:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-28 done...', '2017-12-28 20:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-28 21 start...', '2017-12-28 21:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-28 21 done...', '2017-12-28 21:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-28 start...', '2017-12-28 21:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-28 done...', '2017-12-28 21:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-28 22 start...', '2017-12-28 22:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-28 22 done...', '2017-12-28 22:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-28 start...', '2017-12-28 22:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-28 done...', '2017-12-28 22:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-28 23 start...', '2017-12-28 23:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-28 23 done...', '2017-12-28 23:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-28 start...', '2017-12-28 23:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-28 done...', '2017-12-28 23:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 00 start...', '2017-12-29 00:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 00 done...', '2017-12-29 00:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 start...', '2017-12-29 00:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 done...', '2017-12-29 00:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 01 start...', '2017-12-29 01:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 01 done...', '2017-12-29 01:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 start...', '2017-12-29 01:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 done...', '2017-12-29 01:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 02 start...', '2017-12-29 02:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 02 done...', '2017-12-29 02:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 start...', '2017-12-29 02:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 done...', '2017-12-29 02:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 03 start...', '2017-12-29 03:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 03 done...', '2017-12-29 03:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 start...', '2017-12-29 03:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 done...', '2017-12-29 03:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 04 start...', '2017-12-29 04:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 04 done...', '2017-12-29 04:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 start...', '2017-12-29 04:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 done...', '2017-12-29 04:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 05 start...', '2017-12-29 05:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 05 done...', '2017-12-29 05:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 start...', '2017-12-29 05:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 done...', '2017-12-29 05:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 06 start...', '2017-12-29 06:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 06 done...', '2017-12-29 06:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 start...', '2017-12-29 06:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 done...', '2017-12-29 06:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 07 start...', '2017-12-29 07:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 07 done...', '2017-12-29 07:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 start...', '2017-12-29 07:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 done...', '2017-12-29 07:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 08 start...', '2017-12-29 08:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 08 done...', '2017-12-29 08:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 start...', '2017-12-29 08:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 done...', '2017-12-29 08:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 09 start...', '2017-12-29 09:28:58');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 09 done...', '2017-12-29 09:28:58');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 09 start...', '2017-12-29 09:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 09 done...', '2017-12-29 09:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 start...', '2017-12-29 09:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 done...', '2017-12-29 09:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 10 start...', '2017-12-29 10:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 10 done...', '2017-12-29 10:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 start...', '2017-12-29 10:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 done...', '2017-12-29 10:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 11 start...', '2017-12-29 11:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 11 done...', '2017-12-29 11:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 start...', '2017-12-29 11:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 done...', '2017-12-29 11:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 12 start...', '2017-12-29 12:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 12 done...', '2017-12-29 12:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 start...', '2017-12-29 12:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 done...', '2017-12-29 12:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 13 start...', '2017-12-29 13:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 13 done...', '2017-12-29 13:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 start...', '2017-12-29 13:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 done...', '2017-12-29 13:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 14 start...', '2017-12-29 14:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 14 done...', '2017-12-29 14:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 start...', '2017-12-29 14:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 done...', '2017-12-29 14:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 15 start...', '2017-12-29 15:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 15 done...', '2017-12-29 15:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 start...', '2017-12-29 15:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 done...', '2017-12-29 15:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 16 start...', '2017-12-29 16:59:00');
INSERT INTO `mysql_pro_log` VALUES ('***hour-2017-12-29 16 done...', '2017-12-29 16:59:01');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 start...', '2017-12-29 16:59:30');
INSERT INTO `mysql_pro_log` VALUES ('***day-2017-12-29 done...', '2017-12-29 16:59:30');

-- ----------------------------
-- Table structure for replay_info
-- ----------------------------
DROP TABLE IF EXISTS `replay_info`;
CREATE TABLE `replay_info` (
  `recordid` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `blogimei` varchar(256) DEFAULT NULL,
  `replay_recordid` int(11) DEFAULT '0' COMMENT '回复id号',
  `content` text,
  `userid` varchar(1000) DEFAULT NULL COMMENT 'yonghuid 串或者email',
  `create_date` datetime DEFAULT NULL,
  `user_blog` varchar(250) DEFAULT NULL COMMENT '用户url,blog,email等',
  KEY `Index_1` (`recordid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='评论';

-- ----------------------------
-- Records of replay_info
-- ----------------------------
INSERT INTO `replay_info` VALUES ('1', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '0', '311111111111111111111111111', '131231', '2017-12-28 13:00:38', null);
INSERT INTO `replay_info` VALUES ('2', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '0', '313123131231231', '1231231', '2017-12-28 13:01:10', null);
INSERT INTO `replay_info` VALUES ('3', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '0', '%3Cscript%3Ealert%281%29%3C/script%3E%3Cscript%3Ealert%281%29%3C/script%3E', '%3Cscript%3Ealert%281%29%3C/script%3E', '2017-12-28 13:01:44', null);
INSERT INTO `replay_info` VALUES ('4', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '0', '2sssssssssssssssssssssss', 'ddd', '2017-12-28 13:43:27', null);
INSERT INTO `replay_info` VALUES ('5', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '0', '2sssssssssssssssssssssss', 'ddd', '2017-12-28 13:43:30', null);
INSERT INTO `replay_info` VALUES ('6', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '0', 'xxxxxxxxxxxxxxxxxxx', 'cc', '2017-12-28 13:47:52', null);
INSERT INTO `replay_info` VALUES ('7', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '0', '12312311111111111', 'vvvv', '2017-12-28 13:49:20', null);
INSERT INTO `replay_info` VALUES ('8', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '5', '%u6211%u6765%u7559%u8A00%u4E00%u5929%u4E0B%20ad', '%u65E0%u654C%u5C31%u662F%u5BC2%u5BDE', '2017-12-28 13:51:30', null);
INSERT INTO `replay_info` VALUES ('9', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '8', '%u662F%u4E0D%u662F%u554A%uFF0C%u6211%u4E5F%u8BD5%u8BD5111', '%u5BF9%u5BF9%u5BF9', '2017-12-28 13:55:42', null);
INSERT INTO `replay_info` VALUES ('10', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '4', '%u5F1F%u5F1F%u9876%u9876%u9876%u9876%u9876%u9876%u9876%u9876%u9876%u9876%u9876%u9876%u9876%u9876%u9876%u9876%u9876%u9876%u9876%u9876', '%u5BF9%u5BF9%u5BF9', '2017-12-28 13:56:28', null);
INSERT INTO `replay_info` VALUES ('11', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '8', '%u53D1%u6C34%u7535%u8D39%u6C34%u7535%u8D39%u7535%u8D39%u6C34%u7535%u8D39%u7535%u8D39%u6C34%u7535%u8D39', '%u7684', '2017-12-28 13:59:01', null);
INSERT INTO `replay_info` VALUES ('12', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '10', '1233333333333333333', '123123', '2017-12-28 14:08:32', null);
INSERT INTO `replay_info` VALUES ('13', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '7', '12311111111111111111111', '33', '2017-12-28 14:09:02', null);
INSERT INTO `replay_info` VALUES ('14', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '13', '333333333333333333333333', '44', '2017-12-28 14:09:11', null);
INSERT INTO `replay_info` VALUES ('15', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '0', 'blog%u4E0D%u9519%uFF0C%u4E92%u8BBF%7E%7E', '%u80E1%u4E00%u5200', '2017-12-28 14:40:07', null);
INSERT INTO `replay_info` VALUES ('16', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '15', '%u5927%u4FA0%u62DC%u8BBF%uFF01%uFF01%uFF01%uFF0111111', '%u5F20%u4E09%u4E30', '2017-12-28 14:43:00', null);
INSERT INTO `replay_info` VALUES ('17', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '16', 'ddddddddddddddddddddddddddddddddddddddddddddddd', '%u4E54%u5CF0', '2017-12-28 14:46:09', 'qfeng.net');
INSERT INTO `replay_info` VALUES ('18', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '16', '%u662F%u7684%u53D1%u9001%u5230%u53D1%u9001%u5230%u6B7B%u662F%u591A%u5C11', '%u865A%u7AF9', '2017-12-28 14:54:28', '');
INSERT INTO `replay_info` VALUES ('19', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '18', '%u662F%u7684%u53D1%u9001%u5230%u53D1%u9001%u5230%u6B7B%u662F%u591A%u5C11', '%u6C34%u7535%u8D39%u6C34%u7535%u8D39', '2017-12-28 14:55:54', '');
INSERT INTO `replay_info` VALUES ('20', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '16', '12111111111111111111111111111111', '123123', '2017-12-28 14:59:58', '%u5BF9%u5BF9%u5BF9111123');
INSERT INTO `replay_info` VALUES ('21', 'b95ee7f5-8cd1-471b-8ba8-ad1fb6e9a4ab', '15', 'sd%20sdfdsd%20sdfdsd%20sdfdsd%20sdfdsd%20sdfd', '%u662F%u7684%u53D1%u9001%u5230', '2017-12-28 15:00:53', '%3Cscript%3Ealert%281%29%3C/script%3E');
INSERT INTO `replay_info` VALUES ('22', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b', '0', 'contextcontextcontext', 'test', '2017-12-28 15:52:29', '');
INSERT INTO `replay_info` VALUES ('23', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b', '0', 'contextcontextcontext', 'test', '2017-12-28 15:53:09', '');
INSERT INTO `replay_info` VALUES ('24', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b', '0', 'contextcontextcontext', 'test', '2017-12-28 15:53:09', '');
INSERT INTO `replay_info` VALUES ('25', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b', '0', 'contextcontextcontext', 'test', '2017-12-28 15:53:09', '');
INSERT INTO `replay_info` VALUES ('26', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b', '0', 'contextcontextcontext', 'test', '2017-12-28 15:53:10', '');
INSERT INTO `replay_info` VALUES ('27', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b', '0', 'contextcontextcontext', 'test', '2017-12-28 15:53:10', '');
INSERT INTO `replay_info` VALUES ('28', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b', '0', 'contextcontextcontext', 'test', '2017-12-28 15:53:10', '');
INSERT INTO `replay_info` VALUES ('29', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b', '0', 'contextcontextcontext', 'test', '2017-12-28 15:53:10', '');
INSERT INTO `replay_info` VALUES ('30', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b', '0', 'contextcontextcontext', 'test', '2017-12-28 15:53:10', '');
INSERT INTO `replay_info` VALUES ('31', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b', '0', 'contextcontextcontext', 'test', '2017-12-28 15:53:11', '');
INSERT INTO `replay_info` VALUES ('32', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b', '0', 'contextcontextcontext', 'test', '2017-12-28 15:53:11', '');
INSERT INTO `replay_info` VALUES ('33', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b', '0', 'contextcontextcontext', 'test', '2017-12-28 15:54:25', '');
INSERT INTO `replay_info` VALUES ('34', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b', '0', 'contextcontextcontext', 'test', '2017-12-28 15:55:19', '');
INSERT INTO `replay_info` VALUES ('35', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b', '0', 'contextcontextcontext', 'test', '2017-12-28 15:55:24', '');
INSERT INTO `replay_info` VALUES ('36', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b', '0', 'contextcontextcontext', 'test', '2017-12-28 15:55:24', '');
INSERT INTO `replay_info` VALUES ('37', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b', '0', 'contextcontextcontext', 'test', '2017-12-28 16:06:22', '');
INSERT INTO `replay_info` VALUES ('38', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b', '0', 'contextcontextcontext', 'test', '2017-12-28 16:06:35', '');
INSERT INTO `replay_info` VALUES ('39', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b', '0', 'contextcontextcontext', 'test', '2017-12-28 16:06:42', '');
INSERT INTO `replay_info` VALUES ('40', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b', '0', 'contextcontextcontext', 'test', '2017-12-28 16:06:43', '');
INSERT INTO `replay_info` VALUES ('41', '479d6e1f-ed72-4a9b-9cf0-38cd05360b1b', '0', 'contextcontextcontext', 'test', '2017-12-28 16:07:34', '');

-- ----------------------------
-- Table structure for request_info
-- ----------------------------
DROP TABLE IF EXISTS `request_info`;
CREATE TABLE `request_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(30) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `action_type` varchar(30) DEFAULT NULL COMMENT '请求类型',
  `sessionid` varchar(30) DEFAULT NULL,
  `uri` varchar(30) DEFAULT NULL,
  `createTime` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='请求数记录';

-- ----------------------------
-- Records of request_info
-- ----------------------------
INSERT INTO `request_info` VALUES ('41', '127.0.0.1', '', 'replay_addOrUpdate', null, '/kb/replay/addOrUpdate.do', '2017-12-28 16:07:34');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_en` varchar(50) NOT NULL,
  `role_zh` varchar(50) DEFAULT NULL,
  `role_desc` varchar(250) DEFAULT NULL,
  `createby` varchar(30) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `updatedby` varchar(30) DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  PRIMARY KEY (`role_en`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('normal_login_user', '普通登录用户', '普通登录用户', 'root', '2017-12-26 14:04:28', null, null);
INSERT INTO `role` VALUES ('normal_user', '普通用户', '未登录普通用户', 'root', '2017-12-25 17:56:40', null, '2017-12-25 18:03:30');
INSERT INTO `role` VALUES ('root', '超级管理员', '1', null, null, null, '2017-12-25 14:06:03');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `role_id` varchar(50) NOT NULL COMMENT '角色id,',
  `menu_id` varchar(200) DEFAULT NULL COMMENT '角色可见菜单id集合  逗号分隔'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='简单角色-菜单对应关系';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('normal_user', 'blogindex');
INSERT INTO `role_menu` VALUES ('normal_login_user', 'blogindex');

-- ----------------------------
-- Table structure for user_action_day
-- ----------------------------
DROP TABLE IF EXISTS `user_action_day`;
CREATE TABLE `user_action_day` (
  `type_first` varchar(20) DEFAULT NULL COMMENT '操作大类（办事指南/应用服务）',
  `type_second` varchar(20) DEFAULT NULL COMMENT '具体的操作项ID',
  `action_date` varchar(20) DEFAULT NULL COMMENT '日期时间 2016-09-01',
  `total_click` double DEFAULT NULL COMMENT '累积点击次数 每次访问都算一次',
  `total_uv` double DEFAULT NULL COMMENT '独立IP用户点击次数-uv ,每个独立IP0-24小时算一次'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户操作统计表-天';

-- ----------------------------
-- Records of user_action_day
-- ----------------------------
INSERT INTO `user_action_day` VALUES ('detailpag', '', '2017-12-28 18', '0', '0');
INSERT INTO `user_action_day` VALUES ('homepage', '', '2017-12-28 18', '0', '0');
INSERT INTO `user_action_day` VALUES ('detailpag', 'MYSQL', '2017-12-28 18', '0', '0');
INSERT INTO `user_action_day` VALUES ('detailpag', 'python', '2017-12-28 18', '0', '0');
INSERT INTO `user_action_day` VALUES ('homepage', 'index', '2017-12-28 18', '0', '0');
INSERT INTO `user_action_day` VALUES ('detailpag', '', '2017-12-28', '3', '1');
INSERT INTO `user_action_day` VALUES ('homepage', '', '2017-12-28', '3', '1');
INSERT INTO `user_action_day` VALUES ('detailpag', 'MYSQL', '2017-12-28', '2', '1');
INSERT INTO `user_action_day` VALUES ('detailpag', 'python', '2017-12-28', '1', '1');
INSERT INTO `user_action_day` VALUES ('homepage', 'index', '2017-12-28', '3', '1');
INSERT INTO `user_action_day` VALUES ('detailpag', '', '2017-12-29', '13', '1');
INSERT INTO `user_action_day` VALUES ('homepage', '', '2017-12-29', '91', '1');
INSERT INTO `user_action_day` VALUES ('detailpag', 'JAVA', '2017-12-29', '3', '1');
INSERT INTO `user_action_day` VALUES ('detailpag', 'MYSQL', '2017-12-29', '4', '1');
INSERT INTO `user_action_day` VALUES ('detailpag', 'Python', '2017-12-29', '3', '1');
INSERT INTO `user_action_day` VALUES ('detailpag', '日常', '2017-12-29', '3', '1');
INSERT INTO `user_action_day` VALUES ('homepage', 'index', '2017-12-29', '91', '1');

-- ----------------------------
-- Table structure for user_action_hour
-- ----------------------------
DROP TABLE IF EXISTS `user_action_hour`;
CREATE TABLE `user_action_hour` (
  `type_first` varchar(20) DEFAULT NULL COMMENT '操作大类（办事指南/应用服务）',
  `type_second` varchar(20) DEFAULT NULL COMMENT '具体的操作项ID',
  `action_date` varchar(20) DEFAULT NULL COMMENT '日期时间 2016-09-01 01',
  `total_click` double DEFAULT NULL COMMENT '累积点击次数 每次访问都算一次',
  `total_uv` double DEFAULT NULL COMMENT '独立IP用户点击次数-uv ,每个独立IP0-24小时算一次'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户操作统计表-小时';

-- ----------------------------
-- Records of user_action_hour
-- ----------------------------
INSERT INTO `user_action_hour` VALUES ('detailpag', '', '2017-12-28 18', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', '', '2017-12-28 18', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-28 18', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-28 18', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-28 18', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-28 19', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-28 19', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-28 19', '1', '1');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-28 20', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-28 20', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-28 20', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-28 21', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-28 21', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-28 21', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-28 22', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-28 22', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-28 22', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-28 23', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-28 23', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-28 23', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-29 00', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-29 00', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-29 00', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-29 01', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-29 01', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-29 01', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-29 02', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-29 02', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-29 02', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-29 03', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-29 03', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-29 03', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-29 04', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-29 04', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-29 04', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-29 05', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-29 05', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-29 05', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-29 06', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-29 06', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-29 06', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-29 07', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-29 07', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-29 07', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-29 08', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-29 08', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-29 08', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-29 09', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-29 09', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-29 09', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-29 10', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-29 10', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-29 10', '3', '1');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-29 11', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-29 11', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-29 11', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-29 12', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-29 12', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-29 12', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'JAVA', '2017-12-29 13', '2', '1');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-29 13', '1', '1');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'Python', '2017-12-29 13', '2', '1');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-29 13', '4', '1');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'JAVA', '2017-12-29 14', '1', '1');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-29 14', '3', '1');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'Python', '2017-12-29 14', '1', '1');
INSERT INTO `user_action_hour` VALUES ('detailpag', '日常', '2017-12-29 14', '1', '1');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-29 14', '13', '1');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'JAVA', '2017-12-29 15', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-29 15', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-29 15', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', '日常', '2017-12-29 15', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-29 15', '4', '1');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'JAVA', '2017-12-29 16', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'MYSQL', '2017-12-29 16', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', 'python', '2017-12-29 16', '0', '0');
INSERT INTO `user_action_hour` VALUES ('detailpag', '日常', '2017-12-29 16', '0', '0');
INSERT INTO `user_action_hour` VALUES ('homepage', 'index', '2017-12-29 16', '62', '1');

-- ----------------------------
-- Table structure for user_action_log
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log`;
CREATE TABLE `user_action_log` (
  `userid` varchar(30) DEFAULT NULL COMMENT 'user表id- 访问ip',
  `action_date` varchar(20) DEFAULT NULL COMMENT '操作时间  2016-01-01 22:11:21',
  `type_first` varchar(20) DEFAULT NULL COMMENT '操作大类； 办事指南/应用服务',
  `type_second` varchar(20) DEFAULT NULL COMMENT '具体的项ID,',
  `city` varchar(30) DEFAULT NULL,
  KEY `Index_1` (`action_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户操作点击记录，统计各个大小类的各时间粒度的原始数据';

-- ----------------------------
-- Records of user_action_log
-- ----------------------------
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-28 16:35:39', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-28 16:35:50', 'detailpag', 'MYSQL', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-28 16:36:06', 'detailpag', 'MYSQL', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-28 16:36:07', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-28 16:36:06', 'detailpag', 'python', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-28 19:43:52', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 10:06:01', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 10:48:09', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 10:48:13', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 13:56:02', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 13:56:07', 'detailpag', 'Python', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 13:56:16', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 13:56:22', 'detailpag', 'JAVA', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 13:56:25', 'detailpag', 'Python', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 13:56:29', 'detailpag', 'JAVA', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 13:57:30', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 13:57:52', 'detailpag', 'MYSQL', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 13:57:54', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 13:59:10', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 13:59:16', 'detailpag', '日常', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 13:59:18', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 14:05:06', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 14:06:03', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 14:06:54', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 14:07:39', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 14:09:35', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 14:09:38', 'detailpag', '日常', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 14:09:41', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 14:09:44', 'detailpag', 'MYSQL', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 14:10:01', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 14:10:06', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 14:10:10', 'detailpag', 'JAVA', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 14:10:16', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 14:11:03', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 14:11:11', 'detailpag', 'MYSQL', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 14:11:17', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 14:11:20', 'detailpag', 'MYSQL', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 14:11:22', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 14:11:25', 'detailpag', 'Python', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 14:11:27', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 15:56:21', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 15:56:59', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 15:58:10', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 15:58:26', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 15:59:28', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 15:59:32', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:01:08', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:01:13', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:04:31', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:04:37', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:05:10', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:05:33', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:05:47', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:06:02', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:06:08', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:06:12', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:06:14', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:06:18', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:09:46', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:09:56', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:12:17', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:12:48', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:16:52', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:37:02', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:37:23', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:38:28', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:39:09', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:39:30', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:40:15', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:40:17', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:40:20', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:40:24', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:40:30', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:40:36', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:42:42', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:42:45', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:42:53', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:42:57', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:43:45', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:44:49', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:44:52', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:45:41', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:45:45', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:45:51', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:47:43', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:47:47', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:47:52', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:47:59', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:50:38', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:50:41', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:50:48', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:50:50', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:51:32', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:51:34', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:51:38', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:53:40', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:53:42', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:53:45', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:54:37', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:54:43', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:54:48', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:54:51', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:54:56', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:54:59', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:55:02', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:55:09', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:55:24', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:56:13', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:59:12', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 16:59:19', 'detailpag', '日常', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:00:50', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:06:08', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:06:11', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:06:14', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:06:18', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:13:33', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:13:39', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:13:43', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:13:52', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:14:27', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:14:48', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:15:49', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:15:55', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:16:01', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:24:36', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:24:53', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:25:15', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:25:17', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:25:20', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:25:22', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:26:39', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:26:42', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:26:44', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:26:46', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:26:54', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:27:16', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:27:21', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:27:24', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:31:28', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:31:33', 'detailpag', 'MYSQL', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:31:36', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:31:40', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:31:43', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:31:46', 'detailpag', 'Python', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:31:54', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:32:00', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:32:03', 'detailpag', 'JAVA', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:32:06', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:32:18', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:32:21', 'homepage', 'index', null);
INSERT INTO `user_action_log` VALUES ('127.0.0.1', '2017-12-29 17:32:22', 'homepage', 'index', null);

-- ----------------------------
-- Table structure for user_action_log_template
-- ----------------------------
DROP TABLE IF EXISTS `user_action_log_template`;
CREATE TABLE `user_action_log_template` (
  `type_first` varchar(20) DEFAULT NULL,
  `type_second` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_action_log_template
-- ----------------------------
INSERT INTO `user_action_log_template` VALUES ('homepage', 'index', 'homepage/index');
INSERT INTO `user_action_log_template` VALUES ('detailpag', 'MYSQL', 'detailpag/MYSQL');
INSERT INTO `user_action_log_template` VALUES ('detailpag', 'python', 'detailpag/python');
INSERT INTO `user_action_log_template` VALUES ('detailpag', 'JAVA', 'detailpag/JAVA');
INSERT INTO `user_action_log_template` VALUES ('detailpag', '日常', 'detailpag/日常');

-- ----------------------------
-- Table structure for user_action_month
-- ----------------------------
DROP TABLE IF EXISTS `user_action_month`;
CREATE TABLE `user_action_month` (
  `type_first` varchar(20) DEFAULT NULL COMMENT '操作大类（办事指南/应用服务）',
  `type_second` varchar(20) DEFAULT NULL COMMENT '具体的操作项ID',
  `action_date` varchar(20) DEFAULT NULL COMMENT '日期时间 2016-09',
  `total_click` double DEFAULT NULL COMMENT '累积点击次数 每次访问都算一次',
  `total_uv` double DEFAULT NULL COMMENT '独立IP用户点击次数-uv ,每个独立IP0-24小时算一次'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户操作统计表-月';

-- ----------------------------
-- Records of user_action_month
-- ----------------------------
INSERT INTO `user_action_month` VALUES ('detailpag', '', '2017-12', '3', '1');
INSERT INTO `user_action_month` VALUES ('homepage', '', '2017-12', '2', '1');
INSERT INTO `user_action_month` VALUES ('detailpag', 'MYSQL', '2017-12', '2', '1');
INSERT INTO `user_action_month` VALUES ('detailpag', 'python', '2017-12', '1', '1');
INSERT INTO `user_action_month` VALUES ('homepage', 'index', '2017-12', '2', '1');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_name` varchar(256) DEFAULT NULL,
  `recordid` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `desc_info` varchar(256) DEFAULT NULL COMMENT 'desc',
  `pass` varchar(64) DEFAULT NULL COMMENT 'pass',
  `accountid` varchar(32) DEFAULT NULL COMMENT 'userid',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  KEY `Index_1` (`recordid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='普通用户账号';

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('啊啊', '1', '122', '1', '1', '2017-12-25 11:22:52', '2017-12-25 11:40:46');
INSERT INTO `user_info` VALUES ('2', '2', '2', '1', 'test', '2017-12-25 11:41:18', '2017-12-25 14:06:10');
INSERT INTO `user_info` VALUES ('asan1', '3', '11', '123321', 'test@123.com', '2017-12-25 11:44:19', null);

-- ----------------------------
-- Table structure for user_token
-- ----------------------------
DROP TABLE IF EXISTS `user_token`;
CREATE TABLE `user_token` (
  `token` varchar(50) NOT NULL,
  `userid` varchar(100) NOT NULL,
  `createdate` datetime NOT NULL,
  `info` varchar(1024) DEFAULT NULL,
  UNIQUE KEY `token_UNIQUE` (`token`) USING BTREE,
  UNIQUE KEY `userid_UNIQUE` (`userid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_token
-- ----------------------------
INSERT INTO `user_token` VALUES ('1lhv9ttz31sdcdj2oi4jdv0oj', 'root', '2017-12-29 13:48:18', 'root|2');

-- ----------------------------
-- Procedure structure for pro_action_day
-- ----------------------------
DROP PROCEDURE IF EXISTS `pro_action_day`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `pro_action_day`(IN indate varchar(30))
begin


declare v_finished INTEGER DEFAULT 0;
	 declare _Done int default 0;

 declare _type_first varchar(30) default "";
 declare _type_second varchar(30) default "";
 declare _hour varchar(30) default "";
declare _num_pv double default 0;
declare _num_uv double default 0;

declare _tp_pv double default 0;
declare _tp_uv double default 0;


declare _error_pv double(8,4) default 0;
declare _error_uv double(8,4) default 0;

declare _total_user_num int default 0;


 DECLARE _CurTypeTwo CURSOR FOR 
select t2.type_first,t2.type_second,t2.hour,sum(t2.pv) pv,sum(t2.uv) uv from 
(
select type_first,type_second,hour,
max(case type when 'pv' then num else 0 end) 'pv',
        max(case type when 'uv' then num else 0 end) 'uv'
from (
-- uv其他
select t.type_first,t.type_second,'uv' type,t.hour ,count(*) num from (
select type_first,type_second,userid, count(*) total_click,date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m-%d') hour  from user_action_log  g where   1=1
and date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m-%d')=indate
group by type_first,type_second,userid
)  t group by t.type_second,type_first,hour

union
(
select type_first,type_second,'pv' type, date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m-%d') hour , count(*) num 
from user_action_log  g
 where   1=1
and date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m-%d')=indate

group by type_first,type_second
)

)tb2
group by type_first,type_second,hour

union all
(select DISTINCT type_first,type_second,indate hour,0 pv,0 uv from  user_action_log)
) t2 group by type_first,type_second,hour;


 DECLARE _CurTypeOne CURSOR FOR 
select t2.type_first,t2.type_second,t2.hour,sum(t2.pv) pv,sum(t2.uv) uv from 
(
select type_first,type_second,hour,
max(case type when 'pv' then num else 0 end) 'pv',
        max(case type when 'uv' then num else 0 end) 'uv'
from (
-- uv其他
select t.type_first,'' type_second,'uv' type,t.hour ,count(*) num from (
select type_first,type_second,userid, count(*) total_click,date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m-%d') hour  from user_action_log  g where   1=1
and date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m-%d')=indate
group by type_first,userid
)  t group by type_first,hour

union
(
select type_first,'' type_second,'pv' type, date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m-%d') hour , count(*) num 
from user_action_log  g
 where   1=1
and date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m-%d')=indate

group by type_first
)
)tb2 group by type_first,type_second,hour

union all
(select DISTINCT type_first,'' type_second,indate hour,0 pv,0 uv from  user_action_log)
) t2 group by type_first,type_second,hour;




DECLARE CONTINUE HANDLER 
FOR NOT FOUND SET v_finished = 1;

 

insert into  mysql_pro_log  values(concat('***day-',indate,' start...'),now());

delete from user_action_day where action_date=indate;

OPEN _CurTypeOne;
dowork: LOOP
 FETCH _CurTypeOne INTO  _type_first,_type_second,_hour,_num_pv,_num_uv;
 IF v_finished = 1 THEN 
 LEAVE dowork;
 END IF;
 -- action

 	insert into user_action_day values(_type_first,_type_second,_hour,_num_pv,_num_uv);

END LOOP dowork;
 CLOSE _CurTypeOne;


set v_finished=0;

-- 普通的统计
OPEN _CurTypeTwo;
dowork2: LOOP
 FETCH _CurTypeTwo INTO  _type_first,_type_second,_hour,_num_pv,_num_uv;
 IF v_finished = 1 THEN 
 LEAVE dowork2;
 END IF;
 -- action

 	insert into user_action_day values(_type_first,_type_second,_hour,_num_pv,_num_uv);

END LOOP dowork2;
 CLOSE _CurTypeTwo;




insert into  mysql_pro_log   values(concat('***day-',indate,' done...'),now());




end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pro_action_hour
-- ----------------------------
DROP PROCEDURE IF EXISTS `pro_action_hour`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `pro_action_hour`(IN indate varchar(30))
begin


declare v_finished INTEGER DEFAULT 0;
	 declare _Done int default 0;

 declare _type_first varchar(30) default "";
 declare _type_second varchar(30) default "";
 declare _hour varchar(30) default "";
declare _num_pv double default 0;
declare _num_uv double default 0;

declare _tp_pv double default 0;
declare _tp_uv double default 0;


declare _error_pv double(8,4) default 0;
declare _error_uv double(8,4) default 0;

declare _total_user_num int default 0;


DECLARE _CurType CURSOR FOR 
select distinct type_first,type_second  from  user_action_log;



 DECLARE _CurTypeTwo CURSOR FOR 
select t2.type_first,t2.type_second,t2.hour,sum(t2.pv) pv,sum(t2.uv) uv from 
(
select type_first,type_second,hour,
max(case type when 'pv' then num else 0 end) 'pv',
        max(case type when 'uv' then num else 0 end) 'uv'
from (
-- uv其他
select t.type_first,t.type_second,'uv' type,t.hour ,count(*) num from (
select type_first,type_second,userid, count(*) total_click,date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m-%d %H') hour  from user_action_log  g where   1=1
and date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m-%d %H')=indate
group by type_first,type_second,userid
)  t group by t.type_second,type_first,hour

union
(
select type_first,type_second,'pv' type, date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m-%d %H') hour , count(*) num 
from user_action_log  g
 where   1=1
and date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m-%d %H')=indate

group by type_first,type_second
)

)tb2
group by type_first,type_second,hour

union all
(select DISTINCT type_first,type_second,indate hour,0 pv,0 uv from  user_action_log)
) t2 group by type_first,type_second,hour;


 DECLARE _CurTypeOne CURSOR FOR 
select t2.type_first,t2.type_second,t2.hour,sum(t2.pv) pv,sum(t2.uv) uv from 
(
select type_first,type_second,hour,
max(case type when 'pv' then num else 0 end) 'pv',
        max(case type when 'uv' then num else 0 end) 'uv'
from (
-- uv其他
select t.type_first,'' type_second,'uv' type,t.hour ,count(*) num from (
select type_first,type_second,userid, count(*) total_click,date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m-%d %H') hour  from user_action_log  g where   1=1
and date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m-%d %H')=indate
group by type_first,userid
)  t group by type_first,hour

union
(
select type_first,'' type_second,'pv' type, date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m-%d %H') hour , count(*) num 
from user_action_log  g
 where   1=1
and date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m-%d %H')=indate

group by type_first
)
)tb2 group by type_first,type_second,hour

union all
(select DISTINCT type_first,'' type_second,indate hour,0 pv,0 uv from  user_action_log)
) t2 group by type_first,type_second,hour;




DECLARE CONTINUE HANDLER 
FOR NOT FOUND SET v_finished = 1;

 

insert into  mysql_pro_log  values(concat('***hour-',indate,' start...'),now());

delete from user_action_log_template;

OPEN _CurType;
dowork: LOOP
 FETCH _CurType INTO  _type_first,_type_second;
 IF v_finished = 1 THEN 
 LEAVE dowork;
 END IF;
 -- action

 	
insert into user_action_log_template values(_type_first,_type_second,concat(_type_first,'/',_type_second));

END LOOP dowork;
 CLOSE _CurType;


delete from user_action_hour where action_date=indate;

OPEN _CurTypeOne;
dowork: LOOP
 FETCH _CurTypeOne INTO  _type_first,_type_second,_hour,_num_pv,_num_uv;
 IF v_finished = 1 THEN 
 LEAVE dowork;
 END IF;
 -- action

 	insert into user_action_hour values(_type_first,_type_second,_hour,_num_pv,_num_uv);

END LOOP dowork;
 CLOSE _CurTypeOne;


set v_finished=0;

-- 普通的统计
OPEN _CurTypeTwo;
dowork2: LOOP
 FETCH _CurTypeTwo INTO  _type_first,_type_second,_hour,_num_pv,_num_uv;
 IF v_finished = 1 THEN 
 LEAVE dowork2;
 END IF;
 -- action

 	insert into user_action_hour values(_type_first,_type_second,_hour,_num_pv,_num_uv);

END LOOP dowork2;
 CLOSE _CurTypeTwo;




insert into  mysql_pro_log   values(concat('***hour-',indate,' done...'),now());




end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for pro_action_month
-- ----------------------------
DROP PROCEDURE IF EXISTS `pro_action_month`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `pro_action_month`(IN indate varchar(30))
begin


declare v_finished INTEGER DEFAULT 0;
	 declare _Done int default 0;

 declare _type_first varchar(30) default "";
 declare _type_second varchar(30) default "";
 declare _hour varchar(30) default "";
declare _num_pv double default 0;
declare _num_uv double default 0;

declare _tp_pv double default 0;
declare _tp_uv double default 0;


declare _error_pv double(8,4) default 0;
declare _error_uv double(8,4) default 0;

declare _total_user_num int default 0;


 DECLARE _CurTypeTwo CURSOR FOR 
select t2.type_first,t2.type_second,t2.hour,sum(t2.pv) pv,sum(t2.uv) uv from 
(
select type_first,type_second,hour,
max(case type when 'pv' then num else 0 end) 'pv',
        max(case type when 'uv' then num else 0 end) 'uv'
from (
-- uv其他
select t.type_first,t.type_second,'uv' type,t.hour ,count(*) num from (
select type_first,type_second,userid, count(*) total_click,date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m') hour  from user_action_log  g where   1=1
and date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m')=indate
group by type_first,type_second,userid
)  t group by t.type_second,type_first,hour

union
(
select type_first,type_second,'pv' type, date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m') hour , count(*) num 
from user_action_log  g
 where   1=1
and date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m')=indate

group by type_first,type_second
)

)tb2
group by type_first,type_second,hour

union all
(select DISTINCT type_first,type_second,indate hour,0 pv,0 uv from  user_action_log)
) t2 group by type_first,type_second,hour;


 DECLARE _CurTypeOne CURSOR FOR 
select t2.type_first,t2.type_second,t2.hour,sum(t2.pv) pv,sum(t2.uv) uv from 
(
select type_first,type_second,hour,
max(case type when 'pv' then num else 0 end) 'pv',
        max(case type when 'uv' then num else 0 end) 'uv'
from (
-- uv其他
select t.type_first,'' type_second,'uv' type,t.hour ,count(*) num from (
select type_first,type_second,userid, count(*) total_click,date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m') hour  from user_action_log  g where   1=1
and date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m')=indate
group by type_first,userid
)  t group by type_first,hour

union
(
select type_first,'' type_second,'pv' type, date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m') hour , count(*) num 
from user_action_log  g
 where   1=1
and date_format( str_to_date(action_date,'%Y-%m-%d %H:%i:%s'),'%Y-%m')=indate

group by type_first
)
)tb2 group by type_first,type_second,hour

union all
(select DISTINCT type_first,'' type_second,indate hour,0 pv,0 uv from  user_action_log)
) t2 group by type_first,type_second,hour;




DECLARE CONTINUE HANDLER 
FOR NOT FOUND SET v_finished = 1;

 

insert into  mysql_pro_log  values(concat('***month-',indate,' start...'),now());

delete from user_action_month where action_date=indate;

OPEN _CurTypeOne;
dowork: LOOP
 FETCH _CurTypeOne INTO  _type_first,_type_second,_hour,_num_pv,_num_uv;
 IF v_finished = 1 THEN 
 LEAVE dowork;
 END IF;
 -- action

 	insert into user_action_month values(_type_first,_type_second,_hour,_num_pv,_num_uv);

END LOOP dowork;
 CLOSE _CurTypeOne;


set v_finished=0;

-- 普通的统计
OPEN _CurTypeTwo;
dowork2: LOOP
 FETCH _CurTypeTwo INTO  _type_first,_type_second,_hour,_num_pv,_num_uv;
 IF v_finished = 1 THEN 
 LEAVE dowork2;
 END IF;
 -- action

 	insert into user_action_month values(_type_first,_type_second,_hour,_num_pv,_num_uv);

END LOOP dowork2;
 CLOSE _CurTypeTwo;




insert into  mysql_pro_log   values(concat('***month-',indate,' done...'),now());




end
;;
DELIMITER ;

-- ----------------------------
-- Event structure for event_pro_action_day
-- ----------------------------
DROP EVENT IF EXISTS `event_pro_action_day`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` EVENT `event_pro_action_day` ON SCHEDULE EVERY 1 HOUR STARTS '2016-09-13 15:59:30' ON COMPLETION NOT PRESERVE ENABLE DO call pro_action_day(date_format(now(),'%Y-%m-%d'))
;;
DELIMITER ;

-- ----------------------------
-- Event structure for event_pro_action_hour
-- ----------------------------
DROP EVENT IF EXISTS `event_pro_action_hour`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` EVENT `event_pro_action_hour` ON SCHEDULE EVERY 1 HOUR STARTS '2016-09-13 16:59:00' ON COMPLETION NOT PRESERVE ENABLE DO call pro_action_hour(date_format(now(),'%Y-%m-%d %H'))
;;
DELIMITER ;

-- ----------------------------
-- Event structure for event_pro_action_month
-- ----------------------------
DROP EVENT IF EXISTS `event_pro_action_month`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` EVENT `event_pro_action_month` ON SCHEDULE EVERY 1 MONTH STARTS '2016-09-30 23:50:00' ON COMPLETION NOT PRESERVE ENABLE DO call pro_action_month(date_format(now(),'%Y-%m'))
;;
DELIMITER ;
