package shop.titupet.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.models.entities.Order;
import shop.titupet.service.OrderService;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.PREFIX)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


}
