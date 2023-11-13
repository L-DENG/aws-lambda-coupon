//package com.jracademic.coupon.lambda;
//
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.RequestHandler;
//import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
//import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.jracademic.coupon.entity.Coupon;
//import com.jracademic.coupon.service.CouponService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class updateCouponRequestHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
//
//    private final Logger logger = LoggerFactory.getLogger(updateCouponRequestHandler.class);
//    private final CouponService couponService = new CouponService();
//
//
//    /**
//     * Handle PUT requests to create a unicorn
//     * Test with POST body: {"name": "Big Unicorn", "age": "Quite NEW", "type": "Beautiful", "size": "Very big and new"}
//     * @param event
//     * @param context
//     * @return API Gateway response with the created unicorn data
//     */
//    @Override
//    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, final Context context)  {
//        try {
//			event.getPathParameters()
//                    unicornId = UnicornUtils.getUnicornParameter(event);
//			var unicorn = UnicornUtils.getBodyUnicorn(event);
//			unicorn.setId(unicornId);
//			unicorn = unicornService.updateUnicorn(unicorn);
//
//			var response = objectMapper.writeValueAsString(unicorn);
//            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(response);
//        } catch (MissingParameterException e) {
//        	logger.error("MissingParameterException: no unicorn ID provided via Path Parameters", e);
//        	return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody("Error: please provide a Unicorn ID");
//        } catch (JsonProcessingException e) {
//        	logger.error("JSONException on handling event body", e);
//        	return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody("Error: the provided Request JSON body is invalid or incomplete");
//        } catch (ResourceNotFoundException e) {
//        	logger.error("ResourceNotFoundException", e);
//            return new APIGatewayProxyResponseEvent().withStatusCode(404).withBody("Error: the provided unicorn does not exist");
//        } catch (Exception e) {
//            logger.error("Error handling request", e );
//            return new APIGatewayProxyResponseEvent().withStatusCode(500).withBody("Error while handling the Request");
//        }
//    }
//
//}