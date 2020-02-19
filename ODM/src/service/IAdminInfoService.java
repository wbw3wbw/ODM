package service;

import java.util.List;

import model.AdminInfo;

public interface IAdminInfoService {
	 public List<AdminInfo> findAll();
	 public AdminInfo findByID(String id);
	 public AdminInfo findByName(String name);
	 public void addAdminInfo(AdminInfo adminInfo) ;
	 public void delAdminInfo(AdminInfo adminInfo);
	 public void updateAdminInfo(AdminInfo adminInfo);
	 public AdminInfo login(AdminInfo adminInfo);
	 public AdminInfo register(AdminInfo adminInfo);
	 
}
