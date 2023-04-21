package com.asuven.afterspring.service;

import com.asuven.afterspring.entity.menuAndStatusEntity;
import com.asuven.afterspring.mapper.menuAndStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class menuAndStatusService {

    @Autowired
    private menuAndStatusMapper menuAndStatusMapper;

    @Transactional//添加事务注解，要么全部完成，要么不完成回溯
    public boolean SetMenuAndStatusSer(Integer statusId, List<Integer> menuIds) {
        //设置之前先删除
        menuAndStatusMapper.DeleteMenuAndStatus(statusId);
        boolean flag=false;
        //把前端的数据传入后端依次添加绑定
        for (Integer menuId :
                menuIds) {
            menuAndStatusEntity entity = new menuAndStatusEntity();
            entity.setStatusId(statusId);
            entity.setMenuId(menuId);
            menuAndStatusMapper.AddMenuAndStatus(entity);
            flag = true;
        }
        return flag;
    }
}
