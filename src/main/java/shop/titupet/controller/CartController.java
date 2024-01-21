package shop.titupet.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.models.converter.CartDtoConverter;
import shop.titupet.models.dtos.cart.CartReq;
import shop.titupet.models.dtos.cart.CartRes;
import shop.titupet.repository.CartRepo;
import shop.titupet.service.CartService;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.PREFIX)
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping(ApiEndpoints.CART_V1)
    public List<CartRes> getAllCart(){
        return cartService.getAllCart().stream()
                .map(CartDtoConverter::toResponse).toList();
    }

    @PostMapping(ApiEndpoints.CART_V1)
    @ResponseStatus(HttpStatus.CREATED)
    public CartRes createCart(@RequestBody @Valid CartReq req){
        return CartDtoConverter.toResponse(cartService.createCart(req));
    }

}
