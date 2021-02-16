package assignment_2;

/**
* CLASS:        com.assignment_2.Rational
* CREATED:      2/16/2021
* DESCRIPTION:  
*
*/
public class Rational {
  private int Numerator, Denominator;

  public Rational(int num, int denom) throws Exception {
    this.Numerator = num;
    if (denom != 0) {
      this.Denominator = denom;
    } else {
      throw new Exception("Denominator is invalid (Is the Denominator zero?); cannot create Rational object. Values given to constructor: numerator=" + num + " | denominator = " + denom + "\n");
    }
  }

  public int GetNumerator() {
    return this.Numerator;
  }

  public int GetDenominator() {
    return this.Denominator;
  }

  /** Get the Greatest Common Divisor between two numbers */
  private int GetGCD(int num, int denom){
    // Initiate the GCD to 1
    int gcd = 1;

    //  loop through all values less than or equal to the lesser of the two numbers, and if both are evenly divisible by
    //    'i', set gcd equal to 'i'
    for (int i = 1; i <= Math.abs(num) && i <= Math.abs(denom); i++) {
      if ((num % i == 0) && (denom % i == 0)) {
        gcd = i;
        //System.out.println("\tGCD == " + gcd);
      }
    }
    return gcd;
  }

  public Rational Add(Rational rhs) throws Exception {
    //  Initialize variables with the sum of the adjusted numerator along with the least common denominator
    int sum = (this.Numerator * rhs.Denominator) + (rhs.Numerator * this.Denominator);
    int lcd = this.Denominator * rhs.Denominator;

    // Get the greatest common divisor between the new numerator and denominator and reduce the fraction
    int gcd = this.GetGCD(sum, lcd);
    sum /= gcd;
    lcd /= gcd;

    //  Return a new Rational object using the newly reduced rational number
    try {
      return new Rational(sum, lcd);
    } catch (Exception e) {
      throw e;
    }
  }

  public Rational Subtract(Rational rhs) throws Exception {
    //  Initialize variables with difference of the adjusted numerators along with the least common denominator
    int diff = (this.Numerator * rhs.Denominator) - (rhs.Numerator * this.Denominator);
    int lcd = this.Denominator * rhs.Denominator;

    // Get the greatest common divisor between the new numerator and denominator and reduce the fraction
    int gcd = this.GetGCD(diff, lcd);
    diff /= gcd;
    lcd /= gcd;

    //  Return a new Rational object using the newly reduced rational number
    try {
      return new Rational(diff, lcd);
    } catch (Exception e) {
      throw e;
    }
  }

  public Rational Multiply(Rational rhs) throws Exception {
    //  Initialize variables with the products of the numerators and denominators
    int num = this.Numerator * rhs.Numerator;
    int denom = this.Denominator * rhs.Denominator;

    // Get the greatest common divisor between the new numerator and denominator and reduce the fraction
    int gcd = this.GetGCD(num, denom);
    num /= gcd;
    denom /= gcd;

    //  Return a new Rational object using the newly reduced rational number
    try {
      return new Rational(num, denom);
    } catch (Exception e) {
      throw e;
    }
  }

  public Rational Divide(Rational rhs) throws Exception {
    //  Initialize variables with the product of cross-multiplying the numerators and denominators
    int num = this.Numerator * rhs.Denominator;
    int denom = this.Denominator * rhs.Numerator;

    // Get the greatest common divisor between the new numerator and denominator and reduce the fraction
    int gcd = this.GetGCD(num, denom);

    //  Return a new Rational object using the newly reduced rational number
    try {
      return new Rational((num / gcd), (denom / gcd));
    } catch (Exception e) {
      throw e;
    }
  }
  /** Returns the Rational object's numerator and denominator, delimited with a '/' as a String */
  public String toString(){
    return this.Numerator+ " / " + this.Denominator;
  }

  /** Returns the Rational object's numerator and denominator, delimited with a '/' as a String */
  public float toDecimal(){
    return (float)this.Numerator/(float)this.Denominator;
  }
}
