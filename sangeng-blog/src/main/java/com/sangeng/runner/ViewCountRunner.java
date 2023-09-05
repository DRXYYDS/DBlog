package com.sangeng.runner;

import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.entity.Article;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.utils.RedisCache;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 曼迪卡尔多
 * @ClassName ViewCountRunner
 * @description: TODO
 * @date 2023年07月31日
 * @version: 1.0
 */
@Component
public class ViewCountRunner implements CommandLineRunner {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询博客信息 id viewCount
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article1 -> article1.getId().toString(), article -> article.getViewCount().intValue()));
        //存储到redis中
        redisCache.setCacheMap(SystemConstants.ARTICLE_VIEW_COUNT,viewCountMap);
    }
}
