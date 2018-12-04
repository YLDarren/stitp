package cn.edu.njupt.service.impl;

import cn.edu.njupt.service.ImageHandleService;
import cn.edu.njupt.utils.opencvUtils.BinaryUtils.BinaryUtils;
import cn.edu.njupt.utils.opencvUtils.CutUtils.CutUtils;
import cn.edu.njupt.utils.opencvUtils.GeneralUtils.GeneralUtils;
import cn.edu.njupt.utils.opencvUtils.PreHandleUtils.PreHandleUtils;
import cn.edu.njupt.utils.opencvUtils.ResizeUtils.ResizeUtils;
import org.opencv.core.Mat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageHandleServiceImpl implements ImageHandleService {

    //日志
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ImageHandleServiceImpl.class);


    @Override
    public Mat matFactory(String imgPath) {
        System.out.println(imgPath);
        return GeneralUtils.matFactory(imgPath);
    }

    @Override
    public int channels(Mat src) {
        return src.channels();
    }

    @Override
    public List<String> cut(String path) {
        String destPath = "C:/Users/X240/Desktop/opencv/web/web/";
        Mat src = GeneralUtils.matFactory(path);

        src = PreHandleUtils.preHandleUtils(src);

        src = BinaryUtils.binaryzation(src);

        List<Mat> result = CutUtils.cutUtils(src);

        for(int j = 0 ; j < result.size() ; j++){
//            Mat dst = ResizeUtils.resize(result.get(j) , GeneralUtils.getDsize());
            Mat dst = result.get(j);
            GeneralUtils.saveImg(dst , destPath + "cut-"+ j +".jpg");
        }


        return null;
    }


}
