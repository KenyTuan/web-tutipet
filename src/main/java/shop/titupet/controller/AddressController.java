package shop.titupet.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.service.AddressService;

@RestController
@RequestMapping(ApiEndpoints.PREFIX)
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;



}
