package model;

/**
 * DutyPerson entity. @author MyEclipse Persistence Tools
 */

public class DutyPerson implements java.io.Serializable {

	// Fields

	private Integer dutyPersonId;
	private String dutyPersonName;
	private String dutyPersonPhone;
	private String dutyPersonSex;
	private String dutyPersonMail;
	private String dutyPersonStatus;
	private String dutyPersonRemark;

	// Constructors

	/** default constructor */
	public DutyPerson() {
	}

	/** minimal constructor */
	public DutyPerson(String dutyPersonName, String dutyPersonPhone) {
		this.dutyPersonName = dutyPersonName;
		this.dutyPersonPhone = dutyPersonPhone;
	}

	/** full constructor */
	public DutyPerson(String dutyPersonName, String dutyPersonPhone,
			String dutyPersonSex, String dutyPersonMail,
			String dutyPersonStatus, String dutyPersonRemark) {
		this.dutyPersonName = dutyPersonName;
		this.dutyPersonPhone = dutyPersonPhone;
		this.dutyPersonSex = dutyPersonSex;
		this.dutyPersonMail = dutyPersonMail;
		this.dutyPersonStatus = dutyPersonStatus;
		this.dutyPersonRemark = dutyPersonRemark;
	}

	// Property accessors

	public Integer getDutyPersonId() {
		return this.dutyPersonId;
	}

	public void setDutyPersonId(Integer dutyPersonId) {
		this.dutyPersonId = dutyPersonId;
	}

	public String getDutyPersonName() {
		return this.dutyPersonName;
	}

	public void setDutyPersonName(String dutyPersonName) {
		this.dutyPersonName = dutyPersonName;
	}

	public String getDutyPersonPhone() {
		return this.dutyPersonPhone;
	}

	public void setDutyPersonPhone(String dutyPersonPhone) {
		this.dutyPersonPhone = dutyPersonPhone;
	}

	public String getDutyPersonSex() {
		return this.dutyPersonSex;
	}

	public void setDutyPersonSex(String dutyPersonSex) {
		this.dutyPersonSex = dutyPersonSex;
	}

	public String getDutyPersonMail() {
		return this.dutyPersonMail;
	}

	public void setDutyPersonMail(String dutyPersonMail) {
		this.dutyPersonMail = dutyPersonMail;
	}

	public String getDutyPersonStatus() {
		return this.dutyPersonStatus;
	}

	public void setDutyPersonStatus(String dutyPersonStatus) {
		this.dutyPersonStatus = dutyPersonStatus;
	}

	public String getDutyPersonRemark() {
		return this.dutyPersonRemark;
	}

	public void setDutyPersonRemark(String dutyPersonRemark) {
		this.dutyPersonRemark = dutyPersonRemark;
	}

}