package ahualy.neepu.dao;

import static ahualy.neepu.util.common.Constants.LEAVESTATUSTABLE;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import ahualy.neepu.pojo.LeaveStatus;;

public interface LeaveStatusDao {
	
	//查询
			@Select("select * from "+LEAVESTATUSTABLE+" ")
			List<LeaveStatus> selectAllLeaveType();
			
			@Select("select * from "+LEAVESTATUSTABLE+" where name like CONCAT('%',#{content},'%')")
			List<LeaveStatus> selectLikeAllLeaveType(String content);

			@Select("select * from "+LEAVESTATUSTABLE+" where id = #{id}")
			LeaveStatus get_Info(Integer id);

}
