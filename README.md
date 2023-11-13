
# Environment Config
* Java 17
* Maven
* AWS Lambda
* AWS DynamoDB


# Test



## Database
* need to create database on AWS DynamoDB as following information: 
~~~
table name : coupon_table,
partition key: platform,
sort key: couponCode
~~~

## Building

* Run the below command under the root directory

  ```shell
  mvn clean package
  ```

## Upload file onto the AWS Lambda function

- the `Coupon-SNAPSHOT-1.0.jar` file onto the AWS lambda



## API

### create coupon

~~~json
{
  "platform": "platform-test01", 
  "couponCode": "SAVE25",
  "discount": 25,
  "codeType": "MONEY_OFF", //PERCENT_OFF or MONEY_OFF
  "amountTotal": 200,
  "minimumPurchase": 50,
  "commenceDate":"2011-12-03T10:15:30+01:00", // yyyy-MM-dd'T'HH:mm:ss.SSS+[zoneOff]
  "expireDate": "2012-12-03T10:15:30+01:00"
}
~~~

### Get Coupon By Platform And Coupon Code

 ~~~json
 {
   "platform": "platform-test01", 
   "couponCode": "SAVE25",
 }
 ~~~

### Get All Coupon By Platform (Admin) 

~~~json
{
  "platform": "platform-test01", 
}
~~~

### User Use the Coupon

~~~json
{
  "couponCode": "SAVE25",
  "platform": "platform_test01",
  "products": ["Product A", "Product B", "Product C"],
  "purchase": 100.50
}
~~~

### Add users into Coupon User Limit List

~~~json
{
  "userId": [123456,23456],  //list
  "platform": "Platform-test01",
  "couponCode": "SAVE25"
}
~~~

### Add products into Product List





### Remove User From Users List 

after user use this coupon or the used was removed by admin

~~~json
{
  "userId": 123456,
  "platform": "Platform-test01",
  "couponCode": "SAVE25"
}
~~~



