<%--
  Created by IntelliJ IDEA.
  User: huanhuan
  Date: 2014/10/13
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*, libraryservlet.*"
         pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>山东理工大学图书馆</title>
    <style type="text/css">
        body {
            background-size: 100%;
            text-align: left;
            background-image: url('/img/bookph3.jpg');
            background-repeat: no-repeat;
            height: 200%;
            width: 100%;
        }

        #para2 {
            text-align: center;
            font-size: 25px;
            color: red;
            font-size: 40px;
        }

        img {
            position: fixed;
            bottom: 0px;
            right: 1px
        }

        .pitem {
            margin-left: 80px;
            font-size: 17px;
        }
    </style>
</head>
<body>
<p id="para2">山东理工大学图书馆</p>
<table border="1" cellspacing="0" cellpadding="10" align="center">
    <tr>
        <td>书的条码号</td>
        <td>书名</td>
        <td>责任者</td>
        <td>借阅日期</td>
        <td>应还日期</td>
        <td>馆藏地</td>
    </tr>
        <%
		ArrayList<Book> books = (ArrayList<Book>) request
				.getAttribute("books");
		for (int j = 0; j < books.size(); j++) {
	%>
    <tr>
        <%
            Book a = books.get(j);
        %>

        <p class="pitem">
        <td>
            <%
                out.write("书的条码号:"+a.num);
            %>
        </td>
        <td>
            <%
                out.write("书名:"+a.bookName);
                //out.write(a.toString());
            %>
        </td>
        <td>
            <%
                out.write("责任者:"+a.author);
            %>
        </td>
        <td>
            <%
                out.write("借阅日期:"+	a.borrowDate);
            %>
        </td>
        <td>
            <%
                out.write("应还日期:"+a.returnDate);
            %>
        </td>
        <td>
            <%
                out.write("馆藏地:"+a.place);
            %>
        </td>
    </tr>
    </p>
        <%
		}
	%>
    </table>

</body>
</html>
