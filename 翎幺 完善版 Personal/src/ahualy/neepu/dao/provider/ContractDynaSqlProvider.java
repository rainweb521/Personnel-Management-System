package ahualy.neepu.dao.provider;

import static ahualy.neepu.util.common.Constants.CONTRACTTABLE;

import java.util.Map;


import org.apache.ibatis.jdbc.SQL;

import ahualy.neepu.pojo.Contract;

public class ContractDynaSqlProvider {
	
	public String selectWhitParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(CONTRACTTABLE);
				if(params.get("contract") != null){
					Contract contract = (Contract) params.get("contract");
					if(contract.getDept() != null && contract.getDept().getId() != null && contract.getDept().getId() != 0){
						WHERE(" DEPT_ID = #{contract.dept.id} ");
					}
					if(contract.getJob() != null && contract.getJob().getId() != null && contract.getJob().getId() != 0){
						WHERE(" JOB_ID = #{contract.job.id} ");
					}
					if(contract.getEmployee() != null && !contract.getEmployee().equals("")){
						WHERE("  EMP_ID LIKE CONCAT ('%',#{contract.employee.id},'%') ");
					}
					if(contract.getTrainContract()!= null && !contract.getTrainContract().equals("")){
						WHERE(" TRAIN_CONTRACT LIKE CONCAT ('%',#{contract.trainContract},'%') ");
					}
					if(contract.getLaborContract()!= null && !contract.getLaborContract().equals("") ){
						WHERE(" LABOR_CONTRACT LIKE CONCAT ('%',#{contract.laborContract},'%') ");
					}
					if(contract.getConfidentialityContract()!= null && !contract.getConfidentialityContract().equals("") ){
						WHERE(" CONFIDENTIALITY_CONTRACT LIKE CONCAT ('%',#{contract.confidentialityContract},'%') ");
					}
				}
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		
		return sql;
	}	
	// 动态查询总数量
	public String count(Map<String, Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(CONTRACTTABLE);
				if(params.get("contract") != null){
					Contract contract = (Contract) params.get("contract");
					if(contract.getDept() != null && contract.getDept().getId() != null && contract.getDept().getId() != 0){
						WHERE(" DEPT_ID = #{contract.dept.id} ");
					}
					if(contract.getJob() != null && contract.getJob().getId() != null && contract.getJob().getId() != 0){
						WHERE(" JOB_ID = #{contract.job.id} ");
					}
					if(contract.getEmployee() != null && !contract.getEmployee().equals("")){
						WHERE("  EMP_ID LIKE CONCAT ('%',#{contract.employee.id},'%') ");
					}
					if(contract.getTrainContract()!= null && !contract.getTrainContract().equals("")){
						WHERE(" TRAIN_CONTRACT LIKE CONCAT ('%',#{contract.trainContract},'%') ");
					}
					if(contract.getLaborContract()!= null && !contract.getLaborContract().equals("") ){
						WHERE(" LABOR_CONTRACT LIKE CONCAT ('%',#{contract.laborContract},'%') ");
					}
					if(contract.getConfidentialityContract()!= null && !contract.getConfidentialityContract().equals("") ){
						WHERE(" CONFIDENTIALITY_CONTRACT LIKE CONCAT ('%',#{contract.confidentialityContract},'%') ");
					}
				}
			}
		}.toString();
	}	

	
public String insert_Contract(Contract contract){
		
		return new SQL(){
			{
				INSERT_INTO(CONTRACTTABLE);
				if(contract.getId()!=null) {
					VALUES("id", "#{employee.id}");
				}
				if(contract.getDept() != null){
					VALUES("DEPT_ID", "#{dept.id}");
				}
				if(contract.getJob() != null){
					VALUES("JOB_ID", "#{job.id}");
				}
				if(contract.getEmployee() != null){
					VALUES("EMP_ID", "#{employee.id}");
				}
				if(contract.getConfidentialityContract() != null){
					VALUES("CONFIDENTIALITY_CONTRACT", "#{confidentialityContract.id}");
				}
				if(contract.getLaborContract() != null){
					VALUES("LABOR_CONTRACT", "#{laborContract.id}");
				}
				if(contract.getTrainContract() != null){
					VALUES("TRAIN_CONTRACT", "#{trainContract.id}");
				}
			}
		}.toString();
	}	
	// 动态更新
	public String update_Contract(Contract contract){
		
		return new SQL(){
			{
				UPDATE(CONTRACTTABLE);
				if(contract.getEmployee() != null){
					SET("EMP_ID = #{employee.id}");
				}
				if(contract.getDept()!= null){
					SET("DEPT_ID = #{dept.id}");
				}
				if(contract.getJob()!= null){
					SET("JOB_ID = #{job.id}");
				}
				if(contract.getConfidentialityContract()!= null){
					SET(" CONFIDENTIALITY_CONTRACT = #{confidentialityContract.id} ");
				}
				if(contract.getLaborContract()!= null){
					SET(" LABOR_CONTRACT = #{laborContract.id} ");
				}
				if(contract.getTrainContract()!= null){
					SET(" TRAIN_CONTRACT = #{trainContract.id} ");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
}
