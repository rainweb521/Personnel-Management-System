package ahualy.neepu.util.common;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

 public class RegistRandomCode {
	public static String getRegisCode() {
		
		char[] chr = {'2', '3', '4', '5', '6', '7', '8', '9',
		        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		        Random random = new Random();
		        StringBuffer buffer = new StringBuffer();
		        for (int i = 0; i < 9; i++) {
		        buffer.append(chr[random.nextInt(33)]);
		        }
		        return buffer.toString();
	}
	
	public static Integer getSataticId() {
		//这个方法生成随机id
		     int min=0;
		     int max=9999;
	         int num = min + (int)(Math.random() * (max-min+1));
	         return num;
	         
	        
	      /*// 产生的字符串太长，浪费存储，再进行MD5
	      // 可以使用apache的org.apache.commons.codec.digest.DigestUtils
	      // 也可以是使用java.security.MessageDigest进行加密
	      // 注意返回的是长度为16的byte数组，使用Hex转换成32的char数组，在转成字符串
	      String uuid = new String(Hex.encodeHex(DigestUtils.md5(str)));*/

	}
	
	public static Integer getStatic() {
		// 准备好1万个不同的四位数
				List<String> list = new LinkedList<String>();
				for (int i = 0; i < 10000; i++) {
					list.add(String.format("%04d", i));
				}
				//System.out.println(list.size());
				// 随机取1万次不同的四位数
				int index = 0;
				for (int i = 0; i < 10000; i++) {
					index = (int) (Math.random() * list.size());
					System.out.println(list.remove(index));
				}
			return index;

	}
	
	public static void main(String args[]) {
		System.out.println(RegistRandomCode.getStatic());
		
	}
}
