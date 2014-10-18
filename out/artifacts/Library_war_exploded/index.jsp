<%--
  Created by IntelliJ IDEA.
  User: huanhuan
  Date: 2014/10/13
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>山东理工大学图书馆</title>
    <meta http-equiv="Content-Type" content="text/html; charset=GB2312">
    <style type="text/css">
        #para1{
            text-align:center;
        //color:rgb(239,155,160);
        //color:rgb(0,124,195);
            color:blue;
            font-style:italic;
            font-size:40px;
            font-weight:900;
            text-shadow:5px 5px 5px #F0FFFF;
        }
        form.margin{
            margin-top:200px;
            margin-bottom:100px;
            margin-right:50px;
            margin-left:50px;
        }
        .center{
            text-align:center;
        }
        input{
            border:#09F 1px solid;
        // color:red;
        }
        body{
            background-size: 100%;
            background-image:url('img/bookph1.jpg');
            background-repeat:no-repeat;

        }

        input.big{
            line-height:200%;
        }

    </style>
</head>
<body>
<p id="para1">欢迎来到山东理工大学图书馆</p>
<form action="/servlet/mainDemo" method="post" align="center" class="margin">

    学号:<input class="big" type="text" name="user" value="" style="border-style:solid; border-color:blue"><br/>
    密码:<input class="big" type="password" name="psw" value="" style="border-style:solid; border-color:blue"><br/>
    <input type="reset" value="清除数据">
    <input type="submit" value="提交数据">
</form>
</body>
</html>