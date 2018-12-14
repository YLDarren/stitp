package cn.edu.njupt.utils.uploadUtils;

import java.io.File;

/**
 * 上传工具类
 */
public class UploadUtils {

    //WIN
    private static final String ABSOLUTE_PATH = "C:/Users/X240/Desktop/upload/";//绝对路径
    public static final String RELATIVE_PATH = "/upload/";//相对路径
    private static final String IMAGE_PREFIX_URL = "http://127.0.0.1:8080/stitp/upload/";//图片url前缀

    //STITP
    private static final String STITP_ABSOLUTE_PATH = "/home/stitp/javaData/data/";
    private static final String STITP_IMAGE_PREFIX_URL = "http://10.166.33.86:8080/stitp/upload/";

    //YTO
    private static final String YTO_ABSOLUTE_PATH = "/usr/local/data/";
    private static final String YTO_IMAGE_PREFIX_URL = "http://39.108.188.185:8080/stitp/upload/";


    public static final String WIN = "WIN";

    public static final String STITP = "STITP";

    public static final String YTO = "YTO";

    public static String getImagePrefixUrl(String type){
        switch (type){
            case WIN:
                return IMAGE_PREFIX_URL;
            case STITP:
                return STITP_IMAGE_PREFIX_URL;
            case YTO:
                return YTO_IMAGE_PREFIX_URL;
            default:
                return IMAGE_PREFIX_URL;
        }
    }

    public static String getAbsolutePath(String type){
        switch (type){
            case WIN:
                return ABSOLUTE_PATH;
            case STITP:
                return STITP_ABSOLUTE_PATH;
            case YTO:
                return YTO_ABSOLUTE_PATH;
            default:
                return ABSOLUTE_PATH;
        }
    }


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

    public static String dirFactory(String filePath){
        File path = new File(filePath);
        if(!path.exists()){
            path.mkdirs();
        }
        return path.toString() + "/";
    }


}
