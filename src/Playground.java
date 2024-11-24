import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Playground {
    private static ArrayList<Sprite> environment;

    public Playground(String level) {
        environment = new ArrayList<>();
        try {
            Image grass = ImageIO.read(new File(Main.getImagePath() + "grass.png"));
            Image rock = ImageIO.read(new File(Main.getImagePath() + "rock.png"));
            Image trap = ImageIO.read(new File(Main.getImagePath() + "trap.png"));
            Image tree = ImageIO.read(new File(Main.getImagePath() + "tree.png"));
            Image door = ImageIO.read(new File(Main.getImagePath() + "door.png"));
            Image gate = ImageIO.read(new File(Main.getImagePath() + "gate.png"));
            Image heart = ImageIO.read(new File(Main.getImagePath() + "heart.png"));

            BufferedReader bufferedReader = new BufferedReader(new FileReader(level));
            ArrayList<Point> heartPositions = new ArrayList<>();
            String line = bufferedReader.readLine();
            if (line != null && line.startsWith("+[{")) {
                heartPositions = parseHeartCoordinates(line);
            }

            int row = 0;
            int column = 0;
            line = bufferedReader.readLine();
            while (line != null) {
                column = 0;
                for (byte character : line.getBytes(StandardCharsets.UTF_8)) {
                    switch (character) {
                        case 'T':
                            environment.add(new SolidSprite(column * tree.getWidth(null), row * tree.getHeight(null), tree.getWidth(null), tree.getHeight(null), tree,"tree"));
                            break;
                        case ' ':
                            environment.add(new Sprite(column * grass.getWidth(null), row * grass.getHeight(null), grass.getWidth(null), grass.getHeight(null), grass,"grass"));
                            break;
                        case 'R':
                            environment.add(new SolidSprite(column * rock.getWidth(null), row * rock.getHeight(null), rock.getWidth(null), rock.getHeight(null), rock,"rock"));
                            break;
                        case 'H':
                            environment.add(new MalusSprite(column * trap.getWidth(null), row * trap.getHeight(null), trap.getWidth(null), trap.getHeight(null), trap,"trap"));
                            break;
                        case '.':
                            environment.add(new LevelSprite(column * door.getWidth(null), row * door.getHeight(null), door.getWidth(null), door.getHeight(null), door,"door"));
                            break;
                        case 'E':
                            environment.add(new Sprite(column * door.getWidth(null), row * door.getHeight(null), door.getWidth(null), door.getHeight(null), door,"door"));
                            break;
                        case 'G':
                            environment.add(new LevelSprite(column * gate.getWidth(null), row * gate.getHeight(null), gate.getWidth(null), gate.getHeight(null), gate,"gate", true));
                            break;
                    }
                    column++;
                }
                column = 0;
                row++;
                line = bufferedReader.readLine();
            }
            for (Point p : heartPositions) {
                environment.add(new BonusSprite(p.x * heart.getWidth(null), p.y * heart.getHeight(null), heart.getWidth(null), heart.getHeight(null), heart, "heart", true, 30));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Sprite> getSolidSpriteList() {
        ArrayList<Sprite> solidSpriteList = new ArrayList<>();
        for (Sprite sprite : environment) {
            if (sprite instanceof SolidSprite) {
                solidSpriteList.add(sprite);
            }
        }
        return solidSpriteList;
    }

    public ArrayList<Displayable> getSpriteList() {
        return new ArrayList<>(environment);
    }

    private ArrayList<Point> parseHeartCoordinates(String line) {
        ArrayList<Point> heartPositions = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\{(\\d+),(\\d+)\\}");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            heartPositions.add(new Point(x, y));

        }
        return heartPositions;
    }


}
