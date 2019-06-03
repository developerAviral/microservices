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

    public DbDerviceResource(QuotesRepository qoutesRepository) {
        this.qoutesRepository = qoutesRepository;
    }

    @GetMapping("/{username}")
    public List<String> getQoutes(@PathVariable("username") final String username){
        return getQuotesByUserName(username);
    }

    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes){
        quotes.getQuotes()
                .stream()
                .map(quote -> qoutesRepository.save(new Quote(quotes.getUserName(),quote)))
                .forEach(quote -> qoutesRepository.save(quote));
        return getQuotesByUserName(quotes.getUserName());
    }

    private List<String> getQuotesByUserName(String username){
        return qoutesRepository.findByUserName(username)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }
}
