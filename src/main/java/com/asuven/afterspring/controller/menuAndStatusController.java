package com.asuven.afterspring.controller;

import com.asuven.afterspring.entity.menuAndStatusEntity;
import com.asuven.afterspring.mapper.menuAndStatusMapper;
import com.asuven.afterspring.service.menuAndStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menuAndStatus")
public class menuAndStatusController {

    @Autowired
    private menuAndStatusService menuAndStatusService;
    @Autowired
    private menuAndStatusMapper menuAndStatusMapper;

    //设置身份Id和菜单Id，设置之前先删除之前设置好的再添加新的
    @PostMapping("/{statusId}")
    public boolean SetMenuAndStatusCon(@PathVariable("statusId") Integer statusId, @RequestBody List<Integer> menuIds){
        return menuAndStatusService.SetMenuAndStatusSer(statusId,menuIds);
    }

    //通过statusId获取数据库中之前设置的值
    @GetMapping("/{statusId}")
    public List<Integer> GetMenuAndStatusCon(@PathVariable("statusId") Integer statusId){
        return menuAndStatusMapper.ByIdMenuAndStatus(statusId);
    }

}
