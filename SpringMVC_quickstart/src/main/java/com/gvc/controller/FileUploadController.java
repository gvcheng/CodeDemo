package com.gvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController {
    //单文件上传
    @RequestMapping("/fileUpload")
    public String fileUpload(String username,MultipartFile filePic) throws IOException {
        //获取表单提交的参数
        System.out.println(username);
        //给上传文件赋予名字,获取上传原始文件名
        String originalFilename = filePic.getOriginalFilename();
        filePic.transferTo(new File("D:/Program/MyNotes/uploadFile/" + originalFilename));

        return "success";
    }

    //多文件上传
    @RequestMapping("/filesUpload")
    public String filesUpload(String username,MultipartFile[] filePic) throws IOException {
        //获取表单提交的参数
        System.out.println(username);
        //给上传文件赋予名字,获取上传原始文件名
        for (MultipartFile file : filePic) {
            String originalFilename = file.getOriginalFilename();
            file.transferTo(new File("D:/Program/MyNotes/uploadFile/" + originalFilename));
        }

        return "success";
    }
}
