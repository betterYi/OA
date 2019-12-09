<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 李红义
  Date: 2019/12/6
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>栏目管理</title>
    <link href="../../css/style.css" rel="stylesheet" type="text/css">
    <script>
        function xiugai2()
        {
            var str = confirm("是否确定修改栏目名称？");
            if(str == true)
            {
                alert("确认修改");
            }
            else
            {
                alert("取消修改！");
            }
        }
    </script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<center>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td height="25" align="center" valign="bottom" class="td06"> <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="2%" valign="middle" background="../../images/bg_03.gif">&nbsp;</td>
                    <td width="2%" valign="middle" background="../../images/bg_03.gif"><img src="../../images/main_28.gif" width="9" height="9" align="absmiddle"></td>
                    <td height="30" valign="middle" background="../../images/bg_03.gif"><div align="left"><font color="#FFFFFF">栏目修改</font></div></td>
                </tr>
            </table></td>
        </tr>
    </table>
    <form name="form1" method="post" action="${pageContext.request.contextPath}/desktop/news/newsLabelEdit.do">
        <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table01">
            <tr>
                <td class="td_02">栏目名称</td>
                <td class="td_02"><input name="name" type="text" class="input" style="width:99% " value="${sessionScope.newsLabel.name}"></td>
            </tr>
            <tr>
                <td class="td_02">上级栏目名称</td>
                <td class="td_02">
                    <input name="id" type="hidden" value="${sessionScope.newsLabel.id}">

                    <select name="pid" style="width:99% ">
                        <%--首先判断第一个，如果第一个符合，自动选第一个--%>
                        <option value="0"
                                <c:if test="${sessionScope.newsLabel.parent.id == 0}">selected="selected"</c:if>
                        >无</option>
                        <c:forEach items="${sessionScope.newsLabelParentList}" var="p">
                            <option value="${p.id}"
                                    <c:if test="${sessionScope.newsLabel.parent.id == p.id}">selected="selected"</c:if>
                                    >${p.name}</option>
                        </c:forEach>

                    </select>
                </td>
            </tr>
            <tr>
                <td width="14%" class="td_02">栏目描述</td>
                <td width="86%" class="td_02">
                    <textarea name="content" rows="5" style="width:99% ">${sessionScope.newsLabel.content}</textarea>
                </td>
            </tr>
        </table>
        <br>
        <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td class="td_page"><div align="center">

                    <input name="Submit" type="submit" class="buttonface02" value="  修 改  " onClick="xiugai2()">
                    &nbsp;&nbsp;
                    <input name="Submit" type="reset" class="buttonface02" value="  重 置  ">
                    &nbsp;
                    <input name="Submit" type="submit" class="buttonface02" value="  关 闭  " onClick="window.close()">
                </div></td>
            </tr>
        </table>
        <p>&nbsp;</p>
    </form>
</center>
</body>
</html>