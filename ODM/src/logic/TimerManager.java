/**
 * 定时执行任务的管理，增加于2020-01-17
 */

package logic;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class TimerManager {
     
     //每次运行的时间间隔
     private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
      
     public TimerManager() {
      Calendar calendar1 = Calendar.getInstance();
      Calendar calendar2 = Calendar.getInstance();
      Calendar calendar3 = Calendar.getInstance();
      Calendar calendar4 = Calendar.getInstance();
            
      /*** 每日4次执行 ***/
     
      calendar1.set(Calendar.HOUR_OF_DAY, 7);
      calendar1.set(Calendar.MINUTE, 20);
      calendar1.set(Calendar.SECOND, 0);
      
      calendar2.set(Calendar.HOUR_OF_DAY, 7);
      calendar2.set(Calendar.MINUTE, 20);
      calendar2.set(Calendar.SECOND, 0);
      
      calendar3.set(Calendar.HOUR_OF_DAY, 11);
      calendar3.set(Calendar.MINUTE, 0);
      calendar3.set(Calendar.SECOND, 0);
      
      calendar4.set(Calendar.HOUR_OF_DAY, 16);
      calendar4.set(Calendar.MINUTE, 27);
      calendar4.set(Calendar.SECOND, 0);
      
      Date date1=calendar1.getTime(); //第一次执行定时任务的时间
      Date date2=calendar2.getTime(); //第一次执行定时任务的时间
      Date date3=calendar3.getTime(); //第一次执行定时任务的时间
      Date date4=calendar4.getTime(); //第一次执行定时任务的时间
      
      //如果第一次执行定时任务的时间 小于 当前的时间
      //此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
      if (date1.before(new Date())) {          date1 = this.addDay(date1, 1);      }
      if (date2.before(new Date())) {          date2 = this.addDay(date2, 1);      }
      if (date3.before(new Date())) {          date3 = this.addDay(date3, 1);      }
      if (date4.before(new Date())) {          date4 = this.addDay(date4, 1);      }
       
      Timer timer1 = new Timer();
      Timer timer2 = new Timer();
      Timer timer3 = new Timer();
      Timer timer4 = new Timer();
       
      DutyTimerTask task1 = new DutyTimerTask("0");
      DutyTimerTask task2 = new DutyTimerTask("1");
      DutyTimerTask task3 = new DutyTimerTask("2");
      DutyTimerTask task4 = new DutyTimerTask("3");
      //安排指定的任务在指定的时间开始进行重复的固定延迟执行。
      try{
	      timer1.schedule(task1, date1,PERIOD_DAY);
	      timer2.schedule(task2, date2,PERIOD_DAY);
	      timer3.schedule(task3, date3,PERIOD_DAY);
	      timer4.schedule(task4, date4,PERIOD_DAY);
      }catch (Exception e) {
      	e.printStackTrace();
        System.out.println("-------------发生异常--------------");
      } 
     }
     
     // 增加或减少天数
     public Date addDay(Date date, int num) {
      Calendar startDT = Calendar.getInstance();
      startDT.setTime(date);
      startDT.add(Calendar.DAY_OF_MONTH, num);
      return startDT.getTime();
     }
      
    }