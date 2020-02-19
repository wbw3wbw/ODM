package model;
// default package



/**
 * sysMenu entity. @author MyEclipse Persistence Tools
 */

public class SysMenu  implements java.io.Serializable {


    // Fields    

     private Integer sysMenuId;
     private String sysMenuName;
     private String sysMenuLevel;
     private Integer sysMenuParentid;
     private String sysMenuIcon;
     private String sysMenuImage;
     private String sysMenuAddress;
     private String sysMenuClick;
     private String sysMenuSpare1;
     private String sysMenuSpare2;
     private String sysMenuSpare3;


    // Constructors

    /** default constructor */
    public SysMenu() {
    }

	/** minimal constructor */
    public SysMenu(String sysMenuName) {
        this.sysMenuName = sysMenuName;
    }
    
    /** full constructor */
    public SysMenu(String sysMenuName, String sysMenuLevel, Integer sysMenuParentid, String sysMenuIcon, String sysMenuImage, String sysMenuAddress, String sysMenuClick, String sysMenuSpare1, String sysMenuSpare2, String sysMenuSpare3) {
        this.sysMenuName = sysMenuName;
        this.sysMenuLevel = sysMenuLevel;
        this.sysMenuParentid = sysMenuParentid;
        this.sysMenuIcon = sysMenuIcon;
        this.sysMenuImage = sysMenuImage;
        this.sysMenuAddress = sysMenuAddress;
        this.sysMenuClick = sysMenuClick;
        this.sysMenuSpare1 = sysMenuSpare1;
        this.sysMenuSpare2 = sysMenuSpare2;
        this.sysMenuSpare3 = sysMenuSpare3;
    }

   
    // Property accessors

    public Integer getSysMenuId() {
        return this.sysMenuId;
    }
    
    public void setSysMenuId(Integer sysMenuId) {
        this.sysMenuId = sysMenuId;
    }

    public String getSysMenuName() {
        return this.sysMenuName;
    }
    
    public void setSysMenuName(String sysMenuName) {
        this.sysMenuName = sysMenuName;
    }

    public String getSysMenuLevel() {
        return this.sysMenuLevel;
    }
    
    public void setSysMenuLevel(String sysMenuLevel) {
        this.sysMenuLevel = sysMenuLevel;
    }

    public Integer getSysMenuParentid() {
        return this.sysMenuParentid;
    }
    
    public void setSysMenuParentid(Integer sysMenuParentid) {
        this.sysMenuParentid = sysMenuParentid;
    }

    public String getSysMenuIcon() {
        return this.sysMenuIcon;
    }
    
    public void setSysMenuIcon(String sysMenuIcon) {
        this.sysMenuIcon = sysMenuIcon;
    }

    public String getSysMenuImage() {
        return this.sysMenuImage;
    }
    
    public void setSysMenuImage(String sysMenuImage) {
        this.sysMenuImage = sysMenuImage;
    }

    public String getSysMenuAddress() {
        return this.sysMenuAddress;
    }
    
    public void setSysMenuAddress(String sysMenuAddress) {
        this.sysMenuAddress = sysMenuAddress;
    }

    public String getSysMenuClick() {
        return this.sysMenuClick;
    }
    
    public void setSysMenuClick(String sysMenuClick) {
        this.sysMenuClick = sysMenuClick;
    }

    public String getSysMenuSpare1() {
        return this.sysMenuSpare1;
    }
    
    public void setSysMenuSpare1(String sysMenuSpare1) {
        this.sysMenuSpare1 = sysMenuSpare1;
    }

    public String getSysMenuSpare2() {
        return this.sysMenuSpare2;
    }
    
    public void setSysMenuSpare2(String sysMenuSpare2) {
        this.sysMenuSpare2 = sysMenuSpare2;
    }

    public String getSysMenuSpare3() {
        return this.sysMenuSpare3;
    }
    
    public void setSysMenuSpare3(String sysMenuSpare3) {
        this.sysMenuSpare3 = sysMenuSpare3;
    }
   








}