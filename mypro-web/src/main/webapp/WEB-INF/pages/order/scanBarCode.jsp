<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>">
    <title>导入模板</title>
    <meta http-equiv="X-UA-Compatible" content="IE=8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>third-plugins/jquery-easyui-1.5.5.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>third-plugins/jquery-easyui-1.5.5.2/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>third-plugins/jquery-easyui-1.5.5.2/demo/demo.css">
    <script type="text/javascript" src="<%=basePath%>third-plugins/jquery-easyui-1.5.5.2/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>third-plugins/jquery-easyui-1.5.5.2/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="<%=basePath%>third-plugins/jquery-easyui-1.5.5.2/locale/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">
        function SubmitKeyClick(obj, evt) {
            evt = (evt) ? evt : ((window.event) ? window.event : "")
            keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which
                    : evt.charCode);
            if (keyCode == 13) {
                document.getElementById("numform").submit();
            }
        }
        function b1() {
            $("#p").focus();
            $('#p').css('ime-mode', 'disabled');
            return false;
        }
        $(document).ready(function () {
            b1();
        });
    </script>

</head>

<body>
<div class="easyui-panel" title="New Topic" style="width:100%;padding:30px 60px;">
    <form action="${pageContext.request.contextPath}/order/scanBarCode"
          method="post" id="numform">
        <input type="text" id="p" name="expressNum"
               onkeydown="return SubmitKeyClick(this,event)" onblur="return b1()"/> <input
            type="submit" onclick="return b1()" value="开始录入"/>

        <br/>
        <a href="${pageContext.request.contextPath}/order/scanIndexPage">返回上一页</a>
        <a href="${pageContext.request.contextPath}/order/importScanOrderPage">查看源数据</a>
    </form>
</div>

</body>


</html>