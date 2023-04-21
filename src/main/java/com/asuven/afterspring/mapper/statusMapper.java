package com.asuven.afterspring.mapper;

import com.asuven.afterspring.entity.statusEmpty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface statusMapper {
    List<statusEmpty> AllStatus();

    int AddStatus(statusEmpty statusEmpty);

    int DelStatus(@Param("statusId") Integer statusId);
}
