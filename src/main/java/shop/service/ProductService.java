package shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import shop.model.Product;
import shop.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


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

    public Page<Product> getAllProductsByPage(String name, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        if (name != null && !name.trim().isEmpty()) {
            return productRepository.findByNameContainingIgnoreCase(name, pageable);
        } else {
            return productRepository.findAll(pageable);
        }
    }
}

