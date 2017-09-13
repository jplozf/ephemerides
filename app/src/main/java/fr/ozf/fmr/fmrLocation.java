package fr.ozf.fmr;

public class fmrLocation
{
	public double Latitude;
	public double Longitude;
	public double Elevation;
	public String Name;

	public fmrLocation(String Name, double Latitude, double Longitude, double Elevation)
	{
		this.Name = Name;
		this.Latitude = Latitude;
		this.Longitude = Longitude;
		this.Elevation = Elevation;
	}

	public fmrLocation(String Name, double Latitude, double Longitude)
	{
		this.Name = Name;
		this.Latitude = Latitude;
		this.Longitude = Longitude;
		this.Elevation = 0.0;
	}

	public fmrLocation(double Latitude, double Longitude)
	{
		this.Name = "N/A";
		this.Latitude = Latitude;
		this.Longitude = Longitude;      
		this.Elevation = 0.0;
	}

	@Override
	public String toString()
	{
		return this.Name + " @ " + fmrDate.dd2dms(this.Latitude) + " / " + fmrDate.dd2dms(this.Longitude) + " / " + Double.toString(this.Elevation) + " m";
	}
}
