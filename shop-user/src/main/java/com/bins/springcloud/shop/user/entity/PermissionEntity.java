package com.bins.springcloud.shop.user.entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PermissionEntity {

	private Long id;

    private String permissionName;

    private String permissionCode;

    private Integer permissionType;

    private String url;

    private String urlClass;

    private Integer sort;

    private Integer level;

    private Long pid;

    private Integer isDel;

    private Long createBy;

    private Long updateBy;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
