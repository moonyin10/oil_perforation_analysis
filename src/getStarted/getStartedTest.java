package getStarted;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import getStarted.processData;

import org.junit.Test;

import DomainObject.Hole;

public class getStartedTest {

//	@Test
	public void OneWellTest() throws FileNotFoundException, IOException {
		
		ArrayList<Hole> PasedData=new ArrayList<Hole>();
		System.out.println("Test starting...");
		String csvFile="/Users/Moonshine/Engineering/eclipse_workspace/KeithProjectOilData/dataFile/smallerSample.csv";
	//	String csvFile="/Users/Moonshine/Engineering/eclipse_workspace/KeithProjectOilData/dataFile/no_header_origional.csv";
		
		//read csv file
		PasedData=processData.readCsv(csvFile);
		TreeMap<Float,Integer> result=new TreeMap<Float,Integer>();
		
		//generate list of well id
		ArrayList<String> wellList=processData.wellListGenerator(csvFile);
		
//		ArrayList<Hole> h1=processData.holeFilter(PasedData, "49035200000000");
//		System.out.println("filted size:"+h1.size());
		
		result=processData.prefMapGenerator(processData.holeFilter(PasedData, "49035200000004"));
		
	
		System.out.println("Actual Map size:" + result.size());
		
//		System.out.println("::TESTING::"+Arrays.toString(PasedData.toArray()));
	}
	
	
//	@Test
	public void wellIdGeneratorTest(){
		String csvFile="/Users/Moonshine/Engineering/eclipse_workspace/KeithProjectOilData/dataFile/smallerSample.csv";
		ArrayList<String> wellIds=processData.wellListGenerator(csvFile);
		for(String i:wellIds){
			System.out.println(i);
		}
	}
	
	@Test
	public void integrationTest() throws FileNotFoundException, IOException {
		
		ArrayList<Hole> PasedData=new ArrayList<Hole>();
		System.out.println("Test starting...");
//		String csvFile="/Users/Moonshine/Engineering/eclipse_workspace/KeithProjectOilData/dataFile/smallerSample.csv";
//		String csvFile="/Users/Moonshine/Engineering/eclipse_workspace/KeithProjectOilData/dataFile/no_header_origional.csv";
		String csvFile="/Users/Moonshine/Engineering/eclipse_workspace/KeithProjectOilData/dataFile/no_header_origional.csv";

		//read csv file
		PasedData=processData.readCsv(csvFile);
		
		//generate list of well id
		ArrayList<String> wellList=processData.wellListGenerator(csvFile);
		

		
		for(String wellId:wellList){
			TreeMap<Float,Integer> result=new TreeMap<Float,Integer>();
			result=processData.prefMapGenerator(processData.holeFilter(PasedData, wellId));
			System.out.println(wellId+"file Map size:" + result.size());
		}
		
	}
	


	
	
}
