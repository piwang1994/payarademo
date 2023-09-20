/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.a20478885.web;

import edu.iit.sat.itmd4515.a20478885.model.Customer;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sas
 */
@WebServlet(name = "CustomerServlet", urlPatterns = {"/cust", "/c", "/customer"})
public class CustomerServlet extends HttpServlet {

    @Resource
    Validator validator;
    
    @Resource(name = "java:app/jdbc/itmd4515DS")
    DataSource ds;
    
    private static final Logger LOG = Logger.getLogger(CustomerServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOG.info("CustomerServlet.doGet - Redirecting user to Create A Customer page");
        resp.sendRedirect(req.getContextPath() + "/customer.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // this is like a debug message - only generates output in non-prod env
        LOG.fine("This is a FINE message");
        // info is common
        LOG.info("This is an INFO message");
        // indicates an awful Thing
        LOG.severe("This is a SEVERE message");

        LOG.info("CustomerServlet.doPost - Getting parameters of Form in my servlet");
        String custIdParameter = req.getParameter("custId");
        String custFirstNameParameter = req.getParameter("firstName");
        String custLastNameParameter = req.getParameter("lastName");
        String custEmailParameter = req.getParameter("email");
        String custAddressIDParameter = req.getParameter("addressId");
        String custActiveParameter = req.getParameter("activeInd");
        String custStoreIDParameter = req.getParameter("storeId");
        String custExampleDateParameter = req.getParameter("dateExample");

        LOG.info("custIdParameter\t" + custIdParameter);
        LOG.info("custFirstNameParameter\t" + custFirstNameParameter);
        LOG.info("custLastNameParameter\t" + custLastNameParameter);
        LOG.info("custEmailParameter\t" + custEmailParameter);
        LOG.info("custAddressIDParameter\t" + custAddressIDParameter);
        LOG.info("custActiveParameter\t" + custActiveParameter);
        LOG.info("custStoreIDParameter\t" + custStoreIDParameter);
        LOG.info("custExampleDate\t" + custExampleDateParameter);

        // check if the field even has a value, and try to convert it to the appropriate type
        // we still have issues to address if not a valid number format in this case
        // how might you check for that?
        Integer custId = null;
        if (custIdParameter != null && !custIdParameter.isBlank()) {
            custId = Integer.valueOf(custIdParameter);
        }
        Integer addrId = null;
        if (custAddressIDParameter != null && !custAddressIDParameter.isBlank()) {
            addrId = Integer.valueOf(custAddressIDParameter);
        }
        Integer storeId = null;
        if (custStoreIDParameter != null && !custStoreIDParameter.isBlank()) {
            storeId = Integer.valueOf(custStoreIDParameter);
        }
        LocalDate exampleDate = null;
        if (custExampleDateParameter != null && !custExampleDateParameter.isBlank()) {
            exampleDate = LocalDate.parse(custExampleDateParameter);
        }
        Boolean activeInd = false;
        if (custActiveParameter != null && !custActiveParameter.isBlank()) {
            if (custActiveParameter.equalsIgnoreCase("active")) {
                activeInd = true;
            }
        }

        // the goal is to BUILD a customer, so that we can validate it
        Customer c = new Customer(custId, 
                storeId, 
                custFirstNameParameter, 
                custLastNameParameter, 
                custEmailParameter, 
                addrId, 
                activeInd, 
                exampleDate);
        
        LOG.info(c.toString());
        
        Set<ConstraintViolation<Customer>> violations = validator.validate(c);
        
        if(violations.size() > 0){
            // if we have violations, the customer failed validation
            for (ConstraintViolation<Customer> v : violations) {
                LOG.info(v.toString());
            }
            
            req.setAttribute("violations", violations);
            req.setAttribute("customer", c);
            RequestDispatcher rd = req.getRequestDispatcher("customer.jsp");
            rd.forward(req, resp);
        } else {
            // we do not have any violations, therefore the customer passed validation
            
            // grad students will need to create a customer here
            createACustomer(c);
            
            req.setAttribute("customer", c);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/confirmation.jsp");
            rd.forward(req, resp);
            
        }
    }
    
    private void createACustomer(Customer c)  {
        String insertCustomer = "insert into customer "
                + "(store_id, first_name, last_name, email, "
                + "address_id, active, create_date) "
                + "values (?,?,?,?,?,?,?)";

        try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(insertCustomer)) {

//            ps.setInt(1, c.getId());
            ps.setInt(1, c.getStoreId());
            ps.setString(2, c.getFirstName());
            ps.setString(3, c.getLastName());
            ps.setString(4, c.getEmail());
            ps.setInt(5, c.getAddressId());
            ps.setBoolean(6, c.isActive());
            ps.setObject(7, c.getCreateDate());

            ps.executeUpdate();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    

}
