package com.kxjl.web.version.model;
import java.math.BigInteger;
/**
 * 升级号格式转抱
 * @author Administrator
 *
 */
public class VersionNumFormatTool {
	private BigInteger bi;
	private int singleLength = 2;//转成16进制之后最大位数
	
	/**
	 * 将升级包版本号（例如：2.0.0.1）转成10进制格式的字符串
	 * @param versionNum：升级包版本号字符串
	 * @return 10进制的版本号字符串
	 */
	public String FormatTo10(String versionNum){
		String result ="";
		try{
			String[] temp = versionNum.replace('.', '@').split("@");
			for(int i=0;i<temp.length;i++){
				String temp16 = new BigInteger(temp[i]).toString(16);
				if(temp16.length()<singleLength){
					for(int j=0;j<singleLength-temp16.length();j++){
						result+="0";
					}
					result+=temp16;
				}
				else{
					result+=temp16;
				}
			}
			bi = new BigInteger(result,16);
			result = bi.toString();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 将10进制的升级包版本号转成正常格式的版本号，例如：2.0.0.1
	 * @param versionNum10：10进制的升级包版本号
	 * @return	“2.0.0.1”形式的版本号
	 */
	public String FormatToNomarlVersionNum(String versionNum10){
		String result="";
		try{
			bi = new BigInteger(versionNum10.substring(0,versionNum10.length()-2));
			versionNum10 = bi.toString(16);
			int totalLength = singleLength*4;
			//int totalLength = singleLength*3;
			if(versionNum10.length()<totalLength){
				String temp = "";
				for(int j=0;j<(totalLength-versionNum10.length());j++){
					temp+="0";
				}
				versionNum10=temp+versionNum10;
			}
			for(int i=0;i<=versionNum10.length();i++){
				if(i>0 && i%singleLength==0){
					result+=Integer.valueOf(versionNum10.substring(i-singleLength,i), 16).toString();
					if(i!=versionNum10.length())
						result+=".";
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args)
	{
		VersionNumFormatTool v = new VersionNumFormatTool();
		String rs = v.FormatTo10("11");
		System.out.println(rs);
		System.out.println(new BigInteger("11").toString(16));
	}
}