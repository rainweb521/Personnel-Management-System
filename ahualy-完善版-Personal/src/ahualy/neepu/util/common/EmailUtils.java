package ahualy.neepu.util.common;



import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.ui.velocity.VelocityEngineUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
public class EmailUtils {

	/**
	 * 
	 * Text或者HTML格式邮件的方法
	 * 
	 * @param text
	 *            要发送的内容
	 * @param subject
	 *            邮件的主题也就是邮件的标题
	 * @param location
	 *            文件的地址
	 * @param emailAdress
	 *            目的地
	 * @param javaMailSender
	 *            发送邮件的核心类（在xml文件中已经配置好了）
	 * @param type
	 *            如果为true则代表发送HTML格式的文本
	 * @return
	 * @throws TemplateException
	 */
	public String sendMail(String text, String subject, String emailAdress,
			JavaMailSender javaMailSender, Boolean type) {
		MimeMessage mMessage = javaMailSender.createMimeMessage();// 创建邮件对象
		MimeMessageHelper mMessageHelper;
		Properties prop = new Properties();
		try {
			// 从配置文件中拿到发件人邮箱地址
			prop.load(this.getClass().getResourceAsStream("/mail.properties"));
			String from = prop.get("mail.smtp.username") + "";
			mMessageHelper = new MimeMessageHelper(mMessage, true, "UTF-8");
			// 发件人邮箱
			//设置自定义发件人昵称  
	        String nick="";  
	        try {  
	            nick=javax.mail.internet.MimeUtility.encodeText("创新创业型小微企业人力资源管理平台");  
	        } catch (UnsupportedEncodingException e) {  
	            e.printStackTrace();  
	        }   
	        mMessageHelper.setFrom(new InternetAddress(nick+" <"+from+">"));  
			//mMessageHelper.setFrom(from);
			// 收件人邮箱
			mMessageHelper.setTo(emailAdress);
			// 邮件的主题也就是邮件的标题
			mMessageHelper.setSubject(subject);
			// 邮件的文本内容，true表示文本以html格式打开
			if (type) {
				mMessageHelper.setText(text, true);
			} else {
				mMessageHelper.setText(text, false);
			}
			// 在邮件中添加一个附件
			javaMailSender.send(mMessage);// 发送邮件
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "发送成功";
	}

	/**
	 * FreeMarker模板格式的邮件的方法
	 * 
	 * @param subject
	 *            邮件的主题也就是邮件的标题
	 * @param location
	 *            文件的地址
	 * @param emailAdress
	 *            目的地
	 * @param javaMailSender
	 *            发送邮件的核心类（在xml文件中已经配置好了）
	 * @param freeMarkerConfiguration
	 *            freemarker配置管理类
	 * @return
	 * @throws TemplateException
	 */
	public String sendMailFreeMarker(String subject, String location, String emailAdress, JavaMailSender javaMailSender,
			Configuration freeMarkerConfiguration){
		MimeMessage mMessage = javaMailSender.createMimeMessage();// 创建邮件对象
		MimeMessageHelper mMessageHelper;
		Properties prop = new Properties();
		try {
			// 从配置文件中拿到发件人邮箱地址
			prop.load(this.getClass().getResourceAsStream("/mail.properties"));
			String from = prop.get("mail.smtp.username") + "";
			mMessageHelper = new MimeMessageHelper(mMessage, true);
			
			// 发件人邮箱
			mMessageHelper.setFrom(from);
			// 收件人邮箱
			mMessageHelper.setTo(emailAdress);
			// 邮件的主题也就是邮件的标题
			mMessageHelper.setSubject(subject);
			// 解析模板文件
			mMessageHelper.setText(getText(freeMarkerConfiguration), true);
			// 通过文件路径获取文件名字
			String filename = StringUtils.getFileName(location);
			// 定义要发送的资源位置
			File file = new File(location);
			FileSystemResource resource = new FileSystemResource(file);
			mMessageHelper.addAttachment(filename, resource);// 在邮件中添加一个附件
			javaMailSender.send(mMessage);// 发送邮件
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "发送成功";
	}

	/**
	 * 读取freemarker模板的方法
	 */
	private String getText(Configuration freeMarkerConfiguration) {
		String txt = "";
		try {
			Template template = freeMarkerConfiguration.getTemplate("email.ftl");
			// 通过map传递动态数据
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user", "Huaxiren");
			// 解析模板文件
			txt = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
			System.out.println("getText()->>>>>>>>>");// 输出的是HTML格式的文档
			System.out.println(txt);
		} catch (IOException e) {
			// TODO 异常执行块！
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO 异常执行块！
			e.printStackTrace();
		}

		return txt;
	}

	/**
	 * 
	 * @param subject
	 *            邮件主题
	 * @param location
	 *            收件人地址
	 * @param emailAdress
	 *            目的地
	 * @param javaMailSender
	 *            发送邮件的核心类（在xml文件中已经配置好了）
	 * @param velocityEngine
	 *            Velocity模板引擎
	 * @return
	 */
	public String sendMailVelocity(String subject, String location, String emailAdress, JavaMailSender javaMailSender,
			VelocityEngine velocityEngine) {
		MimeMessage mMessage = javaMailSender.createMimeMessage();// 创建邮件对象
		MimeMessageHelper mMessageHelper;
		Properties prop = new Properties();
		try {
			// 从配置文件中拿到发件人邮箱地址
			prop.load(this.getClass().getResourceAsStream("/mail.properties"));
			System.out.println(this.getClass().getResourceAsStream("/mail.properties"));
			String from = prop.get("mail.smtp.username") + "";
			mMessageHelper = new MimeMessageHelper(mMessage, true, "UTF-8");
			// 发件人邮箱
			mMessageHelper.setFrom(from);
			// 收件人邮箱
			mMessageHelper.setTo(emailAdress);
			// 邮件的主题也就是邮件的标题
			mMessageHelper.setSubject(subject);
			Map<String, Object> map = new HashMap<>();
			// 获取日期并格式化
			Date date = new Date();
			DateFormat bf = new SimpleDateFormat("yyyy-MM-dd E a HH:mm:ss");
			String str = bf.format(date);
			map.put("date", str);
			String content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "email.vm", "UTF-8", map);
			mMessageHelper.setText(content, true);
			// 通过文件路径获取文件名字
			String filename = StringUtils.getFileName(location);
			// 定义要发送的资源位置
			File file = new File(location);
			FileSystemResource resource = new FileSystemResource(file);
			mMessageHelper.addAttachment(filename, resource);// 在邮件中添加一个附件
			// mMessageHelper.addAttachment("JavaApiRename.txt", resource2);//
			// 在邮件中添加一个附件
			javaMailSender.send(mMessage);// 发送邮件
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "发送成功";
	}
}
