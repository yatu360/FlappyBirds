import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class flappyBirds implements ActionListener {

    public static flappyBirds flappyBird;

    public final int WIDTH = 800, HEIGHT = 800;

    public Renderer renderer;

    public Rectangle bird;

    public flappyBirds(){
        JFrame jframe = new JFrame();
        renderer = new Renderer();
        Timer timer = new Timer(20, this);


        jframe.add(renderer);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.setResizable(false);
        jframe.setVisible(true);

        bird = new Rectangle(WIDTH/2 - 10, HEIGHT/2 - 10, 20, 20);

        timer.start();

    }

    public static void main(String[] args) {
        flappyBird = new flappyBirds();
    }


    public void repaint(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.orange);
        g.fillRect(0,HEIGHT-150, WIDTH, 150);

        g.setColor(Color.green);
        g.fillRect(0, HEIGHT-150, WIDTH, 20);

        g.setColor(Color.red);
        g.fillRect(bird.x, bird.y, bird.width, bird.height);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        renderer.repaint();
    }
}
