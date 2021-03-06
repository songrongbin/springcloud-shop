package com.bins.springcloud.shop.user.vo;

import java.util.List;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class PermissionVo implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String permissionName;

    private String permissionCode;

    private Integer permissionType;

    private String url;

    private String urlClass;

    private Integer sort;

    private Integer level;

    private Long pid;

    private String pidName;

    private Long createBy;

    private String createByName;

    private Long updateBy;

    private List<PermissionVo> permissionList;

    @Override
    public String getAuthority() {
        return this.permissionCode;
    }
}
