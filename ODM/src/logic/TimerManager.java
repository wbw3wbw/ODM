/**
 * ��ʱִ������Ĺ���������2020-01-17
 */

package logic;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class TimerManager {
     
     //ÿ�����е�ʱ����
     private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
      
     public TimerManager() {
      Calendar calendar1 = Calendar.getInstance();
      Calendar calendar2 = Calendar.getInstance();
      Calendar calendar3 = Calendar.getInstance();
      Calendar calendar4 = Calendar.getInstance();
            
      /*** ÿ��4��ִ�� ***/
     
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
      
      Date date1=calendar1.getTime(); //��һ��ִ�ж�ʱ�����ʱ��
      Date date2=calendar2.getTime(); //��һ��ִ�ж�ʱ�����ʱ��
      Date date3=calendar3.getTime(); //��һ��ִ�ж�ʱ�����ʱ��
      Date date4=calendar4.getTime(); //��һ��ִ�ж�ʱ�����ʱ��
      
      //�����һ��ִ�ж�ʱ�����ʱ�� С�� ��ǰ��ʱ��
      //��ʱҪ�� ��һ��ִ�ж�ʱ�����ʱ�� ��һ�죬�Ա���������¸�ʱ���ִ�С��������һ�죬���������ִ�С�
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
      //����ָ����������ָ����ʱ�俪ʼ�����ظ��Ĺ̶��ӳ�ִ�С�
      try{
	      timer1.schedule(task1, date1,PERIOD_DAY);
	      timer2.schedule(task2, date2,PERIOD_DAY);
	      timer3.schedule(task3, date3,PERIOD_DAY);
	      timer4.schedule(task4, date4,PERIOD_DAY);
      }catch (Exception e) {
      	e.printStackTrace();
        System.out.println("-------------�����쳣--------------");
      } 
     }
     
     // ���ӻ��������
     public Date addDay(Date date, int num) {
      Calendar startDT = Calendar.getInstance();
      startDT.setTime(date);
      startDT.add(Calendar.DAY_OF_MONTH, num);
      return startDT.getTime();
     }
      
    }