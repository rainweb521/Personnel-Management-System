package ahualy.neepu.pojo;


public class CreateStaticId {
        
	//定义生成的全局id
    public  Integer staticId;
    
    
    //初始化的值存再数据库中，每次先初始化，然后将这个值再更新到数据库，确保每次的值都在+1
    public CreateStaticId(Integer staticId) {
    	this.staticId = staticId+1;//拿到的初始化值+1得到本次操作所需要的值
    }

	public  Integer getStaticId() {
		return staticId;
	}

	public  void setStaticId(Integer staticId) {
		this.staticId = staticId;
	}
    
    
}
