package com.wan.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
public class FileUploadController {

    @RequestMapping("fileUpload")
    //传入上传者姓名和文件列表
    public String handleFormUpload(@RequestParam("username") String name, @RequestParam("filename") List<MultipartFile> fileList, HttpServletRequest request){
        if(!fileList.isEmpty()&&fileList.size()>0){
            //循环输出上传文件
            for (MultipartFile file:fileList){
                //获取上传文件的原始名称
                String filename=file.getOriginalFilename();
                //设置文件保存地址
                String path=request.getServletContext().getRealPath("/file/");
                File filePath=new File(path);
                //如果保存文件的路径不存在,就先创建
                if (!filePath.exists()){
                    filePath.mkdirs();
                }
                //对上传文件进行重新命名
                String newfilename=name+"_"+ UUID.randomUUID()+"_"+filename;

                try{
                    //使用MultipartFile接口的方法完成文件上传到指定位置保存和更改文件名
                    file.transferTo(new File(path+newfilename));
                }catch (Exception e){
                    e.printStackTrace();
                    return "error";
                }
            }
            //跳转到成功页面
            return "success";
        }else {
            return "error";
        }
    }

    //下载
    @RequestMapping("/download")
    public ResponseEntity<byte[]> fileDownload(HttpServletRequest request, String filename)throws Exception{
        //指定要下载的文件路径
        String path=request.getServletContext().getRealPath("/file/");
        //创建该文件对象
        File file=new File(path+File.separator+filename);
        //设置响应头
        HttpHeaders headers=new HttpHeaders();
        //通知浏览器以下载的方式打开文件
        headers.setContentDispositionFormData("attachment",filename);
        //定义以流的形式下载放回文件数据
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //使用ResponseEntity对象封装返回下载数据
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);
    }
}
