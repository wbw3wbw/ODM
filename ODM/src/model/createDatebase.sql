CREATE TABLE `odm`.`admin_info` (
  `admin_id` VARCHAR(30) NOT NULL COMMENT '用户ID',
  `admin_pwd` VARCHAR(45) NULL COMMENT '密码',
  `admin_name` VARCHAR(45) NULL COMMENT '姓名 ',
  `admin_telphone` VARCHAR(45) NULL COMMENT '手机号',
  `admin_mail` VARCHAR(45) NULL COMMENT '邮箱',
  `admin_level` VARCHAR(45) NULL COMMENT '权限级别',
  `admin_power` VARCHAR(100) NULL COMMENT '权限明细',
  `admin_lastlogintime` DATETIME NULL COMMENT '最后登录时间',
  PRIMARY KEY (`admin_id`));

CREATE TABLE `odm`.`od_record` (
  `od_record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收文ID',
  `od_record_adminid` varchar(45) DEFAULT NULL COMMENT '记录人ID',
  `od_record_instime` datetime DEFAULT NULL COMMENT '录入时间',
  `od_record_cometime` datetime DEFAULT NULL COMMENT '来文时间',
  `od_record_name` varchar(100) DEFAULT NULL COMMENT '文件名称',
  `od_record_num` varchar(60) DEFAULT NULL COMMENT '内部编号',
  `od_record_serial` varchar(60) DEFAULT NULL COMMENT '文件编号',
  `od_record_year` varchar(30) DEFAULT NULL COMMENT '归档年度',
  `od_record_limit` varchar(30) DEFAULT NULL COMMENT '保存年限',
  `od_record_org` varchar(100) DEFAULT NULL COMMENT '来文单位',
  `od_record_from` varchar(60) DEFAULT NULL COMMENT '文件来源',
  `od_record_date` date DEFAULT NULL COMMENT '发文时间',
  `od_record_keywords` varchar(60) DEFAULT NULL COMMENT '关键词',
  `od_record_remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`od_record_id`)
) ;

CREATE TABLE `odm`.`od_att` (
  `od_att_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '附件ID',
  `od_att_odrid` int(11) DEFAULT NULL COMMENT '收文ID',
  `od_att_filename` varchar(100) DEFAULT NULL COMMENT '附件名称',
  `od_att_path` varchar(100) DEFAULT NULL COMMENT '存放路径',
  `od_att_suffix` varchar(100) DEFAULT NULL COMMENT '附件后缀',
  `od_att_remark` varchar(60) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`od_att_id`)
);

CREATE TABLE `odm`.`duty_person` (
  `duty_person_id` INT NOT NULL AUTO_INCREMENT COMMENT '值班人ID' ,
  `duty_person_name` VARCHAR(45) NOT NULL COMMENT '姓名' ,
  `duty_person_phone` VARCHAR(45) NOT NULL COMMENT '值班人手机' ,
  `duty_person_sex` VARCHAR(10) NULL COMMENT '性别' ,
  `duty_person_mail` VARCHAR(45) NULL COMMENT '邮箱' ,
  `duty_person_status` VARCHAR(10) NULL COMMENT '值班状态（Y：参与值班，N：不参与）' ,
  `duty_person_remark` VARCHAR(100) NULL COMMENT '备注' ,
  PRIMARY KEY (`duty_person_id`));
  
 CREATE TABLE `odm`.`duty_info` (
  `duty_info_id` INT NOT NULL AUTO_INCREMENT COMMENT '值班ID' ,
  `duty_info_personid` VARCHAR(45) NOT NULL COMMENT '关联值班人ID',
  `duty_info_personname` VARCHAR(45) NULL COMMENT '值班人姓名' ,
  `duty_info_date` VARCHAR(45) NOT NULL COMMENT '值班日期',
  `duty_info_order` VARCHAR(45) NOT NULL COMMENT '班次（0：全天，1：8-12，2：12-18，3：18-8）' ,
  `duty_info_isremind` VARCHAR(45) NOT NULL COMMENT '是否提醒' ,
  PRIMARY KEY (`duty_info_id`));
  
 CREATE TABLE `odm`.`sys_menu` (
  `sys_menu_id` INT NOT NULL AUTO_INCREMENT,
  `sys_menu_name` VARCHAR(45) NOT NULL COMMENT '菜单显示名称',
  `sys_menu_level` VARCHAR(20) NULL COMMENT '级别',
  `sys_menu_parentid` INT NULL COMMENT '父结点ID',
  `sys_menu_icon` VARCHAR(45) NULL COMMENT '字库图标名称',
  `sys_menu_image` VARCHAR(100) NULL COMMENT '图片图标路径',
  `sys_menu_address` VARCHAR(100) NULL COMMENT '地址',
  `sys_menu_click` VARCHAR(20) NULL COMMENT '是否可点击',
  `sys_menu_spare1` VARCHAR(45) NULL COMMENT '备用1',
  `sys_menu_spare2` VARCHAR(45) NULL COMMENT '备用2',
  `sys_menu_spare3` VARCHAR(45) NULL COMMENT '备用3',
  PRIMARY KEY (`sys_menu_id`));
  