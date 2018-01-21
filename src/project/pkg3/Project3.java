package project.pkg3;

/**
 * Air Hockey java game!
 *
 * @author Elias
 * @version 1.0
 */
public class Project3 {

    static GameScreen gs = new GameScreen();

    /**
     * Main method is the driver to start the game
     *
     * @param args String argument for compiler
     */
    public static void main(String[] args) {
        gs.gameRefreshTimer();
        //space equals start game
    }
}
