package com.company.recyclerview;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PokemonRepositorio {

    Executor executor = Executors.newSingleThreadExecutor();
    PokemonsBaseDeDatos.PokemonsDao pokemonsDao;

    PokemonRepositorio(Application application){
        pokemonsDao = PokemonsBaseDeDatos.obtenerInstancia(application).obtenerPokemonsDao();
    }

    LiveData<List<Pokemon>> obtener(){
        return pokemonsDao.obtener();
    }

    void meterPokemons (){
        //if (pokemonsDao.obtener().getValue() != null) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    pokemonsDao.meterPokemons();
                }
            });
        //}
    }
    void insertar(Pokemon pokemon){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                pokemonsDao.insertar(pokemon);
            }
        });
    }

    void eliminar(Pokemon pokemon) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                pokemonsDao.eliminar(pokemon);
            }
        });
    }

    public void actualizar(Pokemon pokemon, float poder) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                pokemon.poder = poder;
                pokemonsDao.actualizar(pokemon);
            }
        });
    }
}



        /*pokemons.add(new Pokemon("bulbasaur", "Bulbasaur es un Pokémon de tipo planta/veneno introducido en la primera generación. Es uno de " +
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
                "Arañazo", "Gruñido", "Mordisco", "Sorpresa"));*/