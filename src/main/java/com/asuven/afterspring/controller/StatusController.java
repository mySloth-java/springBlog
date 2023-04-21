package com.asuven.afterspring.controller;

import com.asuven.afterspring.entity.statusEmpty;
import com.asuven.afterspring.mapper.statusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private statusMapper statusMapper;

    //查询所有身份，不需要分页，身份太少，没必要
    @GetMapping
    public List<statusEmpty> GetAllStatusCon(){
        return statusMapper.AllStatus();
    }

    //添加身份
    @PostMapping
    public Integer AddStatusCon(@RequestBody statusEmpty statusEmpty){
        //可以改进，添加判断逻辑
        return statusMapper.AddStatus(statusEmpty);
    }

    //删除身份
    @DeleteMapping("/{statusId}")
    public Integer DelStatusCon(@PathVariable("statusId") Integer statusId){
        return statusMapper.DelStatus(statusId);
    }

}
