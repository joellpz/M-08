package com.example.retrofit_example;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.bumptech.glide.Glide;
import com.example.retrofit_example.databinding.FragmentItunesBinding;
import com.example.retrofit_example.databinding.ViewholderContenidoBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItunesFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItunesFragment extends Fragment {
    private FragmentItunesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentItunesBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ItunesViewModel itunesViewModel = new ViewModelProvider(this).get(ItunesViewModel.class);

        binding.texto.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                itunesViewModel.buscar();
                return false;
            }
        });

        itunesViewModel.respuestaMutableLiveData.observe(getViewLifecycleOwner(), new Observer<Itunes.Respuesta>() {
            @Override
            public void onChanged(Itunes.Respuesta respuesta) {
                respuesta.documents.forEach(contenido -> Log.e("ABCD", contenido.fields.power.stringValue + ", " + contenido.fields.name.stringValue + ", " + contenido.fields.imagen));
            }
        });

        ContenidosAdapter contenidosAdapter = new ContenidosAdapter();
        binding.recyclerviewContenidos.setAdapter(contenidosAdapter);

        itunesViewModel.respuestaMutableLiveData.observe(getViewLifecycleOwner(), new Observer<Itunes.Respuesta>() {
            @Override
            public void onChanged(Itunes.Respuesta respuesta) {
                contenidosAdapter.establecerListaContenido(respuesta.documents);
                // respuesta.results.forEach(r -> Log.e("ABCD", r.artistName + ", " + r.trackName + ", " + r.artworkUrl100));
            }
        });
    }

    static class ContenidoViewHolder extends RecyclerView.ViewHolder {
        ViewholderContenidoBinding binding;

        public ContenidoViewHolder(@NonNull ViewholderContenidoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class ContenidosAdapter extends RecyclerView.Adapter<ContenidoViewHolder> {
        List<Itunes.Document> contenidoList;

        @NonNull
        @Override
        public ContenidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ContenidoViewHolder(ViewholderContenidoBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ContenidoViewHolder holder, int position) {
            Itunes.Document contenido = contenidoList.get(position);

            holder.binding.title.setText(contenido.fields.name.stringValue);
            holder.binding.artist.setText(contenido.fields.power.stringValue);
            Glide.with(requireActivity()).load(contenido.fields.imagen.stringValue).into(holder.binding.artwork);
        }

        @Override
        public int getItemCount() {
            return contenidoList == null ? 0 : contenidoList.size();
        }

        void establecerListaContenido(List<Itunes.Document> contenidoList) {
            this.contenidoList = contenidoList;
            notifyDataSetChanged();
        }
    }
}