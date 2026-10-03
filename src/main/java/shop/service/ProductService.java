package shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.model.Product;
import shop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(String name, String category, int unitPrice, int cost, boolean discontinued, int stock, String img) {
        Product product = new Product(name, category, unitPrice, cost, discontinued, stock, img);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, String name, String category, int unitPrice, int cost, boolean discontinued, int stock, String img) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(name);
            product.setCategory(category);
            product.setUnitPrice(unitPrice);
            product.setCost(cost);
            product.setDiscontinued(discontinued);
            product.setStock(stock);
            product.setImg(img);
            return productRepository.save(product);
        }
        return null;
    }

    public boolean deleteProduct(long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}