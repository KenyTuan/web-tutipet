package shop.titupet;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import shop.titupet.models.entities.Product;
import shop.titupet.models.entities.ProductType;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.models.enums.ObjectStatus;
import shop.titupet.models.enums.PetType;
import shop.titupet.repository.ProductRepo;
import shop.titupet.repository.ProductTypeRepo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class TitupetApplication {

    public static void main(String[] args) {
        SpringApplication.run(TitupetApplication.class, args);
    }


    @Bean
    public CommandLineRunner dataLoader(ProductTypeRepo productTypeRepo,ProductRepo productRepo){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                List<ProductType> types = dataProductType();

                productTypeRepo.saveAll(types);


                List<Product> products = dataProduct(productTypeRepo.findAll());

                productRepo.saveAll(products);
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

        return types;
    }

    public List<Product> dataProduct(List<ProductType> types){

        List<Product> products = new ArrayList<>();

        Product product = Product.builder()
                .name("Thức ăn cho mèo con ROYAL CANIN Kitten")
                .price(120000)
                .status(EnableStatus.ENABLED)
                .type(types.get(0))
                .build();
        product.setObjectStatus(ObjectStatus.ACTIVE);
        products.add(product);

        Product product1 = Product.builder()
                .name("Thức ăn cho mèo trưởng thành ROYAL CANIN Indoor 27")
                .price(120000)
                .status(EnableStatus.ENABLED)
                .type(types.get(0))
                .build();
        product1.setObjectStatus(ObjectStatus.ACTIVE);
        products.add(product1);

        Product product2  = Product.builder()
                .name("Thức ăn cho mèo con và mèo mẹ ROYAL CANIN Mother & Babycat")
                .price(120000)
                .status(EnableStatus.ENABLED)
                .type(types.get(0))
                .build();
        product2.setObjectStatus(ObjectStatus.ACTIVE);
        products.add(product2);

        Product product3 = Product.builder()
                .name("Pate cho mèo vị cá ngừ nguyên chất CAT SEA FISH Pure Tuna Meat")
                .price(120000)
                .status(EnableStatus.ENABLED)
                .type(types.get(1))
                .build();
        product3.setObjectStatus(ObjectStatus.ACTIVE);

        products.add(product3);

        Product product4 = Product.builder()
                .name("Pate cho mèo vị cá ngừ nguyên chất CAT SEA FISH Pure Tuna Meat")
                .price(120000)
                .status(EnableStatus.ENABLED)
                .type(types.get(0))
                .build();
        product4.setObjectStatus(ObjectStatus.ACTIVE);
        products.add(product4);

        Product product5 = Product.builder()
                .name("Pate cho mèo CIAO Tuna & Whitebait vị cá ngừ và cá chạch trắng")
                .price(120000)
                .status(EnableStatus.ENABLED)
                .type(types.get(0))
                .build();
        product5.setObjectStatus(ObjectStatus.ACTIVE);
        products.add(product5);

        return products;
    }

}
