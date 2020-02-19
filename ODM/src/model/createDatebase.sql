CREATE TABLE `odm`.`admin_info` (
  `admin_id` VARCHAR(30) NOT NULL COMMENT '�û�ID',
  `admin_pwd` VARCHAR(45) NULL COMMENT '����',
  `admin_name` VARCHAR(45) NULL COMMENT '���� ',
  `admin_telphone` VARCHAR(45) NULL COMMENT '�ֻ���',
  `admin_mail` VARCHAR(45) NULL COMMENT '����',
  `admin_level` VARCHAR(45) NULL COMMENT 'Ȩ�޼���',
  `admin_power` VARCHAR(100) NULL COMMENT 'Ȩ����ϸ',
  `admin_lastlogintime` DATETIME NULL COMMENT '����¼ʱ��',
  PRIMARY KEY (`admin_id`));

CREATE TABLE `odm`.`od_record` (
  `od_record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `od_record_adminid` varchar(45) DEFAULT NULL COMMENT '��¼��ID',
  `od_record_instime` datetime DEFAULT NULL COMMENT '¼��ʱ��',
  `od_record_cometime` datetime DEFAULT NULL COMMENT '����ʱ��',
  `od_record_name` varchar(100) DEFAULT NULL COMMENT '�ļ�����',
  `od_record_num` varchar(60) DEFAULT NULL COMMENT '�ڲ����',
  `od_record_serial` varchar(60) DEFAULT NULL COMMENT '�ļ����',
  `od_record_year` varchar(30) DEFAULT NULL COMMENT '�鵵���',
  `od_record_limit` varchar(30) DEFAULT NULL COMMENT '��������',
  `od_record_org` varchar(100) DEFAULT NULL COMMENT '���ĵ�λ',
  `od_record_from` varchar(60) DEFAULT NULL COMMENT '�ļ���Դ',
  `od_record_date` date DEFAULT NULL COMMENT '����ʱ��',
  `od_record_keywords` varchar(60) DEFAULT NULL COMMENT '�ؼ���',
  `od_record_remark` varchar(100) DEFAULT NULL COMMENT '��ע',
  PRIMARY KEY (`od_record_id`)
) ;

CREATE TABLE `odm`.`od_att` (
  `od_att_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `od_att_odrid` int(11) DEFAULT NULL COMMENT '����ID',
  `od_att_filename` varchar(100) DEFAULT NULL COMMENT '��������',
  `od_att_path` varchar(100) DEFAULT NULL COMMENT '���·��',
  `od_att_suffix` varchar(100) DEFAULT NULL COMMENT '������׺',
  `od_att_remark` varchar(60) DEFAULT NULL COMMENT '��ע',
  PRIMARY KEY (`od_att_id`)
);

CREATE TABLE `odm`.`duty_person` (
  `duty_person_id` INT NOT NULL AUTO_INCREMENT COMMENT 'ֵ����ID' ,
  `duty_person_name` VARCHAR(45) NOT NULL COMMENT '����' ,
  `duty_person_phone` VARCHAR(45) NOT NULL COMMENT 'ֵ�����ֻ�' ,
  `duty_person_sex` VARCHAR(10) NULL COMMENT '�Ա�' ,
  `duty_person_mail` VARCHAR(45) NULL COMMENT '����' ,
  `duty_person_status` VARCHAR(10) NULL COMMENT 'ֵ��״̬��Y������ֵ�࣬N�������룩' ,
  `duty_person_remark` VARCHAR(100) NULL COMMENT '��ע' ,
  PRIMARY KEY (`duty_person_id`));
  
 CREATE TABLE `odm`.`duty_info` (
  `duty_info_id` INT NOT NULL AUTO_INCREMENT COMMENT 'ֵ��ID' ,
  `duty_info_personid` VARCHAR(45) NOT NULL COMMENT '����ֵ����ID',
  `duty_info_personname` VARCHAR(45) NULL COMMENT 'ֵ��������' ,
  `duty_info_date` VARCHAR(45) NOT NULL COMMENT 'ֵ������',
  `duty_info_order` VARCHAR(45) NOT NULL COMMENT '��Σ�0��ȫ�죬1��8-12��2��12-18��3��18-8��' ,
  `duty_info_isremind` VARCHAR(45) NOT NULL COMMENT '�Ƿ�����' ,
  PRIMARY KEY (`duty_info_id`));
  
 CREATE TABLE `odm`.`sys_menu` (
  `sys_menu_id` INT NOT NULL AUTO_INCREMENT,
  `sys_menu_name` VARCHAR(45) NOT NULL COMMENT '�˵���ʾ����',
  `sys_menu_level` VARCHAR(20) NULL COMMENT '����',
  `sys_menu_parentid` INT NULL COMMENT '�����ID',
  `sys_menu_icon` VARCHAR(45) NULL COMMENT '�ֿ�ͼ������',
  `sys_menu_image` VARCHAR(100) NULL COMMENT 'ͼƬͼ��·��',
  `sys_menu_address` VARCHAR(100) NULL COMMENT '��ַ',
  `sys_menu_click` VARCHAR(20) NULL COMMENT '�Ƿ�ɵ��',
  `sys_menu_spare1` VARCHAR(45) NULL COMMENT '����1',
  `sys_menu_spare2` VARCHAR(45) NULL COMMENT '����2',
  `sys_menu_spare3` VARCHAR(45) NULL COMMENT '����3',
  PRIMARY KEY (`sys_menu_id`));
  