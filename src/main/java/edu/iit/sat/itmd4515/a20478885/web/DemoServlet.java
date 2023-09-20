/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.iit.sat.itmd4515.a20478885.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 *
 * @author sas
 */
@WebServlet(name = "DemoServlet", urlPatterns = {"/demo", "/d"})
public class DemoServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(DemoServlet.class.getName());

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
        
        PrintWriter out = response.getWriter();
        
        String demoParam = request.getParameter("param1");
        
        out.println("Hello Class from DemoServlet with param1=" + demoParam);
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
        
        String param1 = request.getParameter("param1");
        String param2 = request.getParameter("param2");
        String param3 = request.getParameter("param3");
        
        // "" if the user input is blank on the HTML form
        // this is NOT the same as null
        
        
        LOG.info("DemoServlet doPost method with param1=" + param1 );
        LOG.info("DemoServlet doPost method with param2=" + param2 );
        LOG.info("DemoServlet doPost method with param3=" + param3 );
        // we could easily be doing app logic here someMethod( param1, param2, param3);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
