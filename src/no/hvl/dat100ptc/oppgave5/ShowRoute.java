package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 500; // var 800
	private static int MAPYSIZE = 500; // var 800

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;

	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);

		playRoute(MARGIN + MAPYSIZE);

		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon));

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {

		double ystep;

		// TODO - START

		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		ystep = MAPYSIZE / (Math.abs(maxlat - minlat));

		return ystep;

//		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	public void showRouteMap(int ybase) {

		// TODO - START
		double minlong = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		setColor(123, 200, 102);
		double long0 = gpspoints[0].getLongitude() - minlong;
		double lat0 = gpspoints[0].getLatitude() - minlat;

		for (int i = 0; i < gpspoints.length; i++) {
			double long1 = gpspoints[i].getLongitude() - minlong;
			double lat1 = gpspoints[i].getLatitude() - minlat;

			if (i > 0) {
				drawLine(MARGIN + (int) (xstep() * long0), ybase - (int) (ystep() * lat0),
						MARGIN + (int) (xstep() * long1), ybase - (int) (ystep() * lat1));
			}
			fillCircle(MARGIN + (int) (xstep() * long0), ybase - (int) (ystep() * lat0), 2);
			long0 = long1;
			lat0 = lat1;

		}

//		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0, 0, 0);
		setFont("Courier", 12);

		// TODO - START
		double WEIGHT = 80.0;
		int mellomrom = 14;

		drawString(String.format("%-15s: %s\n", "Total Time", GPSUtils.formatTime(gpscomputer.totalTime())),
				TEXTDISTANCE, TEXTDISTANCE);
		drawString(
				String.format("%-15s: %s km\n", "Total distance",
						GPSUtils.formatDouble(gpscomputer.totalDistance() / 1000)),
				TEXTDISTANCE, TEXTDISTANCE + mellomrom);
		drawString(
				String.format("%-15s: %s m\n", "Total elevation", GPSUtils.formatDouble(gpscomputer.totalElevation())),
				TEXTDISTANCE, TEXTDISTANCE + 2 * mellomrom);
		drawString(String.format("%-15s: %s km/h\n", "Max speed", GPSUtils.formatDouble(gpscomputer.maxSpeed())),
				TEXTDISTANCE, TEXTDISTANCE + 3 * mellomrom);
		drawString(
				String.format("%-15s: %s km/h\n", "Average speed", GPSUtils.formatDouble(gpscomputer.averageSpeed())),
				TEXTDISTANCE, TEXTDISTANCE + 4 * mellomrom);
		drawString(String.format("%-15s: %s kcal\n", "Energy", GPSUtils.formatDouble(gpscomputer.totalKcal(WEIGHT))),
				TEXTDISTANCE, TEXTDISTANCE + 5 * mellomrom);
//		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT;
	}

	public void playRoute(int ybase) {

//		// TODO - START
		double minlong = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		setColor(53, 130, 32);

		double long0 = gpspoints[0].getLongitude() - minlong;
		double lat0 = gpspoints[0].getLatitude() - minlat;
		int circle = fillCircle(MARGIN + (int) (xstep() * long0), ybase - (int) (ystep() * lat0), 4);
		setSpeed(1);
		double minSpeed = GPSUtils.findMin(gpscomputer.speeds());
		double speedRange = gpscomputer.maxSpeed() - minSpeed;

		for (int i = 1; i < gpspoints.length; i++) {
			/* set speed */
			setSpeed(1);

			if ((gpscomputer.speeds()[i - 1] - minSpeed) >= speedRange / 10) {
				setSpeed(2);
			}
			if ((gpscomputer.speeds()[i - 1] - minSpeed) >= 2 * speedRange / 10) {
				setSpeed(3);
			}
			if ((gpscomputer.speeds()[i - 1] - minSpeed) >= 3 * speedRange / 10) {
				setSpeed(4);
			}
			if ((gpscomputer.speeds()[i - 1] - minSpeed) >= 4 * speedRange / 10) {
				setSpeed(5);
			}
			if ((gpscomputer.speeds()[i - 1] - minSpeed) >= 5 * speedRange / 10) {
				setSpeed(6);
			}
			if ((gpscomputer.speeds()[i - 1] - minSpeed) >= 6 * speedRange / 10) {
				setSpeed(7);
			}
			if ((gpscomputer.speeds()[i - 1] - minSpeed) >= 7 * speedRange / 10) {
				setSpeed(8);
			}
			if ((gpscomputer.speeds()[i - 1] - minSpeed) >= 8 * speedRange / 10) {
				setSpeed(9);
			}
			if ((gpscomputer.speeds()[i - 1] - minSpeed) >= 9 * speedRange / 10) {
				setSpeed(10);
			}

			/* move circle at speed given by setSpeed() */
			long0 = gpspoints[i].getLongitude() - minlong;
			lat0 = gpspoints[i].getLatitude() - minlat;
			moveCircle(circle, MARGIN + (int) (long0 * xstep()), ybase - (int) (lat0 * ystep()));

		}

//		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
	}

}
