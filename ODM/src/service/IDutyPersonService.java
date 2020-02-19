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
	   * ��ҳ��ѯ    
	   * @param pageSize ÿҳ��С     
	   * @param pageIndex ��ǰ       
	   * @return ����˷�ҳ��Ϣ(������¼��list)��Bean     
	   */ 
	public Page queryForPage(int pageSize, int pageIndex, String hql);

}
