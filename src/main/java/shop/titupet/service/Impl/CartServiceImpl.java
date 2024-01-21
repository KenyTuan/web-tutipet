package shop.titupet.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.titupet.config.exception.BadRequestException;
import shop.titupet.models.converter.CartDtoConverter;
import shop.titupet.models.converter.ProductCartDtoConverter;
import shop.titupet.models.dtos.cart.CartReq;
import shop.titupet.models.entities.Cart;
import shop.titupet.models.entities.ProductCart;
import shop.titupet.models.entities.User;
import shop.titupet.repository.CartRepo;
import shop.titupet.repository.ProductCartRepo;
import shop.titupet.repository.ProductRepo;
import shop.titupet.repository.UserRepository;
import shop.titupet.service.CartService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;
    private final ProductRepo productRepo;
    private final UserRepository userRepo;
    private final ProductCartRepo productCartRepo;

    @Override
    public List<Cart> getAllCart() {
        return cartRepo.findAll();
    }

    @Override
    @Transactional
    public Cart createCart(CartReq req) {
        final User user = userRepo.getReferenceById(req.getUserId());
        try {
            Cart cart = CartDtoConverter.toEntity(req);

            cart.setUser(user);

            Set<ProductCart> productCarts = req.getProductCartReqs().stream()
                            .map(productCartReq -> {
                                ProductCart item = ProductCartDtoConverter.toEntity(productCartReq, cart);
                                item.setProduct(productRepo.getReferenceById(productCartReq.getCartId()));
                                return item;
                            })
                    .collect(Collectors.toSet());

            cart.setProductCarts(productCarts);
            cartRepo.save(cart);

            productCartRepo.saveAll(productCarts);
            return cart;
        }catch (Exception e){
            throw new BadRequestException("400", e.getMessage());
        }
    }
}
