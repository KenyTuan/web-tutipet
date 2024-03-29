package shop.titupet.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.titupet.config.PaymentConfig;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.converter.VnPayDtoConverter;
import shop.titupet.dtos.Vnpay.CreateVnPayReq;
import shop.titupet.dtos.Vnpay.VnPayRes;
import shop.titupet.dtos.payment.PaymentRes;
import shop.titupet.service.OrderPaymentService;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(ApiEndpoints.PREFIX)
@RequiredArgsConstructor
public class PayController {

    private final OrderPaymentService orderPaymentService;

    @SneakyThrows
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(ApiEndpoints.PAY_V1)
    public ResponseEntity<Map<String, Object>> createPayment(HttpServletRequest request, @RequestBody CreateVnPayReq req)
    {
        Map<String, Object> result = this.orderPaymentService.createOrder(request, req);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }







}
