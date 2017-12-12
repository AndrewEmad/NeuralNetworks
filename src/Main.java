import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static int inputNeuransNumber;
	public static int hiddenNeuransNumber;
	public static int outputNeuransNumber;
	public static int numberOfTrainingData;
	
	public static void main(String [] args){
		ArrayList<TrainingRecord> trainingRecords = readData();
		
	}
	
	public static ArrayList<TrainingRecord> readData(){
		ArrayList<TrainingRecord> trainingRecords = new ArrayList<>();
		
		try{
			Scanner in = new Scanner(new File("data.txt"));
			
			if(in.hasNext()){
				inputNeuransNumber = in.nextInt();
				hiddenNeuransNumber = in.nextInt();
				outputNeuransNumber = in.nextInt();
				numberOfTrainingData = in.nextInt();
			}
			
			for(int i=0;i<numberOfTrainingData;i++){
				ArrayList<Double> input = new ArrayList<>();
				ArrayList<Double> output = new ArrayList<>();
				
				for(int inputNuranIndex=0;inputNuranIndex<inputNeuransNumber;inputNuranIndex++){
					input.add(in.nextDouble());
				}
				for(int outputNuranIndex=0;outputNuranIndex<outputNeuransNumber;outputNuranIndex++){
					output.add(in.nextDouble());
				}
				
				trainingRecords.add(new TrainingRecord(input,output));
			}
			in.close();
		}catch(Exception e){
			System.out.println("file not found!");
		}
		return trainingRecords;
	}
}
