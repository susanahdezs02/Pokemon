package com.mshs.pokemon_api.PersistenceLayer.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mshs.pokemon_api.PersistenceLayer.Entity.Pokemon;


@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
   
}
