package br.edu.ufape.crud.produto.controller;

import br.edu.ufape.crud.produto.model.domain.ProductDomain;
import br.edu.ufape.crud.produto.model.dto.ProductDTO;
import br.edu.ufape.crud.produto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDomain>> findAll(){
        List<ProductDomain> obj = productService.findAll();
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDomain> findByid(@PathVariable Long id){
        return ResponseEntity.ok().body(productService.find(id));
    }

    @PostMapping("/insert")
    public ResponseEntity<Void> insert(@RequestBody ProductDTO objDto){
        ProductDomain obj = productService.fromDTO(objDto);
        obj = productService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getCode()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<Void> update(@RequestBody ProductDTO objDto, @PathVariable Long code){
        ProductDomain obj = productService.fromDTO(objDto);
        obj.setCode(code);
        obj = productService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable Long code){
        productService.delete(code);
        return ResponseEntity.noContent().build();
    }
}
