package com.sangeng.job;

import com.sangeng.constants.SystemConstants;
import com.sangeng.domain.entity.Article;
import com.sangeng.service.ArticleService;
import com.sangeng.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 曼迪卡尔多
 * @ClassName UpdateViewCountJob
 * @description: TODO
 * @date 2023年07月31日
 * @version: 1.0
 */
@Component
public class UpdateViewCountJob {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private RedisCache redisCache;
    @Scheduled(cron =  "0/5 * * * * ?")
    public void updateViewCount(){
        //获取redis中的浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap(SystemConstants.ARTICLE_VIEW_COUNT);
        List<Article> articles = viewCountMap.entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());
        //更新到数据库中
        articleService.updateBatchById(articles);
    }
}
