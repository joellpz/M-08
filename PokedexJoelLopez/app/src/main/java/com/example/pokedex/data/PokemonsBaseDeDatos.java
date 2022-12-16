package com.example.pokedex.data;


import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import com.example.pokedex.R;

import java.util.List;

@Database(entities = {Pokemon.class}, version = 2, exportSchema = false)
public abstract class PokemonsBaseDeDatos extends RoomDatabase {

    private static volatile PokemonsBaseDeDatos INSTANCIA;

    public abstract PokemonsDao obtenerPokemonsDao();

    static PokemonsBaseDeDatos obtenerInstancia(final Context context) {
        if (INSTANCIA == null) {
            synchronized (PokemonsBaseDeDatos.class) {
                if (INSTANCIA == null) {
                    INSTANCIA = Room.databaseBuilder(context,
                                    PokemonsBaseDeDatos.class, "pokemons.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCIA;
    }

    @Dao
    interface PokemonsDao {
        @Query("SELECT * FROM Pokemon")
        LiveData<List<Pokemon>> obtener();

        @Insert
        void insertar(Pokemon pokemon);

        @Update
        void actualizar(Pokemon pokemon);

        @Delete
        void eliminar(Pokemon pokemon);

        @Insert
        default void meterPokemons() {
            insertar(new Pokemon("bulbasaur", "Bulbasaur es un Pokémon de tipo planta/veneno introducido en la primera generación. Es uno de " +
                    "los Pokémon iniciales que pueden elegir los entrenadores que empiezan su aventura en la región Kanto, junto a Squirtle y Charmander " +
                    "(excepto en Pokémon Amarillo). Destaca por ser el primer Pokémon de la Pokédex Nacional y la en la Pokédex de Kanto.", "Placaje", "Gruñido", "Látigo cepa", "Drenadoras", R.drawable.bulbasaur));
            insertar(new Pokemon("charmander", "Charmander es un Pokémon de tipo fuego introducido en la primera generación. Es uno de los Pokémon iniciales que " +
                    "pueden elegir los entrenadores que empiezan su aventura en la región Kanto, junto a Bulbasaur y Squirtle, excepto en Pokémon Amarillo y Pokémon: Let's Go, Pikachu! y " +
                    "Pokémon: Let's Go, Eevee!", "Arañazo", "Gruñido", "Ascuas", "Lanzallamitas", R.drawable.charmander));
            /*insertar(new Pokemon("squirtle", "Squirtle es un Pokémon de tipo agua introducido en la primera generación. Es uno de los Pokémon iniciales que pueden " +
                    "elegir los entrenadores que empiezan su aventura en la región Kanto, junto a Bulbasaur y Charmander, excepto en Pokémon Amarillo.", "Placaje", "Látigo", "Burbuja", "Pistola Agua", R.drawable.squirtle));*/
            /*insertar(new Pokemon("pikachu", "Pikachu es un Pokémon de tipo eléctrico introducido en la primera generación. Es el Pokémon más conocido de la historia, " +
                    "mayormente por ser el acompañante del protagonista del anime, Ash Ketchum y la mascota representante de la franquicia Pokémon. Es el Pokémon inicial de Pokémon Amarillo " +
                    "y Pokémon: Let's Go Pikachu!. A partir de la segunda generación, es la forma evolucionada de Pichu.", "Látigo", "Impactrueno", "Gruñido", "Látigo", R.drawable.pikachu));*/
            /*insertar(new Pokemon("jigglypuff", "Jigglypuff es un Pokémon de tipo normal/hada3 introducido en la primera generación. Es la contraparte de Clefairy. A partir de la segunda generación es la evolución de Igglybuff. En la sexta generación se le añadió el tipo hada.",
                    "Canto", "Rizo defensa", "Destructor", "Esculpir", R.drawable.jigglypuff));*/
            /*insertar(new Pokemon("meowth", "Meowth es un Pokémon de tipo normal introducido en la primera generación, también a partir de la séptima generación posee una forma regional de tipo siniestro.",
                    "Arañazo", "Gruñido", "Mordisco", "Sorpresa", R.drawable.meowth));*/

        }

        @Query("SELECT * FROM Pokemon ORDER BY poder DESC")
        LiveData<List<Pokemon>> masValorados();
    }
}