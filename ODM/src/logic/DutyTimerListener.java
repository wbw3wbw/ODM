/**
 * ��ʱִ������ļ�����������2020-01-17
 */
package logic;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *  ����һ�������������õ�web.xml���ڼ�����ʱ����
 * @author WangBowen
 *
 */
public class DutyTimerListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        new TimerManager();
    }

    public void contextDestroyed(ServletContextEvent event) {
    }

}