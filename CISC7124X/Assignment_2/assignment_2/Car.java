package assignment_2;/**
* CLASS:        com.assignment_2.Car
* CREATED:      2/16/2021
* DESCRIPTION:  
*
*/
public class Car {
  private float Efficiency, Tank_Capacity, Fuel_Level;

  public Car(float _efficiency, float _tank_capacity){
    this.Efficiency = _efficiency;
    this.Tank_Capacity = _tank_capacity;
    this.Fuel_Level = 0.0F;
  }

  public void Drive(float _miles){
    if(this.Fuel_Level < (_miles/this.Efficiency)){
      System.out.println("You ran out of gas after " + (this.Fuel_Level*this.Efficiency) + " miles, with "
          + (_miles/this.Efficiency) + " left to travel.");
    }else {
      this.Fuel_Level -= (_miles / this.Efficiency);
    }
  }

  public String GetGas(){return String.format(
      "%f gallons of fuel remain. Fuel level is at %f%",
      this.Fuel_Level,
      (this.Fuel_Level/this.Tank_Capacity)*100.0F);
  }

  public void AddGas(float _fuel){
    this.Fuel_Level = this.Fuel_Level + _fuel > this.Tank_Capacity ? this.Tank_Capacity : this.Fuel_Level + _fuel;
  }

}
