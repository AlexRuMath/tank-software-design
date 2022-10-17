package ru.mipt.bit.platformer.parser;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.entity.TankEntity;
import ru.mipt.bit.platformer.entity.TreeEntity;
import ru.mipt.bit.platformer.util.Transform;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserLevelFromTxt implements IParser {

    private List<Transform> tankPositions = new ArrayList<>();
    private List<Transform> treePositions = new ArrayList<>();
    private Transform playerPosition;

    private int height = 0;
    private int width = 0;

    public ParserLevelFromTxt(int width, int height){
        this.height = height;
        this.width = width;
    }

    @Override
    public ParseResult parse(String pathToFile) {
        ParseResult result = new ParseResult();
        result.width = this.width;
        result.height = this.height;
        try {
            FileReader fileReader = new FileReader(pathToFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            processingLine(bufferedReader);

            result.addList(TankEntity.class, this.tankPositions);
            result.addList(TreeEntity.class, this.treePositions);
            result.setPosition(this.playerPosition);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private void processingLine(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        Transform transform;

        for (int y = this.height - 1; y >= 0; y--) {
            for (int x = 0; x < this.width; x++) {
                char symbol = line.charAt(x);
                switch (symbol) {
                    case '_':
                        break;
                    case 'T':
                        transform = this.parsePosition(x, y);
                        treePositions.add(transform);
                        break;
                    case 'X':
                        transform = this.parsePosition(x, y);
                        tankPositions.add(transform);
                    case 'P':
                        playerPosition = this.parsePosition(x, y);
                        break;
                }
            }
            line = reader.readLine();
        }
    }

    private Transform parsePosition(int x, int y) {
        return new Transform(new GridPoint2(x, y), 0f);
    }
}
