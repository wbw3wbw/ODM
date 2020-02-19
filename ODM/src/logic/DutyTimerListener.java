/**
 * 定时执行任务的监听，增加于2020-01-17
 */
package logic;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *  建立一个监听器，配置到web.xml用于监听定时任务
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