package com.tienda.dao;

import com.tienda.domain.Rol;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RolDao extends JpaRepository<Rol, Long> {
    
    @Query("SELECT r FROM Rol r WHERE r.idUsuario = :idUsuario")
    List<Rol> findByIdUsuario(@Param("idUsuario") Long idUsuario);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Rol r WHERE r.idUsuario = :idUsuario")
    void deleteByIdUsuario(@Param("idUsuario") Long idUsuario);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Rol r WHERE r.idUsuario = :idUsuario AND r.nombre = :nombreRol")
    void eliminarPorIdUsuarioYRol(@Param("idUsuario") Long idUsuario, @Param("nombreRol") String nombreRol);
}