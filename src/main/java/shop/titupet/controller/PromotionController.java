package shop.titupet.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import shop.titupet.constants.ApiEndpoints;
import shop.titupet.converter.PromotionDtoConverter;
import shop.titupet.dtos.promotion.CreatePromotionDtoReq;
import shop.titupet.dtos.promotion.PromotionRes;
import shop.titupet.dtos.promotion.UpdatePromotionReq;
import shop.titupet.models.enums.EnableStatus;
import shop.titupet.service.PromotionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiEndpoints.PREFIX)
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;

    // ============================ GET ALL PROMOTION =============================
    @GetMapping(ApiEndpoints.PROMOTION_V1)
    public List<PromotionRes> getAllPromotion(){
        return promotionService.getAllPromotion().stream()
                .map(PromotionDtoConverter::toResponse).collect(Collectors.toList());
    }

    // ============================ GET PROMOTION =============================
    @GetMapping(ApiEndpoints.PROMOTION_V1 + "/{id}")
    public PromotionRes getPromotion(@PathVariable Long id){
        return PromotionDtoConverter.toResponse(promotionService.getPromotionById(id));
    }

    // ============================ POST PROMOTION =============================
    @PostMapping(ApiEndpoints.PROMOTION_V1)
    @ResponseStatus(HttpStatus.CREATED)
    public PromotionRes createPromotion(@RequestBody @Valid CreatePromotionDtoReq req){
        return PromotionDtoConverter.toResponse(promotionService.createPromotion(req));
    }

    @DeleteMapping(ApiEndpoints.PROMOTION_V1 + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePromotion(@PathVariable Long id){
         promotionService.deletePromotion(id);
    }

    @PutMapping(ApiEndpoints.PROMOTION_V1)
    @ResponseStatus(HttpStatus.OK)
    public PromotionRes updatePromotion(@RequestBody @Valid UpdatePromotionReq req){
        return PromotionDtoConverter.toResponse(promotionService.updatePromotion(req));
    }

//    @PatchMapping(ApiEndpoints.PROMOTION_V1)
//    @ResponseStatus(HttpStatus.OK)
//    public PromotionRes updateEnablePromotion(@RequestParam(name = "id") Long id,
//                                              @RequestParam(name = "enable") EnableStatus enable){
//        return PromotionDtoConverter.toResponse(promotionService.updateEnablePromotion(id,enable));
//    }
}
