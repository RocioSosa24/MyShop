package com.myshop.MyShop.Model.Entity.Repository;

import com.myshop.MyShop.Model.Entity.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRepository extends JpaRepository<TipoProducto, Integer> {
}
