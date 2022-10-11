package com.example.contador;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.contador.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    int contador_total=0;
    int contador_1=0;
    int contador_2=0;
    int contador_3=0;
    int contador_4=0;
    //TextView contadorDeClics;
    //Button augmentarElContador;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_main);

        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());

        binding.resetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {
                binding.contAll.setText("0");
                binding.cont1.setText("0");
                binding.cont2.setText("0");
                binding.cont3.setText("0");
                binding.cont4.setText("0");
                contador_1=contador_2=contador_3=contador_4=contador_total=0;

            }
        });

        binding.reset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {
                binding.cont1.setText("0");
                contador_total = contador_total - contador_1;
                binding.contAll.setText(""+contador_total);
                contador_1=0;
            }
        });
        binding.reset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {
                binding.cont2.setText("0");
                contador_total = contador_total - contador_2;
                binding.contAll.setText(""+contador_total);
                contador_2=0;
            }
        });
        binding.reset3.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {
                binding.cont3.setText("0");
                contador_total = contador_total - contador_3;
                binding.contAll.setText(""+contador_total);
                contador_3=0;
            }
        });
        binding.reset4.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {
                binding.cont4.setText("0");
                contador_total = contador_total - contador_4;
                binding.contAll.setText(""+contador_total);
                contador_4=0;
            }
        });

        binding.mas1.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {
                contador_1++;
                contador_total++;
                binding.cont1.setText(""+ contador_1);
                binding.contAll.setText(""+contador_total);
            }
        });

        binding.mas2.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {
                contador_2++;
                contador_total++;
                binding.cont2.setText(""+ contador_2);
                binding.contAll.setText(""+contador_total);
            }
        });

        binding.mas3.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {
                contador_3++;
                contador_total++;
                binding.cont3.setText(""+contador_3);
                binding.contAll.setText(""+contador_total);
            }
        });
        binding.mas4.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {
                contador_4++;
                contador_total++;
                binding.cont4.setText(""+contador_4);
                binding.contAll.setText(""+contador_total);
            }
        });
    }
        /*contadorDeClics = findViewById(R.id.texto);
        augmentarElContador = findViewById(R.id.boton);

        augmentarElContador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // aumentar el contador
                contador++;
                // mostrar el contador en el TextView
                contadorDeClics.setText("Has clicado "+ contador + " veces crack! :D");
            }
        });
    }*/
}