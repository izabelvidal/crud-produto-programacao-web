package br.edu.ufape.crud.produto.repositories;

import br.edu.ufape.crud.produto.model.domain.ProductDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductDomain, Long> {
}
