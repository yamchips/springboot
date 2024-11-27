package weather;

import java.util.Objects;

/**
 * This class represents a StevensonReading shelter.
 * 
 * @author Fan Wu
 *
 */
public final class StevensonReading implements WeatherReading {
	private double airTemp;
	private double dewTemp;
	private double windSpeed;
	private double totalRain;	
	

  /**
   * This is the constructor.
   * @param airTemp means the air temperature in Celsius.
   * @param dewTemp means the dew point temperature in Celsius.
   * @param windSpeed means the non-negative wind speed in miles per hour.
   * @param totalRain means the non-negative total rain received
   *        in the last 24 hours in millimeters.
   */   
  public StevensonReading(double airTemp, double dewTemp, double windSpeed, double totalRain) { 
    // check inputs
    if (airTemp < -273.15 || dewTemp < -273.15 || dewTemp >= airTemp || windSpeed < 0 || totalRain < 0) {
      throw new IllegalArgumentException("Invalid inputs.");
    } 
    this.airTemp = airTemp;
    this.dewTemp = dewTemp;
    this.windSpeed = windSpeed;
    this.totalRain = totalRain;
    
  }
  
  @Override
  public int getTemperature() {
    return (int) Math.rint(this.airTemp);
  }
  
  @Override 
  public int getDewPoint() {
    return (int) Math.rint(this.dewTemp);
  }
  
  @Override
  public int getWindSpeed() {
    return (int) Math.rint(this.windSpeed);
  }
  
  @Override
  public int getTotalRain() {
    return (int) Math.rint(this.totalRain);
  }
  
  @Override
  public int getRelativeHumidity() {
    double rh;
    
    rh = (this.dewTemp * (237.3 + this.airTemp))
        / (this.airTemp * (237.3 + this.dewTemp));
    
    return (int) Math.rint(rh * 100);   
  }
  
  @Override
  public int getHeatIndex() {
    final double C1 = -8.78469475556;
    final double C2 = 1.61139411;
    final double C3 = 2.33854883889;
    final double C4 = -0.14611605;
    final double C5 = -0.012308094;
    final double C6 = -0.0164248277778;
    final double C7 = 0.002211732;
    final double C8 = 0.00072546;
    final double C9 =  -0.000003582;
    double rh;
    
    rh = 100 * (this.dewTemp * (237.3 + this.airTemp)) 
        / (this.airTemp * (237.3 + this.dewTemp));
    
    double heatIndex = C1 + C2 * this.airTemp + C3 * rh 
        + C4 * this.airTemp * rh + C5 * this.airTemp * this.airTemp 
        + C6 * rh * rh + C7 * this.airTemp * this.airTemp * rh 
        + C8 * this.airTemp * rh * rh + C9 * this.airTemp * this.airTemp * rh * rh;
    
    return (int) Math.rint(heatIndex);
  }
  
  @Override
  public int getWindChill() {
    double tempf = (9 * this.airTemp / 5) + 32;
    double wc = (double) (35.74 + 0.6215 * tempf 
        - 35.75 * Math.pow(this.windSpeed, 0.16)
        + 0.4275 * Math.pow(this.windSpeed, 0.16));
    return (int) Math.rint(wc);
  }
  

  @Override
  public String toString() {
    // format: Reading: T = 23, D = 12, v = 3, rain = 12
    return "Reading: T = " + (int) Math.rint(this.airTemp) + ", D = " 
        + (int) Math.rint(this.dewTemp) + ", v = " 
        + (int) Math.rint(this.windSpeed) + ", rain = " + (int) Math.rint(this.totalRain);
  }
  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof StevensonReading)) {
      return false;
    }
    
    StevensonReading p = (StevensonReading) o;
    return this.airTemp == p.airTemp && this.dewTemp == p.dewTemp
        && this.totalRain == p.totalRain && this.windSpeed == p.windSpeed;
  
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.airTemp, this.dewTemp, this.totalRain, this.windSpeed);
  }

}
