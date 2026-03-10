package Examenarrays;

import java.util.Scanner;

public class ExamenArraysMarco {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        char[][] sala = new char[6][10];
        inicializarSala(sala);

        int opcion;

        do {
            mostrarMenu();
            opcion = pedirEntero(sc, "Elige una opción: ");

            switch (opcion) {
                case 1 -> 
                pintarSala(sala);
                
                case 2 -> 
                reservarAsientoSuelto(sc, sala);
                
                case 3 -> 
                reservarGrupo(sc, sala);
                
                case 4 -> 
                confirmarReservas(sala);
                
                case 5 -> 
                cancelarReservas(sala);
                
                case 6 -> 
                mostrarEstadisticas(sala);
                
                case 7 -> 
                System.out.println("Saliendo...");
                
                default ->
                System.out.println("Opción no válida.");
            }

        } while (opcion != 7);

        sc.close();
    }

//Funcion para inicializar la sala
    static void inicializarSala(char[][] sala) {
        for (int i = 0; i < sala.length; i++) {
            for (int j = 0; j < sala[i].length; j++) {
            	//Inicia todos los asientos como libres
                sala[i][j] = 'L';
            }
        }
    }

 //Funcion para mostrar la sala
    static void pintarSala(char[][] sala) {

    	//Primero colocamos los numeros de la columna sobre las columnas 
        System.out.print("    ");
        for (int col = 0; col < sala[0].length; col++) {
            System.out.print("  "+ col);
        }
        System.out.println();

        //Colocacion de los numeros de la fila sobre las filas
        for (int filas = 0; filas < sala.length; filas++) {
            System.out.print( "  " +filas + " | ");
            
            for (int columnas = 0; columnas < sala[filas].length; columnas++) {
                System.out.print(sala[filas][columnas] + "  ");
            }
            System.out.println();
        }
    }

//Funcion para validar si el numero de fila y columna elegido esta en un rango adecuado
    static boolean esPosicionValida(char[][] sala, int fila, int col) {
    	
         return fila >= 0 && fila < sala.length && col >= 0 && col < sala[0].length;
               
    }

//Recorremos las filas y las columnas de nuevo para contar cuantos numeros de asientos libres, ocupados o reservados hay
    static int contarEstado(char[][] sala, char estado) {
        int contador = 0;

        for (char[] fila : sala) {
        	
            for (char asiento : fila) {
                if (asiento == estado) contador++;
            }
        }
        return contador;
    }

  
    static boolean reservarAsiento(char[][] sala, int fila, int col) {

        if (!esPosicionValida(sala, fila, col)) return false;

        else if (sala[fila][col] == 'L') {
            sala[fila][col] = 'R';
            return true;
        }
        return false;
    }


    static int[] reservarGrupoEnFila(char[][] sala, int fila, int numPersonas) {

        if (fila < 0 || fila >= sala.length) return null;

        for (int col = 0; col <= sala[fila].length - numPersonas; col++) {

            boolean bloqueLibre = true;

            for (int reserva = 0; reserva < numPersonas; reserva++) {
                if (sala[fila][col + reserva] != 'L') {
                    bloqueLibre = false;
                    break;
                }
            }

            if (bloqueLibre) {
                for (int reserva = 0; reserva < numPersonas; reserva++) {
                    sala[fila][col + reserva] = 'R';
                }
                
            }
        }

      return null;
    }

 
    static void confirmarReservas(char[][] sala) {
        for (int i = 0; i < sala.length; i++) {
            for (int j = 0; j < sala[i].length; j++) {
                if (sala[i][j] == 'R') {
                	sala[i][j] = 'O';
                }
            }
        }
    }


    static void cancelarReservas(char[][] sala) {
        for (int i = 0; i < sala.length; i++) {
            for (int j = 0; j < sala[i].length; j++) {
                if (sala[i][j] == 'R') {
                	sala[i][j] = 'L';
                }
            }
        }
    }


    static void mostrarEstadisticas(char[][] sala) {

        int libres = contarEstado(sala, 'L');
        int reservados = contarEstado(sala, 'R');
        int ocupados = contarEstado(sala, 'O');

        int total = sala.length * sala[0].length;

        double porcentajeOcupacion = (ocupados * 100.0) / total;

        int filaMasOcupada = -1;
        int maxOcupados = -1;

        for (int i = 0; i < sala.length; i++) {
            int ocupadosFila = 0;
            for (char asiento : sala[i]) {
                if (asiento == 'O') ocupadosFila++;
            }
            if (ocupadosFila > maxOcupados) {
                maxOcupados = ocupadosFila;
                filaMasOcupada = i;
            }
        }

        System.out.println("Libres: " + libres);
        System.out.println("Reservados: " + reservados);
        System.out.println("Ocupados: " + ocupados);
        System.out.println("Porcentaje ocupación real: " + porcentajeOcupacion + "%");
        System.out.println("Fila con más ocupados: " + filaMasOcupada);
    }


    static void mostrarMenu() {
        System.out.println("\nTEATRO");
        
        System.out.println("1. Mostrar sala");
        
        System.out.println("2. Reservar asiento suelto");
        
        System.out.println("3. Reservar grupo en una fila");
        
        System.out.println("4. Confirmar reserva");
        
        System.out.println("5. Cancelar reservas");
        
        System.out.println("6. Estadísticas");
        
        System.out.println("7. Salir");
    }


    static int pedirEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("No es valido. Intenta de nuevo.");
            }
        }
    }


    static void reservarAsientoSuelto(Scanner sc, char[][] sala) {
        int fila = pedirEntero(sc, "Fila: ");
        int col = pedirEntero(sc, "Columna: ");

        if (reservarAsiento(sala, fila, col)) {
            System.out.println("Asiento reservado.");
        } else {
            System.out.println("No se pudo reservar.");
        }
    }

   
    static void reservarGrupo(Scanner sc, char[][] sala) {
        int fila = pedirEntero(sc, "Fila: ");
        int num = pedirEntero(sc, "Número de personas: ");

        int[] bloque = reservarGrupoEnFila(sala, fila, num);

        if (bloque == null) {
            System.out.println("No hay hueco para ese grupo.");
        } else {
            System.out.println("Grupo reservad " + bloque[0] + " a " + bloque[1]);
        }
    }
}
