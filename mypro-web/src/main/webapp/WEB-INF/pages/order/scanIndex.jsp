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

    </script>

</head>

<body>
<div class="easyui-panel" title="New Topic" style="width:100%;padding:30px 60px;">
    扫描条形码打印操作步骤：
    <br/>
    1、上传条形码源文件（库中已存在则无需上传）
    <br/>
    2、使用扫码枪扫描条形码（扫到的条形码和库中的数据匹配则开始打印）
    <br/>
    <br/>
    <a href="${pageContext.request.contextPath}/order/importScanOrderPage">上传条形码源文件</a>
    <br/>
    <a href="${pageContext.request.contextPath}/order/scanBarCodePage">开始扫描</a>
    <br/>
    <a href="${pageContext.request.contextPath}/order/mainPage">返回上一页</a>
</div>

</body>


</html>