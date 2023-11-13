package com.faxd.coupon.lambda.coupon;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.faxd.coupon.dto.coupon.CouponPostDto;
import com.faxd.coupon.service.CouponService;
import com.faxd.coupon.utils.ObjectMapperWithDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PutCouponRequestHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>{
    private final Logger logger = LoggerFactory.getLogger(PutCouponRequestHandler.class);
    private final CouponService couponService = new CouponService();
    private final ObjectMapper objectMapper = ObjectMapperWithDate.getObjectMapper();

    /**
     * Handle PUT requests to create a unicorn
     * Test with POST body:
     * {
     *   "platform": "platform_test01",
     *   "couponCode": "SAVE20",
     *   "discount": 10.5,
     *   "codeType": "MONEY_OFF",
     *   "amountTotal": 200,
     *   "miniSpend": 50,
     *   "commenceDate":null,
     *   "expireDate": null
     * }
     * @param request
     * @param context
     * @return API Gateway response with the created unicorn data
     */

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        String body = request.getBody();
        CouponPostDto couponPostDto;
        try {
            couponPostDto = objectMapper.readValue(body, CouponPostDto.class);
        } catch (Exception e) {
            logger.error("Error while processing the request with message: {}", e.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody("Error processing the request: " + e.getMessage());
        }

        Boolean created = couponService.createCoupon(couponPostDto);

        if (created){
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200)
                    .withBody("Received Coupon: " + couponPostDto.toString());
        }else{
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody(couponPostDto.getCouponCode() + " has existed or inValid on the platform: " + couponPostDto.getPlatform());
        }
    }
}
