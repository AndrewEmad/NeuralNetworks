import java.util.ArrayList;

public class TrainingRecord {
	public ArrayList<Double> inputs = new ArrayList<>();
	public ArrayList<Double> outputs = new ArrayList<>();
	
	public TrainingRecord(ArrayList<Double> inputs,ArrayList<Double> outputs){
		this.inputs = inputs;
		this.outputs = outputs;
	}
}
