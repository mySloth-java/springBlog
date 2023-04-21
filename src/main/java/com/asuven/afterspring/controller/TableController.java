package com.asuven.afterspring.controller;

import com.asuven.afterspring.entity.loginEmpty;
import com.asuven.afterspring.entity.menuEmpty;
import com.asuven.afterspring.mapper.loginMapper;
import com.asuven.afterspring.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loginCon")
public class TableController {
    @Autowired
    private loginMapper loginMapper;
    @Autowired
    private TableService tableService;

    //注册功能
    @PostMapping("/register")
    public boolean RegisterCon(@RequestBody loginEmpty loginEmpty){
        return tableService.RegisterSer(loginEmpty);
    }

    //登录功能
    @PostMapping("/login")
    public loginEmpty LoginCon(@RequestBody loginEmpty loginEmpty){
        String loginName = loginEmpty.getLoginName();
        String loginPassword = loginEmpty.getLoginPassword();
        //判断是否为空，判空操作没啥必要了，前端做了校验
        if(loginName.isEmpty() || loginPassword.isEmpty()){
            return new loginEmpty();
        }
        return tableService.LoginSer(loginEmpty);
    }

    //绑定菜单功能
//    @PostMapping("/getMenu")
//    public List<menuEmpty> GetMenuCon(@RequestBody loginEmpty loginEmpty){
//        return tableService.GetMenuSer(loginEmpty);
//    }

    //查询所有登录用户
    @GetMapping("/")
    public List<loginEmpty> GetAllLoginCon(){
        return loginMapper.AllLogin();
    }

    //分页查询和总记录数查询
    @GetMapping("/page")
    public Map<String,Object> PagingCon(
             @RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam String loginName){
        //@RequestParam 获取url后面的参数，没有报错
        pageNum = (pageNum-1)*pageSize;
        //分页查询数据结果
        List<loginEmpty> data = loginMapper.AllLoginPaging(pageNum, pageSize,loginName);
        //查询总条数,此处传入的形参时配合分页查询，用来传入接受前端的搜索框数据
        Integer total = loginMapper.AllLoginCount(loginName);

        //创建Map集合存入分页查询数据和total返回
        Map<String,Object> result = new HashMap<>();
        result.put("data",data);
        result.put("total",total);
        return result;
    }


    //添加和修改登录用户
    @PostMapping
    public Integer SaveLoginCon(@RequestBody loginEmpty loginEmpty){//@RequestBody注解让浏览器传入的json参数转为对象
        return tableService.SaveLoginSer(loginEmpty);
    }

    //删除用户
    @DeleteMapping("/{loginId}")
    public Integer DeleteLoginCon(@PathVariable("loginId") Integer loginId){
        return loginMapper.DeleteLogin(loginId);
    }
}
