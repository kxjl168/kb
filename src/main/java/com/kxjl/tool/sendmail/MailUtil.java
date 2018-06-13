package com.kxjl.tool.sendmail;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

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
