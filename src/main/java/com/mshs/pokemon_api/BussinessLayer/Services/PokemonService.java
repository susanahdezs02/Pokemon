package com.mshs.pokemon_api.BussinessLayer.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class PokemonService {

    private static final String POKEAPI_URL = "https://pokeapi.co/api/v2/pokemon/";

    public PokemonDTO processPokemonById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = POKEAPI_URL + id;
        PokemonDTO pokemon = restTemplate.getForObject(url, PokemonDTO.class);
        
        return pokemon;
    }


    }






  

        


