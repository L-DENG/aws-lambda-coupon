
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
