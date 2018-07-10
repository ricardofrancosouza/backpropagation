package view;

import mod.CGeneticNet;
import mod.Simplenet;

public class main {

	public static void main(String[] args) {
		 CGeneticNet nnuga = new CGeneticNet();
	        Simplenet sn = nnuga.Run();
	        if(sn != null){
	            System.out.println("Solution found!!");
	            System.out.println("0 xor 0 = " + sn.rodar(0,0,0));
	            System.out.println("0 xor 1 = " + sn.rodar(0,1,1));
	            System.out.println("1 xor 0 = " + sn.rodar(1,0,1));
	            System.out.println("1 xor 1 = " + sn.rodar(1,1,0));
		} else {
	            System.out.println("Solution not found!!");
		}

	}

}
