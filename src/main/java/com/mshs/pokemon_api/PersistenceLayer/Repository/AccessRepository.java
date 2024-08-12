package com.mshs.pokemon_api.PersistenceLayer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mshs.pokemon_api.PersistenceLayer.Entity.PokemonAccess;


@Repository // Repositorio de nuestro objeto repositorio
public interface AccessRepository extends JpaRepository<PokemonAccess, Long>{

}
