package service;

import java.util.List;

import logic.Page;
import model.DutyPerson;

public interface IDutyPersonService {
    public List<DutyPerson> findAll();
    public DutyPerson findByID(Integer id);
    public void addDutyPerson(DutyPerson dutyPerson) ;
    public void delDutyPerson(DutyPerson dutyPerson);
    public void updateDutyPerson(DutyPerson dutyPerson);
	  /** 
	   * 分页查询    
	   * @param pageSize 每页大小     
	   * @param pageIndex 当前       
	   * @return 封闭了分页信息(包括记录集list)的Bean     
	   */ 
	public Page queryForPage(int pageSize, int pageIndex, String hql);

}
