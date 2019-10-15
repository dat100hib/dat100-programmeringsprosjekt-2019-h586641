package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	/* Konstruktør */
	public GPSData(int n) {  // n var antall i original kode

		// TODO - START
		this.antall = 0;
		this.gpspoints = new GPSPoint[n];
		
//		throw new UnsupportedOperationException(TODO.construtor("GPSData"));

		// TODO - SLUTT
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;

		// TODO - START
		if (antall <  gpspoints.length) {
			this.gpspoints[antall] = gpspoint;
			antall++;
			inserted = true;
		}
		
		return inserted;
		
//		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint;
		
		// TODO - START
		gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);
		
		gpspoints[antall] = gpspoint;
		return insertGPS(gpspoint);
		
//		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}

	public void print() {

		System.out.println("====== Konvertert GPS Data - START ======");

		// TODO - START
		
		for (GPSPoint point : gpspoints) {
			System.out.println(point.toString());
		}
		
		
		/* med utvidet løkke */
		for (int i = 0; i < antall; i++) {
			System.out.println(gpspoints[i].toString());
		}
		
		

//		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
		System.out.println("====== Konvertert GPS Data - SLUTT ======");

	}
	

	
	
}
