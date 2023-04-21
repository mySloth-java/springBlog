package com.asuven.afterspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class fileEmpty {
    private Integer fileId;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private String fileUrl;
    private Boolean isDelete;
    private Boolean enable;
}
