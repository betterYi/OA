package com.oa.desktop.news.newsLabel.handler;

import com.oa.commons.beans.NewsLabel;
import com.oa.commons.vo.Page;
import com.oa.desktop.news.newsLabel.service.NewsLabelService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/desktop/news")
public class NewsLabelHandler {
    @Autowired
    private NewsLabelService newsLabelService;

    /***
     * 分页
     * @param pageNum
     * @param session
     * @return
     */
    @RequestMapping("/queryNewsLabel.do")
    public String queryNesLabel(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, HttpSession session){

        Page page = newsLabelService.findCurrentPage(pageNum);
        // System.out.println(pageNum);
        session.setAttribute("page",page);//将page保存到session域中；
        //System.out.println(page);
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
     * 无效绑定 的 错误原因；其实
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
        if(count1>0)System.out.println("删除自身条目成功");

        //获取参数
        if(del_parent == true) {
            /**
             * 同时其上一个删除父栏目,首先通过调用id查到这条newsLabel所有属性
             */
            System.out.println("级联删除父栏目");

            /*需求不明确，*/
            if(newsLabel.getParent().getId() != 0){
                int count2;
                count2 = newsLabelService.deleteNewsLabelById(newsLabel.getParent().getId());
                if(count2>0)System.out.println("删除父条目成功");
            }

        }else{
            System.out.println("没有删除父条目");
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
}