package ahualy.neepu.dao;


import static ahualy.neepu.util.common.Constants.SEXTABLE;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import ahualy.neepu.pojo.Sex;
public interface SexDao {
	//查询
		@Select("select * from "+SEXTABLE+" ")
		List<Sex> selectAllSex();
		
		@Select("select * from "+SEXTABLE+" where name like CONCAT('%',#{content},'%')")
		List<Sex> selectLikeAllSex(String content);
		
		@Select("select * from "+SEXTABLE+" where id = #{id}")
		Sex get_Info(Integer id);
}
