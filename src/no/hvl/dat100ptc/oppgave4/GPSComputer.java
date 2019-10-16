package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START
		
		for (int i = 0; i < gpspoints.length-1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i+1]);		
		}

		return distance;
//		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO - START
		for (int i = 0; i < gpspoints.length-1; i++) {
			if ( gpspoints[i+1].getElevation() > gpspoints[i].getElevation() ) {
				
			elevation += gpspoints[i+1].getElevation() - gpspoints[i].getElevation();
			}
		}

		return elevation;
//		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {

		return gpspoints[gpspoints.length-1].getTime() - gpspoints[0].getTime();
//		throw new UnsupportedOperationException(TODO.method());

	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		
		// TODO - START		// OPPGAVE - START
		
		double[] tab = new double[gpspoints.length-1];
		for (int i = 0; i < gpspoints.length-1; i++) {
			tab[i] = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
		}
		
		return tab;
//		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
	}
	
	/* returnerer den største hastigheten vi har beveget oss med mellom to punkter på ruten.*/
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		// TODO - START
		maxspeed = GPSUtils.findMax(speeds());
		return maxspeed;
		
//		throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
		
	}

	/* Hjelpemetode som returnerer gjennomsnittshastigheten vi har 
	 * beveget oss med total sett for hele ruten. km/t
	 */
	public double averageSpeed() {

		double average;
		
		// TODO - START		
		
		average = 3.6 * totalDistance() / totalTime();
		
		return average;
//		throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
		
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	/* Energy Expended (kcal) = MET x Body Weight (kg) x Time (h) */
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;

		// TODO - START
		
		if (speedmph < 10)
			met = 4;
		if (speedmph > 10)
			met = 6;
		if (speedmph > 12)
			met = 8;
		if (speedmph > 14)
			met = 10;
		if (speedmph > 16)
			met = 12;
		if (speedmph > 20 )
			met = 16;
		
		
		kcal = met * weight * secs/3600;
		
		return kcal;
//		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO - START
		
		for (int i = 0; i < speeds().length; i++) {
			totalkcal += kcal (weight, gpspoints[i+1].getTime() - gpspoints[i].getTime(), speeds()[i]);
		}
		
		return totalkcal;
		
//		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		System.out.println("==============================================");

		// TODO - START
		System.out.printf("%-16s: %s%n", "Total Time", GPSUtils.formatTime(totalTime()));
		System.out.printf("%-16s: %s km%n", "Total distance", GPSUtils.formatDouble(totalDistance()/1000) );
		System.out.printf("%-16s: %s m%n", "Total elevation", GPSUtils.formatDouble(totalElevation()) );
		System.out.printf("%-16s: %s km/h%n", "Max speed", GPSUtils.formatDouble(maxSpeed()) );
		System.out.printf("%-16s: %s km/h%n", "Average speed", GPSUtils.formatDouble(averageSpeed()) );
		System.out.printf("%-16s: %s kcal%n", "Energy", GPSUtils.formatDouble(totalKcal(WEIGHT)) );		
		System.out.println("==============================================");
//		throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT	
	}

	/* Ekstraoppgave (a) Metode som beregner stigningsprosent for de ulike deler av ruten */
	public double[] climbs() {
		/* pente = y/100 % */
		double[] slope = new double[gpspoints.length-1];
		double d, elevation;
		
		for (int i = 0; i < slope.length; i++) {
			d = GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
			elevation = gpspoints[i+1].getElevation() - gpspoints[i].getElevation();
			slope[i] = elevation/d/100;	 // stigning i prosent
		}
		return slope;
	}
	
	
	/* Ekstraoppgave (a) Metode som finner den del av ruten som har den høyeste stigningsprosent */
	public void maxClimb() {
		System.out.println(GPSUtils.findMax(climbs()));
	}
}
