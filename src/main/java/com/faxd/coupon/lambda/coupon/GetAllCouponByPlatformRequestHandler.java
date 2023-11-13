package com.faxd.coupon.lambda.coupon;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.faxd.coupon.service.CouponService;
import com.faxd.coupon.constant.CouponTable;
import com.faxd.coupon.dto.coupon.CouponGetDto;
import com.faxd.coupon.utils.ObjectMapperWithDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class GetAllCouponByPlatformRequestHandler
        implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private final Logger logger = LoggerFactory.getLogger(GetAllCouponByPlatformRequestHandler.class);
    private final CouponService couponService = new CouponService();
    private final ObjectMapper objectMapper = ObjectMapperWithDate.getObjectMapper();

    /**
     * Handle PUT requests to create a unicorn
     * Test with POST body:
     * {
     *   "platform": "platform_test01",
     *   "couponCode": "SAVE20",
     * }
     * @param request
     * @param context
     * @return API Gateway response with the get coupon data
     */

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        logger.info("request: {}",request);
        String platform = request.getQueryStringParameters().get(CouponTable.PARTITION_KEY.getName());
        logger.info("Fetching all coupon codes in platform: {} ", platform);
        Optional<List<CouponGetDto>> couponList = couponService.getAllCouponByPlatform(platform);
        if (couponList.isEmpty()) {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(404)
                    .withBody("{\"message\": \"no coupons found on the platform: {}"+ platform+" \"}");
        }
        logger.info(couponList.toString());
        try {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200)
                    .withBody(objectMapper.writeValueAsString(couponList.get()));
        } catch (JsonProcessingException e) {
            logger.info("Error while get item with message: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
