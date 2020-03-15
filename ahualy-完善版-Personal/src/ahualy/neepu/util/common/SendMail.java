package ahualy.neepu.util.common;

import java.util.List;

import org.springframework.mail.javamail.JavaMailSender;

import ahualy.neepu.pojo.User;

/**
 * 邮件发送
 * @author Huaxiren
 * 2019年3月25日
 */

public class SendMail {
	
		//注册成功发送邮件给用户，提醒用户2小时之内完成审核  方可登录系统
		public void sendEmail(JavaMailSender javaMailSender,User user) {
			EmailUtils emailUtils = new EmailUtils();
			if(user.getEmail()!=null) {
						emailUtils.sendMail(
					"<p>"+user.getLoginname()+",Hello!"+"</p><br/>" + "<p>Your information will be reviewed within 2 hours. After the review is completed, you can log in to the system!</p><br/>"
							+"<a href='http://222605g71y.iask.in/Personal/'>创新创业型小微企业人力资源管理平台<a/><br/>",
					"注册成功~", user.getEmail(), javaMailSender, true);
						System.out.println("给"+user.getLoginname()+"的邮件发送成功！！！");
			}
		}
		
		//审核通过后个用户发送邮件提醒可以登录
		public void sendEmail1(JavaMailSender javaMailSender,User user) {
			EmailUtils emailUtils = new EmailUtils();
			if(user.getEmail()!=null) {
				emailUtils.sendMail(
					"<p>"+user.getLoginname()+",Hello!"+"</p><br/>" + "<p>Your account has been reviewed. Please go to the login page to log in to the system!</p><br/>"
							+"<a href='http://222605g71y.iask.in/Personal/'>创新创业型小微企业人力资源管理平台<a/><br/>",
					"审核通过~", user.getEmail(), javaMailSender, true);
				System.out.println("给"+user.getLoginname()+"的邮件发送成功！！！");
			}
		}
		
		//账号被禁用之后通过后给用户发送邮件告知
		public void sendEmail2(JavaMailSender javaMailSender,User user) {
			EmailUtils emailUtils = new EmailUtils();
			if(user.getEmail()!=null) {
					emailUtils.sendMail(
					"<p>"+user.getLoginname()+",Hello!"+"</p><br/>" + "<p>Your account is disabled!</p><br/>"
							+"<p>You will not have access to the website!<p/><br/>"
							+"<a href='http://222605g71y.iask.in/Personal/'>创新创业型小微企业人力资源管理平台<a/><br/>",
					"禁用通知~", user.getEmail(), javaMailSender, true);
					System.out.println("给"+user.getLoginname()+"的邮件发送成功！！！");
			}
		}
		
		//管理员发布公告，如果用户不在线，就发邮件给用户告知
				public void sendEmail3(JavaMailSender javaMailSender,List<User> sqluser,String message) {
					//将所有员工的邮箱账号存放在List集合中，遍历逐个发送邮件
					for (User user : sqluser) {//开始遍历List集合,获取到每个员工的邮箱账号
						EmailUtils emailUtils = new EmailUtils();
						if(user.getEmail()!=null) {
								emailUtils.sendMail(
								"<p>"+user.getLoginname()+",Hello!"+"</p><br/>" + "<p>"+message+"</p><br/>"
										+"<a href='http://222605g71y.iask.in/Personal/'>创新创业型小微企业人力资源管理平台<a/><br/>",
								"公告通知~", user.getEmail(), javaMailSender, true);
								System.out.println("给"+user.getLoginname()+"的公告邮件发送成功！！！");
						}
					}
				
				}
		
		
				//管理员发布公告，如果用户不在线，就发邮件给用户告知
				public void sendEmail4(JavaMailSender javaMailSender,List<User> sqluser,String message) {
					//将所有员工的邮箱账号存放在List集合中，遍历逐个发送邮件
					for (User user : sqluser) {//开始遍历List集合,获取到每个员工的邮箱账号
						EmailUtils emailUtils = new EmailUtils();
						if(user.getEmail()!=null) {
								emailUtils.sendMail(
								"<p>"+user.getLoginname()+",Hello!"+"</p><br/>" + "<p>"+message+"</p><br/>"
										+"<a href='http://222605g71y.iask.in/Personal/'>创新创业型小微企业人力资源管理平台<a/><br/>",
								"公告变更通知~", user.getEmail(), javaMailSender, true);
								System.out.println("给"+user.getLoginname()+"的公告邮件发送成功！！！");
						}
					}
				
				}


				
				//审核通过后个用户发送邮件提醒可以登录
				public void sendEmail5(JavaMailSender javaMailSender,User user) {
					EmailUtils emailUtils = new EmailUtils();
					if(user.getEmail()!=null) {
						emailUtils.sendMail(
							"<p>"+user.getLoginname()+",Hello!"+"</p><br/>" + "<p>Your leave information has been approved! ! !</p><br/>"
									+"<a href='http://222605g71y.iask.in/Personal/'>创新创业型小微企业人力资源管理平台<a/><br/>",
							"请假批准~", user.getEmail(), javaMailSender, true);
						System.out.println("给"+user.getLoginname()+"的邮件发送成功！！！");
					}
				}
}

