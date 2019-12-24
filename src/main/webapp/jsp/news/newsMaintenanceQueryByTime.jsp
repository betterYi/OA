<%--
  Created by IntelliJ IDEA.
  User: 李红义
  Date: 2019/12/6
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>个人设置</title>
    <link href="../../css/style.css" rel="stylesheet" type="text/css">
    <script language="javascript" type="text/JavaScript" src="../../js/util.js"></script>
    <script language="javascript" type="text/JavaScript" src="../../js/laydate/laydate.js" charset="utf-8"></script>
    <script>
        function query() {
            /*在传递值之前判断是否为空**/
            if(!document.getElementById("beginTime").value || !document.getElementById("endTime").value){
                alert("请选择开始时间和结束时间");
            }else{
                /*alert(document.getElementById("beginTime").value);
                alert(document.getElementById("endTime").value);*/
                var beginTime = document.getElementById("beginTime").value;
                var endTime = document.getElementById("endTime").value;

                var Date1 = new Date(beginTime);
                var Date2 = new Date(endTime);
                if(Date1.getTime() > Date2.getTime()){
                    alert("开始时间应该早于结束时间,请重新选择！！");
                }else{
                    window.location.href="${pageContext.request.contextPath}/desktop/news/queryNewsByTime.do?beginTime="+beginTime+"&endTime="+endTime;
                }
            }
        }
    </script>
<%--    <script>
        function GetDate (nText)
        {
            /*showModalDialog方法已经被遗弃*/
            var reVal = window.open("../time.htm",'',"status:no;center:yes;scroll:no;resizable:no;help:no;dialogWidth:255px;dialogHeight:260px");
            if (reVal != null)
            {//赋值
                if (nText == 1)
                    document.getElementById("beginTime").value = reVal;
                if (nText == 2)
                    document.getElementById("endTime").value = reVal;
            }
        }
    </script>--%>
    <style type="text/css">
        <!--
        .style1 {font-size: 14px}
        -->
    </style>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td height="25" align="center" valign="bottom" class="td06"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td width="2%" valign="middle" background="../../images/bg_03.gif">&nbsp;</td>
                <td width="2%" valign="middle" background="../../images/bg_03.gif"><img src="../../images/main_28.gif" width="9" height="9" align="absmiddle"></td>
                <td height="30" valign="middle" background="../../images/bg_03.gif"><div align="left"><font color="#FFFFFF">新闻维护</font></div></td>
            </tr>
        </table></td>
    </tr>
</table>
<br>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
    <td width="79%" class="td_page style1">--<a href="${pageContext.request.contextPath}/jsp/news/newsMaintenanceQueryByCommon.jsp">普通查询</a>--按时间查询--</td>
</table>
<form name="form1" method="post" action="">
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0"
           class="table01">
        <tr>
            <td colspan="4" class="td_02"><span class=td_title>新闻查询</span></td>
        </tr>
        <tr>
            <td width="10%" align="center" class="td_02">开始时间</td>
            <td width="40%" class="td_02">
                <input name="beginTime" id="beginTime" type="text" class="input" placeholder="请选择日期" size="15" maxlength="12" readonly="true" />
                <script>
                    laydate.render({
                    elem: '#beginTime',type: 'datetime',lang: 'en'//指定元素
                });
                </script>
                <%--<img src="../../images/search.gif" width="21" height="20" align="absmiddle" onclick="WdatePicker({el:this,maxDate:'%y-%M-%d'})"&lt;%&ndash;"GetDate(1)"&ndash;%&gt; />--%>
            </td>
            <td width="10%" align="center" class="td_02">结束时间</td>
            <td width="40%">
                <span class="td_02">
                <input name="endTime" id="endTime" type="text" class="input" placeholder="选择日期" size="15" maxlength="12" readonly="true" />
                    <script>
                        laydate.render({
                        elem: '#endTime',type: 'datetime',lang: 'en'//指定元素
                        });
                    </script>
        <%--<img src="../../images/search.gif" width="21" height="20" align="absmiddle" onclick="GetDate(2)" />--%>
            </span>
            </td>
        </tr>
        <tr>
            <td class="td_02" align="center">&nbsp;</td>
            <td class="td_02">&nbsp;</td>
            <td class="td_02" align="center">&nbsp;</td>
            <td class="td_02">
                <input name="Submit" type="button" class="buttonface02" value="  查询  " onclick="query();"/></td>
        </tr>
    </table>
    <br>
    <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td class="td_page">
                <div align="right">
                <input name="Submit" type="submit" class="buttonface02" value="  删 除  "
                       onClick="queding()">
                </div>
            </td>
        </tr>
    </table>
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0"

           class="table01">
        <tr>
            <td colspan="8" align="right" class="td07"><img src="../../images/1.gif" width="4"
                                                            height="5" align="absmiddle"> 首页　 <img src="../../images/2.gif" width="3" height="5"
                                                                                                   align="absmiddle"> 上一页　 <img src="../../images/2-2.gif" width="3" height="5"
                                                                                                                                align="absmiddle"> 下一页　 <img src="../../images/3.gif" width="4" height="5"
                                                                                                                                                             align="absmiddle"> 末页　　共 1 页 1 条记录</td>
        </tr>
        <tr>
            <td width="6%" class="td_top"c align="center">
                <input name="chk" type="checkbox" id="chk" onClick="selectAllByChk(chk,checkbox)" value="checkbox0"></td>
            <td width="16%" class="td_top">新闻标题</td>
            <td width="15%" class="td_top">栏目名称</td>
            <td width="14%" class="td_top">上级栏目名称</td>
            <td width="13%" class="td_top">新闻发布者</td>
            <td width="19%" class="td_top">新闻发布时间</td>
            <td width="11%" class="td_top">审核状态</td>
            <td width="6%" class="td_top">修改</td>
        </tr>
        <c:forEach items="${newsList}" var="news">
        <tr>
            <td class="td07" align="center">
                <input type="checkbox" name="ids" value="${news.id}"></td>
            <td class="td07"><a href="#" onClick="javascript:windowOpen('${pageContext.request.contextPath}/jsp/news/newsDesc.jsp','','',670,450,'','','')">${news.title}</a></td>
            <td class="td07">${news.newsLabel.name}</td>
            <td class="td07">${news.newsLabel.parent.name}</td>
            <td class="td07">张三(session中获取)</td>
            <td class="td07"><FONT style="FONT-SIZE: 10pt" color=#000000>2008/04/10 <FONT

                    style="FONT-SIZE: 10pt" color=#000000>11:23</FONT></FONT></td>
            <td class="td07"><a href="#" onClick="javascript:windowOpen('${pageContext.request.contextPath}/jsp/news/newsReview.jsp','','',700,600,'no','yes','100','100')">未审核</a></td>
            <td class="td07">
                <a href="#" onClick="javascript:windowOpen('${pageContext.request.contextPath}/jsp/news/newsEdit.jsp','','',700,430,'no','yes','100','100')">修改</a>
            </td>
        </tr>
        </c:forEach>
    </table>
    <p><br>
    </p>
</form>
</body>
</html>
