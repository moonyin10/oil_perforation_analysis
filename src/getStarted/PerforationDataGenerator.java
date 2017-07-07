package getStarted;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TreeMap;

import DomainObject.Hole;
import javax.swing.JOptionPane;


/**
 * @author Moon Yin
 * 7/7/2017
 *
 */
public class PerforationDataGenerator {

	public static void main(String[] args) {
		String inputAddress=JOptionPane.showInputDialog("Enter the file address:");
		generateFile(inputAddress);
		JOptionPane.showMessageDialog(null,"Done!","Title",JOptionPane.PLAIN_MESSAGE );
	}
	
	public static void generateFile(String csvFile){
		
		ArrayList<Hole> PasedData=new ArrayList<Hole>();
		System.out.println("Test starting...");

		try {
			//read csv file
			PasedData=processData.readCsv(csvFile);
			//generate list of well id
			ArrayList<String> wellList=processData.wellListGenerator(csvFile);
			//generate file
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
