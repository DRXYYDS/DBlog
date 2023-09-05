package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.dto.QueryTagDto;
import com.sangeng.domain.entity.Tag;
import com.sangeng.domain.vo.TagVo;
import com.sangeng.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

/**
 * @author 曼迪卡尔多
 * @ClassName TagController
 * @description: TODO
 * @date 2023年07月31日
 * @version: 1.0
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 查询所有标签（分页）
     * @param pageNum
     * @param pageSize
     * @param tagDto
     * @return
     */
    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, QueryTagDto tagDto){
        return tagService.querylist(pageNum,pageSize,tagDto);

    }

    /**
     * 添加标签
     * @param tagDto
     * @return
     */
    @PostMapping
    public ResponseResult addTag(@RequestBody QueryTagDto tagDto){
        return tagService.addTag(tagDto);
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseResult DeleteTag(@PathVariable("id")List<Long> id){
        return tagService.DeleteTag(id);
    }

    /**
     * 查询某一个标签
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult GetTag(@PathVariable("id")Long id){
        return tagService.GetTag(id);

    }

    /**
     * 更新标签
     * @param tag
     * @return
     */
    @PutMapping
    public ResponseResult updateTag(@RequestBody Tag tag){
        return tagService.updateTag(tag);
    }

    /**
     * 查询所有标签
     */

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        return tagService.listAllTag();
    }
}
