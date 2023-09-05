package com.sangeng.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.QueryTagDto;
import com.sangeng.domain.entity.Tag;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.domain.vo.TagListVo;
import com.sangeng.domain.vo.TagVo;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.mapper.TagMapper;
import com.sangeng.service.TagService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-07-31 16:53:34
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public ResponseResult querylist(Integer pageNum, Integer pageSize, QueryTagDto tagDto) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(tagDto.getName()),Tag::getName,tagDto.getName());
        queryWrapper.like(StringUtils.hasText(tagDto.getRemark()),Tag::getRemark,tagDto.getRemark());
        queryWrapper.eq(Tag::getDelFlag,0);
        Page<Tag> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);
        List<Tag> records = page.getRecords();
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(records, TagVo.class);
        PageVo pageVo1 = new PageVo(tagVos, page.getTotal());
        return ResponseResult.okResult(pageVo1);
    }

    @Override
    public ResponseResult addTag(QueryTagDto tagDto) {
        if(!StringUtils.hasText(tagDto.getName())){
            throw new SystemException(AppHttpCodeEnum.TAGNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(tagDto.getRemark())){
            throw new SystemException(AppHttpCodeEnum.TAGREMARK_NOT_NULL);
        }
        Tag tag = BeanCopyUtils.copyBean(tagDto, Tag.class);
        boolean save = save(tag);
        if(!save){
            throw new SystemException(AppHttpCodeEnum.TAG_INSERT_ERROR);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult DeleteTag(List<Long> id) {
        for (Long Id : id) {
            LambdaUpdateWrapper<Tag> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.set(Tag::getDelFlag, SystemConstants.Del);
            updateWrapper.eq(Tag::getId,Id);
            boolean update = update(updateWrapper);
            if(!update){
                throw new SystemException(AppHttpCodeEnum.TAG_UPDATE_ERROR);
            }
        }

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult GetTag(Long id) {
        Tag tag = tagMapper.selectById(id);
        if(tag == null){
            throw new SystemException(AppHttpCodeEnum.TAG_NOT_NULL);
        }
        TagVo tagVo = BeanCopyUtils.copyBean(tag, TagVo.class);
        return ResponseResult.okResult(tagVo);
    }

    @Override
    public ResponseResult updateTag(Tag tag) {
        if(tag.getId() == null){
            throw new SystemException(AppHttpCodeEnum.TAGID_NOT_NULL);
        }
        if(!StringUtils.hasText(tag.getName())){
            throw new SystemException(AppHttpCodeEnum.TAGNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(tag.getRemark())){
            throw new SystemException(AppHttpCodeEnum.TAGREMARK_NOT_NULL);
        }
        updateById(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult listAllTag() {
        List<Tag> list = list();
        List<TagListVo> tagListVos = BeanCopyUtils.copyBeanList(list, TagListVo.class);
        return ResponseResult.okResult(tagListVos);
    }
}
