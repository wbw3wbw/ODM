package dtree;

public class DTreeResponse {
	  /** ״̬��*/
	  private int code;
	  /** ��Ϣ��ʶ*/
	  private String msg;
	  /** ״̬��*/
	  private Status status;
	  /** ����*/
	  private Object data;
	  
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	  

	}
