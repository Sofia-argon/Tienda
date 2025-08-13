package com.tienda.dao;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoDao extends JpaRepository <Producto,Long> {
    //Ejemplo de una consulta ampliada
    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);
    
    //Ejemplo de una consulta ampliada utilizando JPQL
    @Query(value="SELECT a FROM Producto WHERE a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.descripcion ASC")
    public List<Producto> metodoJPQL(double precioInf, double precioSup);
    
    //Ejemplo de una consulta ampliada utilizando SQL nativo
    @Query(nativeQuery=true,value="SELECT * FROM producto WHERE a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.descripcion ASC")
    public List<Producto> metodoSQL(double precioInf, double precioSup);
    
}