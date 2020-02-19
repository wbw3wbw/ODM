package service;

import java.util.List;

import logic.Page;
import model.DutyInfo;

public interface IDutyInfoService {
    public List<DutyInfo> findAll();
    public DutyInfo findByID(Integer id);
    public List<DutyInfo> findByExample(DutyInfo dutyInfo);
    public void addDutyInfo(DutyInfo dutyInfo) ;
    public void delDutyInfo(DutyInfo dutyInfo);
    public void updateDutyInfo(DutyInfo dutyInfo);
	  /** 
	   * 分页查询    
	   * @param pageSize 每页大小     
	   * @param pageIndex 当前       
	   * @return 封闭了分页信息(包括记录集list)的Bean     
	   */ 
	public Page queryForPage(int pageSize, int pageIndex, String hql);
}
