import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {


    private ArrayList<Displayable> renderList;

    public RenderEngine() {
        renderList = new ArrayList<>();
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
        for(Displayable displayable : renderList)
            displayable.draw(graphics);
    }

    @Override
    public void update() {
        this.repaint();
    }
}

//this RenderEngine class is finished