import interfaces.Displayable;
import interfaces.Engine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static enumerations.NumericalConstants.*;
import static enumerations.StringConstants.*;

public class RenderEngine extends JPanel implements Engine {


    private ArrayList<Displayable> renderList;
    private int fps;
    private int frameCount;
    private long lastFpsTime;
    private static int remainingTime = (int) INITIAL_VALUE.getNumericalValue();
    private static int gamingTime = (int) INITIAL_VALUE.getNumericalValue();

    public RenderEngine() {
        renderList = new ArrayList<>();
        this.fps = (int) INITIAL_VALUE.getNumericalValue();
        this.frameCount = (int) INITIAL_VALUE.getNumericalValue();;
        this.lastFpsTime = System.currentTimeMillis();
    }

    public static void setRemainingTime(int seconds) {
        remainingTime = seconds;
        if(gamingTime < remainingTime) {
            gamingTime = remainingTime;
        }
    }

    public void addToRenderList(Displayable displayable) {
        if (!renderList.contains(displayable))
            renderList.add(displayable);
    }

    public void addToRenderList(ArrayList<Displayable> displayables) {
        if (!renderList.containsAll(displayables))
            renderList.addAll(displayables);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        for (Displayable displayable : renderList)
            displayable.draw(graphics);

        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font(POLICE_NAME.getValue(), Font.BOLD, (int) POLICE_SIZE.getNumericalValue()));
        String scoreText = SCORE.getValue() + Main.getScore();
        FontMetrics metrics = graphics.getFontMetrics(graphics.getFont());
        int x = (getWidth() - metrics.stringWidth(scoreText)) / 2;
        graphics.drawString(scoreText, x, (int) SCORE_POSY.getNumericalValue());

        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font(POLICE_NAME.getValue(), Font.BOLD, (int) POLICE_SIZE.getNumericalValue()));
        graphics.drawString(FPS.getValue() + fps, (int) FPS_POSX.getNumericalValue(),  (int) FPS_POSY.getNumericalValue());

        graphics.setColor(Color.BLACK);
        graphics.drawString(TIME.getValue() + remainingTime , getWidth() -  (int) TIME_POSX.getNumericalValue(),  (int) TIME_POSY.getNumericalValue());
    }

    @Override
    public void update() {
        this.repaint();
        this.updateFps();
    }

    private void updateFps() {
        frameCount++;
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFpsTime >= ONE_SECOND.getNumericalValue()) {
            this.fps = frameCount;
            this.frameCount = (int) INITIAL_VALUE.getNumericalValue();
            this.lastFpsTime = currentTime;
            this.updateTime();
        }
    }

    private void updateTime() {
        remainingTime--;
        if (remainingTime == INITIAL_VALUE.getNumericalValue()) {
            Main.perdre();
        }
    }

    public static void resetTime(){
        remainingTime = gamingTime;
    }
}
