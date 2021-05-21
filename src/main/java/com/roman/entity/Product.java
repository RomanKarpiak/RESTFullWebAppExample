package com.roman.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.roman.views.Views;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "product")
public class Product extends StoreItem {

    @Column(name = "description")
    @JsonView(Views.Private.class)
    private String description;

    @Column(name = "price")
    @JsonView(Views.Private.class)
    private int price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "productId", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonView(Views.Private.class)
    private List<ProductPhoto> productPhotos = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    private Set<Cart> carts = new HashSet<>();

    public Product() {
        super();
    }

    public Product(String description, int price) {
        super();
        this.description = description;
        this.price = price;
    }

    public void addProductPhoto(ProductPhoto productPhoto) {
        productPhoto.setProductId(this);
        productPhotos.add(productPhoto);
    }

    public void removeProductPhoto(ProductPhoto productPhoto) {
        productPhotos.remove(productPhoto);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<ProductPhoto> getProductPhotos() {
        return productPhotos;
    }

    public void setProductPhotos(List<ProductPhoto> productPhotos) {
        this.productPhotos = productPhotos;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && Objects.equals(description, product.description) && Objects.equals(productPhotos, product.productPhotos) && Objects.equals(carts, product.carts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, price, productPhotos, carts);
    }

    @Override
    public String toString() {
        return "Product{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", productPhotos=" + productPhotos +
                ", carts=" + carts +
                '}';
    }
}
