package service;

import java.util.List;

import dao.IAdminInfoDAO;
import model.AdminInfo;

public class AdminInfoService implements IAdminInfoService {

	private IAdminInfoDAO adminInfoDAO = null;
	@Override
	public List<AdminInfo> findAll() {
		return adminInfoDAO.findAll();
	}

	@Override
	public AdminInfo findByID(String id) {
		return  adminInfoDAO.findById(id);
	}

	@Override
	public AdminInfo findByName(String name) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void addAdminInfo(AdminInfo adminInfo) {
		adminInfoDAO.attachDirty(adminInfo);

	}

	@Override
	public void delAdminInfo(AdminInfo adminInfo) {
		adminInfoDAO.delete(adminInfo);

	}

	@Override
	public void updateAdminInfo(AdminInfo adminInfo) {
		adminInfoDAO.attachDirty(adminInfo);

	}

	@Override
	public AdminInfo login(AdminInfo adminInfo) {
		AdminInfo adminInfoReturn = null; 
		adminInfoReturn = adminInfoDAO.findById(adminInfo.getAdminId());
		return adminInfoReturn;
	}

	@Override
	public AdminInfo register(AdminInfo adminInfo) {
		// TODO 自动生成的方法存根
		return null;
	}

	public IAdminInfoDAO getAdminInfoDAO() {
		return adminInfoDAO;
	}

	public void setAdminInfoDAO(IAdminInfoDAO adminInfoDAO) {
		this.adminInfoDAO = adminInfoDAO;
	}

}
