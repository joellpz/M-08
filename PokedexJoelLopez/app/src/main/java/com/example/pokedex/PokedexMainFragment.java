package com.example.pokedex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.pokedex.databinding.FragmentPokedexMainBinding;
import com.example.pokedex.data.NuevoPokemonFragment;
import com.google.android.material.tabs.TabLayoutMediator;

public class PokedexMainFragment extends Fragment {
            private FragmentPokedexMainBinding binding;
            private NavController navController;
            @Override
            public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                return (binding = FragmentPokedexMainBinding.inflate(inflater, container, false)).getRoot();
            }

            @Override
            public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
                super.onViewCreated(view, savedInstanceState);

                navController = Navigation.findNavController(view);

                binding.btnBack.setOnClickListener(view1 -> navController.navigate(R.id.action_pokedexMainFragment_to_nav_graph));

                binding.viewPager.setAdapter(new FragmentStateAdapter(this) {
                    @NonNull
                    @Override
                    public Fragment createFragment(int position) {
                        switch (position) {
                            case 0: default:
                                return new HomeFragment();
                            case 1:
                                return new PokedexPreFragment();
                            case 2:
                                return new NuevoPokemonFragment();
                        }
                    }

                    @Override
                    public int getItemCount() {
                        return 3;
                    }
                });
                new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
                    switch (position) {
                        case 0: default:
                            tab.setIcon(R.drawable.home_icon).setText("HOME");
                            break;
                        case 1:
                            tab.setIcon(R.drawable.pokedex_icon).setText("POKEDEX");
                            break;
                        case 2:
                            tab.setIcon(R.drawable.addnew_icon).setText("ADD NEW");
                            break;
                    }
                }).attach();
            }
        }