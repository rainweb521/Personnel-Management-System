package ahualy.neepu.dao;


import static ahualy.neepu.util.common.Constants.LABORCONTRACTTABLE;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import ahualy.neepu.pojo.Laborcontract;
public interface LaborcontractDao {
	//查询
		@Select("select * from "+LABORCONTRACTTABLE+" ")
		List<Laborcontract> selectAllLaborcontract();
		
		@Select("select * from "+LABORCONTRACTTABLE+" where name like CONCAT('%',#{content},'%')")
		List<Laborcontract> selectLikeAllLaborcontract(String content);
	

		@Select("select * from "+LABORCONTRACTTABLE+" where id = #{id}")
		Laborcontract get_Info(Integer id);
}
