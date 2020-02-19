package model;
// default package



/**
 * OdAtt entity. @author MyEclipse Persistence Tools
 */

public class OdAtt  implements java.io.Serializable {


    // Fields    

     private Integer odAttId;
     private Integer odAttOdrid;
     private String odAttFilename;
     private String odAttPath;
     private String odAttSuffix;
     private String odAttRemark;


    // Constructors

    /** default constructor */
    public OdAtt() {
    }

    
    /** full constructor */
    public OdAtt(Integer odAttOdrid, String odAttFilename, String odAttPath, String odAttSuffix, String odAttRemark) {
        this.odAttOdrid = odAttOdrid;
        this.odAttFilename = odAttFilename;
        this.odAttPath = odAttPath;
        this.odAttSuffix = odAttSuffix;
        this.odAttRemark = odAttRemark;
    }

   
    // Property accessors

    public Integer getOdAttId() {
        return this.odAttId;
    }
    
    public void setOdAttId(Integer odAttId) {
        this.odAttId = odAttId;
    }

    public Integer getOdAttOdrid() {
        return this.odAttOdrid;
    }
    
    public void setOdAttOdrid(Integer odAttOdrid) {
        this.odAttOdrid = odAttOdrid;
    }

    public String getOdAttFilename() {
        return this.odAttFilename;
    }
    
    public void setOdAttFilename(String odAttFilename) {
        this.odAttFilename = odAttFilename;
    }

    public String getOdAttPath() {
        return this.odAttPath;
    }
    
    public void setOdAttPath(String odAttPath) {
        this.odAttPath = odAttPath;
    }

    public String getOdAttSuffix() {
        return this.odAttSuffix;
    }
    
    public void setOdAttSuffix(String odAttSuffix) {
        this.odAttSuffix = odAttSuffix;
    }

    public String getOdAttRemark() {
        return this.odAttRemark;
    }
    
    public void setOdAttRemark(String odAttRemark) {
        this.odAttRemark = odAttRemark;
    }
   








}