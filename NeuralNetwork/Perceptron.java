package NeuralNetwork;

import java.util.ArrayList;

public class Perceptron {

    double[] weights;
    double alpha;
    boolean autoTrain;

    public Perceptron(){
        weights = new double[3];
        for (int i = 0; i < 3; i++){
            weights[i] = Run.random.nextDouble()/15;
        }
        alpha = 0.01;
        autoTrain = false;
    }

    public static double sigmoid(double x){
        return 1/(1+Math.exp(-x));
    }

    public double feedForward(double[] inputs){
        double sum = 0;
        for (int i = 0; i < inputs.length; i++){
            sum += inputs[i]*weights[i];
        }
        return sigmoid(sum);
    }

    float getError(ArrayList<double[]> points, ArrayList<Double> answer) {
        float avg = 0;
        for (int i = 0; i < points.size(); i++) {
            avg += answer.get(i)-feedForward(points.get(i));
        }
        return avg/points.size();
    }

    double guessF(float x) {
        return (-(weights[0]*x) - weights[2])/weights[1];
    }

    String printLinear(){
        double m = Math.round(-weights[0]/weights[1] * 10000) / 1000.0;
        double b = Math.round(-weights[2]/weights[1] * 10000) / 1000.0;
        String note = "Linear Estimation\n";
        if (b > 0)
            return note+"y = " + m + "x + " + b;
        else
            return note+"y = " + m + "x " + b;
    }

    public void step(double[] inputs, double answer){
        double guess = feedForward(inputs);
        double error = answer-guess;

        for (int i = 0; i < weights.length; i++) {
            weights[i] += inputs[i]*error*alpha;
        }
    }

    public String getWeights() {
        return "Alpha: " + alpha + "\nWeights\n"+Math.round(weights[0]*1000)/1000.0+"\n"+Math.round(weights[1]*1000)/1000.0+"\n"+Math.round(weights[2]*1000)/1000.0;
    }

    public String classification(int sample){
        String a = "Input: " + Run.points.get(sample)[0] + ", " + Run.points.get(sample)[0];
        String guess = "Guess: " + Math.round(Run.p.feedForward(Run.points.get(sample))*1000)/1000.0;
        String correct = "Correct: " + Run.answers.get(sample);

        return a+'\n'+guess+'\n'+correct;
    }
}
