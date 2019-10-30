package ahualy.neepu.dao.provider;

import static ahualy.neepu.util.common.Constants.REGISTCODETABLE;

import org.apache.ibatis.jdbc.SQL;

import ahualy.neepu.pojo.RegistCode;

public class RegistCodeDynaSqlProvider {

	
	
	public String insert_Code(RegistCode registCode){
		return new SQL(){
			{
				INSERT_INTO(REGISTCODETABLE);
				if(registCode.getCode()!= null ){
					VALUES("code", "#{code}");
				}
			}
		}.toString();
	}	
}
