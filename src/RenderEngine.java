import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {


    private ArrayList<Displayable> renderList;
    private int fps;
    private int frameCount;
    private long lastFpsTime;
    private static int remainingTime = 60;

    public RenderEngine() {
        renderList = new ArrayList<>();
        this.fps = 0;
        this.frameCount = 0;
        this.lastFpsTime = System.currentTimeMillis();
    }

    public static void setRemainingTime(int seconds) {
        remainingTime = seconds;
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
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        String scoreText = "SCORE: " + Main.getScore();
        FontMetrics metrics = graphics.getFontMetrics(graphics.getFont());
        int x = (getWidth() - metrics.stringWidth(scoreText)) / 2;
        graphics.drawString(scoreText, x, 30);

        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        graphics.drawString("FPS: " + fps, 10, 20);

        graphics.setColor(Color.BLACK);
        graphics.drawString("Time Left: " + remainingTime + "s", getWidth() - 140, 20);
    }

    @Override
    public void update() {
        this.repaint();
        this.updateFps();
    }

    private void updateFps() {
        frameCount++;
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFpsTime >= 1000) {
            this.fps = frameCount;
            this.frameCount = 0;
            this.lastFpsTime = currentTime;
            this.updateTime();
        }
    }

    private void updateTime() {
        remainingTime--;
        if (remainingTime == 0) {
            Main.perdre();
        }
    }
}

//this RenderEngine class is finished