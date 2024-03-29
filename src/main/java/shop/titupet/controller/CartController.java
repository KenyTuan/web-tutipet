package shop.titupet.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import shop.titupet.assembler.CartModelAssembler;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.converter.CartDtoConverter;
import shop.titupet.dtos.cart.CreateCartReq;
import shop.titupet.dtos.cart.CartRes;
import shop.titupet.dtos.cart.UpdateCartReq;
import shop.titupet.models.entities.Cart;
import shop.titupet.service.CartService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(ApiEndpoints.PREFIX)
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartModelAssembler assembler;

    @GetMapping(ApiEndpoints.CART_V1)
    public CollectionModel<EntityModel<CartRes>> getAllCart(){
        final List<CartRes> cartResList = cartService.getAllCart()
                .stream()
                .map(CartDtoConverter::toResponse)
                .toList();

        final List<EntityModel<CartRes>> entityModels = cartResList
                .stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(
                entityModels,
                linkTo(methodOn(CartController.class).getAllCart()
                        ).withSelfRel()
        );
    }

    @GetMapping(ApiEndpoints.CART_V1 + "/{id}")
    public EntityModel<CartRes> getCartById(@PathVariable(name = "id") Long id){
        final Cart cart = cartService.getCartById(id);
        return assembler.toModel(CartDtoConverter.toResponse(cart));
    }

    @GetMapping(ApiEndpoints.CART_V1 + "/user")
    public EntityModel<CartRes> getAllCartByUser(
            @RequestHeader("Authorization") String token){
        final Cart cart = cartService.getAllCartByUser(token);

        return assembler.toModel(
                CartDtoConverter.toResponse(cart)
        );
    }

    // Thêm sản phẩm vào giỏ hàng của người dùng
    @PostMapping(ApiEndpoints.CART_V1)
    @ResponseStatus(HttpStatus.CREATED)
    public CartRes createCart(@RequestBody @Valid CreateCartReq req,
                              @RequestHeader("Authorization") String token){
        return CartDtoConverter.toResponse(cartService.createCart(req,token));
    }

//    @PutMapping(ApiEndpoints.CART_V1)
//    @ResponseStatus(HttpStatus.OK)
    public CartRes updateCart(@RequestBody @Valid CreateCartReq req){
//        return CartDtoConverter.toResponse(cartService.createCart(req));
        return null;
    }

    @DeleteMapping(ApiEndpoints.CART_V1 + "/product_cart/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductCart(@PathVariable Long id){
        cartService.deleteCart(id);
    }

    @PutMapping(ApiEndpoints.CART_V1)
    public CartRes updateProductCart(@RequestBody @Valid UpdateCartReq req){
        return CartDtoConverter.toResponse(cartService.updateCart(req));
    }

    @DeleteMapping(ApiEndpoints.CART_V1 + "/product_cart/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllProductCart(
            @RequestHeader("Authorization") String token
    ){
        cartService.deleteAllProductCart(token);
    }

}
