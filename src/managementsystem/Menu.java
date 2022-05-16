package managementsystem;

import java.util.Scanner;

public class Menu {

    public static void main(String[] args){
        Scanner testScanner = new Scanner(System.in);
        System.out.println("Test: Input a number:");


        int testNum = testScanner.nextInt();
        System.out.println("Number is: " + testNum);
    }

}
