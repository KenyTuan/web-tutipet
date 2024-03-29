package shop.titupet;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import shop.titupet.models.entities.*;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.models.enums.PetType;
import shop.titupet.models.enums.Role;
import shop.titupet.repository.CartRepo;
import shop.titupet.repository.ProductRepo;
import shop.titupet.repository.ProductTypeRepo;
import shop.titupet.repository.UserRepository;

import java.util.*;

@SpringBootApplication
@EnableJpaAuditing
@Configuration
public class TitupetApplication {

    public static void main(String[] args) {
        SpringApplication.run(TitupetApplication.class, args);
    }




    @Bean
    public CommandLineRunner dataLoader(ProductTypeRepo productTypeRepo, ProductRepo productRepo, UserRepository userRepository, CartRepo cartRepo){


        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                List<ProductType> types = dataProductType();

                productTypeRepo.saveAll(types);


                List<Product> products = dataProduct(productTypeRepo.findAll());

                productRepo.saveAll(products);

                BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();

                User user = User
                        .builder()
                        .email("vothanhtuan098@gmail.com")
                        .fullName("vo thanh tuan")
                        .gender(true)
                        .password(encoder.encode("123456"))
                        .role(Role.ADMIN)
                        .build();
                user.setObjectStatus(ObjectStatus.ACTIVE);
                Cart cart = Cart.builder().user(user).build();
                cart.setObjectStatus(ObjectStatus.ACTIVE);
                userRepository.save(user);
                cartRepo.save(cart);
            }
        };

    }


    public List<ProductType> dataProductType(){
        List<ProductType> types = new ArrayList<>();
        types.add(ProductType.builder().name("Thức ăn cho mèo").petTypes(PetType.CAT).build());
        types.add(ProductType.builder().name("Lồng mèo").petTypes(PetType.CAT).build());
        types.add(ProductType.builder().name("Phụ kiện cho mèo").petTypes(PetType.CAT).build());
        types.add(ProductType.builder().name("Khay vệ sinh, Nhà vệ sinh cho mèo").petTypes(PetType.CAT).build());
        types.add(ProductType.builder().name("Cát mèo").petTypes(PetType.CAT).build());
        types.add(ProductType.builder().name("Máy lọc nước cho mèo").petTypes(PetType.CAT).build());
        types.add(ProductType.builder().name("Bát ăn cho mèo").petTypes(PetType.CAT).build());
        types.add(ProductType.builder().name("Đệm cho mèo").petTypes(PetType.CAT).build());
        types.add(ProductType.builder().name("Thức ăn cho chó").petTypes(PetType.DOG).build());
        types.add(ProductType.builder().name("Lồng cho chó").petTypes(PetType.DOG).build());
        types.add(ProductType.builder().name("Phụ kiện cho chó").petTypes(PetType.DOG).build());
        types.add(ProductType.builder().name("Bát ăn cho chó").petTypes(PetType.DOG).build());
        types.add(ProductType.builder().name("Khay vệ sinh, Nhà vệ sinh cho chó").petTypes(PetType.DOG).build());
        types.add(ProductType.builder().name("Máy lọc nước cho chó").petTypes(PetType.DOG).build());

        return types;
    }

    public List<Product> dataProduct(List<ProductType> types){

        List<Product> products = new ArrayList<>();

        Product product = Product.builder()
                .name("Thức ăn cho mèo con ROYAL CANIN Kitten")
                .price(120)
                .status(EnableStatus.ENABLED)
                .type(types.get(0))
                .img("https://res.cloudinary.com/dzhlo17cn/image/upload/v1710699421/thuc-an-cho-meo-con-royal-canin-kitten-400x400_m2i18m.jpg")
                .build();
        product.setObjectStatus(ObjectStatus.ACTIVE);
        products.add(product);

        Product product1 = Product.builder()
                .name("Thức ăn cho mèo trưởng thành ROYAL CANIN Indoor 27")
                .price(120)
                .status(EnableStatus.ENABLED)
                .type(types.get(0))
                .img("https://res.cloudinary.com/dzhlo17cn/image/upload/v1710699731/thuc-an-cho-meo-truong-thanh-royal-canin-indoor-27-400x400_tswsyw.jpg")
                .build();
        product1.setObjectStatus(ObjectStatus.ACTIVE);
        products.add(product1);

        Product product2  = Product.builder()
                .name("Thức ăn cho mèo con và mèo mẹ ROYAL CANIN Mother & Babycat")
                .price(120)
                .status(EnableStatus.ENABLED)
                .type(types.get(0))
                .img("https://res.cloudinary.com/dzhlo17cn/image/upload/v1710699731/thuc-an-cho-meo-con-va-meo-me-royal-canin-mother-babycat-400x400_almpyp.jpg")
                .build();
        product2.setObjectStatus(ObjectStatus.ACTIVE);
        products.add(product2);

        Product product3 = Product.builder()
                .name("Pate cho mèo vị cá ngừ nguyên chất CAT SEA FISH Pure Tuna Meat")
                .price(120)
                .status(EnableStatus.ENABLED)
                .type(types.get(1))
                .img("https://res.cloudinary.com/dzhlo17cn/image/upload/v1710699733/pate-cho-meo-ciao-tuna-whitebait-ca-ngu-va-ca-chach-trang-400x400_e0tz2a.jpg")
                .build();
        product3.setObjectStatus(ObjectStatus.ACTIVE);

        products.add(product3);

        Product product4 = Product.builder()
                .name("Thức ăn cho mèo trưởng thành ROYAL CANIN Regular Fit 32")
                .price(120)
                .status(EnableStatus.ENABLED)
                .type(types.get(0))
                .img("https://res.cloudinary.com/dzhlo17cn/image/upload/v1710699734/thuc-an-cho-meo-truong-thanh-royal-canin-regular-fit-321-400x400_uh40s3.jpg")
                .build();
        product4.setObjectStatus(ObjectStatus.ACTIVE);
        products.add(product4);

        Product product5 = Product.builder()
                .name("Pate cho mèo CIAO Tuna & Whitebait vị cá ngừ và cá chạch trắng")
                .price(120)
                .status(EnableStatus.ENABLED)
                .type(types.get(0))
                .img("https://res.cloudinary.com/dzhlo17cn/image/upload/v1710699734/pate-cho-meo-vi-ca-ngu-nguyen-chat-cat-sea-fish-pure-tuna-meat_i8mxww.jpg")
                .build();
        product5.setObjectStatus(ObjectStatus.ACTIVE);
        products.add(product5);

        return products;
    }

}
