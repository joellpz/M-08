package com.example.pokedex.data;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pokedex.R;
import com.example.pokedex.databinding.FragmentRecyclerPokedexBinding;
import com.example.pokedex.databinding.ViewholderPokemonBinding;

import java.util.List;
import java.util.Locale;

public class RecyclerPokedexFragment extends Fragment {


    private FragmentRecyclerPokedexBinding binding;
    protected PokemonsViewModel pokemonsViewModel;
    private NavController navController;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentRecyclerPokedexBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pokemonsViewModel = new ViewModelProvider(requireActivity()).get(PokemonsViewModel.class);
        navController = Navigation.findNavController(view);

        // crear el Adaptador
        PokemonsAdapter pokemonsAdapter = new PokemonsAdapter();

        // asociar el Adaptador con el RecyclerView
        binding.recyclerView.setAdapter(pokemonsAdapter);
        binding.btnPower.setOnClickListener(view1 -> navController.navigate(R.id.recyclerPoderFragment));
        binding.btnId.setOnClickListener(view1 -> navController.navigate(R.id.recyclerPokedexFragment));

        // obtener el array de Elementos, y pasarselo al Adaptador
        obtenerPokemons().observe(getViewLifecycleOwner(), pokemonsAdapter::establecerLista);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posicion = viewHolder.getAdapterPosition();
                Pokemon pokemon = pokemonsAdapter.obtenerElemento(posicion);
                pokemonsViewModel.eliminar(pokemon);

            }
        }).attachToRecyclerView(binding.recyclerView);

    }
    LiveData<List<Pokemon>> obtenerPokemons(){
        return pokemonsViewModel.obtener();
    }

    class PokemonsAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

        // referencia al Array que obtenemos del ViewModel
        List<Pokemon> pokemons;

        // crear un nuevo ViewHolder
        @NonNull
        @Override
        public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PokemonViewHolder(ViewholderPokemonBinding.inflate(getLayoutInflater(), parent, false));
        }

        // rellenar un ViewHolder en una posición del Recycler con los datos del elemento que
        // esté en esa misma posición en el Array
        @Override
        public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {

            Pokemon pokemon = pokemons.get(position);

            holder.binding.nombre.setText(pokemon.nombre.toUpperCase(Locale.ROOT));
            holder.binding.valoracion.setRating(pokemon.poder);
            holder.binding.image.setImageResource(pokemon.imagen);

            holder.binding.valoracion.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
                if (fromUser) {
                    pokemonsViewModel.actualizar(pokemon, rating);
                }
            });

            holder.itemView.setOnClickListener(v -> {
                pokemonsViewModel.seleccionar(pokemon);
                navController.navigate(R.id.action_mostrarElementoFragment);
            });

        }

        // informar al Recycler de cuántos elementos habrá en la lista
        @Override
        public int getItemCount() {
            return pokemons != null ? pokemons.size() : 0;
        }

        // establecer la referencia a la lista, y notificar al Recycler para que se regenere
        @SuppressLint("NotifyDataSetChanged")
        public void establecerLista(List<Pokemon> pokemons) {
            this.pokemons = pokemons;
            notifyDataSetChanged();
        }

        public Pokemon obtenerElemento(int posicion) {
            return pokemons.get(posicion);
        }
    }

    // Clase para inicializar el ViewBinding en los ViewHolder
    static class PokemonViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderPokemonBinding binding;

        public PokemonViewHolder(ViewholderPokemonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}