package cn.com.code4fun.learn.tomcat;

/**
 * @className: ManualServlet
 * @desc:
 * @author: niejian
 * @time: 2022/11/6 12:59
 * @version: 0.0.1
 */
public abstract class ManualServlet {
    public void service(ManualRequest request, ManualResponse response ) throws Exception {
        if ("GET".equals(request.getMethod())) {
            doGet(request, response);
        }else {
            doPost(request, response);

        }
    }

    public abstract void doGet(ManualRequest request, ManualResponse response) throws Exception;

    public abstract void doPost(ManualRequest request, ManualResponse response) throws Exception;
}
