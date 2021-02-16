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
    // Initiate the gcd to 1
    int gcd = 1;

    //  loop through all values less than or equal to the lesser of the two numbers, and if both are evenly divisible by
    //    'i', set gcd equal to 'i'
    for (int i = 1; i <= num && i <= denom; i++) {
      if ((num % i == 0) && (denom % i == 0)) {
        gcd = i;
      }
    }
    return gcd;
  }

  public Rational Add(Rational rhs) throws Exception {
    //  Initialize variables with the sum of the adjusted numerator along with the least common denominator
    int sum = (this.Numerator * rhs.Denominator) + (rhs.Numerator * this.Denominator);
    int lcd = this.Denominator * rhs.Denominator;

    // Get the greatest common divisor between the new numerator and denominator
    int gcd = this.GetGCD(sum, lcd);

    try {
      return new Rational((sum / gcd), (lcd / gcd));
    } catch (Exception e) {
      throw e;
    }
  }

  public Rational Subtract(Rational rhs) throws Exception {
    //  Initialize variables with difference of the adjusted numerators along with the least common denominator
    int diff = (this.Numerator * rhs.Denominator) - (rhs.Numerator * this.Denominator);
    int lcd = this.Denominator * rhs.Denominator;

    // Get the greatest common divisor between the new numerator and denominator
    int gcd = this.GetGCD(diff, lcd);

    //  Reduce the obtained numerator and denominator, create a new Rational object using the result, and return
    try {
      return new Rational((diff / gcd), (lcd / gcd));
    } catch (Exception e) {
      throw e;
    }
  }

  public Rational Multiply(Rational rhs) throws Exception {
    //  Initialize variables with the products of the numerators and denominators
    int num = this.Numerator * rhs.Numerator;
    int denom = this.Denominator * rhs.Denominator;

    // Get the greatest common divisor between the new numerator and denominator
    int gcd = this.GetGCD(num, denom);

    try {
      return new Rational((num / gcd), (denom / gcd));
    } catch (Exception e) {
      throw e;
    }
  }

  public Rational Divide(Rational rhs) throws Exception {
    //  Initialize variables with the product of cross-multiplying the numerators and denominators
    int num = this.Numerator * rhs.Denominator;
    int denom = this.Denominator * rhs.Numerator;

    // Get the greatest common divisor between the new numerator and denominator
    int gcd = this.GetGCD(num, denom);

    //  Reduce the obtained numerator and denominator, create a new Rational object using the result, and return
    try {
      return new Rational((num / gcd), (denom / gcd));
    } catch (Exception e) {
      throw e;
    }
  }
  /** Returns the Rational object's numerator and denominator, delimited with a '/' as a String */
  public String toString(){
    return new String(String.valueOf(this.Numerator)+ " / " + String.valueOf(this.Denominator);
  }

  /** Returns the Rational object's numerator and denominator, delimited with a '/' as a String */
  public float toDecimal(){
    return (float)this.Numerator/(float)this.Denominator;
  }
}
