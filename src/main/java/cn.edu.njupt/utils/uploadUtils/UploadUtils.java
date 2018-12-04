package cn.edu.njupt.utils.uploadUtils;

import java.io.File;

/**
 * 上传工具类
 */
public class UploadUtils {
    public static final String ABSOLUTE_PATH = "C:/Users/X240/Desktop/upload/";//绝对路径
    public static final String RELATIVE_PATH = "/upload/";//相对路径
    public static final String IMAGE_PREFIX_URL = "http://127.0.0.1:8080/stitp/upload/";//图片url前缀


    /**
     * 文件工厂
     * @param filePath
     * @param fileName
     * @return
     */
    public static File fileFactory(String filePath , String fileName){
        File path = new File(filePath);
        if(!path.exists()){
           path.mkdirs();
        }
        return new File(path + "/" + fileName);
    }
}
