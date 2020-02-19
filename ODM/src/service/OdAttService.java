package service;

import java.util.List;

import dao.IOdAttDAO;
import model.OdAtt;
import model.OdRecord;

public class OdAttService implements IOdAttService {

	private IOdAttDAO odAttDAO = null;
	@Override
	public List<OdAtt> findAll() {
		// TODO 自动生成的方法存根
		return odAttDAO.findAll();
	}

	@Override
	public List<OdAtt> findByOdRecordId(Integer OdRecordId) {
		return odAttDAO.findByOdAttOdrid(OdRecordId);
	}

	@Override
	public OdAtt findByID(Integer id) {
		return odAttDAO.findById(id);
	}

	@Override
	public void addOdAtt(OdAtt odAtt) {
		odAttDAO.save(odAtt);
	}

	@Override
	public void delOdAtt(OdAtt odAtt) {
		odAttDAO.delete(odAtt);
	}

	@Override
	public void updateOdAtt(OdAtt odAtt) {
		odAttDAO.attachDirty(odAtt);
	}

	public IOdAttDAO getOdAttDAO() {
		return odAttDAO;
	}

	public void setOdAttDAO(IOdAttDAO odAttDAO) {
		this.odAttDAO = odAttDAO;
	}

}
