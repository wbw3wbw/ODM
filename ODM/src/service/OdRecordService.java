package service;

import java.util.List;

import dao.IOdRecordDAO;
import logic.Page;
import model.OdRecord;

public class OdRecordService implements IOdRecordService {

	private IOdRecordDAO odRecordDAO = null;
	
	@Override
	public List<OdRecord> findAll() {
		return odRecordDAO.findAll();
	}

	@Override
	public OdRecord findByID(Integer id) {
		return odRecordDAO.findById(id);
	}

	@Override
	public void addOdRecord(OdRecord odRecord) {
		odRecordDAO.attachDirty(odRecord);

	}

	@Override
	public void delOdRecord(OdRecord odRecord) {
		odRecordDAO.delete(odRecord);
	}

	@Override
	public void updateOdRecord(OdRecord odRecord) {
		odRecordDAO.attachDirty(odRecord);
	}

	@Override
	public Page queryForPage(int pageSize, int pageIndex, String hql) {
	   int allRow = odRecordDAO.getAllRowCount(hql);// �ܼ�¼��
	   int totalPage = Page.countTotalPage(pageSize, allRow);// ��ҳ��
	   final int offset = Page.countOffset(pageSize, pageIndex);// ��ǰ��ҳ��ʼ��¼
	
	   final int lenght = pageSize;// ÿҳ��¼��
	   final int currentPage = Page.countCurrentPage(pageIndex);
	   List<OdRecord> list = odRecordDAO.queryForPage(hql, offset, lenght);// ÿһҳ�ļ�¼��
	   // �ѷ�ҳ��Ϣ���浽Bean��
	   Page pageBean = new Page();
	   pageBean.setPageSize(pageSize);
	   pageBean.setCurrentPage(currentPage);
	   pageBean.setAllRow(allRow);
	   pageBean.setTotalPage(totalPage);
	   pageBean.setList(list);
	   pageBean.init();
	   return pageBean;
	}

	public IOdRecordDAO getOdRecordDAO() {
		return odRecordDAO;
	}

	public void setOdRecordDAO(IOdRecordDAO odRecordDAO) {
		this.odRecordDAO = odRecordDAO;
	}

}
