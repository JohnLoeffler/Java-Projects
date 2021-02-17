import java.lang.reflect.Array;
import java.util.ArrayList;

/**
* CLASS:        com.PACKAGE_NAME.HugeInteger
* CREATED:      2/17/2021
* DESCRIPTION:  
*
*/
public class HugeInteger {
  private ArrayList<Integer>  Number;
  private boolean             bPositive;

  public HugeInteger(String _number){
    this.Number = new ArrayList<>();
    this.Parse(_number);
  }

  public HugeInteger(HugeInteger _rhs){
    this.Number = new ArrayList<>();
    this.Number.addAll(_rhs.Number);
    this.bPositive = _rhs.bPositive;
  }

  public void Parse(String _number){
    StringBuilder sb = new StringBuilder(_number);

    //  Determine the number's sign, assign it to the member variable 'bPositive', and delete sign from String
    if(sb.charAt(0) == '-'){
      this.bPositive = false;
      sb.deleteCharAt(0);
    }else{
      this.bPositive = true;
    }
    for(int i = 0; i < sb.length(); i++){
      this.Number.add(Character.getNumericValue(sb.charAt(i)));
    }
  }

  /** Converts the HugeInteger into a String object representation of a signed int */
  @Override
  public String   toString(){
    StringBuilder sb = new StringBuilder();
    if(!this.bPositive) sb.append("-");
    for (Integer i: this.Number) sb.append(i);
    return sb.toString();
  }

  public int CompareAbsoluteValue(HugeInteger _rhs){
    int _size = this.Number.size() - _rhs.Number.size();
    if(_size != 0){
       return _size;
    }else{
      for(int i = 0; i < this.Number.size(); i++){
        if(this.GetDigitAt(i) > _rhs.GetDigitAt(i)) return 1;
        else if(this.GetDigitAt(i) < _rhs.GetDigitAt(i)) return -1;
      }
      return 0;
    }
  }



  /* Returns the int value contained at the given index of the HugeInteger's vector */
  public int      GetDigitAt(int index){return this.Number.get(index);}

  /*
  *  Superficial comparison methods
  */

  /** If int returned > 0, lhs is larger; if negative, rhs is larger. If zero, lhs and rhs have same number of digits */
  public int      CompareNumberOfDigits(HugeInteger rhs){return this.Number.size() - rhs.Number.size();}

  /** If int returned > 0, lhs is larger; if negative, rhs is larger. If zero, lhs and rhs have the same sign */
  public int      CompareSign(HugeInteger rhs){return this.bPositive == rhs.bPositive ? 0 : this.bPositive ? 1 : -1;}

  /*
  * Equality and relational comparison methods
  */

  /** Returns true if this HugeInteger is equal to another, returns false otherwise */
  public boolean  IsEqualTo(HugeInteger rhs) {
    // If the two HugeIntegers are not the same size or have different signs, they aren't equal
    if(this.CompareNumberOfDigits(rhs) != 0 || this.CompareSign(rhs) != 0) return false;

    //  Scan the vectors, returning false if inequality exists at 'i', true if loops ends without finding any inequality
    for(int i = 0; i < this.Number.size(); i++) {
      if (this.GetDigitAt(i) != rhs.GetDigitAt(i)) return false;
    }
    return true;
  }

  /** Returns true if this HugeInteger is not equal to another, returns false otherwise */
  public boolean  IsNotEqualTo(HugeInteger rhs){return !this.IsEqualTo(rhs);}

  /** Returns true if this HugeInteger is strictly greater than another, returns false otherwise */
  public boolean  IsGreaterThan(HugeInteger rhs){
    //  Trivially compare if lhs is greater than rhs
    if(this.CompareNumberOfDigits(rhs) > 0 || this.CompareSign(rhs) > 0) return true;

    //  Scan vectors from most significant digit to least, returning true or false depending on type of inequality found
    for(int i = 0; i < this.Number.size(); i++){
      if(this.GetDigitAt(i) < rhs.GetDigitAt(i)) return false;
      else if(this.GetDigitAt(i) > rhs.GetDigitAt(i)) return true;
    }

    // If loop ends without returning, the numbers are equal
    return false;
  }

  /** Returns true if this HugeInteger is greater than or equal to another, returns false otherwise */
  public boolean  IsGreaterThanOrEqualTo(HugeInteger rhs){
    //  Trivially compare if lhs is less than the rhs
    if(this.CompareNumberOfDigits(rhs) <= 0 || this.CompareSign(rhs) <= 0) return true;

    //  Scan vectors from most significant digit to least, returning true or false depending on type of inequality found
    for(int i = 0; i < this.Number.size(); i++){
      if(this.GetDigitAt(i) < rhs.GetDigitAt(i)) return false;
      else if(this.GetDigitAt(i) > rhs.GetDigitAt(i)) return true;
    }

    // If loop ends without returning, the numbers are equal
    return true;
  }

  /** Returns true if this HugeInteger is strictly less than another, returns false otherwise */
  public boolean  IsLessThan(HugeInteger rhs){return !this.IsGreaterThanOrEqualTo(rhs);}

  /** Returns true if this HugeInteger is less than or equal to another, returns false otherwise */
  public boolean  IsLessThanOrEqualTo(HugeInteger rhs){return !this.IsGreaterThan(rhs);}

  /** Returns true if the int value of HugeInteger is exactly zero, returns false otherwise */
  public boolean  IsZero(){ return Integer.getInteger(this.toString()) == 0;}

  /** Adds another HugeInteger to this one and returns the result as a new HugeInteger */
  public HugeInteger Add(HugeInteger _rhs){
    // Create a StringBuilder to contain the result of the addition
    StringBuilder _result= new StringBuilder();

    //  Create local objects to protect original HugeInteger object states and a bNegative flag to track sign changes
    HugeInteger _lOperand = new HugeInteger(this), _rOperand = new HugeInteger(_rhs);
    boolean _bNegative = false;

    //  Check the signs of the two operands and call the appropriate Subtract method if they have opposite signs
    if(_lOperand.bPositive && !_rOperand.bPositive){ // (a + -b)
      _rOperand.bPositive = true;
      return _lOperand.Subtract(_rOperand);
    }else if(!_lOperand.bPositive && _rOperand.bPositive){ // (-a + b)
      _lOperand.bPositive = true;
      return _rOperand.Subtract(_lOperand);
    }else if(!_lOperand.bPositive && !_rOperand.bPositive){
      //  If both are negative, flip signs of operands, set bNegative, and apply negative sign to result at the end
      _lOperand.bPositive = true;
      _rOperand.bPositive = true;
      _bNegative = true;
    }

    /*  Adding the two HugeIntegers (a + b) */

    //  Get both numbers as Strings and reverse them, so numbers run from least significant digits to most significant
    StringBuilder _lNum = new StringBuilder(_rOperand.toString()).reverse();
    StringBuilder _rNum = new StringBuilder(_rhs.toString()).reverse();

    //  Initialize working variables
    int _a, _b, _carry = 0;

    //  While both number strings have characters remaining,
    while(_lNum.length() > 0 && _rNum.length() > 0){

      //  Parse the leading characters of _lNum and _rNum
      _a = Integer.getInteger(_lNum.substring(0, 0));
      _b = Integer.getInteger(_rNum.substring(0, 0));

      //  Delete the leading characters from each String
      _lNum.deleteCharAt(0);
      _rNum.deleteCharAt(0);

      //  Perform addition and carry operations
      _a += (_b + _carry);
      _carry = _a/10;

      //  append remainder of division to result StringBuilder
      _result.append(_a%10);
    }

    //  Set it so lNum contains the remaining digits
    if (_lNum.length() == 0) _lNum = _rNum;

    //  If no other digits remain, but there is still a carry, append it to the result, and continue
    if(_lNum.length() == 0 && _carry > 0){
      _result.append(_carry);
    }else{
      //  otherwise, continue the addition, carrying, and appending until there is no carry or there are no more digits
      while (_carry > 0 && _lNum.length() > 0) {
        _a = Integer.getInteger(_lNum.substring(0, 0)) + _carry;
        _carry = _a / 10;
        _result.append(_a % 10);
      }

      //  Either append the final carry or append the remaining, unaffected digits to the result
      if(_carry !=0){
        _result.append(_carry);
      }else{
        while (_lNum.length() > 0) {
          _result.append(_lNum.charAt(0));
          _lNum.deleteCharAt(0);
        }
      }
    }

    //  Append negative sign if necessary (-a + -b)
    if(_bNegative) _result.append('-');

    //  Return the new HugeInteger after reversing the result String so it runs from most significant digit to least
    return new HugeInteger(_result.reverse().toString());
  }

  /** Subtracts a HugeInteger from this one and returns result as a new HugeInteger */
  public HugeInteger Subtract(HugeInteger _rhs){
    // Create a StringBuilder to contain the result of the addition
    StringBuilder _result= new StringBuilder();

    //  Create local objects to protect original HugeInteger object states and a bNegative flag to track sign changes
    HugeInteger _lOperand = new HugeInteger(this), _rOperand = new HugeInteger(_rhs);

    //  Check if the two operands are equal and if so return a new HugeInteger with a value of zero
    if(_lOperand.IsEqualTo(_rOperand)) return new HugeInteger("0");

    //  Check the signs of the operands and call the appropriate Add function if possible
    if(_lOperand.bPositive && !_rOperand.bPositive){ // a - (-b) = (a + b)
      _rOperand.bPositive = true;
      return _lOperand.Add(_rOperand);
    }else if(!_lOperand.bPositive && _rOperand.bPositive){ // -a - b = (-a + -b)
      _rOperand.bPositive = false;
      return _lOperand.Add(_rOperand);
    }

    /* Subtract one operand from another */

    //  If the left operand has a smaller absolute value than the right operand, create a flag that it is a negative
    //    number and swap the order so the smaller absolute value is being subtracted from the larger
    boolean _bNegative = false;

    //  Compare the Absolute values of the two operands and assign the larger of the two to lNum.
    //    If the order of the operands is swapped, set bSignFlag to true to produce a negative final result
    StringBuilder _lNum, _rNum;
    if(_lOperand.CompareAbsoluteValue(_rOperand) > 0){
      _lNum = new StringBuilder(_lOperand.toString()).reverse();
      _rNum = new StringBuilder(_rOperand.toString()).reverse();
    }else{
      _lNum = new StringBuilder(_rOperand.toString()).reverse();
      _rNum = new StringBuilder(_lOperand.toString()).reverse();
      _bNegative = true;
    }

    //  Initialize working variables
    int _a, _b, _borrow = 0;

    //  While both number strings have characters remaining
    while(_lNum.length() > 0 && _rNum.length() > 0) {
      //  Check if the previous digit borrowed from the current one and use the borrowed value as the left operand
      if(_borrow > 0){
        _a = _borrow;
      }else {
        //  If no borrow, parse the leading integers from the two strings
        _a = Integer.getInteger(_lNum.substring(0, 0));
      }
      _b = Integer.getInteger(_rNum.substring(0,0));

      //  Pop the leading characters off the Strings
      _lNum.deleteCharAt(0);
      _rNum.deleteCharAt(0);

      //  Check if a borrow is required
      if(_a < _b){
        _borrow = Integer.getInteger(_lNum.substring(0,0))-1;
        _a += 10;
      }else {
        _borrow = 0;
      }
      _result.append((_a - _b));
    }

    // If the last operation required a borrow, append the borrowed value instead of the next character in the remaining
    if(_borrow > 0){
      _result.append(_borrow);
      _lNum.deleteCharAt(0);
    }

    //  Append the remaining digits to the result
    while(_lNum.length() >0){
      _result.append(_lNum.charAt(0));
      _lNum.deleteCharAt(0);
    }

    //  Apply a negative sign if required
    if(_bNegative) _result.append('-');

    //  Reverse the resulting string so the most significant digit appears at the front and return a new HugeInteger
    return new HugeInteger(_result.reverse().toString());
  }
}
