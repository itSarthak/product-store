package com.veldtclix.productStore.service;
import com.veldtclix.productStore.dto.ProductDto;
import java.util.List;

public interface ProductService {
    /**
 * Creates a new product using the provided product data.
 *
 * @param productDto the data for the product to be created
 * @return the created product with its assigned properties
 */
ProductDto createProduct(ProductDto productDto);

    /****
 * Retrieves a list of all products.
 *
 * @return a list containing all product data transfer objects
 */
List<ProductDto> getAllProducts();

    /****
 * Retrieves a product by its unique identifier.
 *
 * @param id the unique identifier of the product
 * @return the product corresponding to the given id, or null if not found
 */
ProductDto getProductById(String id);

    /****
 * Updates an existing product with new data.
 *
 * @param id the unique identifier of the product to update
 * @param productDto the product data to update
 * @return the updated product data
 */
ProductDto updateProduct(String id, ProductDto productDto);

    /****
 * Deletes the product identified by the given ID.
 *
 * @param id the unique identifier of the product to delete
 */
void deleteProduct(String id);
}
