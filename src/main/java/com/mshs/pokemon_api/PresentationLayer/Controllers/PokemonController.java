package com.mshs.pokemon_api.PresentationLayer.Controllers;

import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mshs.pokemon_api.PersistenceLayer.Entity.Pokemon;
import com.mshs.pokemon_api.PersistenceLayer.Entity.PokemonAccess;
import com.mshs.pokemon_api.PersistenceLayer.Entity.PokemonApiResponse;
import com.mshs.pokemon_api.PersistenceLayer.Repository.AccessRepository;
import com.mshs.pokemon_api.PersistenceLayer.Repository.PokemonRepository;
import com.mshs.pokemon_api.BussinessLayer.Services.*;;

@RestController // Le dice a Spring que es un controlador
@RequestMapping("/pokemones") // Le dice la direccion del servidor se van a activar los metodos de esta clase
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private PokemonRepository pokemonRepository;
    @Autowired
    private AccessRepository accessRepository;
    @Autowired
    private final RestTemplate restTemplate = new RestTemplate();

    
    public ResponseEntity<PokemonDTO> getPokemonById(@PathVariable("id") Long id) {
        PokemonDTO pokemon = pokemonService.processPokemonById(id);
        return ResponseEntity.ok(pokemon);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getPokemon(@PathVariable Long id) {

        String pokemonUrl = "https://pokeapi.co/api/v2/pokemon/" + id;

        try {
            // Consume Pokémon API
            PokemonApiResponse pokemonApiResponse = restTemplate.getForObject(pokemonUrl, PokemonApiResponse.class);

            if (pokemonApiResponse == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokemon not found");
            }

            // POKEMON
            Pokemon pokemon = new Pokemon();
            pokemon.setPokemonId(pokemonApiResponse.getId().longValue());
            pokemon.setPokemonName(pokemonApiResponse.getName());

            String imageUrl = pokemonApiResponse.getSprites().getFront_default();
            if (imageUrl != null) {
                pokemon.setImageUrl(imageUrl);
            } else {
                pokemon.setImageUrl("No pone la URL :C");
            }
            pokemonRepository.save(pokemon);

            // ACCESS
            PokemonAccess access = new PokemonAccess();
            access.setAccessURL(pokemonUrl);
            access.setAccessId(id);
            access.setAccessTimeStamp(LocalDateTime.now());
            access.setPokemon(pokemon);
            access.setAccessResponseStatus(HttpStatus.OK.value()); // Status de la respuesta
            accessRepository.save(access);

            // OBTENER URL DEL EVOLUTION CHAIN
            String speciesUrl = "https://pokeapi.co/api/v2/pokemon-species/" + id;
            //String evolutionUrl = "https://pokeapi.co/api/v2/evolution-chain/";
            String speciesData = restTemplate.getForObject(speciesUrl, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(speciesData);
            String evolutionChainUrl = jsonNode.path("evolution_chain").path("url").asText();

            //AHORA OBTENGO LA CADENA DE EVOLUCION DEACUERDO AL URL OBTENIDO
             String evolutionData = restTemplate.getForObject(evolutionChainUrl, String.class);
             JsonNode evolutionJsonNode = objectMapper.readTree(evolutionData);
             // Procesar la cadena de evolución según tu necesidad
             JsonNode chain = evolutionJsonNode.path("chain");
             StringBuilder evolutionInfo = new StringBuilder();
             extractEvolution(chain, evolutionInfo);

           /*  String response = "Pokemon data saved successfully. " + "\n" 
             + "Image URL: " + imageUrl + " "+ "\n" 
             + "Evolution chain URL: " + evolutionChainUrl + " "+ "\n" 
             + "Evolution Pokemon: " + evolutionInfo;*/ 

             String response = "<center>Pokemon data saved successfully. " + "<br><br>" 
             + "Pokemon Name:" +  pokemonApiResponse.getName()+ "<br><br><br><br>" 
             + "<center> <img src=\"" + imageUrl + "\" alt=\"Pokemon Image\" width=\"200\" height=\"200\" /><br>" 
             + "Evolution Pokemon: " + evolutionInfo;
            
         return ResponseEntity.ok(response);

        } catch (RestClientException e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Error fetching data from API");

        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }

    }

    private void extractEvolution(JsonNode node, StringBuilder evolutionInfo) {
        if (node != null && node.has("species")) {
            JsonNode speciesNode = node.path("species");
            String speciesName = speciesNode.path("name").asText();
            evolutionInfo.append(speciesName).append(" -> ");

            JsonNode evolvesToNode = node.path("evolves_to");
            if (evolvesToNode.isArray()) {
                for (JsonNode evolveNode : evolvesToNode) {
                    extractEvolution(evolveNode, evolutionInfo);
                }
            }
        }
    }


    public PokemonRepository getPokemonRepository() {
        return pokemonRepository;
    }

    public void setPokemonRepository(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public AccessRepository getAccessRepository() {
        return accessRepository;
    }

    public void setAccessRepository(AccessRepository accessRepository) {
        this.accessRepository = accessRepository;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

}
