package shop.titupet.converter;

import shop.titupet.dtos.Vnpay.VnPayRes;
import shop.titupet.dtos.auth.AuthRes;

public class VnPayDtoConverter {

    public static VnPayRes toResponse(String url){
        return new VnPayRes(
                url
        );
    }
}
