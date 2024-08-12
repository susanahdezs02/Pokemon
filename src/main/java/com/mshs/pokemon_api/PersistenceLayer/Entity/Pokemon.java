package com.mshs.pokemon_api.PersistenceLayer.Entity;

import java.util.List;

import jakarta.persistence.*;



@Entity 
@Table(name = "pokemon") /* Entidad con la que queremos que se mapee a nuestra BD */
public class Pokemon {
      @Id // Derfinimos la Primary key en esta clase, que es la que se va a mapear en la
      private Long pokemonId;
      private String pokemonName;
      private String imageUrl; // Esta es la URL de la imagen del Pokémon

      
      @OneToMany(mappedBy = "pokemon")
      private List<PokemonAccess> accesses;  // Relación uno a muchos con PokemonAccess


      public Pokemon() {
           
      }


      public Pokemon(Long pokemonId, String pokemonName, String imageUrl, List<PokemonAccess> accesses) {
            this.pokemonId = pokemonId;
            this.pokemonName = pokemonName;
            this.imageUrl = imageUrl;
            this.accesses = accesses;
      }


    public Long getPokemonId() {
        return pokemonId;
    }


    public void setPokemonId(Long pokemonId) {
        this.pokemonId = pokemonId;
    }


    public String getPokemonName() {
        return pokemonName;
    }


    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }


    public String getImageUrl() {
        return imageUrl;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public List<PokemonAccess> getAccesses() {
        return accesses;
    }


    public void setAccesses(List<PokemonAccess> accesses) {
        this.accesses = accesses;
    }




}
