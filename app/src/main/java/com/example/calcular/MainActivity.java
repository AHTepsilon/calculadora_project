package com.example.calcular;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView preguntaText;
    private EditText respuestaUsuario;
    private TextView contadorText;
    private TextView puntajeText;
    private Button boton;
    private Button restartButton;
    private Pregunta preguntaActual;
    private int tiempo;

    private int puntaje;
    private boolean restartReady;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preguntaText = findViewById(R.id.preguntaText);
        respuestaUsuario = findViewById(R.id.respuestaUsuario);
        contadorText = findViewById(R.id.contadorText);
        puntajeText = findViewById(R.id.puntajeText);
        boton = findViewById(R.id.boton);
        restartButton = findViewById(R.id.restartButton);

        puntaje = 0;
        puntajeText.setText("Puntaje: " + puntaje);

        tiempo = 30;
        contadorText.setText(" " + tiempo);

        new Thread(
                () ->
        {
            while(tiempo > 0)
            {
                try {
                    tiempo--;
                    runOnUiThread(
                            () ->
                            {
                                contadorText.setText("" + tiempo);
                                if(tiempo > 0)
                                {
                                    restartButton.setVisibility(View.INVISIBLE);
                                }
                                else
                                {
                                    restartButton.setVisibility(View.VISIBLE);
                                }
                            }
                    );

                    Thread.sleep(1000);
                } catch (Exception e) {
                    Log.e("ERROR", e.toString());
                }
            }

        }
        ).start();

        generarNuevaPregunta();

        //preguntaText.setText(preguntaActual.getPregunta());

        boton.setOnClickListener(
            (view) ->
            {
                verificarRespuesta();
            }
        );

        restartButton.setOnClickListener(
                (view) ->
                {
                    tiempo = 30;
                    puntaje = 0;
                    puntajeText.setText("Puntaje: " + puntaje);
                    restartButton.setVisibility(View.INVISIBLE);
                    generarNuevaPregunta();

                    new Thread(
                            () ->
                            {
                                while(tiempo > 0)
                                {
                                    try {
                                        tiempo--;
                                        runOnUiThread(
                                                () ->
                                                {
                                                    contadorText.setText("" + tiempo);
                                                    if(tiempo > 0)
                                                    {
                                                        restartButton.setVisibility(View.INVISIBLE);
                                                    }
                                                    else
                                                    {
                                                        restartButton.setVisibility(View.VISIBLE);
                                                    }
                                                }
                                        );

                                        Thread.sleep(1000);
                                    } catch (Exception e) {
                                        Log.e("ERROR", e.toString());
                                    }
                                }

                            }
                    ).start();
                }
        );

        if(tiempo < 1)
        {
            restartReady = true;
            restartButton.setVisibility(View.VISIBLE);
        }
    }

    public void verificarRespuesta()
    {
        String respuestaTexto = respuestaUsuario.getText().toString();
        int respuestaInt = (int) Integer.parseInt(respuestaTexto);

        if(respuestaInt == preguntaActual.getRespuesta() && tiempo > 0)
        {
            Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
            puntaje += 5;
            puntajeText.setText("Puntaje: " + puntaje);
            tiempo += 5;
        }

        else if(respuestaInt != preguntaActual.getRespuesta() && tiempo > 0)
        {
            Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
            puntaje -= 2;
            puntajeText.setText("Puntaje: " + puntaje);
        }

        generarNuevaPregunta();
    }

    public void generarNuevaPregunta()
    {
        preguntaActual = new Pregunta();
        preguntaText.setText(preguntaActual.getPregunta());
    }
}