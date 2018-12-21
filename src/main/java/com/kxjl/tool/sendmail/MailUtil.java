package com.kxjl.tool.sendmail;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import org.json.JSONArray;


import com.kxjl.tool.config.ConfigReader;

/**
 * 邮件发送工具实现类
 */
public class MailUtil {

	private static Logger logger = Logger.getLogger(MailUtil.class);

	public static boolean sendMail(String recvmail, String title, String message) {
		Mail mail = new Mail();
		mail.setHost(ConfigReader.getInstance().getProperty("MAILHOSTSERVER")); // 设置邮件服务器,如果不用163的,自己找找看相关的
		mail.setSender(ConfigReader.getInstance().getProperty("MAILSENDER"));
		mail.setReceiver(recvmail); // 接收人
		mail.setUsername(ConfigReader.getInstance().getProperty("MAILSENDER")); // 登录账号,一般都是和邮箱名一样吧

		mail.setPassword(ConfigReader.getInstance().getProperty(
				"MAILSENDERPASSWORD")); // 发件人邮箱的登录密码
		mail.setSubject(title);

		mail.setMessage(message);
		return new MailUtil().send(mail);
	}
	

	public static boolean sendMail2(String recvmail, String title, String message,
			String nickname,String action,String bloglink,String blogtitle,String replayData,
			String replayLink) {
		Mail mail = new Mail();
		mail.setHost(ConfigReader.getInstance().getProperty("MAILHOSTSERVER")); // 设置邮件服务器,如果不用163的,自己找找看相关的
		mail.setSender(ConfigReader.getInstance().getProperty("MAILSENDER"));
		mail.setReceiver(recvmail); // 接收人
		mail.setUsername(ConfigReader.getInstance().getProperty("MAILSENDER")); // 登录账号,一般都是和邮箱名一样吧

		mail.setPassword(ConfigReader.getInstance().getProperty(
				"MAILSENDERPASSWORD")); // 发件人邮箱的登录密码
		mail.setSubject(title);

		mail.setMessage(message);
		return new MailUtil().sendTemplate(mail,nickname, action, bloglink, blogtitle, replayData,
				 replayLink);
	}
	
	
	
	
	private static String mailtpStr="";
	
	public static void init() {
		try {

			StringBuilder sb = new StringBuilder();
			InputStream in = MailUtil.class.getClassLoader().getResourceAsStream("mailtp.xml");
			InputStreamReader reader = new InputStreamReader(in); // 建立一个输入流对象reader
			BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
			String line = "";
			line = br.readLine();
			sb.append(line);
			while (line != null) {
				line = br.readLine(); // 一次读入一行数据
				if(line!=null)
				sb.append(line);
			}

			in.close();

			mailtpStr=sb.toString();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public String getTmpleateHtml(String name,String msg) {
		String template=""
				+"  <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"font-family:'微软雅黑',Helvetica,Arial,sans-serif;font-size:14px \" width=\"100%\">  "
				+"   <tbody>"
	      +"            <tr>"
	              +"        <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:14px;\">"
	              +"       <table width=\"100%\" border=\"0\" cellpadding=\"5\" cellspacing=\"0\" >"
	               +"              <tbody>"
	               +"                  <tr>"
	                +"                     <td>"
	                +"                         <p style=\"margin:0;font-size:14px;line-height:24px;font-family:'微软雅黑',Helvetica,Arial,sans-serif;margin-bottom: 20px\"><br>　　　　　　　　　　　　　　　　　　　　　　　　　　你好!：<br>　　　　　　　　　　　　　　　　　　　　　　　　</p>"
	                +"                         <p style=\"color:#000;margin:0;font-size:14px;line-height:24px;font-family:'微软雅黑',Helvetica,Arial,sans-serif;\"><br>　{{content}}<br>　　　　　　　　　　　　　　　　　　　　　　　　</p>"
	                 +"                    </td>"
	                +"                 </tr>"
		+"             </tbody>"
	                 +"    </table>"                                                          
	                +"    </td>"
	            +"   </tr>"
	                
	 +"   </tbody>"
	+"  </table>";
		
		template=template.replace("{{content}}", msg);
		
		return template;
	}
	
	public String getTmp2(String nickname,String action,String bloglink,String blogtitle,String replayData,
			String replayLink) {
		if(mailtpStr==null||mailtpStr.equals(""))
			init();
		
		//配置文件按重读
		if(!ConfigReader.getInstance().getProperty("reloadMailTp", "false").equals("false"))
			init();
		
		String mail=mailtpStr;
		mail=mail.replace("{{nickname}}", nickname);
		mail=mail.replace("{{action}}", action);
		mail=mail.replace("{{bloglink}}", bloglink);
		mail=mail.replace("{{blogtitle}}", blogtitle);
		mail=mail.replace("{{replayData}}", replayData);
		mail=mail.replace("{{replayLink}}", replayLink);
		
		return mail;
				
	}
	
	public boolean sendTemplate(Mail mail,String nickname,String action,String bloglink,String blogtitle,String replayData,
			String replayLink) {
		// 发送email
		HtmlEmail email = new HtmlEmail();
		try {
			// 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
			email.setHostName(mail.getHost());

			String SSL_SMTP = ConfigReader.getInstance().getProperty(
					"SSL_SMTP", "false");
			if (SSL_SMTP.equalsIgnoreCase("true")) {

				email.setSSLOnConnect(true); // 是否启用SSL
				System.setProperty("mail.smtp.ssl.enable","true");

				String SSL_SMTP_PORT = ConfigReader.getInstance().getProperty(
						"SSL_SMTP_PORT", "465");
				email.setSslSmtpPort(SSL_SMTP_PORT); // 若启用，设置smtp协议的SSL端口号
			}

			// 字符编码集的设置
			email.setCharset(Mail.ENCODEING);
			// 收件人的邮箱
			email.addTo(mail.getReceiver());
			// 发送人的邮箱
			email.setFrom(mail.getSender(), mail.getName());
			// 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
			email.setAuthentication(mail.getUsername(), mail.getPassword());
			// 要发送的邮件主题
			email.setSubject(mail.getSubject());
			// 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
			// email.setMsg(mail.getMessage());

			String msg = mail.getMessage();

			// embed the image and get the content id
			URL url;
			try {
			
				
			
				
				//email.setHtmlMsg("<html> " + msg + "</html>");
				email.setHtmlMsg(getTmp2( nickname, action, bloglink, blogtitle, replayData,
						 replayLink));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// set the alternative message
			email.setTextMsg("Your email client does not support HTML messages:"
					+ msg);

			// 发送
			email.send();
			if (logger.isDebugEnabled()) {
				logger.debug(mail.getSender() + " 发送邮件到 " + mail.getReceiver());
			}
			return true;
		} catch (EmailException e) {
			e.printStackTrace();
			logger.info(mail.getSender() + " 发送邮件到 " + mail.getReceiver()
					+ " 失败");
			return false;
		}
	}
	

	public boolean send(Mail mail) {
		// 发送email
		HtmlEmail email = new HtmlEmail();
		try {
			// 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
			email.setHostName(mail.getHost());

			String SSL_SMTP = ConfigReader.getInstance().getProperty(
					"SSL_SMTP", "false");
			if (SSL_SMTP.equalsIgnoreCase("true")) {

				email.setSSLOnConnect(true); // 是否启用SSL
				System.setProperty("mail.smtp.ssl.enable","true");

				String SSL_SMTP_PORT = ConfigReader.getInstance().getProperty(
						"SSL_SMTP_PORT", "465");
				email.setSslSmtpPort(SSL_SMTP_PORT); // 若启用，设置smtp协议的SSL端口号
			}

			// 字符编码集的设置
			email.setCharset(Mail.ENCODEING);
			// 收件人的邮箱
			email.addTo(mail.getReceiver());
			// 发送人的邮箱
			email.setFrom(mail.getSender(), mail.getName());
			// 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
			email.setAuthentication(mail.getUsername(), mail.getPassword());
			// 要发送的邮件主题
			email.setSubject(mail.getSubject());
			// 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
			// email.setMsg(mail.getMessage());

			String msg = mail.getMessage();

			// embed the image and get the content id
			URL url;
			try {
			
				
				
				
				/*
				 * url = new
				 * URL("http://www.apache.org/images/asf_logo_wide.gif"); String
				 * cid = email.embed(url, "Apache logo.gif");
				 * 
				 * // set the html message
				 * email.setHtmlMsg("<html>The apache logo - <img src=\"cid:" +
				 * cid + "\">  "+msg+" <script>alert(1);<script> </html>");
				 */
				
				email.setHtmlMsg("<html> " + msg + "</html>");
				//email.setHtmlMsg(getTmpleateHtml("", msg));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// set the alternative message
			email.setTextMsg("Your email client does not support HTML messages:"
					+ msg);

			// 发送
			email.send();
			if (logger.isDebugEnabled()) {
				logger.debug(mail.getSender() + " 发送邮件到 " + mail.getReceiver());
			}
			return true;
		} catch (EmailException e) {
			e.printStackTrace();
			logger.info(mail.getSender() + " 发送邮件到 " + mail.getReceiver()
					+ " 失败");
			return false;
		}
	}

}
