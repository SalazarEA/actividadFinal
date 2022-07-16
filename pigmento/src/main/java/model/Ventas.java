package model;

public class Ventas {

    private int idproductos;
    private int codigo;
    private String descripcion;
    private String tipo;
    private int cantidad;
    private double precio;

    public Ventas(int idproductos, int codigo, String descripcion, String tipo, int cantidad, double precio) {
        this.idproductos = idproductos;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Ventas(int codigo, String descripcion, String tipo, int cantidad, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getIdproductos() {
        return idproductos;
    }

    public void setIdproductos(int idproductos) {
        this.idproductos = idproductos;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
    
    @Override
    public String toString() {
        return "Productos{" + "codigo=" + codigo + ", descripcion=" + descripcion + ", tipo=" + tipo + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }
}
