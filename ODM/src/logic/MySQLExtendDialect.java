package logic;

import org.hibernate.Hibernate;
import org.hibernate.dialect.*;
import org.hibernate.dialect.function.SQLFunctionTemplate;

/**
 * MySQL��չ����
 * @author Winter Lau
 */
public class MySQLExtendDialect extends MySQLDialect {

	public MySQLExtendDialect(){
		super();
		registerFunction("convert_gbk", 
                 new SQLFunctionTemplate(Hibernate.STRING, "convert(?1 using gbk)") );
	}
	
}
