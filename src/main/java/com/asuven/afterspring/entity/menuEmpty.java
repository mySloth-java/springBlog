package com.asuven.afterspring.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class menuEmpty {
    private Integer menuId;
    private String menuName;
    private String menuDes;
    private String menuPath;
    private String menuIcon;
    private Integer parentId;

    //此属性数据库不必存储
    private List<menuEmpty> children;
}
