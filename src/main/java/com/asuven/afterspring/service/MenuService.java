package com.asuven.afterspring.service;

import com.asuven.afterspring.entity.menuEmpty;
import com.asuven.afterspring.mapper.menuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {
    @Autowired
    private menuMapper menuMapper;


    public Integer SaveMenuSer(menuEmpty menuEmpty){
        if(menuEmpty.getMenuId() == null){
            return menuMapper.AddMenu(menuEmpty);
        }else {
            return menuMapper.UpdateMenu(menuEmpty);
        }
    }

    public List<menuEmpty> GetAllMenuSer() {
        List<menuEmpty> allMenu = menuMapper.GetAllMenu();
        //通过过滤器找出parentId为空的菜单，即为一级菜单
        //此步非常关键，为java8新语法
        List<menuEmpty> parentMenu = allMenu.stream().
                filter(menuEmpty -> menuEmpty.getParentId() == null).collect(Collectors.toList());
        //通过遍历找出一级菜单的子菜单，这一步有点蒙圈
        for (menuEmpty menu : parentMenu){
            menu.setChildren(allMenu.stream().
                    filter(menuEmpty -> menu.getMenuId().equals(menuEmpty.getParentId())).collect(Collectors.toList()));
        }
        return parentMenu;
    }
}
