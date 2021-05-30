package spring.dynamodb.awsdynamodbcrud.dynamodbConfig;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamodbConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamodDBEndpoint;
    @Value("${amazon.aws.region}")
    private String amazonRegion;
    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;
    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB(
            final AWSCredentials amazonAWSCredentials,
            final AwsClientBuilder.EndpointConfiguration endpointConfiguration
    ){
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(
                        new AWSStaticCredentialsProvider(amazonAWSCredentials))
                .build();
    }

    @Bean
    public AwsClientBuilder.EndpointConfiguration endpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(amazonDynamodDBEndpoint, amazonRegion);
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(
            final AmazonDynamoDB amazonDynamoDB
    ){
        return new DynamoDBMapper(amazonDynamoDB);
    }


    @Bean
    public AmazonDynamoDB amazonDynamoDBWithoutCredentials(
            final AwsClientBuilder.EndpointConfiguration endpointConfiguration
    ){
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(endpointConfiguration)
                .build();
    }

    @Bean(name = "withoutC")
    public DynamoDBMapper dynamoDBMapperWithoutCredentials(
            final AmazonDynamoDB amazonDynamoDBWithoutCredentials
    ){
        return new DynamoDBMapper(amazonDynamoDBWithoutCredentials);
    }


}
