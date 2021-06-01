import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class flappyBirds implements ActionListener {

    public static flappyBirds flappyBird;

    public final int WIDTH = 1500, HEIGHT = 800;

    public Renderer renderer;

    public Rectangle bird;

    public ArrayList<Rectangle> columns;

    public Random rand;

    public int ticks, ymotions;

    public flappyBirds(){
        JFrame jframe = new JFrame();
        renderer = new Renderer();
        Timer timer = new Timer(20, this);
        rand = new Random();


        jframe.add(renderer);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.setTitle("Flappy Bird Java Game");
        jframe.setResizable(false);
        jframe.setVisible(true);

        bird = new Rectangle(WIDTH/2 - 10, HEIGHT/2 - 10, 20, 20);
        columns = new ArrayList<Rectangle>();

        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);
        timer.start();

    }

    public static void main(String[] args) {
        flappyBird = new flappyBirds();
    }


    public void repaint(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.orange);
        g.fillRect(0,HEIGHT-120, WIDTH, 150);

        g.setColor(Color.green);
        g.fillRect(0, HEIGHT-120, WIDTH, 20);

        g.setColor(Color.red);
        g.fillRect(bird.x, bird.y, bird.width, bird.height);

        for (Rectangle column: columns){
            paintColumn(g, column);
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {


        ticks++;
        int speed =15;

        for (int i = 0; i<columns.size(); i++){
            Rectangle column = columns.get(i);
            column.x -= speed;
        }


        if (ticks%2 == 0 && ymotions<15){
            ymotions +=2;
        }


        for (int i = 0; i<columns.size(); i++){
            Rectangle column = columns.get(i);
            if (column.x + column.width<0){
                columns.remove(column);
                if (column.y==0){
                    addColumn(false);
                }
            }
        }

        bird.y += ymotions;

        renderer.repaint();

    }

    public void addColumn(boolean start){
        int space= 300;
        int width=100;
        int height = 50 + rand.nextInt(300);
        if (start) {
            columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
            columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
        }
        else{
            columns.add(new Rectangle(columns.get(columns.size()-1).x + 600, HEIGHT-height-120, width, height));
            columns.add(new Rectangle(columns.get(columns.size()-1).x, 0, width, HEIGHT-height-space));

        }
    }


    public void paintColumn (Graphics g, Rectangle column){
        g.setColor(Color.green.darker());
        g.fillRect(column.x, column.y, column.width, column.height );
    }

}
