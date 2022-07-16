package web;

import data.VentasDAO;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.*;

@WebServlet("/servletControlador")
public class ServletControlador extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        String accion = req.getParameter("accion");
        
        if(accion!=null){
            switch(accion){
                case "eliminar":
                    eliminarProducto(req,res);
                    break;
                case "editar":
                    editarProducto(req, res);
                    break;
                default:
                    accionDefault(req, res);
                    break;
            }
        }else{
            accionDefault(req, res);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse res)throws ServletException, IOException{
        String queryParam = req.getParameter("accion");
        if(queryParam!=null){
            switch(queryParam){
                case "insertar":
                    insertarProductos(req,res);
                    break;
                case "modificar":
                    modificarProducto(req,res);
                    break;
                default:
                    accionDefault(req,res);
                    break;
            }
        }
    }
    
    private void accionDefault(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        List <Ventas> productos = new VentasDAO().findAll();
        productos.forEach(System.out::println);
        HttpSession sesion = req.getSession();
        sesion.setAttribute("productos", productos);
        sesion.setAttribute("cantidad",calcular(productos));
        sesion.setAttribute("precioTotal", calcularPrecio(productos));
//        req.getRequestDispatcher("libros.jsp").forward(req, res);
        res.sendRedirect("productos.jsp");
    }
    
    private void insertarProductos(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
       int codigo= Integer.parseInt(req.getParameter("codigo"));
        String descripcion = req.getParameter("descripcion");
        String tipo = req.getParameter("tipo");
        int cantidad = Integer.parseInt(req.getParameter("cantidad"));
        double precio = Double.parseDouble(req.getParameter("precio"));
        
        Ventas producto = new Ventas(codigo, descripcion, tipo, cantidad,precio);
        
        int registrosMod = new VentasDAO().insert(producto);
        
        System.out.println("insertados = " + registrosMod);
        
        accionDefault(req, res);
    }
    
    private void eliminarProducto(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        int idproductos = Integer.parseInt(req.getParameter("idProductos"));
        
        int regMod = new VentasDAO().deleteProducto(idproductos);
        
        System.out.println("SE ELIMINARON: "+ regMod +" REGISTROS");
        
        accionDefault(req, res);
    }
    
    private void editarProducto(HttpServletRequest req , HttpServletResponse res) throws ServletException, IOException{
        int idProductos = Integer.parseInt(req.getParameter("idproductos"));
        Ventas producto = new VentasDAO().findById(idProductos);
        req.setAttribute("producto", producto);
        String jspEditar = "/WEB-INF/paginas/operaciones/editar.jsp";
        req.getRequestDispatcher(jspEditar).forward(req, res);
    }
    
    private void modificarProducto(HttpServletRequest req , HttpServletResponse res)throws ServletException, IOException{
          int codigo = Integer.parseInt(req.getParameter("codigo"));
        String descripcion = req.getParameter("descripcion");
        String tipo = req.getParameter("tipo");
        int cantidad = Integer.parseInt(req.getParameter("cantidad"));
        double precio = Double.parseDouble(req.getParameter("precio"));
        
        int idProductos = Integer.parseInt(req.getParameter("idproductos"));
        
        Ventas pro = new Ventas(idProductos,codigo,descripcion,tipo,cantidad, precio);
        
        int regMod = new VentasDAO().update(pro);
        
        System.out.println("SE ACTUALIZARON: "+ regMod +" REGISTROS");
        
        accionDefault(req, res);
    }
    
    private int calcular(List<Ventas> pro){
        int cant=0;
        for (int i = 0; i < pro.size(); i++) {
            cant += pro.get(i).getCantidad();
        }
        return cant;
    }
    
    private double calcularPrecio(List<Ventas> pro){
        double precio = 0;
        for (int i = 0; i < pro.size(); i++) {
            precio += (pro.get(i).getPrecio() * pro.get(i).getCantidad());
        }
        return precio;
    }
}
