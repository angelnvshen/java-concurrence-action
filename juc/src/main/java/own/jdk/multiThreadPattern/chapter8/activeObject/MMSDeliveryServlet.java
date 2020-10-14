package own.jdk.multiThreadPattern.chapter8.activeObject;

import own.jdk.multiThreadInAction.util.Debug;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class MMSDeliveryServlet extends HttpServlet {
    private static final long serialVersionUID = 5886933373599895099L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MMSDeliverRequest mmsDeliverRequest = this.parsetRequest(req.getInputStream());
        Recipient shortNumRecipient = mmsDeliverRequest.getRecipient();
        Recipient originalNumberRecipient = null;

        try {
            // 将接收方短号转换为长号
            originalNumberRecipient = convertShortNumber(shortNumRecipient);
        } catch (SQLException e) {

            resp.setStatus(202);
        }

        Debug.info(String.valueOf(originalNumberRecipient));
    }

    private MMSDeliverRequest parsetRequest(ServletInputStream inputStream) {
        MMSDeliverRequest mmsDeliverReq = new MMSDeliverRequest();
        // 省略其他代码
        return mmsDeliverReq;
    }

    private Recipient convertShortNumber(Recipient shortNumberRecipient)
            throws SQLException {
        Recipient recipent = null;
        // 省略其他代码
        return recipent;
    }
}
