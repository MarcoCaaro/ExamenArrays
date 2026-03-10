package Examenarrays;

import java.util.Scanner;

public class examenarrays {

	public static void main(String[] args) {
		 
		Scanner sc = new Scanner(System.in);
		
		int[][] sala = new int [6][10];
		for (int i = 0; i <= sala.length; i++ ) {
			for (int j = 0; j < sala [0].length; j++) {
				sala[i][j] = 'L';
				System.out.print(sala[i][j]);
			}
			System.out.println("");
		}
		sc.close();
	}
	 static void pintarSala(char[][] sala) {

	        System.out.print("    ");
	        for (int col = 0; col < sala[0].length; col++) {
	            System.out.printf("%2d ", col);
	        }
	        System.out.println();

	        for (int fila = 0; fila < sala.length; fila++) {
	            System.out.printf("%2d | ", fila);
	            for (int col = 0; col < sala[fila].length; col++) {
	                System.out.print(sala[fila][col] + "  ");
	            }
	            System.out.println();
	        }
	    }
}
