package project.pkg3;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Used to render everything in GameScreen
 *
 * @author Elias
 */
public class Renderer extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        Project3.gs.render((Graphics2D) g);

    }

}
