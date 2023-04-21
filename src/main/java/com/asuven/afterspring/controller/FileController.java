package com.asuven.afterspring.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.asuven.afterspring.entity.fileEmpty;
import com.asuven.afterspring.mapper.fileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileController {

    //通过value注解获取到配置的存储path
    @Value("${files.upload.path}")
    private String uploadPath;

    @Autowired
    private fileMapper fileMapper;

    //删除文件
    @DeleteMapping("/{fileId}")
    public Integer DeleteFileCon(@PathVariable("fileId") Integer fileId){
        return fileMapper.DeleteFile(fileId);
    }


    //分页查询文件
    @GetMapping("/page")
    public Map<String,Object> PagingCon(
            @RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam String fileName){
        //@RequestParam 获取url后面的参数，没有报错
        pageNum = (pageNum-1)*pageSize;
        //分页查询数据结果
        List<fileEmpty> data = fileMapper.AllFilePaging(pageNum, pageSize,fileName);
        //查询总条数
        Integer total = fileMapper.AllFileCount(fileName);

        //创建Map集合存入分页查询数据和total返回
        Map<String,Object> result = new HashMap<>();
        result.put("data",data);
        result.put("total",total);
        return result;
    }

    //文件上传功能,md5还未添加
    @PostMapping("/upload")
    public String UploadCon(@RequestParam MultipartFile file) throws IOException {
        //获取文件长度
        long size = file.getSize();
        //获取文件真实名字，包括类型
        String originalFilename = file.getOriginalFilename();
        //通过文件名获取到类型，此处用到了hutool工具包
        String type = FileUtil.extName(originalFilename);

        //存储到硬盘
        File uploadFilePath = new File(uploadPath);
        //判断配置目录是否存在，不存在创建一个
        if(!uploadFilePath.exists()){
            uploadFilePath.mkdirs();
        }
        //定义文件唯一标识码，防止重名
//        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = IdUtil.fastSimpleUUID()+"."+type;
        File uploadFile = new File(uploadPath + fileUUID);
        //把文件存储到硬盘中
        file.transferTo(uploadFile);

        //存储到数据库中
        String url = "http://localhost:9090/file/"+fileUUID;
        fileEmpty fileEmpty = new fileEmpty();
        fileEmpty.setFileName(originalFilename);
        fileEmpty.setFileSize(size/1024);
        fileEmpty.setFileType(type);
        fileEmpty.setFileUrl(url);
        fileMapper.AddFile(fileEmpty);
        return url;
    }

    //文件下载功能
    @GetMapping("/{fileUUID}")
    public void DownloadCon(@PathVariable String fileUUID, HttpServletResponse res) throws IOException {
        System.out.println(fileUUID);
        File uploadFile = new File(uploadPath + fileUUID);

        //设置输出流格式
        ServletOutputStream outputStream = res.getOutputStream();
        res.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileUUID,"UTF-8"));
        res.setContentType("application/octet-stream");

        //读取文件
        outputStream.write(FileUtil.readBytes(uploadFile));
        //释放和关闭流节点
        outputStream.flush();
        outputStream.close();
    }
}
