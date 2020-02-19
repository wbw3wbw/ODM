package service;

import java.util.List;

import logic.Page;
import model.OdRecord;

public interface IOdRecordService {
    public List<OdRecord> findAll();
    public OdRecord findByID(Integer id);
    public void addOdRecord(OdRecord odRecord) ;
    public void delOdRecord(OdRecord odRecord);
    public void updateOdRecord(OdRecord odRecord);
	  /** 
	   * 分页查询    
	   * @param pageSize 每页大小     
	   * @param pageIndex 当前       
	   * @return 封闭了分页信息(包括记录集list)的Bean     
	   */ 
	public Page queryForPage(int pageSize, int pageIndex, String hql);
}
