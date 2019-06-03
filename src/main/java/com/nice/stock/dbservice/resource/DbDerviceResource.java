package com.nice.stock.dbservice.resource;

import com.nice.stock.dbservice.model.Quote;
import com.nice.stock.dbservice.model.Quotes;
import com.nice.stock.dbservice.repository.QuotesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DbDerviceResource {
    private QuotesRepository qoutesRepository;

    @GetMapping("/{username}")
    public List<String> getQoutes(@PathVariable("username") final String username){
       return qoutesRepository.findByUserName(username)
                         .stream()
                         .map(Quote::getQuote)
                         .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes){
        return null;
    }
}
