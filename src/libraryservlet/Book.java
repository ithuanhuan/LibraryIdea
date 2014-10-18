/**
 * Created by huanhuan on 2014/10/13.
 */
package libraryservlet;
import java.util.Scanner;

/**
 * @author 蔡欢欢、赵巍、陈金凤
 * @version 0.20
 *
 * 书的属性的定义
 */
public class Book {
    /**
     * 书的条码号
     */
    public String num;//条码号

    /**
     * 书名
     */
    public String bookName;

    /**
     * 责任者
     */
    public String author;//责任者

    /**
     * 借阅日期
     */
    public String borrowDate;//借阅日期

    /**
     * 应还日期
     */
    public String returnDate;//应还日期

    /**
     * 馆藏地
     */
    public String place;//馆藏地

    @Override
    public String toString() {
        return "条码号:" + num+ " "+" 书名:" + bookName+" "+"责任者:"+ author+" "+"借阅日期:" + borrowDate+" 应还日期:"+returnDate+" 馆藏地:"+place;
    }
}
