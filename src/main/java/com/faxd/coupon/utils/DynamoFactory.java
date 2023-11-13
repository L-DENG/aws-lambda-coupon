package com.faxd.coupon.utils;

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.SdkSystemSetting;
import software.amazon.awssdk.http.crt.AwsCrtAsyncHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

public class DynamoFactory {
    private static DynamoDbAsyncClient dynamoDbClient;

    private DynamoFactory() {
    }

    public static DynamoDbAsyncClient getDynamoInstance() {
        if (dynamoDbClient == null) {
            dynamoDbClient = DynamoDbAsyncClient
                    .builder()
                    .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                    .region(Region.of(System.getenv(SdkSystemSetting.AWS_REGION.environmentVariable())))
                    .httpClientBuilder(AwsCrtAsyncHttpClient.builder())
                    .build();
        }
        return dynamoDbClient;
    }

//    private static AWSCredentialsProvider getAwsCredentials() {
//        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY));
//    }
}