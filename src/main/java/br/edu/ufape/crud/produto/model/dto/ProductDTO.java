package br.edu.ufape.crud.produto.model.dto;

import br.edu.ufape.crud.produto.model.domain.ProductDomain;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class ProductDTO {
    private static final long serialVersionUID = 1L;

    private Long code;

    @NotBlank(message = "Nome não pode ser vazio")
    @Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres!")
    private String name;

    @NotBlank(message = "Nome não pode ser vazio")
    @Length(min=20, max=500, message="O tamanho deve ser entre 20 e 500 caracteres!")
    private String description;

    @NotEmpty(message="Preenchimento Obrigatório!")
    private Float price;

    public ProductDTO(){}

    public ProductDTO(ProductDomain obj){
        this.code = obj.getCode();
        this.name = obj.getName();
        this.description = obj.getDescription();
        this.price = obj.getPrice();
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
