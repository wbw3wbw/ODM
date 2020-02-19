package model;
// default package



/**
 * DutyInfo entity. @author MyEclipse Persistence Tools
 */

public class DutyInfo  implements java.io.Serializable {


    // Fields    

     private Integer dutyInfoId;
     private Integer dutyInfoPersonid;
     private String dutyInfoPersonname;
     private String dutyInfoDate;
     private String dutyInfoOrder;
     private String dutyInfoIsremind;


    // Constructors

    /** default constructor */
    public DutyInfo() {
    }

	/** minimal constructor */
    public DutyInfo(Integer dutyInfoPersonid, String dutyInfoDate, String dutyInfoOrder, String dutyInfoIsremind) {
        this.dutyInfoPersonid = dutyInfoPersonid;
        this.dutyInfoDate = dutyInfoDate;
        this.dutyInfoOrder = dutyInfoOrder;
        this.dutyInfoIsremind = dutyInfoIsremind;
    }
    
    /** full constructor */
    public DutyInfo(Integer dutyInfoPersonid, String dutyInfoPersonname, String dutyInfoDate, String dutyInfoOrder, String dutyInfoIsremind) {
        this.dutyInfoPersonid = dutyInfoPersonid;
        this.dutyInfoPersonname = dutyInfoPersonname;
        this.dutyInfoDate = dutyInfoDate;
        this.dutyInfoOrder = dutyInfoOrder;
        this.dutyInfoIsremind = dutyInfoIsremind;
    }

   
    // Property accessors

    public Integer getDutyInfoId() {
        return this.dutyInfoId;
    }
    
    public void setDutyInfoId(Integer dutyInfoId) {
        this.dutyInfoId = dutyInfoId;
    }

    public Integer getDutyInfoPersonid() {
        return this.dutyInfoPersonid;
    }
    
    public void setDutyInfoPersonid(Integer dutyInfoPersonid) {
        this.dutyInfoPersonid = dutyInfoPersonid;
    }

    public String getDutyInfoPersonname() {
        return this.dutyInfoPersonname;
    }
    
    public void setDutyInfoPersonname(String dutyInfoPersonname) {
        this.dutyInfoPersonname = dutyInfoPersonname;
    }

    public String getDutyInfoDate() {
        return this.dutyInfoDate;
    }
    
    public void setDutyInfoDate(String dutyInfoDate) {
        this.dutyInfoDate = dutyInfoDate;
    }

    public String getDutyInfoOrder() {
        return this.dutyInfoOrder;
    }
    
    public void setDutyInfoOrder(String dutyInfoOrder) {
        this.dutyInfoOrder = dutyInfoOrder;
    }

    public String getDutyInfoIsremind() {
        return this.dutyInfoIsremind;
    }
    
    public void setDutyInfoIsremind(String dutyInfoIsremind) {
        this.dutyInfoIsremind = dutyInfoIsremind;
    }
   








}