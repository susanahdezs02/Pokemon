/*package com.mshs.pokemon_api.PersistenceLayer.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.mshs.pokemon_api.PersistenceLayer.Entity.Pokemon;

@SpringBootTest
public class PokemonRepositoryTest {

    @Autowired // Inyeccion de
    private PokemonRepository pokemonRepository;

    @Test
    public void guardarPokemon() {

        // construyendo entidad

        Pokemon pokemon = Pokemon.builder()
                // el id no se 
                .pokemonName("Charmander")
                .pokemonType("Fire")
                .pokemonBaseExperience(64)
                .pokemonHeight(45)
                .pokemonWeight(34)
                .build();

        pokemonRepository.save(pokemon); // save recibe una entidad por lo que hay que constuirla
    }






}
    */