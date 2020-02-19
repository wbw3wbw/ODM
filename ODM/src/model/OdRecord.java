package model;
// default package

import java.sql.Timestamp;
import java.util.Date;


/**
 * OdRecord entity. @author MyEclipse Persistence Tools
 */

public class OdRecord  implements java.io.Serializable {


    // Fields    

     private Integer odRecordId;
     private String odRecordAdminid;
     private Timestamp odRecordInstime;
     private String odRecordCometime;
     private String odRecordName;
     private String odRecordNum;
     private String odRecordSerial;
     private String odRecordYear;
     private String odRecordLimit;
     private String odRecordOrg;
     private String odRecordFrom;
     private String odRecordDate;
     private String odRecordKeywords;
     private String odRecordRemark;


    // Constructors

    /** default constructor */
    public OdRecord() {
    }

    
    /** full constructor */
    public OdRecord(String odRecordAdminid, Timestamp odRecordInstime, String odRecordCometime, String odRecordName, String odRecordNum, String odRecordSerial, String odRecordYear, String odRecordLimit, String odRecordOrg, String odRecordFrom, String odRecordDate, String odRecordKeywords, String odRecordRemark) {
        this.odRecordAdminid = odRecordAdminid;
        this.odRecordInstime = odRecordInstime;
        this.odRecordCometime = odRecordCometime;
        this.odRecordName = odRecordName;
        this.odRecordNum = odRecordNum;
        this.odRecordSerial = odRecordSerial;
        this.odRecordYear = odRecordYear;
        this.odRecordLimit = odRecordLimit;
        this.odRecordOrg = odRecordOrg;
        this.odRecordFrom = odRecordFrom;
        this.odRecordDate = odRecordDate;
        this.odRecordKeywords = odRecordKeywords;
        this.odRecordRemark = odRecordRemark;
    }

   
    // Property accessors

    public Integer getOdRecordId() {
        return this.odRecordId;
    }
    
    public void setOdRecordId(Integer odRecordId) {
        this.odRecordId = odRecordId;
    }

    public String getOdRecordAdminid() {
        return this.odRecordAdminid;
    }
    
    public void setOdRecordAdminid(String odRecordAdminid) {
        this.odRecordAdminid = odRecordAdminid;
    }

    public Timestamp getOdRecordInstime() {
        return this.odRecordInstime;
    }
    
    public void setOdRecordInstime(Timestamp odRecordInstime) {
        this.odRecordInstime = odRecordInstime;
    }

    public String getOdRecordCometime() {
        return this.odRecordCometime;
    }
    
    public void setOdRecordCometime(String odRecordCometime) {
        this.odRecordCometime = odRecordCometime;
    }

    public String getOdRecordName() {
        return this.odRecordName;
    }
    
    public void setOdRecordName(String odRecordName) {
        this.odRecordName = odRecordName;
    }

    public String getOdRecordNum() {
        return this.odRecordNum;
    }
    
    public void setOdRecordNum(String odRecordNum) {
        this.odRecordNum = odRecordNum;
    }

    public String getOdRecordSerial() {
        return this.odRecordSerial;
    }
    
    public void setOdRecordSerial(String odRecordSerial) {
        this.odRecordSerial = odRecordSerial;
    }

    public String getOdRecordYear() {
        return this.odRecordYear;
    }
    
    public void setOdRecordYear(String odRecordYear) {
        this.odRecordYear = odRecordYear;
    }

    public String getOdRecordLimit() {
        return this.odRecordLimit;
    }
    
    public void setOdRecordLimit(String odRecordLimit) {
        this.odRecordLimit = odRecordLimit;
    }

    public String getOdRecordOrg() {
        return this.odRecordOrg;
    }
    
    public void setOdRecordOrg(String odRecordOrg) {
        this.odRecordOrg = odRecordOrg;
    }

    public String getOdRecordFrom() {
        return this.odRecordFrom;
    }
    
    public void setOdRecordFrom(String odRecordFrom) {
        this.odRecordFrom = odRecordFrom;
    }

    public String getOdRecordDate() {
        return this.odRecordDate;
    }
    
    public void setOdRecordDate(String odRecordDate) {
        this.odRecordDate = odRecordDate;
    }

    public String getOdRecordKeywords() {
        return this.odRecordKeywords;
    }
    
    public void setOdRecordKeywords(String odRecordKeywords) {
        this.odRecordKeywords = odRecordKeywords;
    }

    public String getOdRecordRemark() {
        return this.odRecordRemark;
    }
    
    public void setOdRecordRemark(String odRecordRemark) {
        this.odRecordRemark = odRecordRemark;
    }
   








}