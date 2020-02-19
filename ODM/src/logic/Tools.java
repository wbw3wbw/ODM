package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Random;

import org.apache.commons.validator.Var;


public class Tools {
	/**
	 * �ж��ַ���ȥ��β�ո���Ƿ�Ϊ��
	 * @param str
	 * @return  Ϊ�շ���true�����շ���false
	 */
	public boolean strEmpty(String str){
		if(str==null || str.length()<=0){
			return true;
		}else if(str.trim().length()==0){
			return true;
		}
		return false;
	}
	
	/**
	 * �ж��Ƿ��ϴ�����Ӧ���ļ�
	 * @param uploadFileList
	 * @param related
	 * @return
	 */
/*	public boolean fileEmpty(List<UploadFile> uploadFileList, String related){
		for(int i=0; i<uploadFileList.size(); i++){
			if(related.equals(uploadFileList.get(i).getFileRelated())){
				return false;
			}
		}
		return true;
	}*/
	
    /**
     * ��ȡ�������˵�webapps·��
     * @return
     */
    public String findServerPath(){
     String classPath = this.getClass().getClassLoader().getResource("/").getPath();
           try {
               classPath =URLDecoder.decode(classPath, "gb2312");
           } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
           }
     String[] strPath = classPath.split("/");
     String path = "";
     for(int i = 0;i < strPath.length ; i++){
      if(i > 0 && i <= 3){
       path = path + strPath[i]+"/";
      }
     }
     return path;
    }
    
    /**
     * ����������Ҫ��ȡ�����õ����N������
     * @param count  Ҫ��ȡ����
     * @param rowNum  �Ӷ��ٸ��г�ȡ
     * @param offset ƫ����
     * @return ����
     */
	public int[] getRandoms(int count, int rowNum, int offset){
		int[] nums = new int[count];
		//�б��0��ʼ�����Դ�2-N��ȡ�����
		Random random = new Random();
		int randomNum = 0;
		for(int i=0; i<count;){
			randomNum = random.nextInt(rowNum-offset)+offset;
			if(checkExsit(randomNum, nums)){
				nums[i] = randomNum;
				i++;
			}
		}
		return nums;
	}
	
	private boolean checkExsit(int randomNum, int[] nums){
		for(int i=0; i<nums.length; i++){
			if(randomNum == nums[i]){
				return false;
			}
		}
		return true;
	}
	
	/**
	    * ʹ���ļ�ͨ���ķ�ʽ�����ļ�
	    * 
	    * @param s
	    *            Դ�ļ�
	    * @param t
	    *            ���Ƶ������ļ�
	    */
	    public void fileChannelCopy(String o, String n) {
	    	File s = new File(o);
	    	File t = new File(n);
	        FileInputStream fi = null;
	        FileOutputStream fo = null;
	        FileChannel in = null;
	        FileChannel out = null;
	        try {
	            fi = new FileInputStream(s);
	            fo = new FileOutputStream(t);
	            in = fi.getChannel();//�õ���Ӧ���ļ�ͨ��
	            out = fo.getChannel();//�õ���Ӧ���ļ�ͨ��
	            in.transferTo(0, in.size(), out);//��������ͨ�������Ҵ�inͨ����ȡ��Ȼ��д��outͨ��
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                fi.close();
	                in.close();
	                fo.close();
	                out.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

}
