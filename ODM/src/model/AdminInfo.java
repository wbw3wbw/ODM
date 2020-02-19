package model;

import java.sql.Timestamp;

/**
 * AdminInfo entity. @author MyEclipse Persistence Tools
 */

public class AdminInfo implements java.io.Serializable {

	// Fields

	private String adminId;
	private String adminPwd;
	private String adminName;
	private String adminTelphone;
	private String adminMail;
	private String adminLevel;
	private String adminPower;
	private Timestamp adminLastlogintime;

	// Constructors

	/** default constructor */
	public AdminInfo() {
	}

	/** minimal constructor */
	public AdminInfo(String adminId) {
		this.adminId = adminId;
	}

	/** full constructor */
	public AdminInfo(String adminId, String adminPwd, String adminName,
			String adminTelphone, String adminMail, String adminLevel,
			String adminPower, Timestamp adminLastlogintime) {
		this.adminId = adminId;
		this.adminPwd = adminPwd;
		this.adminName = adminName;
		this.adminTelphone = adminTelphone;
		this.adminMail = adminMail;
		this.adminLevel = adminLevel;
		this.adminPower = adminPower;
		this.adminLastlogintime = adminLastlogintime;
	}

	// Property accessors

	public String getAdminId() {
		return this.adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPwd() {
		return this.adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public String getAdminName() {
		return this.adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminTelphone() {
		return this.adminTelphone;
	}

	public void setAdminTelphone(String adminTelphone) {
		this.adminTelphone = adminTelphone;
	}

	public String getAdminMail() {
		return this.adminMail;
	}

	public void setAdminMail(String adminMail) {
		this.adminMail = adminMail;
	}

	public String getAdminLevel() {
		return this.adminLevel;
	}

	public void setAdminLevel(String adminLevel) {
		this.adminLevel = adminLevel;
	}

	public String getAdminPower() {
		return this.adminPower;
	}

	public void setAdminPower(String adminPower) {
		this.adminPower = adminPower;
	}

	public Timestamp getAdminLastlogintime() {
		return this.adminLastlogintime;
	}

	public void setAdminLastlogintime(Timestamp adminLastlogintime) {
		this.adminLastlogintime = adminLastlogintime;
	}

}