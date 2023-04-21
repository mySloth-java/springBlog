package com.asuven.afterspring.mapper;

import com.asuven.afterspring.entity.menuEmpty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface menuMapper {

    List<menuEmpty> GetAllMenu();

    int AddMenu(menuEmpty menuEmpty);

    int UpdateMenu(menuEmpty menuEmpty);

    int DeleteMenu(@Param("menuId") Integer menuId);
}
