import java.util.ArrayList;

import aI_Occupation.Player;

public class Tester {

	
	public static void main(String[] args) {
		 int [] a = {1 ,4, 6, 5};
		 
		 a[1] = prove(a);
		 print(a);
	}
	
	public static void print(ArrayList<Integer> i){
		for (Integer inter : i) {
			System.out.println(inter);
		}
	}
	
	public static void print(int []  a){
		for (int i: a) {
			System.out.println(i);
		}
	}
	
}


