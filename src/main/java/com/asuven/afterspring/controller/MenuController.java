package com.asuven.afterspring.controller;

import com.asuven.afterspring.entity.menuEmpty;
import com.asuven.afterspring.mapper.menuMapper;
import com.asuven.afterspring.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private menuMapper menuMapper;

    @Autowired
    private MenuService menuService;

    //查询菜单，非常重要，用到了java8新特性
    @GetMapping
    public List<menuEmpty> GetAllMenuCon(){
        return menuService.GetAllMenuSer();
    }

    //修改、添加菜单
    @PostMapping
    public Integer SaveMenuCon(@RequestBody menuEmpty menuEmpty){
        return menuService.SaveMenuSer(menuEmpty);
    }

    //删除菜单
    @DeleteMapping("/{menuId}")
    public Integer DeleteMenuCon(@PathVariable("menuId") Integer menuId){
        return menuMapper.DeleteMenu(menuId);
    }
}
