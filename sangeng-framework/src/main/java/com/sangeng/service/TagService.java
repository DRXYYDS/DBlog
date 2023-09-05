package com.sangeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.QueryTagDto;
import com.sangeng.domain.entity.Tag;
import com.sangeng.domain.vo.PageVo;

import java.util.List;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2023-07-31 16:53:34
 */
public interface TagService extends IService<Tag> {

    ResponseResult querylist(Integer pageNum, Integer pageSize, QueryTagDto tagDto);

    ResponseResult addTag(QueryTagDto tagDto);

    ResponseResult DeleteTag(List<Long> id);

    ResponseResult GetTag(Long id);

    ResponseResult updateTag(Tag tag);

    ResponseResult listAllTag();

}
