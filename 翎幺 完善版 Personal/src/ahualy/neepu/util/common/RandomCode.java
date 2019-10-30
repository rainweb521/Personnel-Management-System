package ahualy.neepu.util.common;

import java.text.SimpleDateFormat;

public class RandomCode{

    public static String getRandomCode(){
    	SimpleDateFormat tempDate = new SimpleDateFormat("yyMMddHHmmss");  
		 String random = tempDate.format(new java.util.Date());  
		 return random;
    }
}
 
