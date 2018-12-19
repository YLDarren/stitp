package cn.edu.njupt.controller;

import cn.edu.njupt.dto.Result;
import cn.edu.njupt.service.ImageHandleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CutImageController {

    //日志
    private static final Logger logger = (Logger) LoggerFactory.getLogger(UploadFIleController.class);

    @Autowired
    private ImageHandleService imageHandleService;


    @RequestMapping(value = "/cut")
    @ResponseBody
    public Result<List<String>> cut(HttpServletRequest request , @RequestParam String imgPath){
        Result<List<String>> result;

        System.out.println(imgPath);

        List<String> data = imageHandleService.cut(imgPath);

        if(data == null){
            result = new Result<>(false , null , "不是一个有效的图像");
        }else {
            result = new Result<>(true, data, "");

        }


        return result;
    }

}
