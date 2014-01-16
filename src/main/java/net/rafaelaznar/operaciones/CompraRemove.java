/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rafaelaznar.operaciones;

import com.google.gson.Gson;
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
public class CompraRemove implements GenericOperation {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            CompraDao oCompraDAO = new CompraDao(Conexion.getConection());
            CompraBean oCompra = new CompraBean();                                           
            oCompra.setId(Integer.parseInt(request.getParameter("id")));            
            Map<String, String> data = new HashMap<>();
            if (oCompra != null) {
                oCompraDAO.remove(oCompra);
                data.put("status", "200");
                data.put("message", "se ha eliminado el registro con id=" + oCompra.getId());
            } else {
                data.put("status", "error");
                data.put("message", "error");
            }
            Gson gson = new Gson();
            String resultado = gson.toJson(data);
            return resultado;        
        } catch (Exception e) {
            throw new ServletException("CompraRemoveJson: View Error: " + e.getMessage());
        }
    }
}
