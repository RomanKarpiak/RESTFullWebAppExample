package com.roman.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "product")
@JsonIgnoreProperties(value= {"carts"})
public class Product extends StoreItem {

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "productId", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<ProductPhoto> productPhotos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "products", cascade = CascadeType.ALL)
    private Set<Cart> carts = new HashSet<>();

    public void addCart(Cart cart) {
        boolean added = this.carts.add(cart);
        if (added) {
            cart.getProducts().add(this);
        }
    }

    public void removeCart(Cart cart) {
        boolean removed = this.carts.remove(cart);
        if (removed) {
            cart.getProducts().remove(this);
        }
    }


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
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return price == product.price && Objects.equals(description, product.description) && Objects.equals(productPhotos, product.productPhotos) && Objects.equals(carts, product.carts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, price, productPhotos, carts);
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
