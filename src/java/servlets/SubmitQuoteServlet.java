package servlets;

import businesslogic.QuoteService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SubmitQuoteServlet", urlPatterns = {"/SubmitQuoteServlet"})
public class SubmitQuoteServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/submitQuote.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Variables
        String action = request.getParameter("action");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String description = request.getParameter("description");

        QuoteService quoteService = new QuoteService();
        //Adding an Quote
        if (action != null && action.equals("submit")) {
            if (name != null && email != null && description != null) {
                String status = quoteService.addQuote(name, email, description);

                request.setAttribute("message", status);
            } else {
                request.setAttribute("message", "You need to enter something in all fields.");
            }

        }
        request.getRequestDispatcher("/WEB-INF/submitQuote.jsp").forward(request, response);

    }

}
