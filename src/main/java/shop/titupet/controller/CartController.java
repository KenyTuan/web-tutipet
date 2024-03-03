package shop.titupet.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import shop.titupet.assembler.CartModelAssembler;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.models.converter.CartDtoConverter;
import shop.titupet.models.dtos.cart.CartReq;
import shop.titupet.models.dtos.cart.CartRes;
import shop.titupet.models.entities.Cart;
import shop.titupet.repository.CartRepo;
import shop.titupet.service.CartService;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.PREFIX)
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartModelAssembler assembler;


    @GetMapping(ApiEndpoints.CART_V1)
    public List<CartRes> getAllCart(){
        return cartService.getAllCart().stream()
                .map(CartDtoConverter::toResponse).toList();
    }

    @GetMapping(ApiEndpoints.CART_V1 + "/{id}")
    public EntityModel<CartRes> getCartById(@PathVariable(name = "id") Long id){
        final Cart cart = cartService.getCartById(id);
        return assembler.toModel(CartDtoConverter.toResponse(cart));
    }

    @PostMapping(ApiEndpoints.CART_V1)
    @ResponseStatus(HttpStatus.CREATED)
    public CartRes createCart(@RequestBody @Valid CartReq req){
        return CartDtoConverter.toResponse(cartService.createCart(req));
    }

}
