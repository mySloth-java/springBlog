package com.asuven.afterspring.mapper;

import com.asuven.afterspring.entity.fileEmpty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface fileMapper {

   int AddFile(fileEmpty fileEmpty);

   List<fileEmpty> AllFilePaging(Integer pageNum, Integer pageSize, String fileName);

   Integer AllFileCount(String fileName);

   Integer DeleteFile(@Param("fileId") Integer fileId);
}
