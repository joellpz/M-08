package com.company.recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.company.recyclerview.databinding.FragmentRecyclerPokemonsBinding;
import com.company.recyclerview.databinding.ViewholderPokemonBinding;

import java.util.List;
import java.util.Locale;


public class  RecyclerElementosFragment extends Fragment {

    private FragmentRecyclerPokemonsBinding binding;
    private PokemonsViewModel pokemonsViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentRecyclerPokemonsBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pokemonsViewModel = new ViewModelProvider(requireActivity()).get(PokemonsViewModel.class);
        navController = Navigation.findNavController(view);

        binding.irANuevoElemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_recyclerElementosFragment_to_nuevoElementoFragment);
            }
        });

        PokemonsAdapter pokemonsAdapter = new PokemonsAdapter();
        binding.recyclerView.setAdapter(pokemonsAdapter);

        pokemonsViewModel.obtener().observe(getViewLifecycleOwner(), new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                pokemonsAdapter.establecerLista(pokemons);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posicion = viewHolder.getAdapterPosition();
                Pokemon pokemon = pokemonsAdapter.obtenerPokemon(posicion);
                pokemonsViewModel.eliminar(pokemon);

            }
        }).attachToRecyclerView(binding.recyclerView);
    }

    class PokemonViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderPokemonBinding binding;

        public PokemonViewHolder(ViewholderPokemonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class PokemonsAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

        List<Pokemon> pokemons;

        @NonNull
        @Override
        public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PokemonViewHolder(ViewholderPokemonBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {

            Pokemon pokemon = pokemons.get(position);

            holder.binding.nombre.setText(pokemon.nombre.toUpperCase(Locale.ROOT));
            holder.binding.valoracion.setRating(pokemon.poder);
            holder.binding.image.setImageResource(getResources().getIdentifier(pokemon.nombre, "drawable", "com.company.recyclerview"));

            holder.binding.valoracion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if(fromUser) {
                        pokemonsViewModel.actualizar(pokemon, rating);
                    }
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pokemonsViewModel.seleccionar(pokemon);
                    navController.navigate(R.id.action_recyclerElementosFragment_to_mostrarElementoFragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return pokemons != null ? pokemons.size() : 0;
        }

        public void establecerLista(List<Pokemon> pokemons){
            this.pokemons = pokemons;
            notifyDataSetChanged();
        }

        public Pokemon obtenerPokemon(int posicion){
            return pokemons.get(posicion);
        }
    }
}