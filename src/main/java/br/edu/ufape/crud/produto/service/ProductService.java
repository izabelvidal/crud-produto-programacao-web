package br.edu.ufape.crud.produto.service;

import br.edu.ufape.crud.produto.model.domain.ProductDomain;
import br.edu.ufape.crud.produto.model.dto.ProductDTO;
import br.edu.ufape.crud.produto.repositories.ProductRepository;
import br.edu.ufape.crud.produto.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDomain> findAll() {
        return productRepository.findAll();
    }

    public ProductDomain insert(ProductDomain obj) {
        obj.setCode(null);
        obj = productRepository.save(obj);
        return obj;
    }

    public ProductDomain find(Long id) {
        Optional<ProductDomain> obj = productRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + ProductDomain.class.getName()));
    }

    public ProductDomain fromDTO(ProductDTO objDto){
        ProductDomain obj = new ProductDomain(objDto.getCode(), objDto.getName(),
                objDto.getDescription(), objDto.getPrice());
        return obj;
    }

    public ProductDomain update(ProductDomain obj) {
        ProductDomain newObj = find(obj.getCode());
        updateData(newObj, obj);
        return productRepository.save(newObj);
    }

    public void delete(Long code) {
        if(productRepository.existsById(code)) {
            productRepository.deleteById(code);
        }else {
            throw new ObjectNotFoundException("Produto não encontrado");
        }
    }

    public void updateData(ProductDomain newObj, ProductDomain obj) {
        newObj.setName(obj.getName());
        newObj.setDescription(obj.getDescription());
        newObj.setPrice(obj.getPrice());
    }
}
