package ahualy.neepu.util.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

public class Constants {
	// 数据库表常量
		public static final String USERTABLE = "user_inf";
		public static final String DEPTTABLE = "dept_inf";
		public static final String JOBTABLE = "job_inf";
		
		public static final String EMPLOYEETABLE = "employee_inf";
		public static final String NOTICETABLE = "notice_inf";
		public static final String DOCUMENTTABLE = "document_inf";

		public static final String CHECKWORKTABLE = "checkwork_inf";
		public static final String CONTRACTTABLE = "contract_inf";
		public static final String BUSINESSTABLE = "business_inf";
		
		public static final String SALARYTABLE = "salary_inf";
		public static final String USERVISITTABLE = "uservisit_inf";
		public static final String SEXTABLE = "sex_inf";
		
		public static final String STATUSTABLE = "status_inf";
		public static final String EDUCATIONTABLE = "education_inf";
		public static final String REGISTCODETABLE = "code_inf";
		public static final String STATICIDTABLE = "staticid_inf";
		
		
		//请假业务对应的数据库表
		public static final String  LEAVETABLE = "leave_inf";
		public static final String  LEAVESTATUSTABLE= "leavestatus_inf";
		public static final String  LEAVETYPETABLE= "leavetype_inf";
		
		//培训业务对应的数据库表
		public static final String  TRAINTABLE = "train_inf";
		public static final String  COMPLETIONTABLE = "completion_inf";
		public static final String  TRAINDATATABLE= "traindata_inf";
		 
		
		public static final String TRAINCONTRACTTABLE = "traincontract_inf";
		public static final String LABORCONTRACTTABLE = "laborcontract_inf";
		public static final String CONFIDENTIALITYCONTRACTTABLE = "confidentialitycontract_inf";
		
		// 登录
		public static final String LOGIN = "loginForm?visitId="+DigestUtils.md5Hex("999");
		public static final String CODE = "checkCode";
		// 用户的session对象
		public static final String USER_SESSION = "user_session";
		
		//这里记录是否是第一次登录
		public static  int FIRST = 1 ;
		
		//这里记录管理员或者总经理发布的公告
		public static Map<String, Integer> NOTENUMBER = new HashMap<String,Integer>(){/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{
			put("note",1);
		}};
		
				
		// 默认每页4条数据
		public static int PAGE_DEFAULT_SIZE = 6;
		
		//员工信息管理提示
		public static final String ACCOUNTMESSAGE = "您的员工信息尚未完善，请及时完善！！！";
		
		public static final String IMG_FILES = "F:\\Documents\\JavaWed\\";
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
