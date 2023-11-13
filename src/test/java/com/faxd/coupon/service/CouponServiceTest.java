package com.faxd.coupon.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

public class CouponServiceTest {

    @Mock
    private DynamoDbAsyncClient dynamoDbAsyncClient;

    @InjectMocks
    private CouponService service;


}
