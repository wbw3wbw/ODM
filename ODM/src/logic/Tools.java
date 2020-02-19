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
	 * 判断字符串去首尾空格后是否为空
	 * @param str
	 * @return  为空返回true，不空返回false
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
	 * 判断是否上传了相应的文件
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
     * 获取服务器端的webapps路径
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
     * 输入总数和要抽取数，得到随机N个数字
     * @param count  要抽取几个
     * @param rowNum  从多少个中抽取
     * @param offset 偏移量
     * @return 数组
     */
	public int[] getRandoms(int count, int rowNum, int offset){
		int[] nums = new int[count];
		//行标从0开始，所以从2-N中取随机数
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
	    * 使用文件通道的方式复制文件
	    * 
	    * @param s
	    *            源文件
	    * @param t
	    *            复制到的新文件
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
	            in = fi.getChannel();//得到对应的文件通道
	            out = fo.getChannel();//得到对应的文件通道
	            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
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
