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
    for (int i = 0; i < 1000; i++) {
      rand.nextInt();
    }

    /* ******************* PROBLEM NUMBER 1 *********************** */
    System.out.println("===============================\n\tPROBLEM # 1\n===============================");

    //  Create a pair of Rational variables and initialize them to 'null'
    Rational lhs = null, rhs = null;

    //  Create two new Rational objects to perform operations on using random ints, with the denominator between one and
    //    n, and the numerator between zero and n, and randomly assigned a positive or negative sign
    try {
      if (rand.nextBoolean()) {
        lhs = new Rational(rand.nextInt(N), rand.nextInt(N) + 1);
      } else {
        lhs = new Rational(rand.nextInt(N) * -1, rand.nextInt(N) + 1);
      }

      if (rand.nextBoolean()) {
        rhs = new Rational(rand.nextInt(N), rand.nextInt(N) + 1);
      } else {
        rhs = new Rational(rand.nextInt(N) * -1, rand.nextInt(N) + 1);
      }
    } catch (Exception e) {
      //  If exception is thrown by the Rational constructor, one or both variables will be null, so print an error
      //    message and exit
      e.printStackTrace(System.err);
      System.err.println("[Error]: Rational object creation failed. One or both are null.");
      System.exit(1);
    }

    //  Print the generated Rationals before performing operations to verify the output of the class's operation methods
    System.out.println("The LHS Rational == '" + lhs.toString() + "' and the RHS Rational == '" + rhs.toString() + "'");

    /* Test the Rational.Add() method */
    try {
      Rational result = lhs.Add(rhs);
      System.out.println("  Addition resulted in '" + result.toString());
    } catch (Exception e) {
      System.err.println(e.getMessage());
      e.printStackTrace(System.err);
    }
    /* Test the Rational.Subtract() method */
    try {
      Rational result = lhs.Subtract(rhs);
      System.out.println("  Subtraction resulted in '" + result.toString());
    } catch (Exception e) {
      System.err.println(e.getMessage());
      e.printStackTrace(System.err);
    }
    /* Test the Rational.Multiply() method */
    try {
      Rational result = lhs.Multiply(rhs);
      System.out.println("  Multiplication resulted in '" + result.toString());
    } catch (Exception e) {
      System.err.println(e.getMessage());
      e.printStackTrace(System.err);
    }
    /* Test the Rational.Divide() method */
    try {
      Rational result = lhs.Divide(rhs);
      System.out.println("  Division resulted in '" + result.toString());
    } catch (Exception e) {
      System.err.println(e.getMessage());
      e.printStackTrace(System.err);
    }

    /* ******************* PROBLEM NUMBER 2 *********************** */
    System.out.println("\n\n===============================\n\tPROBLEM # 2\n===============================");

    ParkingMeter pm = new ParkingMeter(
        Integer.parseUnsignedInt(args[1]),
        Integer.parseUnsignedInt(args[2]),
        Integer.parseUnsignedInt(args[3])
    );
/*
    //  Test the ParkingMeter.GetMaxTime() method
    System.out.println("Max Time: " + pm.GetMaxTime());

    //  Test the ParkingMeter.GetRate() method
    System.out.println("Per Quarter Rate: " + pm.GetRate());

    //  Test the ParkingMeter.GetTimeRemaining() method
    System.out.println("Time Remaining At Start: " + pm.GetTimeRemaining());

    //  Use a set number of quarters
    int quarters = Integer.parseUnsignedInt(args[4]);

    //  Test the ParkingMeter.AddTime() method
    pm.AddTime();
    quarters--;
    while (quarters > 0) {
      try {
        Thread.sleep(10000);
        System.out.println("Time Remaining After Sleep: " + pm.GetTimeRemaining());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      pm.AddTime();
      quarters--;
    }
    pm.Shutdown();
*/
    /* ******************* PROBLEM NUMBER 3 *********************** */
    System.out.println("\n\n===============================\n\tPROBLEM # 3\n===============================");

    Car car = new Car(Float.parseFloat(args[5]), Float.parseFloat(args[6]));
    System.out.println("Fuel capacity for the car: " + car.GetCapacity());
    System.out.println("Fuel efficiency for the car: " + car.GetEfficiency());
    System.out.println("Maximum range for the car: " + (car.GetEfficiency()*car.GetCapacity()));
    System.out.println("------------------\nAdding gas to the car\n------------------");
    System.out.println(car.ReportGas());
    car.Drive(10.0F);
    car.AddGas(car.GetCapacity()*0.25F);
    System.out.println(car.ReportGas());
    System.out.println("With this much gas, the car can drive " + (car.GetGas()*car.GetEfficiency()) + " miles");

    System.out.println("------------------\nDriving until out of gas\n------------------");
    while(car.GetGas() > 0.0F){
      car.Drive(10.0F);
      System.out.println("\t" + car.ReportGas());
    }

    System.out.println("------------------\nFilling up at a gas station until full\n------------------");
    car.AddGas(car.GetCapacity());
    System.out.println("\t" + car.ReportGas());
    System.out.println("------------------\nDriving until out of gas\n------------------");
    while(car.GetGas() > 0.0F){
      car.Drive(10.0F);
      System.out.println("\t" + car.ReportGas());
    }

    /* ******************* PROBLEM NUMBER 4 *********************** */
    System.out.println("\n\n===============================\n\tPROBLEM # 4\n===============================");


  }
}
