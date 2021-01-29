package kamil.szymoniak.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;


import kamil.szymoniak.controller.CaesarMode;
import kamil.szymoniak.model.IllegalInputException;
import kamil.szymoniak.model.IllegalMessageFormException;
import kamil.szymoniak.model.CipherModel;
import kamil.szymoniak.view.HistoryTable;


/**
 * Servlet interprets input data and sends it to be encoded
 * 
 * @author Kamil Szymoniak
 * @version 4.0
 */
public class CipherServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * Data is being sent received and displayed as an encoded message.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();
        
        try{            
                        
            CaesarMode mode = CaesarMode.getModeFromArgs(request.getParameter("mode"));              
        
            if(request.getParameter("key").matches("([+-]?[1-9]\\d*|0)$")){
                int key = Integer.parseInt(request.getParameter("key"));
                key = key < 0 ?
                        CaesarMode.ENCODING.equals(mode) ? 26 + key % 26 : - key :
                        CaesarMode.DECODING.equals(mode) ? 26 - key % 26 :   key
                ;
        
                CipherModel model = new CipherModel();
                String[] inputs = new String[]{request.getParameter("mode"), request.getParameter("key"), request.getParameter("message")};
                List<String> inputsList = new ArrayList<>(Arrays.asList(inputs));
                String translation = model.translate(inputsList, key);
                out.println("<html>\n<body>\n<h1>" + translation +
                    "</h1>\n</body>\n</html>"
                );  
                String number = "0";
                Cookie[] cookies = request.getCookies();
                if(cookies != null)
                    for(Cookie cookie : cookies){
                        if(cookie.getName().equals("success"))
                            number = cookie.getValue();
                    }
                Cookie cookie = new Cookie("success", Integer.toString(Integer.parseInt(number) + 1));
                response.addCookie(cookie);
                HistoryTable lastHistoryTable = (HistoryTable) request.getSession().getAttribute("History");
                if(lastHistoryTable != null){
                    lastHistoryTable.addRow(inputs[0], inputs[1], inputs[2], translation);
                }
                else{                    
                    lastHistoryTable = new HistoryTable(inputs[0], inputs[1], inputs[2], translation);
                }
                request.getSession().setAttribute("History", lastHistoryTable);
            }
            else
                throw new IllegalInputException("Key must be of integral type");
        } 
        catch (IllegalInputException ex) {
            String number = "0";
            Cookie[] cookies = request.getCookies();
            if(cookies != null)
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("error"))
                        number = cookie.getValue();
                }
            Cookie cookie = new Cookie("error", Integer.toString(Integer.parseInt(number) + 1));
            response.addCookie(cookie);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        } 
        catch (IllegalMessageFormException ex) {
            String number = "0";
            Cookie[] cookies = request.getCookies();
            if(cookies != null)
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("error"))
                        number = cookie.getValue();
                }
            Cookie cookie = new Cookie("error", Integer.toString(Integer.parseInt(number) + 1));
            response.addCookie(cookie);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error in the input message, it can only contain letters and spaces!");       
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
