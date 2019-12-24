<%--
  Created by IntelliJ IDEA.
  User: 李红义
  Date: 2019/12/4
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>栏目管理</title>
    <link href="../../css/style.css" rel="stylesheet" type="text/css">
    <script language="javascript" src="../../js/util.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
    <script>
        $(function () {
                $("select[name='id']").change(
                    function(){
                        chuanzhi(value);
                    }
                )
            }
        );
        function chuanzhi(value) {
            // alert(value);
            if(value != 0)
            window.location.href="${pageContext.request.contextPath}/desktop/news/queryNewsLabelById.do?id="+value;
            else{
                window.location.href="${pageContext.request.contextPath}/desktop/news/queryNewsLabel.do"
            }
        }
        function shanchu(id)
        {
            var del = confirm("确定要删除本条目吗？");
            if(del == true){
                var  str = confirm("是否确定删除上级栏目？");
                var del_p;
                    if(str == true)
                    {
                        del_p = true;
                    }
                    else
                    {/*不删除上级元素*/
                        del_p = false;
                    }
                /*调用ajax删除信息提示删除成功，并重新加载页面*/
                $.ajax({
                    type:"post",
                    dataType : "json",
                    async : false,//涉及到返回值的问题
                    data: { "id": id ,"del_parent": del_p}, //参数值
                    url: "${pageContext.request.contextPath}/desktop/news/deleteNewsLabel.do",
                    success : function(mesg){
                        // alert(mesg);
                        if(mesg==1){
                            alert("删除成功");
                        }
                    },
                    error: function() {
                        alert("error");
                    }
                });
                location.reload();
            }else{
                alert("取消删除")
            }
            }
    </script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<center>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td height="25" align="center" valign="bottom" class="td06"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="2%" valign="middle" background="../../images/bg_03.gif">&nbsp;</td>
                    <td width="2%" valign="middle" background="../../images/bg_03.gif"><img src="../../images/main_28.gif" width="9" height="9" align="absmiddle"></td>
                    <td height="30" valign="middle" background="../../images/bg_03.gif"><div align="left"><font color="#FFFFFF">栏目管理</font></div></td>
                </tr>
            </table></td>
        </tr>
    </table>
    <form name="form1" method="post" action="">
        <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
            <tr>
                <td colspan="2" class="td_02"><SPAN class=td_title>根据栏目名称查询</SPAN></td>
            </tr>
            <tr>
                <td width="14%" class="td_02">栏目名称</td>
                <td width="86%" class="td_02">
                    <select name="id" class="input" style="width:99% " id="queryById" onchange="chuanzhi(this.options[this.options.selectedIndex].value)">
                        <option value="0" selected="selected">--请选择--</option>
                        <c:forEach items="${newsLabelList}" var="newsLabel">
                            <option value="${newsLabel.id}" <c:if test="${id == newsLabel.id}">selected="selected"</c:if>> ${newsLabel.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

        </table>
        <br>
        <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td class="td_page" align="left">
                    <div align="right">
                        <input name="Submit" type="button" class="buttonface02" value="添加栏目" onClick="javascript:windowOpen('${pageContext.request.contextPath}/desktop/news/newsLabelAddPre.do','','',700,300,'','','')">
                    </div></td>
            </tr>
        </table>
        <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
            <tr>
                <td colspan="5" align="right" class="td07"><img src="../../images/1.gif" width="4" height="5" align="absmiddle"> <a
                    <c:if test="${page.pageNum != 1}">
                        <c:choose>
                            <c:when test="${id == 0}">
                                href="${pageContext.request.contextPath}/desktop/news/queryNewsLabel.do?pageNum=1"
                            </c:when>
                            <c:otherwise>
                                href="${pageContext.request.contextPath}/desktop/news/queryNewsLabelById.do?id=${id}&pageNum=1"
                            </c:otherwise>
                        </c:choose>
                    </c:if> >首页</a>
                    <img src="../../images/2.gif" width="3" height="5" align="absmiddle"> <a
                            <c:if test="${page.pageNum > 1}">
                                <c:choose>
                                    <c:when test="${id != 0}">
                                        href="${pageContext.request.contextPath}/desktop/news/queryNewsLabelById.do?id=${id}&pageNum=${sessionScope.page.pageNum-1}"
                                    </c:when>
                                    <c:otherwise>
                                        href="${pageContext.request.contextPath}/desktop/news/queryNewsLabel.do?pageNum=${sessionScope.page.pageNum-1}"
                                    </c:otherwise>
                                </c:choose>
                            </c:if>>上一页</a>

                    <img src="../../images/2-2.gif" width="3" height="5" align="absmiddle"> <a
                            <c:if test="${page.pageNum < page.totalPages}">
                                <c:choose>
                                <c:when test="${id != 0}">
                                    <%--如果id不为0就去查找对应的子栏目--%>
                                    href="${pageContext.request.contextPath}/desktop/news/queryNewsLabelById.do?id=${id}&pageNum=${page.pageNum+1}"
                                </c:when>
                                <c:otherwise>
                                    href="${pageContext.request.contextPath}/desktop/news/queryNewsLabel.do?pageNum=${sessionScope.page.pageNum+1}"
                                </c:otherwise>
                                </c:choose>
                            </c:if>>下一页</a>

                    <img src="../../images/3.gif" width="4" height="5" align="absmiddle"> <a
                        <c:if test="${page.pageNum != page.totalPages}">
                            <c:choose>
                                <c:when test="${id != 0}">
                                    href="${pageContext.request.contextPath}/desktop/news/queryNewsLabelById.do?id=${id}&pageNum=${sessionScope.page.totalPages}"
                                </c:when>
                                <c:otherwise>
                                    href="${pageContext.request.contextPath}/desktop/news/queryNewsLabel.do?pageNum=${sessionScope.page.totalPages}"
                                </c:otherwise>
                            </c:choose>
                        </c:if> >末页</a>
                    　　共 ${sessionScope.page.totalPages} 页 ${sessionScope.page.totalRow} 条记录</td>
            </tr>
            <tr>
                <td width="17%" class="td_top">栏目名称</td>
                <td width="15%" class="td_top">上级栏目名称</td>
                <td width="44%" class="td_top">栏目描述</td>
                <td width="12%" class="td_top"></td>
                <td width="12%" class="td_top"></td>
            </tr>
            <c:forEach items="${sessionScope.page.datas}" var="p">
            <tr>
                <td class="td07">${p.name}</td>
                <td class="td07">${p.parent.name}</td>
                <td class="td07">${p.content}</td>
                <td class="td07"><a href="#" onClick="shanchu(${p.id})">删除</a></td>
                <td class="td07"><a href="#" onClick="javascript:windowOpen('newsLabelEditPre.do?id=${p.id}','','',670,260,'no','yes','100','200')">修改</a>
                </td>
            </tr>
            </c:forEach>
        </table>
        <p>&nbsp;</p>
    </form>
</center>
</body>
</html>
