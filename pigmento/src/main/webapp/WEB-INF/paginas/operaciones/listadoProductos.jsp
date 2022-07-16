<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="es_AR"/>
<section id="productos">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Productos</h4>
                    </div>
                    <table class="table table-striped table-dark">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Codigo</th>
                                <th>Descripcion</th>
                                <th>Tipo</th>
                                <th>Cantidad</th>
                                <th>Precio</th>
                                <th>Accion</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="productos" items="${productos}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${productos.codigo}</td>
                                    <td>${productos.descripcion}</td>
                                    <td>${productos.tipo}</td>
                                    <td>${productos.cantidad}</td>
                                    <td><fmt:formatNumber value="${productos.precio}" type="currency"/></td>
                                    
                                    <td>
                                        <a href="${pageContext.request.contextPath}/servletControlador?accion=editar&idProductos=${productos.idproductos}" class="btn btn-secondary">
                                            <i class="fas fa-angle-double-right"></i>
                                            Editar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center bg-danger text-white mb-3">
                    <div class="card-body">
                        <h3>Cantidad</h3>
                        <h4 class="display-4">${cantidad}</h4>
                    </div>
                </div>

                <div class="card text-center bg-success text-white mb-3">
                    <div class="card-body">
                        <h3>Precio Total</h3>
                        <h4 class="display-4"><fmt:formatNumber value="${precioTotal}" type="currency"/></h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="/WEB-INF/paginas/operaciones/agregarProductos.jsp"/>