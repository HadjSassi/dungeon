import javax.swing.*;
import java.util.ArrayList;

//todo finish the RenderEngine class
public class RenderEngine extends JPanel implements Engine {


    private ArrayList<Displayable> renderList;

    public RenderEngine() {

    }

    public void addToRenderList(Displayable displayable) {
        renderList.add(displayable);
    }

    public void addToRenderList(ArrayList<Displayable> displayables) {
        renderList.addAll(displayables);
    }


    public void paint(){

    }

    @Override
    public void update() {

    }
}
