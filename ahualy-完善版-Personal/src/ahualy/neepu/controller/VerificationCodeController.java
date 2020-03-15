package ahualy.neepu.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ahualy.neepu.service.AhualyService;


@Controller
public class VerificationCodeController {
	
	
	@Autowired
	@Qualifier("AhualyService")
	private AhualyService ahualyservice;
	@RequestMapping(value="/checkCode")

	/**
	 * 获取验证码
	 */
	@ResponseBody
	public void toCreateCode(HttpServletRequest request,HttpServletResponse response,HttpSession session,Integer num) throws IOException {
		
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			ServletOutputStream out = null;
			drawImg(output,session);
			try {
				 
				 out = response.getOutputStream();
				  output.writeTo(out);
	             
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}
	 
		/**
		 * 绘画验证码
		 */
		private String drawImg(ByteArrayOutputStream output,HttpSession session) {
			Random random = new Random();
			String code = "";
			// 随机产生5个字符
			for (int i = 0; i < 5; i++) {
				code += randomChar();
			}
			// 定义图片的宽度和高度
	        int width = (int) Math.ceil(5 * 20);
	        int height = 30;
	        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        // 获取图片的上下文
	        Graphics gr = image.getGraphics();
	        // 设定图片背景颜色
	        gr.setColor(Color.WHITE);
	        gr.fillRect(0, 0, width, height);
	        // 设定图片边框
	        gr.setColor(Color.GRAY);
	        gr.drawRect(0, 0, width - 1, height - 1);
	        // 画十条干扰线
	        for (int i = 0; i < 5; i++) {
	            int x1 = random.nextInt(width);
	            int y1 = random.nextInt(height);
	            int x2 = random.nextInt(width);
	            int y2 = random.nextInt(height);
	            gr.setColor(randomColor());
	            gr.drawLine(x1, y1, x2, y2);
	        }
	        // 设置字体，画验证码
	        gr.setColor(randomColor());
	        gr.setFont(randomFont());
	        gr.drawString(code, 10, 22);
	        // 图像生效
	        gr.dispose();
	       // 输出到页面
			try {
				session.setAttribute("code",code.toLowerCase());
				System.out.println("session=="+session.getAttribute("code"));
				ImageIO.write(image, "jpg", output);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return code;
		}
	 
		/**
		 * 随机参数一个字符
		 */
		private char randomChar() {
			Random r = new Random();
			String s = "abcdefghjkmnprstuvwxyzABCDEFGHJKMNPRSTUVWXYZ23456789";
			return s.charAt(r.nextInt(s.length()));
		}
	    // 生成随机的颜色
	    private Color randomColor() {
	        int red = r.nextInt(150);
	        int green = r.nextInt(150);
	        int blue = r.nextInt(150);
	        return new Color(red, green, blue);
	    }
	    
	    private String[] fontNames = { "宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312" };
	    private Random r = new Random();

	    // 生成随机的字体
	    private Font randomFont() {
	        int index = r.nextInt(fontNames.length);
	        String fontName = fontNames[index];// 生成随机的字体名称
	        int style = r.nextInt(4);
	        int size = r.nextInt(3) + 24; // 生成随机字号, 24 ~ 28
	        return new Font(fontName, style, size);
	    }
		
}
	