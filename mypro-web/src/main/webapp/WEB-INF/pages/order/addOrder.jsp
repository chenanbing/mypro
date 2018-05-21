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
    <title>添加订单</title>
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

    <script>
        function submitForm() {
              // 调用 form  插件的 'submit' 方法来提交 form
              $('#ff').form('submit', {
                  url:"${pageContext.request.contextPath}/order/addOrder",
                  onSubmit: function(){
                         // 做某些检查
                         // 返回 false 来阻止提交
                  },
                  success:function(data){
                      if(data == 1){
                          alert("添加成功");
                      }
                  }
             });
        }
        function clearForm() {
            $('#ff').form('clear');
        }
    </script>

</head>

<body>
<div class="easyui-panel" title="New Topic" style="width:100%;max-width:400px;padding:30px 60px;">
    <form id="ff" >
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="boxNum" style="width:100%" data-options="label:'箱号:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="name" style="width:100%" data-options="label:'货物名称:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="brand" style="width:100%" data-options="label:'品牌:',required:true">
        </div>
    </form>
    <div style="text-align:center;padding:5px 0">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">Submit</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">Clear</a>
    </div>
</div>

</body>


</html>