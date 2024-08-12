document.getElementById('fetchButton').addEventListener('click', () => {
    const pokemonId = document.getElementById('pokemonId').value;

    if (pokemonId) {
        // Realiza una solicitud a la API usando fetch
        fetch(`http://localhost:8080/pokemones/${pokemonId}`)
            .then(response => response.text()) // Cambia esto a response.json() si la respuesta es JSON
            .then(data => {
                // Actualiza el contenido del div con los datos recibidos
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


