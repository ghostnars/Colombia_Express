package database;

import java.util.Vector;

import net.rim.device.api.servicebook.ServiceBook;
import net.rim.device.api.servicebook.ServiceRecord;
import net.rim.device.api.system.CoverageInfo;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.WLANInfo;


public class Autenticacion {

	Vector getConetion = new Vector();
	static String dato1;
	static String dato2;
	//String[] get1 ={dato1,dato2};
	public static String[] getConnectionString() {
        String[] connectionString = {dato1,dato2};                
                        
        // Simulator behavior is controlled by the USE_MDS_IN_SIMULATOR variable.
     /*   if(DeviceInfo.isSimulator()){
            connectionString[0] = ";deviceside=true";
            connectionString[1] = "wifi1";
        }                                                
        // Wifi is the preferred transmission method
        else*/ if(WLANInfo.getWLANState() == WLANInfo.WLAN_STATE_CONNECTED){
            //logMessage("Device is connected via Wifi.");
            connectionString[0] = ";interface=wifi";
            connectionString[1] = "wifi";
        }                    
        // Is the carrier network the only way to connect?
        else if((CoverageInfo.getCoverageStatus() & CoverageInfo.COVERAGE_DIRECT) == CoverageInfo.COVERAGE_DIRECT)
        {             
        	String carrierUid = getCarrierBIBSUid();
            if(carrierUid == null) {
                // Has carrier coverage, but not BIBS.  So use the carrier's TCP network
                 connectionString[0] = ";deviceside=true";
                 connectionString[1] = "BIBS1";
            } else {
                // otherwise, use the Uid to construct a valid carrier BIBS request
                connectionString[0] = ";deviceside=false;connectionUID="+carrierUid + ";ConnectionType=mds-public";
                connectionString[1] = "BIBS";
            }
        }                
        // Check for an MDS connection instead (BlackBerry Enterprise Server)
        else if((CoverageInfo.getCoverageStatus() & CoverageInfo.COVERAGE_MDS) == CoverageInfo.COVERAGE_MDS){
            connectionString[0] = ";deviceside=false";
            connectionString[1] = "BES";
        }
        // If there is no connection available abort to avoid bugging the user unnecssarily.
        else if(CoverageInfo.getCoverageStatus() == CoverageInfo.COVERAGE_NONE){
           // ServerException exception = new ServerException("No connection found");
        	connectionString[1] = "error";
        }
        // In theory, all bases are covered so this shouldn't be reachable.
        else{
            connectionString[0] = ";deviceside=true";
            connectionString[1] = "deviceside=true";
        }        
        return connectionString;
    }
	
	private static String getCarrierBIBSUid(){
        ServiceRecord[] records = ServiceBook.getSB().getRecords();
        int currentRecord;
        
        for(currentRecord = 0; currentRecord < records.length; currentRecord++){
            if(records[currentRecord].getCid().toLowerCase().equals("ippp")){
                if(records[currentRecord].getName().toLowerCase().indexOf("bibs") >= 0){
                    return records[currentRecord].getUid();
                }
            }
        }
        
        return null;
    }	
}
