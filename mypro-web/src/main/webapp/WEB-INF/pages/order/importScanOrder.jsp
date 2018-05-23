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
    <title>导入excel</title>
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
        function getData() {
            var expressNum = $('#expressNum').val();

            $('#grid').datagrid({
                url: "order/getScanOrderList",
                queryParams:{expressNum:expressNum},
                title:'源数据',
                fitColumns:true,
                nowrap:false,
                width:'auto',
                rownumbers:true,
                singleSelect:false,
                selectOnCheck:false,
                checkOnSelect:false,
                idField:'id',
                pagination: true,
                pageSize: 50,
                loadMsg:"正在努力加载数据，请稍后...",
                pageList: [10, 50, 100, 500],
                columns: [[
                    { field: 'id', title: 'id', width: 80, checkbox:true },
                    { field: 'boxNum', title: '箱号', width: 100 },
                    { field: 'name', title: '品名', width: 100 },
                    { field: 'brand', title: '品牌', width: 100 },
                    { field: 'specs', title: '规格', width: 100 },
                    { field: 'num', title: '数量', width: 100 },
                    { field: 'price', title: '单价', width: 100 },
                    { field: 'totalPrice', title: '总价', width: 100 },
                    { field: 'netWeight', title: '净重', width: 100 },
                    { field: 'grossWeight', title: '毛重', width: 100 },
                    { field: 'recipientName', title: '收件人姓名', width: 100 },
                    { field: 'recipientPhone', title: '收件人电话', width: 100 },
                    { field: 'recipientAddress', title: '地址', width: 100 },
                    { field: 'recipientId', title: '身份证号', width: 100 },
                    { field: 'orderNum', title: '客户订单号', width: 100 },
                    { field: 'expressNum', title: '快递单号', width: 100 }
                ]],
                toolbar:$("#tb")
            });
        }
        
        function getSelectedToPrint() {
            var checkedItems = $('#grid').datagrid('getChecked');
            var ids = [];
            $.each(checkedItems, function(index, item){
                ids.push(item.id);//
            });
            console.info(ids.join(","));
            $.ajax({
                type: 'POST',
                url: "order/printOrder",
                data: {idStr:ids.join(",")},
                success: function(){
                    alert("打印成功");
                },
                dataType: 'json'
            });


        }

        function submitScanOrderExcel() {
            // 调用 form  插件的 'submit' 方法来提交 form
            $('#ff').form('submit', {
                url:"${pageContext.request.contextPath}/order/importScanOrder",
                onSubmit: function(){
                    // 做某些检查
                    // 返回 false 来阻止提交
                },
                success:function(data){
                    if(data == 1){
                        alert("添加成功");
                        getData();
                    }
                }
            });
        }
        
        $(document).ready(function(){
            getData();
        });
    </script>

</head>

<body>
<div class="easyui-panel" title="New Topic" style="width:100%;max-width:400px;padding:30px 60px;">
    <form id="ff" method="post" enctype="multipart/form-data">
        <div style="margin-bottom:20px">
            <input name="fileName" type="file">
        </div>
    </form>
    <div style="text-align:center;padding:5px 0">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitScanOrderExcel()" style="width:80px">Submit</a>
    </div>
</div>

<table id="grid"></table>
<div id="tb" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="getSelectedToPrint()">批量打印</a>
    </div>

    <form id="conditions">
        快递单号: <input id="expressNum" type="text" name="expressNum">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="getData()">Search</a>
    </form>

</div>

</body>


</html>