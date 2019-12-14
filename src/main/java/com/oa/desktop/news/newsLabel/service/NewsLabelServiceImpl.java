package com.oa.desktop.news.newsLabel.service;

import com.oa.commons.beans.NewsLabel;
import com.oa.commons.vo.Page;
import com.oa.desktop.news.newsLabel.Dao.NewsLabelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsLabelServiceImpl implements NewsLabelService{
    @Autowired
    private NewsLabelDao newsLabelDao;

    /**
     * 根据当前页查找到所对应的Page
     * @param pageNum
     * @return
     */
    @Override
    public Page findCurrentPage(int pageNum) {
        Page<NewsLabel> page = new Page();

        page.setPageNum(pageNum);//设置需要的页面

        int count = newsLabelDao.selectAllCount();
        page.setTotalRow(count);

        List<NewsLabel> datas = newsLabelDao.selectNewsLabel(page);
        page.setDatas(datas);//需要的页面数据

        return page;
    }

    /**
     * 按照id查询页条目以及他的子条目并获取分页
     * @param id
     * @param pageNum
     * @return
     */
    @Override
    public Page findCurrentPageById(Integer id, Integer pageNum) {
        Page<NewsLabel> page = new Page();

        page.setPageNum(pageNum);//设置需要的页面

        int count = newsLabelDao.selectCountById(id);
        page.setTotalRow(count);

        System.err.println(page);
        List<NewsLabel> datas = newsLabelDao.queryNewsLabelById(id,page);

        page.setDatas(datas);//需要的页面数据
        return page;
    }

    /**
     * 查询所有的栏目
     * @return
     */
    @Override
    public List<NewsLabel> queryAllNewsLabel() {
        return newsLabelDao.selectAllNewsLabel();
    }

    /**
     * 通过id查询新闻条目
     * @param id
     * @return
     */
    @Override
    public NewsLabel selectNewsLabelById(int id) {
        return newsLabelDao.selectNewsLabelById(id);
    }


    @Override
    public int deleteNewsLabelById(int id) {
        return newsLabelDao.deleteNewsLabelById(id);
    }

    @Override
    public List<NewsLabel> selectAllParent() {
        return newsLabelDao.selectAllParent();
    }

    @Override
    public int newsLabelEdit(int id,int pid, String content, String name) {
        return newsLabelDao.newsLabelEdit(id,pid,content,name);
    }

    @Override
    public int addNewsLabel(String name, Integer pid, String textarea) {
        return newsLabelDao.insertNewsLabel(name,pid,textarea);
    }


}