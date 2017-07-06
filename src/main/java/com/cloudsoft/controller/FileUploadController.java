package com.cloudsoft.controller;

/**
 * Created by hzl on 2017/4/9.
 */

import com.cloudsoft.entity.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping("/files")
public class FileUploadController {

    @RequestMapping(value="/upload",method= RequestMethod.POST)
    private String fildUpload(Users users , @RequestParam(value="file",required=false) MultipartFile file,
                              HttpServletRequest request)throws Exception{
        //基本表单
        System.out.println(users.toString());

        //获得物理路径webapp所在路径
        String pathRoot = request.getSession().getServletContext().getRealPath("");
        String path="";
        if(!file.isEmpty()){
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType=file.getContentType();
            //获得文件后缀名称
            String imageName=contentType.substring(contentType.indexOf("/")+1);
            path=uuid+"."+imageName;
            file.transferTo(new File("D:\\bfp\\"+path));
        }
        System.out.println(path);
        request.setAttribute("imagesPath", path);
        return "/show.jsp";
    }
    //因为我的JSP在WEB-INF目录下面，浏览器无法直接访问
    @RequestMapping(value="/forward")
    private String forward(){
        return "index";
    }
}
