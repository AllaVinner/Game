package generation;

public class Test {

	public static void main(String[] args) {
		int [] a = {1, 2, 3, 4};
		int [] b ={1, 5, 3, 4};
		b= a.clone();
		a[1] = 22;
		
		print(b);
		
	}
	
	public static void print(int [] a){
		for(int i=0; i < a.length; i++){
			System.out.println(a[i]);
		}
	}

}
