package com.asuven.afterspring.mapper;

import com.asuven.afterspring.entity.loginEmpty;
import com.asuven.afterspring.entity.menuEmpty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface loginMapper {

    loginEmpty LoginTo(@Param("loginName") String loginName, @Param("loginPassword") String loginPassword);

    List<loginEmpty> AllLogin();

    Integer AllLoginCount(String loginName);

    List<loginEmpty> AllLoginPaging(Integer pageNum,Integer pageSize,String loginName);

    loginEmpty IdLogin(@Param("loginId") Integer loginId);

    List<loginEmpty> NameLogin(@Param("loginName") String loginName);

    int AddLogin(loginEmpty loginEmpty);

    int UpdateLogin(loginEmpty loginEmpty);

    Integer DeleteLogin(@Param("loginId") Integer loginId);

    int RegisterTo(loginEmpty loginEmpty);

    List<menuEmpty> GetMenu(loginEmpty loginEmpty);
}
