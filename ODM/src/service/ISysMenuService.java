package service;

import java.util.List;

import model.SysMenu;;

public interface ISysMenuService {
	 public List<SysMenu> findAll();
	 public SysMenu findByID(Integer id);
	 public List<SysMenu> findByName(String name);
	 public void addSysMenu(SysMenu sysMenu) ;
	 public void delSysMenu(SysMenu sysMenu);
	 public void updateSysMenu(SysMenu sysMenu);
}
