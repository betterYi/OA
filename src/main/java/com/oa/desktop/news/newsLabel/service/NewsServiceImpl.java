package com.oa.desktop.news.newsLabel.service;

import com.oa.commons.beans.News;
import com.oa.desktop.news.newsLabel.Dao.NewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Override
    public int newsAdd(News news) {
        return newsDao.newsAdd(news);
    }

    @Override
    public List<News> queryNewsAll() {
        return newsDao.queryNewsAll();
    }
}
