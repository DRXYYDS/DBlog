package com.sangeng.utils;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.sangeng.domain.entity.Article;
import com.sangeng.domain.vo.HotArticleVo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 曼迪卡尔多
 * @ClassName BeanCopyUtils
 * @description: TODO
 * @date 2023年07月27日
 * @version: 1.0
 */
public class BeanCopyUtils {
    //私有的构造方法表示该类不能直接new出来
    private BeanCopyUtils(){
    }
    public static <V> V copyBean(Object source,Class<V> clazz){
        //创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            //实现属性copy
            BeanUtils.copyProperties(source,result);

        }  catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static <O,V> List<V> copyBeanList(List<O> list,Class<V> clazz){
        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Article article = new Article();
        article.setId(12L);
        article.setTitle("ss");
        article.setViewCount(125L);
        HotArticleVo hotArticleVo =  BeanCopyUtils.copyBean(article,HotArticleVo.class);
        System.out.println(hotArticleVo);
    }



}
