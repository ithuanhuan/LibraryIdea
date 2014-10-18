/**
 * Created by huanhuan on 2014/10/13.
 */
package libraryservlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 本类负责与图书馆的交互操作，包括登录、查询等操作。
 * @author 蔡欢欢、赵巍、陈金凤
 * @version 0.20
 *
 *
 */
public class Library {
    private String inputNumber;
    private String inputPassword;

    private HttpClient httpClient = new DefaultHttpClient(
            new ThreadSafeClientConnManager());

    /**
     * 构造函数
     * @param inputNumber 学号
     * @param inputPassword 密码
     */
    public Library(String inputNumber, String inputPassword) {
        this.inputNumber = inputNumber;
        this.inputPassword = inputPassword;
    }

    /**
     * 登录方法，可以检验用户名是否正确，所有的操作都需要登录后进行。
     * @return 登录成功返回 <b>true</b>，登录失败返回 <b>false</b>
     *
     * @throws ClientProtocolException
     * @throws IOException
     */
    public boolean Login() {
        String url = "http://222.206.65.12/reader/redr_verify.php";

        // 定义参数对象，设置参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("number", inputNumber));
        params.add(new BasicNameValuePair("passwd", inputPassword));
        params.add(new BasicNameValuePair("select", "cert_no"));
        params.add(new BasicNameValuePair("returnUrl", ""));

        try {
            String result1 = post(url, params, httpClient);// 登陆页面成功为null

            if(result1 == null) {
                return true;
            }
            else
                return false;

        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 获取图书馆中借阅的图书信息。
     * 返回的 Book 的数组
     *
     * @see Book
     *
     * @return 借阅的所有的 Book 的集合
     */
    public ArrayList<Book> getBookList() throws Exception {
        String url1 = "http://222.206.65.12/reader/book_lst.php";

        String result = get(url1, httpClient);// 获取图书列表（必须先登陆）
        Document doc = Jsoup.parse(result);

        //Element table = doc.getElementsByTag("table").get(0);
        Element table = doc.getElementsByTag("table").get(0);
        Elements trs = table.getElementsByTag("tr");

        ArrayList<Book> list=new ArrayList();

        for (int i = 1; i < trs.size(); i++) {
            Element tr1 = trs.get(i);
            Elements tds = tr1.getElementsByTag("td");

            Book book = new Book();

            for (int j = 0; j < tds.size() - 2; j++) {


                Element td = tds.get(j);
                String text = td.text();
                if(j==0) {
                    book.num=text;
                }
                else if(j==1) {
                    book.bookName=text;
                }
                else if(j==2) {
                    book.author=text;
                }

                else if(j==3) {
                    book.borrowDate=text;
                }
                else if(j==4) {
                    book.returnDate=text;
                }
                else if(j==5) {
                    book.place=text;
                }

            }

            list.add(book);
        }

        return list;
    }


    private String post(String url, List<NameValuePair> params,
                        HttpClient httpClient) throws ClientProtocolException, IOException {
        // 定义请求对象

        HttpPost request = new HttpPost(url);

        // 增加参数
        request.addHeader("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        request.addHeader("Accept-Encoding", "gzip,deflate,sdch");
        request.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
        request.addHeader("Cache-Control", "max-age=0");
        request.addHeader("Connection", "keep-alive");
        request.addHeader("Host", "222.206.65.12");
        request.addHeader("Referer",
                "http://222.206.65.12/reader/redr_cust_result.php");
        request.addHeader(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.143 Safari/537.36");
        request.addHeader("Origin", "http://222.206.65.12");
        request.addHeader(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.143 Safari/537.36");

        // 设置请求实体并统一编码
        HttpEntity httpEntity = new UrlEncodedFormEntity(params, "utf-8");
        request.setEntity(httpEntity);

        // 客户端执行请求并响应
        HttpResponse response = httpClient.execute(request);

        // 获取并判断响应码，状态代码值等于200
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            // 获取响应实体对象
            HttpEntity entity = response.getEntity();

            // //返回该对象的字符串表示，以文本方式表示此对象的字符串。
            String result = EntityUtils.toString(entity);

            // 统一编码转为utf-8中文
            result = new String(result.getBytes("iso8859-1"), "utf-8");

            // 解决连接没有关闭
            request.releaseConnection();

            return result;
        }

        return null;
    }

    private String get(String url, HttpClient httpClient)
            throws ClientProtocolException, IOException {

        // 定义请求对象
        HttpGet request = new HttpGet(url);

        // 增加参数
        request.addHeader("Accept", "*");
        request.addHeader("Accept-Encoding", "gzip,deflate,sdch");
        request.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
        request.addHeader("Connection", "keep-alive");
        request.addHeader("Content-Type", "application/x-www-form-urlencoded");
        request.addHeader("Accept-Charset", "utf-8");
        request.addHeader("Host", "222.206.65.12");
        request.addHeader("Referer",
                "http://222.206.65.12/reader/redr_verify.php");
        request.addHeader(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.143 Safari/537.36");

        // 获取响应对象
        HttpResponse response = httpClient.execute(request);

        // 获取响应实体获取并判断响应码，状态代码值等于200
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

            // 获取响应实体对象
            HttpEntity entity = response.getEntity();

            String result = EntityUtils.toString(entity);// 返回该对象的字符串表示，以文本方式表示此对象的字符串。

            result = new String(result.getBytes("iso8859-1"), "utf-8");

            // 解决连接没有关闭
            request.releaseConnection();

            return result;
        }

        return null;

    }

}
