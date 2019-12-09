package com.oa.desktop.news.newsLabel.service;

import com.oa.commons.beans.NewsLabel;
import com.oa.commons.vo.Page;

import java.util.List;


public interface NewsLabelService {
   Page findCurrentPage(int pageNum);//给定要查询的页数，然后返回的是给定页面的值；在sql中的参数是页面其实索引和页面的大小

    NewsLabel selectNewsLabelById(int id);//通过id查找到所有的newsLabel属性

    int deleteNewsLabelById(int id);

    List<NewsLabel> selectAllParent();

    int newsLabelEdit(int id,int pid, String content, String name);

    int addNewsLabel(String name, Integer pid, String textarea);
}