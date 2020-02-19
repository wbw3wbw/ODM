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
	   * ��ҳ��ѯ    
	   * @param pageSize ÿҳ��С     
	   * @param pageIndex ��ǰ       
	   * @return ����˷�ҳ��Ϣ(������¼��list)��Bean     
	   */ 
	public Page queryForPage(int pageSize, int pageIndex, String hql);
}
