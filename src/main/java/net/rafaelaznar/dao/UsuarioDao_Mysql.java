package net.rafaelaznar.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.rafaelaznar.data.MysqlData;
import net.rafaelaznar.helper.Conexion;
import net.rafaelaznar.helper.FilterBean;
import net.rafaelaznar.bean.UsuarioBean;

public class UsuarioDao_Mysql implements UsuarioDao {

    private final MysqlData oMysql;
    private final Conexion.Tipo_conexion enumTipoConexion;

    public UsuarioDao_Mysql(Conexion.Tipo_conexion tipoConexion) throws Exception {
        oMysql = new MysqlData();
        enumTipoConexion = tipoConexion;
    }

    @Override
    public int getPages(int intRegsPerPag, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("usuario", intRegsPerPag, hmFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("UsuarioDao.getPages: Error: " + e.getMessage());
        }
    }

    @Override
    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("usuario", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("UsuarioDao.getCount: Error: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<UsuarioBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<UsuarioBean> arrUsuario = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("usuario", intRegsPerPag, intPage, hmFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                UsuarioBean oUsuarioBean = new UsuarioBean(iterador.next());
                arrUsuario.add(this.get(oUsuarioBean));
            }
            oMysql.desconexion();
            return arrUsuario;
        } catch (Exception e) {
            throw new Exception("UsuarioDao.getPage: Error: " + e.getMessage());
        }
    }

    @Override
    public UsuarioBean get(UsuarioBean oUsuarioBean) throws Exception {
        if (oUsuarioBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("usuario", oUsuarioBean.getId())) {
                    oUsuarioBean.setId(0);
                } else {
                    oUsuarioBean.setNombre(oMysql.getOne("usuario", "nombre", oUsuarioBean.getId()));
                    oUsuarioBean.setLogin(oMysql.getOne("usuario", "login", oUsuarioBean.getId()));
                    oUsuarioBean.setPassword(oMysql.getOne("usuario", "password", oUsuarioBean.getId()));
                    oUsuarioBean.setEmail(oMysql.getOne("usuario", "email", oUsuarioBean.getId()));
                }
            } catch (Exception e) {
                throw new Exception("UsuarioDao.getUsuario: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oUsuarioBean.setId(0);
        }
        return oUsuarioBean;
    }

    @Override
    public UsuarioBean set(UsuarioBean oUsuarioBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oUsuarioBean.getId() == 0) {
                oUsuarioBean.setId(oMysql.insertOne("usuario"));
            }
            oMysql.updateOne(oUsuarioBean.getId(), "usuario", "nombre", oUsuarioBean.getNombre());
            oMysql.updateOne(oUsuarioBean.getId(), "usuario", "login", oUsuarioBean.getLogin());
            oMysql.updateOne(oUsuarioBean.getId(), "usuario", "password", oUsuarioBean.getPassword());
            oMysql.updateOne(oUsuarioBean.getId(), "usuario", "email", oUsuarioBean.getEmail());
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("UsuarioDao.setUsuario: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oUsuarioBean;
    }

    @Override
    public void remove(UsuarioBean oUsuarioBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oUsuarioBean.getId(), "usuario");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("UsuarioDao.removeUsuario: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public UsuarioBean getFromLogin(UsuarioBean oUsuario) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            String strId = oMysql.getId("usuario", "login", oUsuario.getLogin());
            if (strId == null) {
                oUsuario.setId(0);
            } else {
                oUsuario.setId(Integer.parseInt(strId));
                oUsuario.setPassword(oMysql.getOne("usuario", "password", oUsuario.getId()));
                oUsuario.setNombre(oMysql.getOne("usuario", "nombre", oUsuario.getId()));
                oUsuario.setEmail(oMysql.getOne("usuario", "email", oUsuario.getId()));
            }
            oMysql.desconexion();
            return oUsuario;
        } catch (Exception e) {
            throw new Exception("UsuarioDao.getFromLogin: Error: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<String> getColumnsNames() throws Exception {
        ArrayList<String> alColumns = null;
        try {
            oMysql.conexion(enumTipoConexion);
            alColumns = oMysql.getColumnsName("usuario", Conexion.getDatabaseName());
            oMysql.desconexion();

        } catch (Exception e) {
            throw new Exception("UsuarioDao.getColumnsNames: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return alColumns;
    }

}