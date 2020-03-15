package ahualy.neepu.dao;


import static ahualy.neepu.util.common.Constants.REGISTCODETABLE;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import ahualy.neepu.dao.provider.RegistCodeDynaSqlProvider;
import ahualy.neepu.pojo.RegistCode;
public interface RegistCodeDao {
		
		@Select("select code from "+REGISTCODETABLE+" where code = #{code}")
		String get_Info(String  code);
		
		@SelectProvider(type=RegistCodeDynaSqlProvider.class,method="insert_Code")
		void insert_Info(RegistCode registCode);
}
