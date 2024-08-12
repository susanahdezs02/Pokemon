package com.mshs.pokemon_api.PersistenceLayer.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;


@Entity
@Table(name = "access")
public class PokemonAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accessId;

    private String accessURL;
    private LocalDateTime accessTimeStamp;
    private int accessResponseStatus;

    @Lob
    private String accessResponseBody;

    @ManyToOne
    @JoinColumn(name = "pokemon_id")  
    private Pokemon pokemon;  

    
    
  
    public PokemonAccess() {
       
    }



    public PokemonAccess(Long accessId, String accessURL, LocalDateTime accessTimeStamp, int accessResponseStatus,
            String accessResponseBody, Pokemon pokemon) {
        this.accessId = accessId;
        this.accessURL = accessURL;
        this.accessTimeStamp = accessTimeStamp;
        this.accessResponseStatus = accessResponseStatus;
        this.accessResponseBody = accessResponseBody;
        this.pokemon = pokemon;
    }



    public Long getAccessId() {
        return accessId;
    }



    public void setAccessId(Long accessId) {
        this.accessId = accessId;
    }



    public String getAccessURL() {
        return accessURL;
    }



    public void setAccessURL(String accessURL) {
        this.accessURL = accessURL;
    }



    public LocalDateTime getAccessTimeStamp() {
        return accessTimeStamp;
    }



    public void setAccessTimeStamp(LocalDateTime accessTimeStamp) {
        this.accessTimeStamp = accessTimeStamp;
    }



    public int getAccessResponseStatus() {
        return accessResponseStatus;
    }



    public void setAccessResponseStatus(int i) {
        this.accessResponseStatus = i;
    }



    public String getAccessResponseBody() {
        return accessResponseBody;
    }



    public void setAccessResponseBody(String accessResponseBody) {
        this.accessResponseBody = accessResponseBody;
    }



    public Pokemon getPokemon() {
        return pokemon;
    }



    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }


    

}
