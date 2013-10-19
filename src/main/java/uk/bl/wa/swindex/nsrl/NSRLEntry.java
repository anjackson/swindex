/**
 * 
 */
package uk.bl.wa.swindex.nsrl;

/**
 * 
 * A de-normalised NSRL fntry for each file.
 * 
 * The NSRLFile.txt contains:
 * 
 *     SHA-1 MD5 CRC32 FileName FileSize ProductCode OpSystemCode SpecialCode
 * 
 * The NSRLPROD.TXT contains the ProductCodes:
 * 
 *     "ProductCode","ProductName","ProductVersion","OpSystemCode","MfgCode","Language","ApplicationType"
 *     
 * The NSRLOS.TXT contains the OpSystemCodes:
 * 
 *     "OpSystemCode","OpSystemName","OpSystemVersion","MfgCode"
 *     
 * And the NSRLMFG.TXT contains the MfgCode:
 * 
 *     "MfgCode","MfgName"
 * 
 * And the SpecialCode? Well:
 *   Blank (no value) – normal file 
 *   “M” – malicious file 
 *   “S” – special file
 *   
 * @see http://www.nsrl.nist.gov/Documents/Data-Formats-of-the-NSRL-Reference-Data-Set-11.pdf
 * 
 * ---
 * 
 * Further, there are also other hashes, keyed on SHA-1, like the SSDEEP hashes .
 * 
 * @see http://www.nsrl.nist.gov/ssdeep.htm
 * 
 * "Each row has a SHA-1 hash, a tab character, and an ssdeep CSV output. "
 * 
00000142988AFA836117B1B572FAE4713F200567	768:Cv9oR/2r/2LMZbZosLapUbFx7ECQsWMFvDiu:Cv93r/2LMZ9oLpqjdQsWMFvWu,"008221197"
 * ---
 * 
 * NOTE, no time index. No notion of what year each product turned up.
 * 
 * 
 * @author Andrew Jackson <Andrew.Jackson@bl.uk>
 *
 */
public class NSRLEntry {

}
