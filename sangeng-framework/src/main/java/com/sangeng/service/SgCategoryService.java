package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.SgCategory;
import com.sangeng.domain.vo.CategoryListVo;

import java.util.List;


/**
 * 分类表(SgCategory)表服务接口
 *
 * @author makejava
 * @since 2023-07-27 16:17:08
 */
public interface SgCategoryService extends IService<SgCategory> {

    ResponseResult getCategoryList();

    List<CategoryListVo> listAllCategory();
}
