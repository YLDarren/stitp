package cn.edu.njupt.configure;

/**
 * 系统环境变量统一配置类
 */
public class SystemVariables {

    //系统统一变量
    private static final String SYS_VARIABLE = "WIN";

    //系统文件路径
    private static final String WIN_SYSTEM = "G:/opencv/opencv/build/java/x64/opencv_java341.dll";

    private static final String STITP_SYSTEM = "/home/stitp/apps/opencv/opencv-3.4.1/build/lib/libopencv_java341.so";

    private static final String YTO_SYSTEM = "/usr/local/opencv/opencv-3.4.1/build/lib/libopencv_java341.so";

    //切割后的图像保存路径
    private static final String WIN_PATH = "C:/Users/X240/Desktop/opencv/web/web/";

    private static final String STITP_PATH = "/home/stitp/javaData/cut/";

    private static final String YTO_PATH = "/usr/local/cut/";

    //图像上传后的保存路径
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


    //标志
    private static final String WIN = "WIN";

    private static final String STITP = "STITP";

    private static final String YTO = "YTO";

    /**
     * 加载opencv系统文件
     * @return
     */
    public static String loadOpencvSystemFile(){
        switch (SYS_VARIABLE){
            case WIN:
                return WIN_SYSTEM;
            case STITP:
                return STITP_SYSTEM;
            case YTO:
                return YTO_SYSTEM;
            default:
                return WIN_SYSTEM;
        }
    }

    /**
     * 获取图像保存的据对路径
     * @return
     */
    public static String saveImgAbsolutePath(){
        switch (SYS_VARIABLE){
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
     * 获取图像url的前缀
     * @return
     */
    public static String getImagePrefixUrl(){
        switch (SYS_VARIABLE){
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

    /**
     * 获取图像切割后保存的路径
     * @return
     */
    public static String saveImgCutPATH(){
        switch (SYS_VARIABLE){
            case WIN:
                return WIN_PATH;
            case STITP:
                return STITP_PATH;
            case YTO:
                return YTO_PATH;
            default:
                return WIN_PATH;
        }
    }

}
