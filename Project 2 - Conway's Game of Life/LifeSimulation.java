package Lab02;
/**
* Author: Daniel Yu
* 
* To simulate Game of Life with roughly 1000 iterations.
*/

public class LifeSimulation {
    public static void main(String[] args) throws InterruptedException {
        Landscape scape = new Landscape(20, 20, .9);
        System.out.println(scape);
        LandscapeDisplay display = new LandscapeDisplay(scape, 10);

        // Uncomment below when advance() has been finished
        // while (true) {
        for (int i = 0; i <= 1000; i++) {
            // display.saveImage("data/life_frame_" + String.format("%03d", i) + ".png");
            scape.advance();
            display.repaint();
            Thread.sleep(250);

        }

    }
}
