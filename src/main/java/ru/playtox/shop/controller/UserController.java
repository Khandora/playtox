package ru.playtox.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.playtox.shop.entity.Product;
import ru.playtox.shop.entity.Purchase;
import ru.playtox.shop.entity.dto.PurchaseRequest;
import ru.playtox.shop.entity.User;
import ru.playtox.shop.facade.PurchaseFacade;
import ru.playtox.shop.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final ProductService productService;
    private final PurchaseFacade purchaseFacade;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/purchase")
    public ResponseEntity<Purchase> createPurchase(@AuthenticationPrincipal UserDetails userDetails,
                                                   @RequestBody PurchaseRequest request) {
        User authenticatedUser = (User) userDetails;
        Purchase purchase = purchaseFacade.makePurchase(request.getProductId(), authenticatedUser.getId(), request.getQuantity());
        return ResponseEntity.ok(purchase);
    }
}
