package ru.playtox.shop.facade;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.playtox.shop.entity.Product;
import ru.playtox.shop.entity.Purchase;
import ru.playtox.shop.entity.User;
import ru.playtox.shop.service.ProductService;
import ru.playtox.shop.service.PurchaseService;
import ru.playtox.shop.service.UserService;

import java.time.LocalDate;

@Component
@Transactional
@RequiredArgsConstructor
public class PurchaseFacade {
    private final UserService userService;
    private final ProductService productService;
    private final PurchaseService purchaseService;

    public Purchase makePurchase(Long productId, Long userId, int quantity) {
        Product product = productService.getProductById(productId);
        User user = userService.getUserById(userId);
        if (product.getQuantityInStock() < quantity) {
            throw new IllegalArgumentException("Not enough quantity in stock for a product with id: " + productId);
        }

        int updatedQuantity = product.getQuantityInStock() - quantity;
        product.setQuantityInStock(updatedQuantity);
        productService.updateProduct(productId, product);

        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setProduct(product);
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setPurchasePrice(product.getPrice());
        purchase.setNumberOfProductUnits(quantity);
        return purchaseService.savePurchase(purchase);
    }
}
