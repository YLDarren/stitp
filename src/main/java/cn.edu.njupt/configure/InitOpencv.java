package cn.edu.njupt.configure;


import cn.edu.njupt.utils.opencvUtils.OpencvConfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 加载dll或者so后缀文件的listener
 */
public class InitOpencv  implements ServletContextListener {


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        //WIN/STITP/YTO；这个可以根据自己的情况自己定义
        System.load(OpencvConfig.getSystemFile(OpencvConfig.WIN));
    }



}
