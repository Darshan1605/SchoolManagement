import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Student;
@WebServlet("/studentremoved")
public class DeleteStudent extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id1 = (String)req.getParameter("id");
		int id = Integer.parseInt(id1);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("darshan");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		Student s = em.find(Student.class, id);
		if(s!=null) {
		et.begin();
		em.remove(s);
		et.commit();
		
		PrintWriter pw = resp.getWriter();
		pw.write("Student Removed");
		RequestDispatcher rd = req.getRequestDispatcher("Student.jsp");
		rd.include(req, resp);
		resp.setContentType("text/html");
		}
		else {
			PrintWriter pw = resp.getWriter();
			pw.write("Id Not Present");
			RequestDispatcher rd = req.getRequestDispatcher("RemoveStudent.html");
			rd.include(req, resp);
			resp.setContentType("text/html");
		}
	}
}
