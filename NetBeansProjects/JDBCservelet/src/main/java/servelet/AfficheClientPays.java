/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelet;

import DaoExtend.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AfficheClientPays extends HttpServlet {

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
            out.println("<title>Servlet AfficheClientPays</title>");            
            out.println("</head>");
            out.println("<body>");
            try {
                
               
                // Création de la dao
               Dao dao = new Dao(DataSourceFactory.getDataSource());
               //Recupération de la liste des etats
               ArrayList<String> stateList = dao.listePays();
               // Trouve la valeur 
                String state = request.getParameter("STATE");
                // si aucun parametre on selectione par defaut le premier etat
                if(state == null){
                    state = stateList.get(0);
                }
                List<CustomerEntity> customers = dao.customersInState(state);
                // Formulaire Création
                out.println("<form>");
                //selection de la valeur qui change
                out.println("<select name='STATE' onchange='this.form.submit()'>");
                
                for(int i=0;i< stateList.size();i++){
                    //Incrémentation des choix
                    out.printf("<option value='%s' %s>%s</option>%n",
                            stateList.get(i),
                            stateList.get(i).equals(state) ? "selected" : "",
                            stateList.get(i));
                }
                
                out.println("</select>");
                out.println("<input type='submit'>");
                
                out.println("</form>");
                // Fin formulaire
               
        
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
        } catch (Exception ex) {
            Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
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
