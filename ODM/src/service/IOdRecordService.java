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
	   * ��ҳ��ѯ    
	   * @param pageSize ÿҳ��С     
	   * @param pageIndex ��ǰ       
	   * @return ����˷�ҳ��Ϣ(������¼��list)��Bean     
	   */ 
	public Page queryForPage(int pageSize, int pageIndex, String hql);
}
