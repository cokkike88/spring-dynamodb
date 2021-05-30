package spring.dynamodb.awsdynamodbcrud.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.dynamodb.awsdynamodbcrud.dynamoTables.MovieProd;
import spring.dynamodb.awsdynamodbcrud.repositories.MovieProdRepository;

@RestController
@RequestMapping("")
public class homeController {

    MovieProdRepository movieProdRepository;

    public homeController(MovieProdRepository movieProdRepository){
        this.movieProdRepository = movieProdRepository;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/movie/{movieId}")
    public String getMovie(@PathVariable(name = "movieId") String movieId){
        MovieProd movie = movieProdRepository.get(movieId);
        if(movie == null){
            return "Movie not found";
        }
        return "Movie found";
    }
}
