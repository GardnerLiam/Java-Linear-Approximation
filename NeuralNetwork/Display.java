package NeuralNetwork;

import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {

    public static Info info = new Info();
    private static Panel panel = new Panel();
    private Listener l = new Listener();

    public Display(int width, int height) {
        setTitle("Linear Function Approximation");
        setSize(width, height);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new GridLayout(1, 2));
        add(panel);
        add(info);
        addKeyListener(l);
        panel.addKeyListener(l);
        setVisible(true);
    }

    public static void update() {
        panel.grabFocus();
    }

}

class Info extends JPanel {

    int index = 0;
    int tick = 0;

    JTextArea estimation = new JTextArea(Run.p.printLinear());

    JTextArea real = new JTextArea(Run.getEquation());
    JTextArea weights = new JTextArea(Run.p.getWeights());
    JTextArea prediction = new JTextArea(Run.p.classification(index));
    JTextArea points = new JTextArea(Run.getData());

    public Info() {
        setLayout(new GridLayout(5, 1));
        estimation.setEditable(false);
        real.setEditable(false);
        weights.setEditable(false);
        prediction.setEditable(false);
        points.setEditable(false);

        estimation.setBackground(Run.infoColor);
        real.setBackground(Run.infoColor);
        weights.setBackground(Run.infoColor);
        prediction.setBackground(Run.infoColor);
        points.setBackground(Run.infoColor);

        estimation.setFont(Run.font);
        real.setFont(Run.font);
        weights.setFont(Run.font);
        prediction.setFont(Run.font);
        points.setFont(Run.font);

        this.add(points);

        this.add(real);
        this.add(estimation);
        this.add(weights);
        this.add(prediction);
    }

    void update() {
        estimation.setText(Run.p.printLinear());
        weights.setText(Run.p.getWeights());
        prediction.setText(Run.p.classification(index));
        points.setText(Run.getData());
        tick++;
        if (tick % 100 == 0) {
            if (index == Run.points.size() - 1) {
                index = 0;
            } else {
                index++;
            }
        }

        Display.update();
    }
}

class Panel extends JPanel {


    private void background(Color c, Graphics g) {
        g.setColor(c);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void ellipse(int x, int y, int size, Color c, Graphics g) {
        g.setColor(Color.BLACK);
        int newSize = size + 2;
        g.fillOval(x - newSize / 2, y - newSize / 2, newSize, newSize);
        g.setColor(c);
        g.fillOval(x - size / 2, y - size / 2, size, size);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        background(Color.WHITE, g);

        BasicStroke s = new BasicStroke(Run.strokeWeight);

        g.setColor(Color.BLACK);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(s);
        g2d.drawLine((int) Run.getX(0), (int) Run.getY(Run.f(0)), (int) Run.getX(1), (int) Run.getY(Run.f(1)));

        for (int i = 0; i < Run.points.size(); i++) {
            int x = (int) Run.getX(Run.points.get(i)[0]);
            int y = (int) Run.getY(Run.points.get(i)[1]);

            if (Run.answers.get(i) == 1) {
                g.setColor(Color.RED);
                ellipse(x, y, 32, Color.RED, g);
            } else {
                g.setColor(Color.BLUE);
                ellipse(x, y, 32, Color.BLUE, g);
            }

            if (Run.p.feedForward(Run.points.get(i)) > 0.5) {
                ellipse(x, y, 16, new Color(200, 0, 0), g);
            } else {
                ellipse(x, y, 16, new Color(0, 0, 200), g);
            }

            g.fillOval(x - 8, y - 8, 16, 16);
            if (Run.p.autoTrain) {
                Run.p.step(Run.points.get(i), Run.answers.get(i));
            }
        }

        g.setColor(Color.GREEN);
        g2d.drawLine((int) Run.getX(0), (int) Run.getY(Run.p.guessF(0)), (int) Run.getX(1), (int) Run.getY(Run.p.guessF(1)));

        Run.width = getWidth();
        Run.height = getHeight();

        Display.info.update();

        repaint();
    }
}
