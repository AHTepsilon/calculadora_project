package com.example.calcular;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView preguntaText;
    private EditText respuestaUsuario;
    private TextView contadorText;
    private TextView puntajeText;
    private Button boton;

    private Pregunta preguntaActual;
    private int tiempo;
    private int puntaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preguntaText = findViewById(R.id.preguntaText);
        respuestaUsuario = findViewById(R.id.respuestaUsuario);
        contadorText = findViewById(R.id.contadorText);
        puntajeText = findViewById(R.id.puntajeText);
        boton = findViewById(R.id.boton);

        puntaje = 0;
        puntajeText.setText("Puntaje: " + puntaje);

        tiempo = 30;
        contadorText.setText(" " + tiempo);

        /*new Thread(
                () ->
        {
            while(tiempo > 0)
            {
                try {
                    tiempo--;
                    runOnUiThread(
                            () ->
                            {
                                contadorText.setText("" + contadorText);
                            }
                    );

                    Thread.sleep(1000);
                } catch (Exception e) {
                    Log.e("ERROR", e.toString());
                }
            }

        }
        ).start();*/

        generarNuevaPregunta();

        //preguntaText.setText(preguntaActual.getPregunta());

        boton.setOnClickListener(
            (view) ->
            {
                verificarRespuesta();
            }
        );
    }

    public void verificarRespuesta()
    {
        String respuestaTexto = respuestaUsuario.getText().toString();
        int respuestaInt = (int) Integer.parseInt(respuestaTexto);

        if(respuestaInt == preguntaActual.getRespuesta())
        {
            Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
            puntaje += 5;
        }

        else
        {
            Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show();
        }

        generarNuevaPregunta();
    }

    public void generarNuevaPregunta()
    {
        preguntaActual = new Pregunta();
        preguntaText.setText(preguntaActual.getPregunta());
    }
}