/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rafaelaznar.operaciones;

/**
 *
 * @author rafa
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rafaelaznar.bean.CompraBean;
import net.rafaelaznar.dao.CompraDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.rafaelaznar.helper.Conexion;

public class CompraGet implements GenericOperation {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String data;         
        try {            
            if (request.getParameter("id") == null) {
                data = "{\"error\":\"id is mandatory\"}";
            } else {
                CompraDao oCompraDAO = new CompraDao(Conexion.getConection());
                CompraBean oCompra = new CompraBean();
                oCompra.setId(Integer.parseInt(request.getParameter("id")));
                oCompraDAO.get(oCompra);                                
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setDateFormat("dd/MM/yyyy");
                Gson gson = gsonBuilder.create();
                data = gson.toJson(oCompra);                
                //data = new Gson().toJson(oCompra);
            }
            return data;
        } catch (Exception e) {
            throw new ServletException("CompraGetJson: View Error: " + e.getMessage());
        }
    }
}