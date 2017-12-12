import java.util.Arrays;
import java.util.Random;

public class NeuralNetwork {

	public Data trainingData[];
	public double Whidden[][];
	public double Woutput[][];
	public int L;
	public double eta;
	
	public void performNeuralNetwork(Data[] trainingData, int L){
		this.trainingData = Arrays.copyOf(trainingData, trainingData.length);
		this.L = L;
		this.eta=0.1;
		
		// just write logic of functions here to be able to test
		// L = hidden layer neurans?
				
	}

	public void train() {
		Whidden=getRandomWeights(L, trainingData[0].input.length);
		Woutput=getRandomWeights(trainingData[0].output.length,L);
		
		boolean finish=false;
		while(!finish) {
			finish=true;
			for(Data data : trainingData) {
				double[]Ihidden=feedForward(Whidden,data.input);
				double[]IOutput=feedForward(Woutput,Ihidden);
				double totalError=0;
				for(int i=0;i<data.output.length;++i) {
					totalError+=(data.output[i]-IOutput[i])*(data.output[i]-IOutput[i]);
				}
				if(totalError/2>=10)
				{	
					finish=false;
					hiddenProbagation(data, IOutput, Ihidden);
					outProbagation(data.output, IOutput, Ihidden);
				}
			}
		}
	}

	private void hiddenProbagation(Data data, double computed[], double Ihidden[]) {
		for (int i = 0; i < Ihidden.length; ++i) {
			double sum = 0;
			for (int j = 0; j < computed.length; ++j) {
				sum += (data.output[j] - computed[j]) * Woutput[j][i];
			}
			double segmaH = Ihidden[i] * (1 - Ihidden[i]) * sum;
			for (int j = 0; j < computed.length; ++j) {
				sum += (data.output[j] - computed[j]) * Woutput[j][i];
			}
			for (int j = 0; j < data.input.length; ++j) {
				Whidden[i][j] += eta * segmaH * data.input[j];
			}
		}
	}

	private void outProbagation(double actual[], double computed[], double Ihidden[]) {
		for (int i = 0; i < computed.length; ++i) {
			double segma = actual[i] - computed[i];
			double segmaO = computed[i] * (1 - computed[i]) * segma;
			for (int j = 0; j < Ihidden.length; ++j) {
				Woutput[i][j] += eta * segmaO * Ihidden[j];
			}
		}
	}

	public double[][] getRandomWeights(int r, int c) {
		// initialize array[r][c] with random values and return it
		double min = -0.5;
		double max = 0.5;
		double weights[][] = new double[r][c];
		Random random = new Random();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				weights[i][j] = (max - min) * random.nextDouble() + min;
			}
		}
		return weights;
	}

	public double[] feedForward(double[][] hiddenWeights, double[] input) {

		// input must be 2d
		double[]netHidden=dotProduct(hiddenWeights, input);

		double[]outHidden=new double[netHidden.length];
		for(int i=0;i<netHidden.length;i++) 
		{ 
				outHidden[i]=Sigmoid(1, netHidden[i]);
		} 
		return outHidden;

	}

	public  double[] dotProduct(double[][] matrix, double[] vector) {
		double result[] = new double[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			double sum = 0;
			for (int j = 0; j < matrix[0].length; j++) {
					sum += matrix[i][j] * vector[j];
			}
			result[i] = sum;

		}
		return result;
	}

	public double Sigmoid(double c, double net) {
		return (1.0 / (1 + Math.exp(-1 * c * net)));
	}
}
