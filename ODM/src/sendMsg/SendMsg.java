package sendMsg;

import java.io.IOException;
import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import sendMsg.Cmcc_mas_wbsStub.MessageFormat;
import sendMsg.Cmcc_mas_wbsStub.SendMethodType;

public class SendMsg {

	public void sendSms(String message, String address) throws Exception {
		try {
			//声明实体化
			Cmcc_mas_wbsStub stub = new Cmcc_mas_wbsStub();
			Cmcc_mas_wbsStub.SendSmsRequest ssr = new Cmcc_mas_wbsStub.SendSmsRequest();
			ssr.setApplicationID("P000000000000032");
			ssr.setDeliveryResultRequest(true);
			ssr.setExtendCode("123456");
			//String message = "WebService短信测试--王博文201911271439";
			ssr.setMessage(message);
			System.out.println(message);
			ssr.setMessageFormat(MessageFormat.GB2312);
			ssr.setSendMethod(SendMethodType.Long);
			//String address  = "tel:13359010375;tel:18756587138";//
			String[] a =address.split(";");
			int leng= a.length;
			org.apache.axis2.databinding.types.URI [] ary=new org.apache.axis2.databinding.types.URI[leng];
			for(int i=0;i<leng;i++){
				org.apache.axis2.databinding.types.URI temp=new org.apache.axis2.databinding.types.URI(a[i]);
				ary[i]=temp;
			}
			ssr.setDestinationAddresses(ary);
	   
			//开始调用
			Cmcc_mas_wbsStub.SendSmsResponse rep = stub.sendSms(ssr);
			String requestIdentifier = rep.getRequestIdentifier();
			System.out.println(requestIdentifier);
			

		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

}
