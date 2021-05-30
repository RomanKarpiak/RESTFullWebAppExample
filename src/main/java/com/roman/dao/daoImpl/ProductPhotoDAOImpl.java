package com.roman.dao.daoImpl;

import com.roman.dao.CrudDAO;
import com.roman.entity.ProductPhoto;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class ProductPhotoDAOImpl implements CrudDAO<ProductPhoto, Long> {

    private final SessionFactory factory;

    @Autowired
    public ProductPhotoDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(ProductPhoto productPhoto) {
        factory.getCurrentSession().save(productPhoto);
    }

    @Override
    public ProductPhoto findById(Long id) {
        ProductPhoto productPhoto = factory.getCurrentSession().get(ProductPhoto.class, id);
        return productPhoto != null ? productPhoto : new ProductPhoto();
    }

    @Override
    public void update(ProductPhoto productPhoto) {
        factory.getCurrentSession().update(productPhoto);
    }

    @Override
    public void delete(ProductPhoto productPhoto) {
        factory.getCurrentSession().delete(productPhoto);
    }

    @Override
    public List<ProductPhoto> findAll() {
        List<ProductPhoto> productPhotoList = factory.getCurrentSession().createQuery("select pp from ProductPhoto pp", ProductPhoto.class).list();
        if (!productPhotoList.isEmpty()) {
            return productPhotoList;
        } else {
            return Collections.emptyList();
        }

    }
}
