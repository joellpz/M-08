package com.company.recyclerview;

import java.util.ArrayList;
import java.util.List;

public class PokemonRepositorio {
    PokemonRepositorio(){
        pokemons.add(new Pokemon("bulbasaur", "Bulbasaur es un Pokémon de tipo planta/veneno introducido en la primera generación. Es uno de " +
                "los Pokémon iniciales que pueden elegir los entrenadores que empiezan su aventura en la región Kanto, junto a Squirtle y Charmander " +
                "(excepto en Pokémon Amarillo). Destaca por ser el primer Pokémon de la Pokédex Nacional y la en la Pokédex de Kanto.", "Placaje", "Gruñido", "Látigo cepa", "Drenadoras" ));
        pokemons.add(new Pokemon("charmander", "Charmander es un Pokémon de tipo fuego introducido en la primera generación. Es uno de los Pokémon iniciales que " +
                "pueden elegir los entrenadores que empiezan su aventura en la región Kanto, junto a Bulbasaur y Squirtle, excepto en Pokémon Amarillo y Pokémon: Let's Go, Pikachu! y " +
                "Pokémon: Let's Go, Eevee!", "Arañazo", "Gruñido", "Ascuas", "Lanzallamitas"));
        pokemons.add(new Pokemon("squirtle", "Squirtle es un Pokémon de tipo agua introducido en la primera generación. Es uno de los Pokémon iniciales que pueden " +
                "elegir los entrenadores que empiezan su aventura en la región Kanto, junto a Bulbasaur y Charmander, excepto en Pokémon Amarillo.", "Placaje", "Látigo", "Burbuja", "Pistola Agua"));
        pokemons.add(new Pokemon("pikachu", "Pikachu es un Pokémon de tipo eléctrico introducido en la primera generación. Es el Pokémon más conocido de la historia, " +
                "mayormente por ser el acompañante del protagonista del anime, Ash Ketchum y la mascota representante de la franquicia Pokémon. Es el Pokémon inicial de Pokémon Amarillo " +
                "y Pokémon: Let's Go Pikachu!. A partir de la segunda generación, es la forma evolucionada de Pichu.", "Látigo", "Impactrueno", "Gruñido", "Látigo"));
        pokemons.add(new Pokemon("jigglypuff","Jigglypuff es un Pokémon de tipo normal/hada3 introducido en la primera generación. Es la contraparte de Clefairy. A partir de la segunda generación es la evolución de Igglybuff. En la sexta generación se le añadió el tipo hada.",
                "Canto", "Rizo defensa", "Destructor", "Esculpir"));
        pokemons.add(new Pokemon("meowth","Meowth es un Pokémon de tipo normal introducido en la primera generación, también a partir de la séptima generación posee una forma regional de tipo siniestro.",
                "Arañazo", "Gruñido", "Mordisco", "Sorpresa"));
    }


    List<Pokemon> pokemons = new ArrayList<>();
    interface Callback {
        void cuandoFinalice(List<Pokemon> pokemons);

    }

    List<Pokemon> obtener() {
        return pokemons;
    }

    void insertar(Pokemon pokemon, Callback callback){
        pokemons.add(pokemon);
        callback.cuandoFinalice(pokemons);
    }

    void eliminar(Pokemon pokemon, Callback callback) {
        pokemons.remove(pokemon);
        callback.cuandoFinalice(pokemons);
    }

    void actualizar(Pokemon pokemon, float valoracion, Callback callback) {
        pokemon.poder = valoracion;
        callback.cuandoFinalice(pokemons);
    }
}
