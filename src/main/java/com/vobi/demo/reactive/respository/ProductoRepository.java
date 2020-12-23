package com.vobi.demo.reactive.respository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.vobi.demo.reactive.domain.Producto;

@Repository
public interface ProductoRepository extends ReactiveCrudRepository<Producto, Long> {

}
