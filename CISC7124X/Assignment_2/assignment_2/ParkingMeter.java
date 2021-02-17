package assignment_2;
/**
* CLASS:        com.assignment_2.ParkingMeter
* CREATED:      2/16/2021
* DESCRIPTION:  
*
*/
public class ParkingMeter{
  private int       MaxTime, Rate, Time;  //  All time in minutes
  private boolean   bActive;              //  Flags whether the ParkingMeter is active

  public ParkingMeter(int _maxTime, int _rate, int _clockTickRate){
    this.MaxTime  = _maxTime;
    this.Rate     = _rate;
    this.Time     = 0;
    this.bActive  = true;

    new Thread(new Timer(this, _clockTickRate)).start();
  }

  public int      GetMaxTime(){return this.MaxTime;}

  public int      GetRate(){return this.Rate;}

  public int      GetTimeRemaining(){return this.Time;}

  public boolean  IsActive(){return this.bActive;}

  protected void  SetTimeRemaining(int _time){this.Time = _time;}

  public void     AddTime(){
    if(this.Time >= (this.MaxTime-this.Rate)){
      this.Time = this.MaxTime;
    }else{
      this.Time += this.Rate;
    }
    System.out.println("There are " + this.GetTimeRemaining()+ " minutes remaining...");
  }

  public void Shutdown(){this.bActive = false;}

  private class Timer implements Runnable{
    private ParkingMeter  Meter;
    private int           ClockTickRate;

    public Timer(ParkingMeter _meter, int _clockRate){
      this.Meter = _meter;
      this.ClockTickRate = _clockRate;
    }

    /**
     *  Creates an infinite loop where the Timer decrements the ParkingMeter's remaining Time according to it's
     *    ClockTickRate, measured in milliseconds
     */
    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run(){
      while (this.Meter.IsActive()){
        if (this.Meter.GetTimeRemaining() > 0){
          try {
            //  Thread sleeps first so that time added to the meter gets used up before remaining time is decremented
            Thread.sleep(this.ClockTickRate*1000); //  1000ms = 1 second
            this.Meter.SetTimeRemaining(Meter.GetTimeRemaining()- 1);
            System.out.println("There are " + this.Meter.GetTimeRemaining()+ " minutes remaining...");
          } catch (InterruptedException e){
            e.printStackTrace();
          }
        }
      }
      System.out.println("Shutting down the ParkingMeter...");
    }
  }
}
