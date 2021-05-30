package spring.dynamodb.awsdynamodbcrud.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import spring.dynamodb.awsdynamodbcrud.dynamoTables.MovieProd;

@Repository
public class MovieProdRepository {
    @Autowired
    @Qualifier("withoutC")
    private DynamoDBMapper dynamoDBMapper;


    public MovieProd get (String movieId){
        return dynamoDBMapper.load(MovieProd.class, movieId);
    }

}
