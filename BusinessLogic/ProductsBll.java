package BusinessLogic;

import java.util.List;
import java.util.NoSuchElementException;
import DataAccess.ProductsDAO;
import Model.Products;
public class ProductsBll {
    private ProductsDAO product1;

    public ProductsBll(){
        this.product1=new ProductsDAO();
    }

    public Products findClientById(int id) {
        Products p = product1.findById(id);
        if(p == null){
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return p;
    }

    public Products findProductByName(String name) {
        Products p = product1.findByName(name);
        if(p == null){
            throw new NoSuchElementException("The product with id =" + name + " was not found!");
        }
        return p;
    }

    public double getProductPriceByName(String productName) {
        Products product = product1.findByName(productName);
        if (product != null) {
            return product.getPrice();
        } else {
            throw new NoSuchElementException("Product with name " + productName + " was not found!");
        }
    }


    public void insert(Products p){
        product1.insert(p);
    }

    public void update(Products p){
        product1.update(p);
    }

    public void delete(Products p){
        product1.delete(p);
    }

    public List<Products> selectall(){
        return product1.findAll();
    }
}
