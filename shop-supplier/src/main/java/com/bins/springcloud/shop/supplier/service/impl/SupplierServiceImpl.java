package com.bins.springcloud.shop.supplier.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bins.springcloud.shop.common.constants.CommonHelper;
import com.bins.springcloud.shop.common.constants.CommonHelper.ResultCodeEnum;
import com.bins.springcloud.shop.common.utils.PageUtil;
import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.supplier.dto.SupplierDto;
import com.bins.springcloud.shop.supplier.dto.SupplierPageDto;
import com.bins.springcloud.shop.supplier.entity.SupplierEntity;
import com.bins.springcloud.shop.supplier.mapper.SupplierMapper;
import com.bins.springcloud.shop.supplier.service.SupplierService;
import com.bins.springcloud.shop.supplier.vo.SupplierVo;
import com.bins.springcloud.shop.user.api.UserApi;
import com.bins.springcloud.shop.user.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private SupplierMapper supplierMapper;
	
	@Autowired
	private UserApi userApi;
	
	@Override
	public PageInfo<SupplierVo> getSupplierPagination(SupplierPageDto pageDto) {
		PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize()).setOrderBy("id DESC");
		List<SupplierEntity> list = supplierMapper.findSupplierList(pageDto);
		PageInfo<SupplierEntity> originPageInfo = new PageInfo<>(list);
		PageInfo<SupplierVo> pageInfo = PageUtil.pageInfoToPageInfoVo(originPageInfo);
		List<SupplierVo> deptList = list.stream().map(temp -> {
			SupplierVo vo = new SupplierVo();
			vo.setId(temp.getId());
			vo.setSupplierCode(temp.getSupplierCode());
			vo.setSupplierName(temp.getSupplierName());
			vo.setIsDel(temp.getIsDel());
			vo.setCreateBy(temp.getCreateBy());
			vo.setCreateTime(temp.getCreateTime());
			vo.setUpdateTime(temp.getUpdateTime());
            return vo;
        }).collect(Collectors.toList());
		pageInfo.setList(deptList);
		return pageInfo;
	}

	@Override
	public SupplierVo getById(Long id) {
		SupplierEntity entity = supplierMapper.findById(id);
		SupplierVo vo = new SupplierVo();
		BeanUtils.copyProperties(entity, vo);
		return vo;
	}

	@Override
	public List<SupplierVo> getByIds(List<Long> ids) {
		List<SupplierEntity> list = supplierMapper.findByIds(ids);
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		}
		List<SupplierVo> voList = list.stream().map(temp -> {
			SupplierVo vo = new SupplierVo();
			BeanCopier voCopier = BeanCopier.create(SupplierEntity.class, SupplierVo.class, false);
			voCopier.copy(temp, vo, null);
            return vo;
        }).collect(Collectors.toList());		
		return voList;
	}

	@Override
	public ResultVo<SupplierVo> getDetail(SupplierDto dto) {
		ResultVo<SupplierVo> result = new ResultVo<SupplierVo>();
		SupplierEntity entity = supplierMapper.findById(dto.getId());
		if (entity == null) {
			result.isFail("供应商不存在", null);
			return result;
		}
		SupplierVo vo = new SupplierVo();
        BeanUtils.copyProperties(entity, vo);
        
        UserVo createBy = userApi.getById(vo.getCreateBy());
        if (createBy != null) {
        	vo.setCreateByName(createBy.getUserName());
        }
        result.setData(vo);
        return result;
	}

	@Override
	public ResultVo<Boolean> updateSupplier(SupplierDto dto) {
		ResultVo<Boolean> result = new ResultVo<Boolean>();
		SupplierEntity entity = supplierMapper.findById(dto.getId());
		if (entity == null) {
			result.isFail("用户组不存在", null);
			return result;
		}
		if (supplierMapper.updateById(dto) == 0) {
			result.isFail("用户组未修改");
			return result;
		}
		result.isOk(Boolean.TRUE);
		return result;
	}

	@Override
	public ResultVo<SupplierVo> addSupplier(SupplierDto dto) {
		dto.setCreateBy(1l);
		dto.setUpdateBy(1l);
		dto.setStatus(1);
		dto.setIsDel(CommonHelper.DeleteStatus.NO_DELETE.getCode());
		int result = supplierMapper.insert(dto);
		if (result > 0) {
			SupplierVo vo = new SupplierVo();
			vo.setId(dto.getId());
			return new ResultVo<SupplierVo>(ResultCodeEnum.SUCCESS.getCode(), "保存成功", vo);
		} else {
			return new ResultVo<SupplierVo>(ResultCodeEnum.FAILURE.getCode(), "保存失败", null);
		}
	}

	@Override
	public ResultVo<Boolean> delSupplier(SupplierDto dto) {
		dto.setIsDel(CommonHelper.DeleteStatus.DELETED.getCode());
		int num = supplierMapper.deleteById(dto);
		if (num > 0) {
			return new ResultVo<Boolean>(ResultCodeEnum.SUCCESS.getCode(), "删除成功", true);
		}
		return new ResultVo<Boolean>(ResultCodeEnum.FAILURE.getCode(), "删除失败", false);
	}

}
