package com.card.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.card.models.CardModel;
import com.card.repository.CardRepository;

@RestController
@RequestMapping("/card")
@CrossOrigin("http://127.0.0.1:5500")
public class CardControllers {

    @Autowired
    private CardRepository cardRepository;
    
    @GetMapping("/")
    public List<CardModel> card(){

        return this.cardRepository.findAll();
    }

    @GetMapping("/{id}")
    public CardModel cardFindById(@PathVariable("id") Long id){

        Optional<CardModel> findCard = this.cardRepository.findById(id);
        
        if(findCard.isPresent()){
            return findCard.get();
        }

        return null;

    }

    @PostMapping("/save")
    public CardModel cardPost(@RequestBody CardModel card) {

        return this.cardRepository.save(card);
    }

    @PutMapping("/atualizar/{id}")
    public CardModel cardSave(@RequestBody CardModel card, @PathVariable("id") Long id) {
        var findCard = this.cardRepository.findById(id);
        if(findCard.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        card.setId(id);
        return this.cardRepository.save(card);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id){

        var findCard = this.cardRepository.findById(id);

        if(findCard.isPresent()){
            this.cardRepository.delete(findCard.get());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


    }
}
