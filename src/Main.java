import java.io.File;
import java.util.Scanner;

public class Main {
	public static int inputNeuransNumber;
	public static int hiddenNeuransNumber;
	public static int outputNeuransNumber;
	public static int numberOfTrainingData;
	
	public static void main(String [] args){
		Data[] trainingRecords = readData();
		NeuralNetwork neuralNetwork = new NeuralNetwork(trainingRecords, hiddenNeuransNumber);
		neuralNetwork.train();
		
		
		System.out.println("Hidden Wights\n");
		for(int i=0;i<neuralNetwork.Whidden.length;++i) {
			for(int j=0;j<neuralNetwork.Whidden[i].length;++j) {
				System.out.print(neuralNetwork.Whidden[i][j] + " ");
			}
			System.out.println("");
		}
		
		
		System.out.println("\n\nOutput Wights\n");
		for(int i=0;i<neuralNetwork.Woutput.length;++i) {
			for(int j=0;j<neuralNetwork.Woutput[i].length;++j) {
				System.out.print(neuralNetwork.Woutput[i][j] + " ");
			}
			System.out.println("");
		}
		
	}
	
	public static Data[] readData(){
		Data[] trainingRecords = null;
		
		try{
			Scanner in = new Scanner(new File("data.txt"));
			
			if(in.hasNext()){
				inputNeuransNumber = in.nextInt();
				hiddenNeuransNumber = in.nextInt();
				outputNeuransNumber = in.nextInt();
				numberOfTrainingData = in.nextInt();
				
				trainingRecords = new Data[numberOfTrainingData];
			}
			
			for(int i=0;i<numberOfTrainingData;i++){
				double[] input = new double[inputNeuransNumber];
				double[] output = new double[outputNeuransNumber];
				
				for(int inputNuranIndex=0;inputNuranIndex<inputNeuransNumber;inputNuranIndex++){
					input[inputNuranIndex] = in.nextDouble();
				}
				for(int outputNuranIndex=0;outputNuranIndex<outputNeuransNumber;outputNuranIndex++){
					output[outputNuranIndex] = in.nextDouble();
				}
				
				trainingRecords[i] = new Data(input,output);
			}
			in.close();
		}catch(Exception e){
			System.out.println("file not found!");
		}
		return trainingRecords;
	}
}
