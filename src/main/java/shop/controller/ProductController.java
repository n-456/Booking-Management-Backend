package shop.controller;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.model.Product;
import shop.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getAllProduct(@PathVariable int id) {
        Product product = productService.getProductById(id);

        if(product != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(product);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Page<Product> productPage = productService.getAllProductsByPage(name, page, size, sortBy, sortDir);
        return ResponseEntity.ok(productPage);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product request) {
        Product savedProduct = productService.addProduct(
                request.getName(),
                request.getCategory(),
                request.getUnitPrice(),
                request.getCost(),
                request.isDiscontinued(),
                request.getStock(),
                request.getImg()
        );

        if (savedProduct != null) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(savedProduct);
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product request) {
        Product updatedProduct = productService.updateProduct(
                id,
                request.getName(),
                request.getCategory(),
                request.getUnitPrice(),
                request.getCost(),
                request.isDiscontinued(),
                request.getStock(),
                request.getImg()
        );

        if (updatedProduct != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(updatedProduct);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        boolean isDeleted = productService.deleteProduct(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}