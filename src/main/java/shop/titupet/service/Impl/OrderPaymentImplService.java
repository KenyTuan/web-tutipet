package shop.titupet.service.Impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import shop.titupet.constants.VnPayConstants;
import shop.titupet.dtos.Vnpay.CreateVnPayReq;
import shop.titupet.service.OrderPaymentService;
import shop.titupet.utils.VnPayHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class OrderPaymentImplService implements OrderPaymentService {


    @Override
    public Map<String, Object> createOrder(HttpServletRequest request, CreateVnPayReq createOrderReq)
            throws UnsupportedEncodingException {

        final String randomOrder = VnPayHelper.getRandomNumber(8);
        final Map<String, Object> payload = new HashMap<>(){{
            put("vnp_Version", VnPayConstants.VNP_VERSION);
            put("vnp_Command", VnPayConstants.VNP_COMMAND_ORDER);
            put("vnp_TmnCode", VnPayConstants.VNP_TMN_CODE);
            put("vnp_Amount", String.valueOf(createOrderReq.getAmount() * 100 * 1000));
            put("vnp_CurrCode", VnPayConstants.VNP_CURRENCY_CODE);
            put("vnp_TxnRef",  randomOrder);
            put("vnp_OrderInfo", "Thanh Toán Đơn Hàng: "+ randomOrder);
            put("vnp_OrderType", VnPayConstants.ORDER_TYPE);
            put("vnp_Locale", VnPayConstants.VNP_LOCALE);
            put("vnp_ReturnUrl", VnPayConstants.VNP_RETURN_URL);
            put("vnp_IpAddr", VnPayHelper.getIpAddress(request));
            put("vnp_CreateDate", VnPayHelper.generateDate(false));
            put("vnp_ExpireDate", VnPayHelper.generateDate(true));
        }};

        final String queryUrl = getQueryUrl(payload).get("queryUrl")
                + "&vnp_SecureHash="
                + VnPayHelper.hmacSHA512(VnPayConstants.SECRET_KEY, getQueryUrl(payload).get("hashData"));

        final String paymentUrl = VnPayConstants.VNP_PAY_URL + "?" + queryUrl;
        payload.put("redirect_url", paymentUrl);

        return payload;
    }

    private Map<String, String> getQueryUrl(Map<String, Object> payload)
            throws UnsupportedEncodingException {

        final List<String> fieldNames = new ArrayList<>(payload.keySet());
        Collections.sort(fieldNames);
        final StringBuilder hashData = new StringBuilder();
        final StringBuilder query = new StringBuilder();
        final Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {

            String fieldName = (String) itr.next();
            String fieldValue = (String) payload.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {

                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {

                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        return new HashMap<>(){{
            put("queryUrl", query.toString());
            put("hashData", hashData.toString());
        }};
    }
}
