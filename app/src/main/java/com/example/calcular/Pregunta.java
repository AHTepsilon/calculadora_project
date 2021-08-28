package com.example.calcular;

public class Pregunta
{
    private int opA, opB;
    private String operador;
    private String[] operadores = {"+", "-", "x", "/"};

    public Pregunta()
    {
        this.opA = (int) (Math.random() * 11)+1;
        this.opB = (int) (Math.random() * 11)+1;
        int pos = (int) (Math.random() * 4);
        this.operador = operadores[pos];
    }

    public String getPregunta()
    {
        return opA + " " + operador + " " + opB;
    }

    public int getRespuesta()
    {
        int respuesta = 0;
        switch(operador)
        {
            case "+":
                respuesta = this.opA + this.opB;
                break;
            case "-":
                respuesta = this.opA - this.opB;
                break;
            case "x":
                respuesta = this.opA * this.opB;
                break;
            case "/":
                respuesta = this.opA / this.opB;
                break;
        }

        return respuesta;
    }
}
