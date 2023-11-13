package com.faxd.coupon.lambda.user;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.faxd.coupon.dto.user.UserCouponDto;
import com.faxd.coupon.service.UserService;
import com.faxd.coupon.utils.ObjectMapperWithDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class PutUserToCouponRequestHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private final Logger logger = LoggerFactory.getLogger(PutUserToCouponRequestHandler.class);
    private final UserService userService = new UserService();

    private final ObjectMapper objectMapper = ObjectMapperWithDate.getObjectMapper();


    /**
     * Handle PUT requests to create a unicorn
     * Test with POST body:
     * {
     *   "platform": "platform_test01",
     *   "couponCode": "SAVE20",
     *   "userIds": [12345,23455]
     * }
     * @param RequestEvent
     * @param context
     * @return API Gateway response with the created unicorn data
     */

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent RequestEvent, Context context) {
        UserCouponDto userCouponDto;

            String body = RequestEvent.getBody();
        try{
            userCouponDto = objectMapper.readValue(body, UserCouponDto.class);
        } catch (Exception e) {
            logger.error("Error while processing the input data with message: {}", e.getMessage());
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody("Error processing the request: " + e.getMessage());
        }
        Set<Long> users = userCouponDto.getUsers();
        logger.info("put user into the coupon: {}",userCouponDto.toString());
        Boolean created = userService.addUserToCoupon(userCouponDto);


        if (created){
            userCouponDto.setUsers(users);
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200)
                    .withBody("Received Coupon: " + userCouponDto);
        }else{
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody(userCouponDto.getCouponCode() + " has not existed on the platform: " + userCouponDto.getPlatform());
        }
    }
}
