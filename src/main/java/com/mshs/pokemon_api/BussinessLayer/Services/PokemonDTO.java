package com.mshs.pokemon_api.BussinessLayer.Services;

public class PokemonDTO{
    private Long id;
    private String name;
    private Sprites sprites;

   

    public static class Sprites {
        private String front_default;

        public String getFront_default() {
            return front_default;
        }

        public void setFront_default(String front_default) {
            this.front_default = front_default;
        }

        
    }



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public Sprites getSprites() {
        return sprites;
    }



    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }
}