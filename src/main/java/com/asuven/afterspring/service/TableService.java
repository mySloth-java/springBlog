package com.asuven.afterspring.service;

import com.asuven.afterspring.entity.loginEmpty;
import com.asuven.afterspring.entity.menuEmpty;
import com.asuven.afterspring.mapper.loginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TableService {
    @Autowired
    private loginMapper loginMapper;

    public Integer SaveLoginSer(loginEmpty loginEmpty){
        //判断是增加还是修改
        //待改进，重复用户名没添加
        if(loginEmpty.getLoginId() == null){
            return loginMapper.AddLogin(loginEmpty);
        }else {
           return loginMapper.UpdateLogin(loginEmpty);
        }
    }

    public loginEmpty LoginSer(loginEmpty loginEmpty) {
        loginEmpty login = loginMapper.LoginTo(loginEmpty.getLoginName(), loginEmpty.getLoginPassword());
        if (login !=null){
            List<menuEmpty> menuEmpties = loginMapper.GetMenu(loginEmpty);

            loginEmpty.setLoginId(login.getLoginId());
            loginEmpty.setLoginName(login.getLoginName());
            loginEmpty.setLoginPassword(login.getLoginPassword());
            loginEmpty.setLoginStatus(login.getLoginStatus());
            loginEmpty.setLoginDate(login.getLoginDate());

            loginEmpty.setMenu(menuEmpties);
            return loginEmpty;
        }else {
            return new loginEmpty();
        }
    }

    public boolean RegisterSer(loginEmpty loginEmpty) {
        boolean flag;
        //注册前判断name是否以及存在
        List<loginEmpty> login = loginMapper.NameLogin(loginEmpty.getLoginName());
        //判断loginName是否为空，直接用集合一劳永逸了
        if(login.isEmpty() || login == null){
            loginMapper.RegisterTo(loginEmpty);
            flag=true;
        }else {
            flag = false;
        }
        return flag;
    }

//    public List<menuEmpty> GetMenuSer(loginEmpty loginEmpty) {
//        loginEmpty login = loginMapper.LoginTo(loginEmpty.getLoginName(), loginEmpty.getLoginPassword());
//
//    }
}
