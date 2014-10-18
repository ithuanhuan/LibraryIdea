/**
 * Created by huanhuan on 2014/10/13.
 */
package libraryservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class mainDemo extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //System.out.println(request.getParameter("user"));
        try {
            test1(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void test1(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //设置response使用的码表，以控制response以什么码表向浏览器写出数据
        //((ServletRequest) response).setCharacterEncoding("UTF-8");

        //指定浏览器以什么码表打开
        response.setHeader("content-type","text/html;charset=UTF-8");

        Library Me = new Library(request.getParameter("user"), request.getParameter("psw"));// 构造函数
        if (Me.Login() == false) {
            System.out.println("对不起，用户名或密码错误，请查实！");
        } else {
            ArrayList<Book> books = Me.getBookList();
            request.setAttribute("books", books);

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/mainDemojsp.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}