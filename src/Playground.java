import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Playground {
    private ArrayList<Sprite> environment;

    public Playground(String level) {
        this.environment = new ArrayList<>();
        try {
            Image grass = ImageIO.read(new File(Main.imagePath + "grass.png"));
            Image rock = ImageIO.read(new File(Main.imagePath + "rock.png"));
            Image trap = ImageIO.read(new File(Main.imagePath + "trap.png"));
            Image tree = ImageIO.read(new File(Main.imagePath + "tree.png"));

            BufferedReader bufferedReader = new BufferedReader(new FileReader(level));
            int row = 0;
            int column = 0;
            String line = bufferedReader.readLine();
            while (line != null) {
                for (byte character : line.getBytes(StandardCharsets.UTF_8)) {
                    switch (character) {
                        case 'T':
                            environment.add(new SolidSprite(column * tree.getWidth(null), row * tree.getHeight(null), tree.getWidth(null), tree.getHeight(null), tree));
                            break;
                        case ' ':
                            environment.add(new Sprite(column * grass.getWidth(null), row * grass.getHeight(null), grass.getWidth(null), grass.getHeight(null), grass));
                            break;
                        case 'R':
                            environment.add(new SolidSprite(column * rock.getWidth(null), row * rock.getHeight(null), rock.getWidth(null), rock.getHeight(null), rock));
                            break;
                        // I suppose that the trap symbol is X
                        case 'X':
                            environment.add(new SolidSprite(column * trap.getWidth(null), row * trap.getHeight(null), trap.getWidth(null), trap.getHeight(null), trap));
                            break;
                    }
                    column++;
                }
                column = 0;
                row++;
                line = bufferedReader.readLine();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Sprite> getSolidSpriteList() {
        ArrayList<Sprite> solidSpriteList = new ArrayList<>();
        for (Sprite sprite : this.environment) {
            if (sprite instanceof SolidSprite) {
                solidSpriteList.add((SolidSprite) sprite);
            }
        }
        return solidSpriteList;
    }

    public ArrayList<Displayable> getSpriteList() {
        ArrayList<Displayable> spriteList = new ArrayList<>();
        for (Sprite sprite : this.environment) {
            spriteList.add((Displayable) sprite);
        }
        return spriteList;
    }
}

// we need to check the trap symbol