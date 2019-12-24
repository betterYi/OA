package com.oa.desktop.news.newsLabel.handler;

import com.oa.commons.beans.News;
import com.oa.commons.beans.NewsLabel;
import com.oa.commons.vo.Page;
import com.oa.desktop.news.newsLabel.service.NewsLabelService;
import com.oa.desktop.news.newsLabel.service.NewsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/desktop/news")
public class NewsLabelHandler {
    @Autowired
    private NewsLabelService newsLabelService;
    @Autowired
    private NewsService newsService;

    /***
     * 分页
     * @param pageNum
     * @param session
     * @return
     */

    @RequestMapping("/queryNewsLabel.do")
    public String queryNesLabel(@RequestParam(value = "id",defaultValue="0") Integer id,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, HttpSession session){

        /**
         * 栏目名称查询包括 自身和他下面的所有子类
         * 在js页面获取表单选中的id，默认值是0，传到后台，如果是0表示查询全部的条目；如果不是0就表示查询查询id和他的子类
         *
         * onchange()触发后获取到id到后台查询；后台查询后获取到id当做下一页的参数；请求
         */

        Page page = newsLabelService.findCurrentPage(pageNum);

        // System.out.println(pageNum);
        session.setAttribute("page",page);//将page保存到session域中；
        //System.out.println(page);

        //第一次加载jsp页面会用到
        session.setAttribute("id",id);//第一次默认查询，上面依据的栏目id是0，就是查询全部

        List<NewsLabel> newsLabelList = newsLabelService.queryAllNewsLabel();
        session.setAttribute("newsLabelList",newsLabelList);

        return "forward:/jsp/news/newsLabelManager.jsp";
    }
    /***
     * 重新写一个方法ajax调用它；刷新页面但是要保存栏目的id，然后如果id不是0调用分类查询的分页；id是0就不调用它；在超链接处加c:if标签
     *
     */
    @RequestMapping("/queryNewsLabelById.do")
    public String queryNewsLabelById(@RequestParam(value = "id",defaultValue="0") Integer id,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,HttpSession session){
        Page page = newsLabelService.findCurrentPageById(id,pageNum);
        session.setAttribute("id",id);//保存的当前选中id
        System.err.println(id);
        session.setAttribute("page",page);
        System.err.println(page);
        return "forward:/jsp/news/newsLabelManager.jsp";
    }





    /***
     * 项目完善：
     * 上一页：传入负数需要验证
     * 显示页面:在jsp中判断上级页面，如果没有输出：无
     */
    /**
     * mvc404排查：是否读到配置文件
     * 域对象 大小范围，以及使用方法
     * 无效绑定 的 错误原因；其实是因为接口和映射文件的名称不一致
     */
    /***
     * 循环调用：一个实体他有自己的父亲，自己的list集合的儿子们；如果两者关联resultMap都调用自身；那么他就无限循环；
     */

    /**
     * 删除功能
     *  逻辑：如果确定删除父栏目；就将查询到的父栏目一起删除，不删除它的子栏目
     * @param del_parent
     * @param id
     * @return
     */
    @RequestMapping("/deleteNewsLabel.do")
    @ResponseBody
    public String deleteNewsLabel(boolean del_parent,int id){
        /**
         *  根据参数判断是否需要删除父元素；
         *  如果删除父元素，就查找它的父元素的id,作为参数执行删除；
         */

        //首先查找到该id对应的新闻条目
        NewsLabel newsLabel = newsLabelService.selectNewsLabelById(id);
        System.err.println("newsLabel:"+newsLabel.toString());


        //只删除自己；
        System.out.println("删除自己");

        int count1 = newsLabelService.deleteNewsLabelById(newsLabel.getId());
        if(count1>0)System.err.println("删除自身条目成功");

        //获取参数
        if(del_parent == true) {
            /**
             * 同时其上一个删除父栏目,首先通过调用id查到这条newsLabel所有属性
             */
            System.out.println("级联删除父栏目");

            //如果有父栏目就删除
            if(newsLabel.getParent() != null){
                int count2;
                count2 = newsLabelService.deleteNewsLabelById(newsLabel.getParent().getId());
                if(count2>0)System.err.println("删除父条目成功");
            }else{
                System.err.println("没有父条目");
            }
        }else{
            System.err.println("不删除父条目");
        }
        return "1";
    }

    /**
     * 修改前的回显工作；
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/newsLabelEditPre.do",produces="application/json;charset=utf-8")
    public String newsLabelEditPre(int id,HttpSession session){
        /***
         *  根据id查询到需要更改的新闻条目，保存到域对象中并回显；
         */
        //查询该id对应的条目
        NewsLabel newsLabel = newsLabelService.selectNewsLabelById(id);
        session.setAttribute("newsLabel",newsLabel);
        System.err.println(newsLabel);

        //查询所有的父栏目；保存到request域中；
        List<NewsLabel> newsLabelParentList = newsLabelService.selectAllParent();
        session.setAttribute("newsLabelParentList",newsLabelParentList);
        System.err.println(newsLabelParentList);
        return "/jsp/news/newsLabelEdit.jsp";
    }
    /**
     * 修改工作
     */
    @RequestMapping(value = "/newsLabelEdit.do",produces="application/json;charset=utf-8")
    public String newsLabelEdit(Integer id,String name,Integer pid,String content){
        /**
         * 获取参数，在数据库中更改参数
         */
        if (pid == null) pid=0;
        System.err.println(id+","+name+","+pid+","+content);
        int count = newsLabelService.newsLabelEdit(id,pid,content,name);
        if (count>1){
            System.err.println("删除成功！");
        }

        return "forward:/desktop/news/newsLabelEditPre.do";
    }
    @RequestMapping(value = "/newsLabelAddPre.do",produces="application/json;charset=utf-8")
    public String newsLabelAddPre(HttpSession session){
        //查询所有的父栏目；保存到session域中；
        List<NewsLabel> newsLabelParentList = newsLabelService.selectAllParent();
        session.setAttribute("newsLabelParentList",newsLabelParentList);
        System.err.println(newsLabelParentList);
        return "/jsp/news/newsLabelAdd.jsp";
    }
    @RequestMapping(value = "/newsLabelAdd.do",produces="application/json;charset=utf-8")
    public String newsLabelAdd(String name,Integer pid,String textarea,HttpSession session){

        System.err.println(name+pid+textarea);

        //保存数据并保存到域对象中；
        int count = newsLabelService.addNewsLabel(name,pid,textarea);
        if (count>0)System.err.println("新增成功");

        //查询所有的父栏目；保存到session域中；
        List<NewsLabel> newsLabelParentList = newsLabelService.selectAllParent();
        session.setAttribute("newsLabelParentList",newsLabelParentList);
        System.err.println(newsLabelParentList);
        return "/jsp/news/newsLabelAdd.jsp";
    }



    @RequestMapping("/newsPublishPre.do")
    public String newsPublishPre(HttpSession session){
        List<NewsLabel> newsLabelParentList = newsLabelService.selectAllParent();
        session.setAttribute("newsLabelParentList",newsLabelParentList);
        System.err.println(newsLabelParentList);
        return "/jsp/news/newsPublish.jsp";
    }

    @RequestMapping("/newsPublish.do")
    public String newsPublish(News news,Integer labelId){
        /**
         * 接收参数，在数据库中添加数据，然后回显到页面
         */

        NewsLabel newsLabel= newsLabelService.selectNewsLabelById(labelId);

        news.setNewsLabel(newsLabel);
        System.err.println(news);
        //封装时间


        // 返回系统当前时间
        Date date = new Date();

/*        // 将当前时间转化为sql时间,不带时间值
        java.sql.Date transDate = new java.sql.Date(date.getTime());
        System.err.println(transDate);
        news.setTime(transDate);*/


        //转换时间格式，带时间值
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        news.setTime(Timestamp.valueOf(simpleDate.format(date)));//timestamp是sql中的类型

        System.err.println(news);

        int count = newsService.newsAdd(news);
        if( count > 0 ) System.err.println("新增新闻成功！！");
        return "/jsp/news/newsPublish.jsp";
    }
    @RequestMapping("/newsMaintenancePre.do")
    public String newsMaintenancePre(HttpSession session){
        /**
         * 获取news对象；保存到域对象中
         */
        List<News>  newsList = newsService.queryNewsAll();///尽量使用request域对象存储，在访问页面前进行回显，存入request域中

        for(News news :newsList)
            System.out.println(news);

        session.setAttribute("newsList",newsList);
        return "/jsp/news/newsMaintenanceQueryByCommon.jsp";
    }
    @RequestMapping("/newsDelete.do")
    public String newsDelete(String ids, HttpSession session){
        /**
         * 获取需要删除的数组，然后调用数据库删除
         */

        //首先将数组拆分；
        System.err.println(ids.length());

        String[] id = ids.split(",");

        Integer[] d = new Integer[id.length];//定义一个同样大小的数组

        for (int i=0;i<id.length;i++){
            d[i]=Integer.parseInt(id[i]);
        }

        int count  = newsService.newsDelete(d);
        return "/desktop/news/newsMaintenancePre.do";
    }
    
    @RequestMapping("/queryNewsByTime.do")
    public String queryNewsByTime(String beginTime,String endTime,HttpSession session) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin = format.parse(beginTime);//按照固定格式转换字符串
        Date end = format.parse(endTime);//

        System.err.println(format.format(begin)+" "+format.format(end));
        System.err.println(begin+" "+end);
//       Timestamp.valueOf(format.format(begin));
//       Timestamp.valueOf(format.format(end));
        List<News> newsList = newsService.queryNewsByDate(format.format(begin),format.format(end));
        for(News news : newsList){
            System.out.println(news);
        }

        session.setAttribute("newsList",newsList);

        return "/jsp/news/newsMaintenanceQueryByTime.jsp";
    }
}