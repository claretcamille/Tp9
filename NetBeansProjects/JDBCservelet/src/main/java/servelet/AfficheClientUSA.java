/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelet;

import DaoExtend.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.CustomerEntity;
import simplejdbc.DataSourceFactory;


/**
 *
 * @author camilleclaret
 */
public class AfficheClientUSA extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AfficheClient</title>");            
            out.println("</head>");
            out.println("<body>");
            try {
                // Trouve la valeur 
                String state = request.getParameter("STATE");
                // Création de la dao
               Dao dao = new Dao(DataSourceFactory.getDataSource());
               // Récupération de la liste des clients
               List<CustomerEntity> customers = dao.customersInState(state);
               
               // En tête de la table
               out.println("<table border=4>");// Bordure de la table
               out.println("<tr> <th>Id</th> <th>Name</th> <th>Address</th> </tr>");// ajout des colonnes et leurs noms
               for(int i = 0; i<customers.size(); i++){
                   // Parcourt de la liste
                   out.printf("<tr> <td>%d</td> <td>%s</td> <td>%s</td> </tr>%n", 
                           customers.get(i).getCustomerId(),
                           customers.get(i).getName(),
                           customers.get(i).getAddressLine1()); // Ajout des valeurs
               }
               out.println("</table>"); // Fermeture du tableau
                
                
            }catch (Exception e) {
                out.printf("Erreur : %s", e.getMessage());
            }
            out.printf("<hr><a href='%s'>Retour au menu</a>",request.getContextPath());
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
