package com.bins.springcloud.shop.supplier.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bins.springcloud.shop.common.constants.CommonHelper;
import com.bins.springcloud.shop.common.constants.CommonHelper.ResultCodeEnum;
import com.bins.springcloud.shop.common.utils.PageUtil;
import com.bins.springcloud.shop.common.vo.ResultVo;
import com.bins.springcloud.shop.supplier.dto.BrandDto;
import com.bins.springcloud.shop.supplier.dto.BrandPageDto;
import com.bins.springcloud.shop.supplier.entity.BrandEntity;
import com.bins.springcloud.shop.supplier.mapper.BrandMapper;
import com.bins.springcloud.shop.supplier.service.BrandService;
import com.bins.springcloud.shop.supplier.vo.BrandVo;
import com.bins.springcloud.shop.user.api.UserApi;
import com.bins.springcloud.shop.user.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private BrandMapper brandMapper;
	
	@Autowired
	private UserApi userApi;

	@Override
	public BrandVo getById(Long brandId) {
		BrandEntity entity = brandMapper.findById(brandId);
		BrandVo vo = new BrandVo();
		BeanUtils.copyProperties(entity, vo);
		return vo;
	}

	@Override
	public List<BrandVo> getByIds(List<Long> brandIds) {
		List<BrandEntity> list = brandMapper.getByIds(brandIds);
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		}
		List<BrandVo> voList = list.stream().map(temp -> {
			BrandVo vo = new BrandVo();
			vo.setId(temp.getId());
			vo.setBrandName(temp.getBrandName());
			vo.setBrandDesc(temp.getBrandDesc());
			vo.setIsDel(temp.getIsDel());
			vo.setSort(temp.getSort());
			vo.setCreateTime(temp.getCreateTime());
			vo.setUpdateTime(temp.getUpdateTime());
            return vo;
        }).collect(Collectors.toList());
		return voList;
	}

	@Override
	public PageInfo<BrandVo> getPagination(BrandPageDto pageDto) {
		PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize()).setOrderBy("id DESC");
		List<BrandEntity> list = brandMapper.findList(pageDto);
		PageInfo<BrandEntity> originPageInfo = new PageInfo<>(list);
		PageInfo<BrandVo> pageInfo = PageUtil.pageInfoToPageInfoVo(originPageInfo);
		List<BrandVo> voList = list.stream().map(temp -> {
			BrandVo vo = new BrandVo();
			vo.setId(temp.getId());
			vo.setBrandName(temp.getBrandName());
			vo.setBrandDesc(temp.getBrandDesc());
			vo.setIsDel(temp.getIsDel());
			vo.setSort(temp.getSort());
			vo.setCreateTime(temp.getCreateTime());
			vo.setUpdateTime(temp.getUpdateTime());
            return vo;
        }).collect(Collectors.toList());
		pageInfo.setList(voList);
		return pageInfo;
	}

	@Override
	public ResultVo<BrandVo> getDetail(BrandDto dto) {
		ResultVo<BrandVo> result = new ResultVo<BrandVo>();
		BrandEntity entity = brandMapper.findById(dto.getId());
		if (entity == null) {
			result.isFail("品牌不存在", null);
			return result;
		}
		BrandVo vo = new BrandVo();
        BeanUtils.copyProperties(entity, vo);
        
        UserVo createBy = userApi.getById(vo.getCreateBy());
        if (createBy != null) {
        	vo.setCreateByName(createBy.getUserName());
        }
        result.setData(vo);
        return result;
	}

	@Override
	public ResultVo<Boolean> delBrand(BrandDto dto) {
		dto.setIsDel(CommonHelper.DeleteStatus.DELETED.getCode());
		int num = brandMapper.deleteById(dto);
		if (num > 0) {
			return new ResultVo<Boolean>(ResultCodeEnum.SUCCESS.getCode(), "删除成功", true);
		}
		return new ResultVo<Boolean>(ResultCodeEnum.FAILURE.getCode(), "删除失败", false);
	}

	@Override
	public ResultVo<BrandVo> addBrand(BrandDto dto) {
		dto.setCreateBy(1l);
		dto.setUpdateBy(1l);
		dto.setIsDel(CommonHelper.DeleteStatus.NO_DELETE.getCode());
		int result = brandMapper.insert(dto);
		if (result > 0) {
			BrandVo vo = new BrandVo();
			vo.setId(dto.getId());
			return new ResultVo<BrandVo>(ResultCodeEnum.SUCCESS.getCode(), "保存成功", vo);
		} else {
			return new ResultVo<BrandVo>(ResultCodeEnum.FAILURE.getCode(), "保存失败", null);
		}
	}

	@Override
	public ResultVo<Boolean> updateBrand(BrandDto dto) {
		ResultVo<Boolean> result = new ResultVo<Boolean>();
		BrandEntity entity = brandMapper.findById(dto.getId());
		if (entity == null) {
			result.isFail("用户组不存在", null);
			return result;
		}
		if (brandMapper.updateById(dto) == 0) {
			result.isFail("用户组未修改");
			return result;
		}
		result.isOk(Boolean.TRUE);
		return result;
	}
}
