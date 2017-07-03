package getStarted;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import DomainObject.Hole;

import java.util.Collections;

public class processData {
	
	public static ArrayList<Hole> holeFilter(ArrayList<Hole> input,String target){
		
		 ArrayList<Hole> targetHole=new ArrayList<Hole>();
		 for(Hole h1:input){
			 if(target.equals(h1.id)){
				 targetHole.add(h1);
			 }
		 }
		 return targetHole;	
	}
	
	
	public static ArrayList<Hole> readCsv(String location) throws FileNotFoundException{
		ArrayList<Hole> PasedData=new ArrayList<Hole>();		
	
		BufferedReader br=null;
		String line="";
		try{
			br=new BufferedReader(new FileReader(location));
			int count=1;
			while((line=br.readLine())!=null){
				String [] field=line.split(",");
	//			System.out.println("::TESTING:: --data:"+field[0]+" "+field[1]+" "+field[2]+" ");
				String well_id=field[0];
				float topNum=Float.parseFloat(field[1]);
				float baseNum=Float.parseFloat(field[2]);
				Hole newHole= new Hole(field[0],topNum,baseNum);
	//			System.out.println("::TESTING::"+count+ "--Parsed data:"+newHole.toString());
	//			count++;
				PasedData.add(newHole);	
			}
		
	 } catch (NumberFormatException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
	     if (br != null) {
	         try {
	             br.close();
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
	     }
	 }	
	return PasedData;
	
	}
	
	/**
	 * This function processes well data table,  return a Pref map <Point(float), Pref Value(Integer)>.
	 * @param input
	 * @return
	 */
	public static TreeMap<Float,Integer> prefMapGenerator(ArrayList<Hole> input){
		PrintWriter writer = null;
		float maxPt=-999;
		float minPt=-999;
		String filename=input.get(0).id+"Well.LAS";
		TreeMap<Float,Integer> PrefMap=new TreeMap<Float,Integer>();
		try {
			writer = new PrintWriter(filename,"UTF-8");
					
			// find largest base .find smallest top
			maxPt=largestVal(input);
			minPt=smallestVal(input);
			//generate PrefMap
			int count=1;
			for (float i=minPt; i<=maxPt;i+=0.5){
//				System.out.println(count+":"+i);
				PrefMap.put(i, 0);
				count++;
			}
//			
//			//go through each record, update value of map
			for (Hole h1:input){
				
				for(float j=h1.top;j<=h1.base;j+=0.5){
					//find the slot in PrefMap, increment count
					PrefMap.put(j, PrefMap.get(j)+1);
				}
				
			}
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printTreeMap(PrefMap,writer,minPt,maxPt,input.get(0).id);
		writer.close();
		return PrefMap;
		

		
		
	}
	
	public static void printTreeMap(TreeMap<Float,Integer> h1,PrintWriter writer,float startFt, float stopFt,String wellId){
		int i=1;
		writer.println("~VERSION INFORMATION");
		writer.println("VERS.      3.0   :CWLS LOG ASCII STANDARD--VERSION 2.0");
		writer.println("WRAP.      NO    :ONE LINE PER DEPTH STEP");
		writer.println("~WELL INFORMATION");
		writer.println("#============================================================");
		writer.println("STRT.FT                            "+startFt+" :START DEPTH");
		writer.println("STOP.FT                              "+stopFt+" :STOP DEPTH");
		writer.println("STEP.FT                                   0.5000 :STEP");
		writer.println("#");
		writer.println("NULL.                                -999.00000 :NULL VALUE");
		writer.println("UWI. "+wellId+"                         :WELL UWI");
		writer.println("API. "+wellId+"                         :WELL API");
		writer.println("~CURVE INFORMATION");
		writer.println("#============================================================");
		writer.println("M_Depth                         .FT                      :Measured Depth");
		writer.println("Perf_Int                        .NONE                    :Net Sand Curve");
		writer.println("~OTHER INFORMATION");
		writer.println("#============================================================");
		writer.println("~A M_Depth      Perf_Int ");
		
		
		for(Map.Entry<Float,Integer> entry: h1.entrySet()){
//			writer.println(i+"---Key: "+ entry.getKey()+" Value: "+entry.getValue());
			writer.println(entry.getKey()+"           "+entry.getValue());
			i++;
			
		}
	}
	
	
	public static float largestVal(ArrayList<Hole> input){
		float max=-99999;
		for (Hole entry: input){
			if(max<entry.base){
				max=entry.base;
			}
		}
		return max;
	}
	public static float smallestVal(ArrayList<Hole> input){
		float min=99999;
		for (Hole entry: input){
			if(min>entry.top){
				min=entry.top;
			}
		}
		return min;
	}


	public static ArrayList<String> wellListGenerator(String location) {
		ArrayList<String> wellList=new ArrayList<String>();
		int count=0;
		BufferedReader br=null;
		String line="";
		try{
			br=new BufferedReader(new FileReader(location));
			while((line=br.readLine())!=null){
				String [] field=line.split(",");
	//			System.out.println("::TESTING:: --data:"+field[0]+" "+field[1]+" "+field[2]+" ");
				String well_id=field[0];
				if(!wellList.contains(well_id)){
					wellList.add(well_id);
					count++;
				}
			}
		
	 } catch (NumberFormatException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
	     if (br != null) {
	         try {
	             br.close();
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
	     }
	 }
		
		System.out.println("total number of wells:"+count);
		return wellList;
	}

}
