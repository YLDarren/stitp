package cn.edu.njupt.controller;

import cn.edu.njupt.service.ImageHandleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CutImageController {

    //日志
    private static final Logger logger = (Logger) LoggerFactory.getLogger(UploadFIleController.class);

    @Autowired
    private ImageHandleService imageHandleService;


    @RequestMapping(value = "/cut")
    @ResponseBody
    public String cut(HttpServletRequest request , @RequestParam String imgPath){

        System.out.println(imgPath);

        imageHandleService.cut(imgPath);

        return "SUCCESS";
    }

}
