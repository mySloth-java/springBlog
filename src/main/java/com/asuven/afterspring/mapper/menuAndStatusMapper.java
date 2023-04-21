package com.asuven.afterspring.mapper;

import com.asuven.afterspring.entity.menuAndStatusEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface menuAndStatusMapper {
    int DeleteMenuAndStatus(@Param("statusId") Integer statusId);

    int AddMenuAndStatus(menuAndStatusEntity menuAndStatusEntity);

    List<Integer> ByIdMenuAndStatus(@Param("statusId") Integer statusId);

}
