package pl.edu.wat.client;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notify")
public class ClientServlet extends HttpServlet {

    @Inject
    private Client client;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        client.makeNotifications();
    }
}
