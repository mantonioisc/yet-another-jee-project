package examples.servlet;

import examples.dao.TagDao;
import examples.dao.UserDao;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TagServlet extends HttpServlet{
	@EJB
	TagDao tagDao;

	@EJB
	UserDao userDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("tags", tagDao.getTagList());
		req.setAttribute("users", userDao.getUserList());

		RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
		
		requestDispatcher.forward(req, resp);
	}
}
