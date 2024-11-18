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
            Image grass = ImageIO.read(new File(Main.getImagePath() + "grass.png"));
            Image rock = ImageIO.read(new File(Main.getImagePath() + "rock.png"));
            Image trap = ImageIO.read(new File(Main.getImagePath() + "trap.png"));
            Image tree = ImageIO.read(new File(Main.getImagePath() + "tree.png"));
            Image door = ImageIO.read(new File(Main.getImagePath() + "trap.png"));
            Image gate = ImageIO.read(new File(Main.getImagePath() + "trap.png"));

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
                        case 'H':
                            environment.add(new SolidSprite(column * trap.getWidth(null), row * trap.getHeight(null), trap.getWidth(null), trap.getHeight(null), trap));
                            break;
                        case '.':
                            environment.add(new LevelSprite(column * door.getWidth(null), row * door.getHeight(null), door.getWidth(null), door.getHeight(null), door));
                            break;
                        case 'G':
                            environment.add(new LevelSprite(column * gate.getWidth(null), row * gate.getHeight(null), gate.getWidth(null), gate.getHeight(null), gate,true));

                            break;
                    }
                    column++;
                }
                column = 0;
                row++;
                line = bufferedReader.readLine();
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Sprite> getSolidSpriteList() {
        ArrayList<Sprite> solidSpriteList = new ArrayList<>();
        for (Sprite sprite : this.environment) {
            if (sprite instanceof SolidSprite) {
                solidSpriteList.add(sprite);
            }
        }
        return solidSpriteList;
    }

    public ArrayList<Displayable> getSpriteList() {
        return new ArrayList<>(this.environment);
    }
}
