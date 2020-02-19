package service;

import java.util.List;

import dao.ISysMenuDAO;
import model.SysMenu;

public class SysMenuService implements ISysMenuService {

	private ISysMenuDAO sysMenuDAO = null;
	@Override
	public List<SysMenu> findAll() {
		return sysMenuDAO.findAll();
	}

	@Override
	public SysMenu findByID(Integer id) {
		return sysMenuDAO.findById(id);
	}

	@Override
	public List<SysMenu> findByName(String name) {
		return sysMenuDAO.findBySysMenuName(name);
	}

	@Override
	public void addSysMenu(SysMenu sysMenu) {
		sysMenuDAO.attachDirty(sysMenu);
	}

	@Override
	public void delSysMenu(SysMenu sysMenu) {
		sysMenuDAO.delete(sysMenu);
	}

	@Override
	public void updateSysMenu(SysMenu sysMenu) {
		sysMenuDAO.attachDirty(sysMenu);
	}


	public ISysMenuDAO getSysMenuDAO() {
		return sysMenuDAO;
	}

	public void setSysMenuDAO(ISysMenuDAO sysMenuDAO) {
		this.sysMenuDAO = sysMenuDAO;
	}

}
