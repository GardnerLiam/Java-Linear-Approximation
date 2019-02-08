package NeuralNetwork;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_T){
            Run.p.autoTrain = !Run.p.autoTrain;
        }

        if (e.getKeyCode() == KeyEvent.VK_E){
            for (int i = 0; i < Run.points.size(); i++){
                Run.p.step(Run.points.get(i), Run.answers.get(i));
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }

        if (e.getKeyCode() == KeyEvent.VK_UP){
            if (Run.strokeWeight < 10)
                Run.strokeWeight++;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            if (Run.strokeWeight > 1) {
                Run.strokeWeight--;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            Run.p.alpha /= 10;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            Run.p.alpha *= 10;
        }

        if (e.getKeyCode() == KeyEvent.VK_W){
            System.out.println(Run.p.printLinear());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
