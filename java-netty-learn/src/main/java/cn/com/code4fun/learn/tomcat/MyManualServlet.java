package cn.com.code4fun.learn.tomcat;

import java.time.LocalDateTime;

/**
 * @className: MyManualServlet
 * @desc:
 * @author: niejian
 * @time: 2022/11/6 13:46
 * @version: 0.0.1
 */
public class MyManualServlet extends ManualServlet{
    @Override
    public void doGet(ManualRequest request, ManualResponse response) throws Exception {
        response.write("first get， 当前时间：" + LocalDateTime.now());

    }

    @Override
    public void doPost(ManualRequest request, ManualResponse response) throws Exception {
        response.write("first post， 当前时间：" + LocalDateTime.now());
    }
}
