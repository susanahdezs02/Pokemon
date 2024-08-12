document.getElementById('fetchButton').addEventListener('click', () => {
    const pokemonId = document.getElementById('pokemonId').value;

    if (pokemonId) {
      
        fetch(`http://localhost:8080/pokemones/${pokemonId}`)
            .then(response => response.text())
            .then(data => {
                const infoContainer = document.getElementById('info-container');
               infoContainer.innerHTML = data;

            })
            .catch(error => {
                console.error('Error al obtener los datos del Pokémon:', error);
                alert('Error al obtener los datos del Pokémon');
            });
    } else {
        alert('Please enter a Pokémon ID');
    }
});


