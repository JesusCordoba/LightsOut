package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    Button btn_solution;
    Button[] listaSoluciones;
    Button[][] listaBotones;
    Boolean[][] listaEncendidos;
    int nfila_aleatorio;
    int ncolumna_aleatorio;
    String[][] solution;
    Boolean showSolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaEncendidos = new Boolean[5][5];
        listaBotones = new Button[5][5];
        // Fila 1
        listaBotones[0][0] = (Button) findViewById(R.id.btn1);
        listaBotones[0][1] = (Button) findViewById(R.id.btn2);
        listaBotones[0][2] = (Button) findViewById(R.id.btn3);
        listaBotones[0][3] = (Button) findViewById(R.id.btn4);
        listaBotones[0][4] = (Button) findViewById(R.id.btn5);
        // Fila 2
        listaBotones[1][0] = (Button) findViewById(R.id.btn6);
        listaBotones[1][1] = (Button) findViewById(R.id.btn7);
        listaBotones[1][2] = (Button) findViewById(R.id.btn8);
        listaBotones[1][3] = (Button) findViewById(R.id.btn9);
        listaBotones[1][4] = (Button) findViewById(R.id.btn10);
        // Fila 3
        listaBotones[2][0] = (Button) findViewById(R.id.btn11);
        listaBotones[2][1] = (Button) findViewById(R.id.btn12);
        listaBotones[2][2] = (Button) findViewById(R.id.btn13);
        listaBotones[2][3] = (Button) findViewById(R.id.btn14);
        listaBotones[2][4] = (Button) findViewById(R.id.btn15);
        // Fila 4
        listaBotones[3][0] = (Button) findViewById(R.id.btn16);
        listaBotones[3][1] = (Button) findViewById(R.id.btn17);
        listaBotones[3][2] = (Button) findViewById(R.id.btn18);
        listaBotones[3][3] = (Button) findViewById(R.id.btn19);
        listaBotones[3][4] = (Button) findViewById(R.id.btn20);
        // Fila 5
        listaBotones[4][0] = (Button) findViewById(R.id.btn21);
        listaBotones[4][1] = (Button) findViewById(R.id.btn22);
        listaBotones[4][2] = (Button) findViewById(R.id.btn23);
        listaBotones[4][3] = (Button) findViewById(R.id.btn24);
        listaBotones[4][4] = (Button) findViewById(R.id.btn25);


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                listaBotones[i][j].setOnClickListener(this);
                listaEncendidos[i][j] = false;
            }
        }

        showSolution = false;
        solution = new String[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                solution[i][j] = "";
            }
        }


        listaSoluciones = new Button[5];
        // Encender 5 botones al iniciar la aplicacion
        int cont = 0;
        while (cont < 5){
            // Generar posicion aleatoria para encender los botones
            nfila_aleatorio = (int) (Math.random() * (4 + 1 - 0)) + 0;
            ncolumna_aleatorio = (int) (Math.random() * (4 + 1 - 0)) + 0;
            // Encender el boton si este no esta encendido
            if (!listaEncendidos[nfila_aleatorio][ncolumna_aleatorio]){
                Log.d(LOG_TAG, " Fila: "+nfila_aleatorio+" Columna: "+ncolumna_aleatorio);
                // Guardar el boton en el array con las soluciones
                listaSoluciones[cont] = listaBotones[nfila_aleatorio][ncolumna_aleatorio];
//                solution[nfila_aleatorio][ncolumna_aleatorio] = "S";
                // Encender el boton
                encender(listaBotones, listaEncendidos, nfila_aleatorio, ncolumna_aleatorio);
                // Sumar al contador tras haber encendido un boton
                cont++;
            }

        }


        btn_solution = (Button) findViewById(R.id.btn_solution);
        btn_solution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                for (int i = 0; i < listaSoluciones.length; i++) {
//                    // Escribir una S en los botones de la solucion
//                    listaSoluciones[i].setText("S");
//                }
                for (int fila = 0; fila < 5; fila++) {
                    for (int columna = 0; columna < 5; columna++) {
                        if(solution[fila][columna].equals("S")){
                            listaBotones[fila][columna].setText("S");
                        }
                    }
                }
                showSolution = true;

            }
        });



    }

    public void encender(Button[][] btn, Boolean[][] encender, int fila, int columna){
        Drawable fondoApagado = getDrawable(R.drawable.boton_redondo);
        // Compobar si el boton esta encendido
        if(encender[fila][columna]){
            // Apagar boton y apuntarlo en la lista de booleans
            encender[fila][columna] = false;
            btn[fila][columna].setBackground(fondoApagado);
            // Enciende o apaga los botones de los alrededores
            encenderAdyacentes(btn, encender, fila, columna);
            Log.d(LOG_TAG, encender[fila][columna].toString()+" "+btn[fila][columna].getId());

        } // Comprobar si el boton esta apagado
        else if(!encender[fila][columna]){
            // Encender boton y apuntarlo en la lista de booleans
            encender[fila][columna] = true;
            btn[fila][columna].setBackgroundColor(getResources().getColor(R.color.black));
            // Enciende o apaga los botones de los alrededores
            encenderAdyacentes(btn, encender, fila, columna);
            Log.d(LOG_TAG, encender[fila][columna].toString()+" "+btn[fila][columna].getId());

        }

        // Actualizar la solucion
        if (showSolution == true){
            if (solution[fila][columna].equals("S")){
                solution[fila][columna] = "";
                listaBotones[fila][columna].setText("");
            }else{
                solution[fila][columna] = "S";
                listaBotones[fila][columna].setText("S");
            }
        }else{
            if (solution[fila][columna].equals("S")){
                solution[fila][columna] = "";
            }else{
                solution[fila][columna] = "S";
            }
        }

    }

    public void encenderAdyacentes(Button[][] btn, Boolean[][] encender, int fila, int columna){
        Drawable fondoApagado = getDrawable(R.drawable.boton_redondo);

        // Encender o apagar el boton de abajo
        int filaAbajo = fila+1;
        if(filaAbajo <= 4){ // Comprobar que la fila de abajo no sea mayor de 4
            // Comprobar si esta encendido y apagar el boton
            if(encender[filaAbajo][columna]){
                encender[filaAbajo][columna] = false;
                btn[filaAbajo][columna].setBackground(fondoApagado);
            } // Comprobar si esta apagado y encender el boton
            else if(!encender[filaAbajo][columna]){
                encender[filaAbajo][columna] = true;
                btn[filaAbajo][columna].setBackgroundColor(getResources().getColor(R.color.black));
            }
        }

        // Encender o apagar el boton de arriba
        int filaArriba = fila-1;
        if(filaArriba >= 0){ // Comprobar que la fila de abajo no sea menor de 0
            // Comprobar si esta encendido y apagar el boton
            if(encender[filaArriba][columna]){
                encender[filaArriba][columna] = false;
                btn[filaArriba][columna].setBackground(fondoApagado);
            }// Comprobar si esta apagado y encender el boton
            else if(!encender[filaArriba][columna]){
                encender[filaArriba][columna] = true;
                btn[filaArriba][columna].setBackgroundColor(getResources().getColor(R.color.black));
            }
        }

        // Encender o apagar el boton de la derecha
        int columnaDerecha = columna+1;
        if(columnaDerecha <= 4){// Comprobar que la columna derecha no sea mayor de 4
            // Comprobar si esta encendido y apagar el boton
            if(encender[fila][columnaDerecha]){
                encender[fila][columnaDerecha] = false;
                btn[fila][columnaDerecha].setBackground(fondoApagado);
            }// Comprobar si esta apagado y encender el boton
            else if(!encender[fila][columnaDerecha]){
                encender[fila][columnaDerecha] = true;
                btn[fila][columnaDerecha].setBackgroundColor(getResources().getColor(R.color.black));
            }
        }

        // Encender o apagar el boton de la izquierda
        int columnaIzquierda = columna-1;
        if(columnaIzquierda >= 0){// Comprobar que la columna izquierda no sea mayor de 4
            // Comprobar si esta encendido y apagar el boton
            if(encender[fila][columnaIzquierda]){
                encender[fila][columnaIzquierda] = false;
                btn[fila][columnaIzquierda].setBackground(fondoApagado);
            }// Comprobar si esta apagado y encender el boton
            else if(!encender[fila][columnaIzquierda]){
                encender[fila][columnaIzquierda] = true;
                btn[fila][columnaIzquierda].setBackgroundColor(getResources().getColor(R.color.black));
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                encender(listaBotones, listaEncendidos, 0, 0);
                break;

            case R.id.btn2:
                encender(listaBotones, listaEncendidos, 0,1);
                break;

            case R.id.btn3:
                encender(listaBotones, listaEncendidos, 0,2);
                break;

            case R.id.btn4:
                encender(listaBotones, listaEncendidos, 0,3);
                break;

            case R.id.btn5:
                encender(listaBotones, listaEncendidos, 0,4);
                break;

            case R.id.btn6:
                encender(listaBotones, listaEncendidos, 1, 0);
                break;

            case R.id.btn7:
                encender(listaBotones, listaEncendidos, 1, 1);
                break;

            case R.id.btn8:
                encender(listaBotones, listaEncendidos, 1, 2);
                break;

            case R.id.btn9:
                encender(listaBotones, listaEncendidos, 1, 3);
                break;

            case R.id.btn10:
                encender(listaBotones, listaEncendidos, 1, 4);
                break;

            case R.id.btn11:
                encender(listaBotones, listaEncendidos, 2, 0);
                break;

            case R.id.btn12:
                encender(listaBotones, listaEncendidos, 2, 1);
                break;

            case R.id.btn13:
                encender(listaBotones, listaEncendidos, 2, 2);
                break;

            case R.id.btn14:
                encender(listaBotones, listaEncendidos, 2, 3);
                break;

            case R.id.btn15:
                encender(listaBotones, listaEncendidos, 2, 4);
                break;

            case R.id.btn16:
                encender(listaBotones, listaEncendidos, 3, 0);
                break;

            case R.id.btn17:
                encender(listaBotones, listaEncendidos, 3, 1);
                break;

            case R.id.btn18:
                encender(listaBotones, listaEncendidos, 3, 2);
                break;

            case R.id.btn19:
                encender(listaBotones, listaEncendidos, 3, 3);
                break;

            case R.id.btn20:
                encender(listaBotones, listaEncendidos, 3, 4);
                break;

            case R.id.btn21:
                encender(listaBotones, listaEncendidos, 4, 0);
                break;

            case R.id.btn22:
                encender(listaBotones, listaEncendidos, 4, 1);
                break;

            case R.id.btn23:
                encender(listaBotones, listaEncendidos, 4, 2);
                break;

            case R.id.btn24:
                encender(listaBotones, listaEncendidos, 4, 3);
                break;

            case R.id.btn25:
                encender(listaBotones, listaEncendidos, 4, 4);
                break;

            default:
                break;
        }
    }


}