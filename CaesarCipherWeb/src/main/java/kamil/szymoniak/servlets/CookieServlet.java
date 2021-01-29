package kamil.szymoniak.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

/**
 * Servlet displays statistics taken from cookies
 * 
 * @author Kamil Szymoniak
 * @version 4.0
 */
public class CookieServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * Displays visits and code request statistics by parsing cookies
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Statistics</title>");            
            out.println("</head>");
            out.println("<body>");
            Cookie[] cookies = request.getCookies();
            int errorsNumber = 0;
            int requests = 0;
            int visits = 0;
            if(cookies != null){
                for(Cookie cookie : cookies){
                    switch (cookie.getName()) {
                        case "error":
                            errorsNumber = Integer.parseInt(cookie.getValue());
                            requests += Integer.parseInt(cookie.getValue());
                            break;
                        case "success":
                            requests += Integer.parseInt(cookie.getValue());
                            break;
                        case "visit":
                            visits = Integer.parseInt(cookie.getValue());
                            break;
                        default:
                            break;
                    }
                }
            }
            out.println("<h1>Number of past encode requests: " + requests + "<br></br>" 
                    + "Number of past errors: " + errorsNumber + "<br></br>"
                    + "Number of visits to history page: " + visits + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
