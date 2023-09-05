package com.sangeng.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Comment;
import com.sangeng.domain.vo.CommentVo;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.exception.SystemException;
import com.sangeng.mapper.CommentMapper;
import com.sangeng.service.CommentService;
import com.sangeng.service.UserService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-07-28 21:56:24
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    UserService userService;

    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //查询对应文章的根评论
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId,articleId);
        queryWrapper.eq(Comment::getRootId, -1);
        queryWrapper.eq(Comment::getType,commentType);

        //分页查询
        Page<Comment> commentPage = new Page<Comment>(pageNum,pageSize);
        page(commentPage,queryWrapper);

        List<CommentVo> commentVoList = toCommentVoList(commentPage.getRecords());
        //查询根评论对应的子评论集合，并且赋值给对应的属性
        for (CommentVo commentVo : commentVoList) {
            //查询对应的子评论
            List<CommentVo> children = getChildren(commentVo.getId());
            commentVo.setChildren(children);
        }
        return ResponseResult.okResult(new PageVo(commentVoList,commentPage.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        //评论内容不能为空
        if(!StringUtils.hasText(comment.getContent())){
            throw  new SystemException(AppHttpCodeEnum.CONENT_NOT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }

    /**
     * 根据跟评论Id查询对应的子评论的集合
     * @param id 根评论id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId,id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper);
        if(comments == null){
            return new ArrayList<>();
        }
        List<CommentVo> commentVos = toCommentVoList(comments);
        return commentVos;
    }

    private List<CommentVo> toCommentVoList(List<Comment> list){
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        for (CommentVo commentVo : commentVos) {
            //通过createBy查询用户的昵称并赋值
            commentVo.setUsername(userService.getById(commentVo.getCreateBy()).getNickName());
            //通过toCommentUserId查询用户的昵称并复制
            //如果toCommentUserId不为-1才进行查询
            if(commentVo.getToCommentId() != -1){
                String nickName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                if(nickName == null){
                    nickName = "";
                }
                commentVo.setToCommentUserName(nickName);
            }
        }


        return commentVos;
    }
}
