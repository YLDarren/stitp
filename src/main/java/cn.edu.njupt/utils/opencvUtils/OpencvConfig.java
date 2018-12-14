package cn.edu.njupt.utils.opencvUtils;

/**
 * 配置信息文件
 */
public class OpencvConfig {
    //系统文件路径
    private static final String WIN_SYSTEM = "G:/opencv/opencv/build/java/x64/opencv_java341.dll";

    private static final String STITP_SYSTEM = "/home/stitp/apps/opencv/opencv-3.4.1/build/lib/libopencv_java341.so";

    private static final String YTO_SYSTEM = "/usr/local/opencv/opencv-3.4.1/build/lib/libopencv_java341.so";

    //切割后的图像保存路径
    private static final String WIN_PATH = "C:/Users/X240/Desktop/opencv/web/web/";

    private static final String STITP_PATH = "/home/stitp/javaData/cut/";

    private static final String YTO_PATH = "/usr/local/cut/";

    public static final String WIN = "WIN";

    public static final String STITP = "STITP";

    public static final String YTO = "YTO";

    public static String getSystemFile(String type){
        switch (type){
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

    public static String getPATH(String type){
        switch (type){
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
