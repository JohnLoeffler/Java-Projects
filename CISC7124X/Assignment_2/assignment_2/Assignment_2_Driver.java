package assignment_2;

import java.util.Random;

/**
* CLASS:        com.assignment_2.Assignment_2_Driver
* CREATED:      2/16/2021
* DESCRIPTION:  The driver class for the classes required by Assignment # 2
*/
public class Assignment_2_Driver{

  public static void main(String[] args) {
    //  Assign a constant to represent the maximum value of a numerator or denominator as passed to the program
    final int N = Integer.parseUnsignedInt(args[0]);

    //  Create, seed, and prime a random number generator
    Random rand = new Random(System.currentTimeMillis());
    for(int i = 0; i < 1000; i++){
      rand.nextInt();
    }

    //  Create a pair of Rational variables
    Rational lhs, rhs;

    //  Test that the Rational object properly rejects zero as a denominator
    try {
      lhs = new Rational(1,0);
    } catch (Exception e) {
      System.err.println(e.getMessage());
      e.printStackTrace(System.err);
      System.out.println("Rational constructor properly rejects a zero denominator");
    }

    //  Create two new Rational objects to perform operations on using random ints, with the denominator between one and
    //    n, and the numerator between zero and n, and randomly assigned a positive or negative sign
    try {
      if(rand.nextBoolean()){
        lhs = new Rational(rand.nextInt(N), rand.nextInt(N)+1);
      }else{
        lhs = new Rational(rand.nextInt(N)*-1, rand.nextInt(N)+1);
      }

      if(rand.nextBoolean()){
        rhs = new Rational(rand.nextInt(N), rand.nextInt(N)+1);
      }else{
        rhs = new Rational(rand.nextInt(N)*-1, rand.nextInt(N)+1);
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
      e.printStackTrace(System.err);
    }

  }
}
