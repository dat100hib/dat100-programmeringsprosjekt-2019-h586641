package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		// TODO - START
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;

//		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double[] tab = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			tab[i] = gpspoints[i].getLatitude();
		}
		
		return tab;
//		throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double[] tab = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			tab[i] = gpspoints[i].getLongitude();
		}
		
		return tab;
		
//		throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		// TODO - START
		
		latitude1 = gpspoint1.getLatitude();
		longitude1 = gpspoint1.getLongitude();
		latitude2 = gpspoint2.getLatitude();
		longitude2 = gpspoint2.getLongitude();
		
		double phi1 = Math.toRadians(latitude1);
		double phi2 = Math.toRadians(latitude2);
		double deltaPhi = phi2 - phi1;
		double deltaLambda = Math.toRadians(longitude2 - longitude1);

		double a = Math.pow(Math.sin(deltaPhi/2),2) + Math.cos(phi1) * Math.cos(phi2) * Math.pow(Math.sin(deltaLambda/2), 2) ;
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		d = R * c;
		return d;
		
//		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START
		
		double d = distance(gpspoint1, gpspoint2);
		
		secs = gpspoint2.getTime() - gpspoint1.getTime();
		speed = 3600 * d/(secs * 1000);	// speed in km/h
		return speed;
		
//		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START
		
		int h = secs / 3600;
		int m = secs % 3600 / 60;
		int s = secs % 60;
		
		timestr = String.format("  %02d%s%02d%s%02d", h, TIMESEP, m, TIMESEP, s);

		return timestr;
		
//		throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT

	}
	
	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START
		d = ((int)(d * 100 + 0.5)) / 100.00;
		str = String.format("%1$10s", d);
		
		return str;
		
		
//		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}
}
