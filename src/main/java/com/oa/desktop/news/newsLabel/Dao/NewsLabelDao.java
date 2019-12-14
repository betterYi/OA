package com.oa.desktop.news.newsLabel.Dao;

import com.oa.commons.beans.NewsLabel;
import com.oa.commons.vo.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsLabelDao {
    int selectAllCount();
    List<NewsLabel> selectNewsLabel(Page page);//根据分页的条件将需要的页面信息查询出来；

    NewsLabel selectNewsLabelById(int id);

    int deleteNewsLabelById(int id);

    List<NewsLabel> selectAllParent();

    int newsLabelEdit(@Param("id")int id, @Param("pid")int pid, @Param("content")String content, @Param("name")String name);

    int insertNewsLabel(@Param("name") String name, @Param("pid")Integer pid, @Param("textarea")String textarea);

    List<NewsLabel> queryNewsLabelById(@Param("id") Integer id, @Param("page") Page<NewsLabel> page);

    List<NewsLabel> selectAllNewsLabel();

    int selectCountById(Integer id);
}
