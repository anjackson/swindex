/**
 * 
 */
package uk.bl.wa.swindex.nsrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;

import au.com.bytecode.opencsv.CSVReader;

/**
 * 
 * For reasons unknown, it seems that Java 7 on OSX does not cope with ZIP64 files.
 * The exception is consistent with using the 32-bit implementation, and the stack points
 * into native code, it seems.
 * 
 * So, using Commons Compress instead.
 * 
 * @author Andrew Jackson <Andrew.Jackson@bl.uk>
 *
 */
public class NRSLParser {

	/**
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ZipFile zipFile = null;
		BufferedReader br = null;
		CSVReader csvr = null;
		try {
			// Open up NSRLFILETXT.ZIP
			zipFile = new ZipFile("nsrl/RDS_241/NSRLFILETXT.ZIP");
			// Open up NSRLFile.txt
			ZipArchiveEntry zipEntry = zipFile.getEntry("NSRLFile.txt"); 
			// Read line by line:
			br = new BufferedReader(new InputStreamReader( zipFile.getInputStream(zipEntry) ) );
			// Read as CSV:
			csvr = new CSVReader(br);
			// Read the header line:
			String[] header = csvr.readNext();
			printCSVLine(header);
			System.out.println("");
			// Read the rest:
			long count = 0;
			String[] line;
			while ((line = csvr.readNext()) != null)   {
				printCSVLine(line);
				count++;
				if( count > 10 )
					break;
			}
		} catch( IOException e ) {
			System.err.println("Exception: "+e);
			e.printStackTrace();
		} finally {
			if( csvr != null ) csvr.close();
			if( br != null ) br.close();
			if( zipFile != null) zipFile.close();
		}
	}
	
	private static void printCSVLine( String[] line ) {
		for( String s : line ) {
			System.out.print(s+" ");
		}
		System.out.println("");
	}

}
