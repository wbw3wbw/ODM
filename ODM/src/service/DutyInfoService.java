package service;

import java.util.List;

import dao.IDutyInfoDAO;
import logic.Page;
import model.DutyInfo;
import model.OdRecord;

public class DutyInfoService implements IDutyInfoService {

	private IDutyInfoDAO dutyInfoDAO = null;
	@Override
	public List<DutyInfo> findAll() {
		return dutyInfoDAO.findAll();
	}

	@Override
	public DutyInfo findByID(Integer id) {
		return dutyInfoDAO.findById(id);
	}

	@Override
	public void addDutyInfo(DutyInfo dutyInfo) {
		dutyInfoDAO.attachDirty(dutyInfo);
	}

	@Override
	public void delDutyInfo(DutyInfo dutyInfo) {
		dutyInfoDAO.delete(dutyInfo);
	}

	@Override
	public void updateDutyInfo(DutyInfo dutyInfo) {
		dutyInfoDAO.attachDirty(dutyInfo);
	}

	@Override
	public Page queryForPage(int pageSize, int pageIndex, String hql) {
		   int allRow = dutyInfoDAO.getAllRowCount(hql);// �ܼ�¼��
		   int totalPage = Page.countTotalPage(pageSize, allRow);// ��ҳ��
		   final int offset = Page.countOffset(pageSize, pageIndex);// ��ǰ��ҳ��ʼ��¼
		
		   final int lenght = pageSize;// ÿҳ��¼��
		   final int currentPage = Page.countCurrentPage(pageIndex);
		   List<OdRecord> list = dutyInfoDAO.queryForPage(hql, offset, lenght);// ÿһҳ�ļ�¼��
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

	public IDutyInfoDAO getDutyInfoDAO() {
		return dutyInfoDAO;
	}

	public void setDutyInfoDAO(IDutyInfoDAO dutyInfoDAO) {
		this.dutyInfoDAO = dutyInfoDAO;
	}

	@Override
	public List<DutyInfo> findByExample(DutyInfo dutyInfo) {
		return dutyInfoDAO.findByExample(dutyInfo);
	}

}
