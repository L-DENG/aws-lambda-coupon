package com.faxd.coupon.lambda;


import com.faxd.coupon.lambda.coupon.GetSingleCouponRequestHandler;
import com.faxd.coupon.service.CouponService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

public class GetCouponRequestHandlerTest {

    @Mock
    private DynamoDbAsyncClient dynamoDbAsyncClient;

    @Mock
    private CouponService couponService;

    @InjectMocks
    private GetSingleCouponRequestHandler getHandler;


//    @Test
//    void shouldReturnOkWhenCreateNotExistingCoupon() throws JsonProcessingException {
//
//        when(couponService.getCouponByPlatformAndCouponCode(any(),any(),any())).thenReturn(Optional.of(MockCouponData.getMockCouponGetDto()));
//
//        APIGatewayProxyRequestEvent event = new APIGatewayProxyRequestEvent();
//        event.setQueryStringParameters(MockDataCouponQueryMap.getMockKeyMap());
//        APIGatewayProxyResponseEvent responseEvent = getHandler.handleRequest(event, new TestContext());
//
//        assertEquals(new ObjectMapper().writeValueAsString(Optional.of(MockCouponData.getMockCouponGetDto()).get()),responseEvent.getBody());
//        assertEquals(200,responseEvent.getStatusCode());
//    }

}
