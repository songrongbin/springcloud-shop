package com.bins.springcloud.user.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.bins.springcloud.common.dto.PageDto;

@Data
@EqualsAndHashCode(callSuper=false)
public class RolePageDto extends PageDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String roleCode;

	private String roleName;

}
