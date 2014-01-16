/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rafaelaznar.operaciones;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.rafaelaznar.dao.CompraDao;
import net.rafaelaznar.helper.Conexion;
import net.rafaelaznar.helper.EncodingUtil;
import net.rafaelaznar.bean.CompraBean;

/**
 *
 * @author rafa
 */
public class CompraSave implements GenericOperation {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            CompraDao oCompraDAO = new CompraDao(Conexion.getConection());
            CompraBean oCompra = new CompraBean();            
            Gson gson=  new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
            String jason = request.getParameter("json");
            jason = EncodingUtil.decodeURIComponent(jason);
            oCompra = gson.fromJson(jason, oCompra.getClass());
            Map<String, String> data = new HashMap<>();
            if (oCompra != null) {
                oCompra = oCompraDAO.set(oCompra);
                data.put("status", "200");
                data.put("message", Integer.toString(oCompra.getId()));
            } else {
                data.put("status", "error");
                data.put("message", "error");
            }
            String resultado = gson.toJson(data);
            return resultado;
        } catch (Exception e) {
            throw new ServletException("CompraSaveJson: View Error: " + e.getMessage());
        }
    }
}
