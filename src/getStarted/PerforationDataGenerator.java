package getStarted;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TreeMap;

import DomainObject.Hole;
import javax.swing.JOptionPane;


public class PerforationDataGenerator {

	public static void main(String[] args) {
		String inputAddress=JOptionPane.showInputDialog("Enter the file address:");
		generateFile(inputAddress);
		JOptionPane.showMessageDialog(null,"Done!","Title",JOptionPane.PLAIN_MESSAGE );
	}
	
	public static void generateFile(String csvFile){
		
		ArrayList<Hole> PasedData=new ArrayList<Hole>();
		System.out.println("Test starting...");
//		String csvFile="/Users/Moonshine/Engineering/eclipse_workspace/KeithProjectOilData/dataFile/smallerSample.csv";
//		String csvFile="/Users/Moonshine/Engineering/eclipse_workspace/KeithProjectOilData/dataFile/no_header_origional.csv";
//		String csvFile="/Users/Moonshine/Engineering/eclipse_workspace/KeithProjectOilData/dataFile/no_header_origional.csv";

		//read csv file
		try {
			PasedData=processData.readCsv(csvFile);
			//generate list of well id
			ArrayList<String> wellList=processData.wellListGenerator(csvFile);
			for(String wellId:wellList){
				TreeMap<Float,Integer> result=new TreeMap<Float,Integer>();
				result=processData.prefMapGenerator(processData.holeFilter(PasedData, wellId));
				System.out.println(wellId+"file Map size:" + result.size());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
