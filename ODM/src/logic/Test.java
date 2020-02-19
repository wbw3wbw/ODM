package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.omg.CORBA.PRIVATE_MEMBER;

public class Test {

	public static void main(String[] args) {
		String str = "2017δ�|2018δ�";
		String nfStr ="2017";
		String statusStr ="���ύ";
		Test test = new Test();
		System.out.println(test.locateByNf(str, nfStr));
		System.out.println(test.replaceByNf(str, nfStr, statusStr));
	}
	
	private  String locateByNf(String str, String nfStr){
		return str.substring(str.indexOf(nfStr)+4, str.indexOf(nfStr)+7);
	}
	
	private String replaceByNf(String str, String nfStr, String statusStr){
		return str.substring(0,str.indexOf(nfStr)+4)+statusStr+str.substring(str.indexOf(nfStr)+7);
	}
}
