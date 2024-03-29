package shop.titupet.service;

import jakarta.servlet.http.HttpServletRequest;
import shop.titupet.dtos.Vnpay.CreateVnPayReq;
import shop.titupet.dtos.order.CreateOrderReq;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface OrderPaymentService {

    Map<String, Object> createOrder(HttpServletRequest request, CreateVnPayReq createOrderReq)
            throws UnsupportedEncodingException;
}
