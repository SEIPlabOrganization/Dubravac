package project1;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ReadExcelFile {

	private static ArrayList<String[]> stringHolder = new ArrayList<String[]>();
	private String warningstr;
	
    public void ReadCSV(String fileName) {
            Vector cellVectorHolder = new Vector();

            try {
                    FileInputStream myInput = new FileInputStream(fileName);

                    POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

                    HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

                    HSSFSheet mySheet = myWorkBook.getSheetAt(0);

                    Iterator rowIter = mySheet.rowIterator();

                    while (rowIter.hasNext()) {
                            HSSFRow myRow = (HSSFRow) rowIter.next();
                            Iterator cellIter = myRow.cellIterator();
                            Vector cellStoreVector = new Vector();
                            while (cellIter.hasNext()) {
                                    HSSFCell myCell = (HSSFCell) cellIter.next();
                                    cellStoreVector.addElement(myCell);
                            }
                            cellVectorHolder.addElement(cellStoreVector);
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            }
            makeStringArray(cellVectorHolder);
    }

    private static void makeStringArray(Vector dataHolder) {
    	ArrayList<String[]> s = new ArrayList<String[]>();
    	
    	for (int i = 0; i < dataHolder.size(); i++) {
            Vector cellStoreVector = (Vector) dataHolder.elementAt(i);
            String[] x = new String[3];
            for (int j = 0; j < 3; j++) {
            		try{
            			HSSFCell myCell = (HSSFCell) cellStoreVector.elementAt(j);
                    	x[j] = myCell.toString();
            		}catch(Exception e){
            			x[j] = "isempty";
            		}
            }
            s.add(i, x);
    	}
    	stringHolder.clear();
    	stringHolder.addAll(s);
    }
    
    public ArrayList<String[]> getStringList() {
    	return stringHolder;
    }
    
    private void checkXLSusers(ArrayList<String[]> s){
    	String a;
    	warningstr="";
    	for (int i = 0; i < s.size(); i++) {
            for (int j = 0; j < 3; j++) {
            	if(j==0){
            		if((s.get(i))[j].length()!=10){
            			warningstr+="JMBAG in row "+(i+1)+" dose not contain 10 signes.<br/>";
            		}
            		a=(s.get(i))[j];
            		for(int k=0; k<a.length();k++){
            			if(!((a.charAt(k)>='0' && a.charAt(k)<='9'))){
            				warningstr+="JMBAG in row "+(i+1)+" contains non-numeric signes.<br/>";
            				break;
            			}
            		}
            	}else{
            		if((s.get(i))[j]=="isempty"){
            			warningstr+="Name or surname in row "+(i+1)+" not present.<br/>";
            		}
            		a=(s.get(i))[j];
            		a=a.toLowerCase();
            		for(int k=0; k<a.length();k++){
            			if(!((a.charAt(k)>='a' && a.charAt(k)<='z') || a.charAt(k)=='č' || a.charAt(k)=='ć' || a.charAt(k)=='đ' || a.charAt(k)=='š' || a.charAt(k)=='ž')){
            				warningstr+="Name or surname in row "+(i+1)+" contains non-alphabetic signes.<br/>";
            				break;
            			}
            		}
            	}
            }
    	}
    }
    
    public String addToDB() {
    	MySQLcon db = new MySQLcon();
		ProjSec sec = new ProjSec();
		String errorstr = "";
		if(stringHolder==null)
			return "neuspjeh";
		checkXLSusers(stringHolder);
		if(warningstr!="")
			return warningstr;
		int k=0;
		for (int i = 0; i < stringHolder.size(); i++, k++) {
        	if(!(db.Upd("INSERT INTO Users SET idUsers='"+(stringHolder.get(i))[0]+"', Name='"+(stringHolder.get(i))[1]+"', Surname='"+(stringHolder.get(i))[2]+"', User_name='"+(stringHolder.get(i))[0]+"', User_password='"+sec.toMD5((stringHolder.get(i))[1]+"."+(stringHolder.get(i))[2])+"', Role='Stud';"))){
    			errorstr += "error inputing row: "+(i+1)+"<br/>";
    			k--;
    			}
		}
		if(errorstr == "")
			return "Sucess.";
		else if(k==0)
			return "error. No new entrys where created";
		else
			return errorstr+"Sucess inputing "+k+" new unique entrys.";
    }
}

