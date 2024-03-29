package shop.titupet.service.Impl;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.titupet.exception.BadRequestException;
import shop.titupet.exception.NotFoundException;
import shop.titupet.converter.CartDtoConverter;
import shop.titupet.converter.ProductCartDtoConverter;
import shop.titupet.dtos.cart.CreateCartReq;
import shop.titupet.dtos.cart.UpdateCartReq;
import shop.titupet.dtos.productCart.UpdateProductCartReq;
import shop.titupet.models.entities.Cart;
import shop.titupet.models.entities.ProductCart;
import shop.titupet.models.entities.User;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.repository.CartRepo;
import shop.titupet.repository.ProductCartRepo;
import shop.titupet.repository.UserRepository;
import shop.titupet.security.JwtService;
import shop.titupet.service.CartService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;
//    private final ProductRepo productRepo;
    private final UserRepository userRepo;
    private final ProductCartRepo productCartRepo;
    private final JwtService jwtService;

    @Override
    public List<Cart> getAllCart() {
        return cartRepo.findAllByActiveCart();
    }

    @Override
    public Cart createCart(CreateCartReq req,  String token) {
        try {

            if (token == null || !token.startsWith("Bearer ")) {
                throw new BadRequestException("400", "Invalid or missing Authorization header");
            }

            token = token.substring(7);
            Claims claims = jwtService.decodeToken(token);

            Long userId = ((Number) claims.get("userId")).longValue();

            final User user = userRepo.getReferenceById(userId);

            final Cart cart = cartRepo.findCartByUser_Id(user.getId())
                    .orElseGet(() -> {
                        Cart newCart = new Cart();
                        newCart.setUser(user);
                        return cartRepo.save(newCart);
                    });

            if (req != null && req.getProductCartReqs() != null) {
                Long productId = req.getProductCartReqs().getProductId();
                ProductCart productCart = productCartRepo
                        .findProductCartByIdAndAndCart_Id(cart.getId(), productId);

                if (productCart == null) {
                    ProductCart entity = ProductCartDtoConverter.toEntity(req.getProductCartReqs(), cart);
                    productCartRepo.save(entity);
                } else {
                    productCart.setQuantity(productCart.getQuantity() + 1);
                    productCartRepo.save(productCart);
                }
            }
            return cart;
        }catch (Exception e){
            throw new BadRequestException("400","Error Server " + e.getMessage());
        }
    }

    @Override
    public Cart getCartById(Long id) {
        return cartRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("404","Not Found!"));
    }

    @Override
    public Cart getAllCartByUser( String token) {

        if((token == null) || !token.startsWith("Bearer ")) {
            throw new BadRequestException("400", "Invalid or missing Authorization header");
        }

        token = token.substring(7);
        Claims claims = jwtService.decodeToken(token);

        Long userId = ((Number) claims.get("userId")).longValue();

        return cartRepo.findCartActiveByUser_Id(userId)
                .orElseThrow(() -> new BadRequestException("400","Bad Request!"));
    }

    @Override
    public void deleteCart(Long id) {
        try{
            final ProductCart productCart = productCartRepo.findProductCarActiveById(id)
                    .orElseThrow(()-> new NotFoundException("404","Not found!"));

            productCart.setObjectStatus(ObjectStatus.DELETED);
            productCartRepo.save(productCart);
        }catch (Exception e){
            throw new BadRequestException("400","Error Server " + e);
        }
    }

    @Override
    public Cart updateCart(UpdateCartReq req) {
        List<Long> productCartIds = req
                .getProductCartReqs()
                .stream()
                .map(UpdateProductCartReq::getId)
                .collect(Collectors.toList());

        Map<Long, ProductCart> productCartMap = productCartRepo.findByIdIn(productCartIds)
                .stream()
                .collect(Collectors.toMap(ProductCart::getId, Function.identity()));

        for (UpdateProductCartReq productCartReq : req.getProductCartReqs()) {
            Long productId = productCartReq.getId();
            if (productCartMap.containsKey(productId)) {
                ProductCart productCart = productCartMap.get(productId);
                productCart.setQuantity(productCartReq.getQuantity());
            }else {
                throw new NotFoundException("404","Not Found!");
            }
        }

        productCartRepo.saveAll(productCartMap.values());

        Cart cart = CartDtoConverter.toEntity(req);
        cart.setProductCarts(new HashSet<>(productCartMap.values()));
        return cart;
    }

    @Override
    public void deleteAllProductCart(String token) {
        if (token == null) {
            throw new BadRequestException("400", "Invalid or missing Authorization header");
        } else if (!token.startsWith("Bearer ")) {
            throw new BadRequestException("400", "Invalid or missing Authorization header");
        }

        token = token.substring(7);
        Claims claims = jwtService.decodeToken(token);

        Long userId = ((Number) claims.get("userId")).longValue();

        Cart cart = cartRepo.findCartActiveByUser_Id(userId)
                .orElseThrow(() -> new BadRequestException("400","Bad Request!"));

        Set<ProductCart> productCarts = cart.getProductCarts().stream()
                .peek(productCart -> productCart.setObjectStatus(ObjectStatus.DELETED))
                .collect(Collectors.toSet());
        cartRepo.save(cart);
        productCartRepo.saveAll(productCarts);
    }

//    private ProductCart  updateDeletedProductCartData(ProductCart productCart) {
//        productCart.setObjectStatus(ObjectStatus.DELETED);
//        return null;
//    }
}
