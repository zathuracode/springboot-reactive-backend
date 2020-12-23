package com.vobi.demo.reactive.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vobi.demo.reactive.domain.Producto;
import com.vobi.demo.reactive.service.ProductoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/productos")
@CrossOrigin("*")
public class ProductoRestController {

    private final ProductoService productoServicio;

    public ProductoRestController(ProductoService productoServicio) {
        this.productoServicio = productoServicio;
    }

    @GetMapping()
    public Flux<Producto> list() {
        return productoServicio.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Producto> findById(@PathVariable Long id) {
        return productoServicio.findById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Producto> create(@RequestBody Producto producto) {
        return productoServicio.save(producto);		
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Producto>> update(@PathVariable Long id,
                                                 @RequestBody Producto producto) {
        return productoServicio.findById(id)
                .flatMap(existingProduct -> {
                    existingProduct.setNombre(producto.getNombre());
                    existingProduct.setDescripcion(producto.getDescripcion());
                    existingProduct.setPrecio(producto.getPrecio());
                    return productoServicio.save(existingProduct);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable Long id){
        return productoServicio.deleteById(id);
    }
    
}
