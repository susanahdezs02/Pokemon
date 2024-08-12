package com.mshs.pokemon_api.PersistenceLayer.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mshs.pokemon_api.PersistenceLayer.Entity.Pokemon;


@Repository // Repositorio de nuestro objeto repositorio
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    // Este repositorio recibe dos arg uno es la Entidad y el otro el tipo de la llave primaria
}
