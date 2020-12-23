package com.vobi.demo.reactive.service;

import com.vobi.demo.reactive.domain.Producto;
import com.vobi.demo.reactive.respository.ProductoRepository;

import java.time.Duration;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoService {
	
    private static final int DELAY_PER_ITEM_MS = 1;

    private final ProductoRepository productoRepositorio;


    public ProductoService(ProductoRepository productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    public Flux<Producto> findAll() {
        //return productoRepositorio.findAll().delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
        return productoRepositorio.findAll();
    }

    public Mono<Producto> findById(Long id) {
        return productoRepositorio.findById(id);
    }

    public Mono<Producto> save(Producto producto) {
        return productoRepositorio.save(producto);
    }
 
    public Mono<Void> deleteById(Long id){
        return this.productoRepositorio.findById(id)
                .flatMap(this.productoRepositorio::delete);
    }
    
}
