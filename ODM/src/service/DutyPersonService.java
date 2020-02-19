package service;

import java.util.List;

import dao.IDutyPersonDAO;
import logic.Page;
import model.DutyPerson;

public class DutyPersonService implements IDutyPersonService {
	private IDutyPersonDAO dutyPersonDAO = null;
	@Override
	public List<DutyPerson> findAll() {
		return dutyPersonDAO.findAll();
	}

	@Override
	public DutyPerson findByID(Integer id) {
		return dutyPersonDAO.findById(id);
	}

	@Override
	public void addDutyPerson(DutyPerson dutyPerson) {
		dutyPersonDAO.attachDirty(dutyPerson);
	}

	@Override
	public void delDutyPerson(DutyPerson dutyPerson) {
		dutyPersonDAO.delete(dutyPerson);
	}

	@Override
	public void updateDutyPerson(DutyPerson dutyPerson) {
		dutyPersonDAO.attachDirty(dutyPerson);
	}

	@Override
	public Page queryForPage(int pageSize, int pageIndex, String hql) {
		// TODO 自动生成的方法存根
		return null;
	}

	public IDutyPersonDAO getDutyPersonDAO() {
		return dutyPersonDAO;
	}

	public void setDutyPersonDAO(IDutyPersonDAO dutyPersonDAO) {
		this.dutyPersonDAO = dutyPersonDAO;
	}

}
