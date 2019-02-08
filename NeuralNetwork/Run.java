package NeuralNetwork;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Run {
    public static ArrayList<double[]> points;
    public static ArrayList<Double> answers;

    public static Random random = new Random();

    public static int width = 1600;
    public static int height = 600;

    public static double m = 8;
    public static double b = 2;

    public static final Color infoColor = new Color(238,238,238);

    public static int strokeWeight = 3;

    public static Font font = new Font("Tahoma", 0, 24);

    public static Perceptron p = new Perceptron();

    public static double f(double x){
        return (m/10)*x + (b/10);
    }

    private static double map(double n, double start1, double stop1, double start2, double stop2){
        return ((n-start1)/(stop1-start1))*(stop2-start2)+start2;
    }

    public static double getX(double value){
        return map(value, 0, 1, 0, width);
    }

    public static double getY(double value){
        return map(value, 0, 1, height, 0);
    }

    public static String getEquation(){
        String note = "Linear Equation\n";
        if (b > 0)
            return note+"y = " + m + "x + " + b;
        else
            return note+"y = " + m + "x " + b;
    }

    public static void main(String[] args) {
        points = new ArrayList<double[]>();
        answers = new ArrayList<Double>();
        for (int i = 0; i < 100; i++){
            double x = random.nextDouble();
            double y = random.nextDouble();
            points.add(new double[] {x,y,1});
            if (y > f(x)){
                answers.add(1.0);
                y+=0.3;
            }else{
                answers.add(0.0);
                y-=0.3;
            }
        }

        new Display(width, height);
    }

    public static String getData() {
        int one = 0;
        int zero = 0;
        int classify = 0;
        for (int i = 0; i < answers.size(); i++){
            if (answers.get(i) == 1){
                one++;
            }else{
                zero++;
            }
            double val = p.feedForward(points.get(i));
            if (val > 0.65)
                val = 1;
            else
                val = 0;
            if (answers.get(i) == val)
                classify++;
        }
        return "Dataset:\n" + answers.size()+" total  " + one + " red points " + zero + " blue points -- classified: " + classify + "/100";
    }
}
