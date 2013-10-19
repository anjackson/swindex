/**
 * 
 */
package uk.bl.wa.swindex.nsrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

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

	private static String solrUrl = "http://localhost:8080/swindex";

	/**
	 * 
	 * @param args
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	public static void main(String[] args) throws IOException, SolrServerException {
		HttpSolrServer solrServer = new HttpSolrServer(solrUrl );
		
		// Parse
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
				//printCSVLine(line);
				SolrInputDocument doc = new SolrInputDocument();
				doc.addField("id", line[0]+"_"+count);
				doc.addField("sha1_s", line[0]);
				doc.addField("md5_s", line[1]);
				doc.addField("crc32_s", line[2]);
				doc.addField("file_name_s", line[3]);
				doc.addField("file_size_tl", line[4]);
				doc.addField("produce_code_s", line[5]);
				doc.addField("op_system_code_s", line[6]);
				doc.addField("special_code_s", line[7]);
				solrServer.add(doc);
				
				count++;
				if( count >= 1000 )
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
		// And finally, commit:
		solrServer.commit();
	}
	
	private static void printCSVLine( String[] line ) {
		for( String s : line ) {
			System.out.print(s+" ");
		}
		System.out.println("");
	}

}
