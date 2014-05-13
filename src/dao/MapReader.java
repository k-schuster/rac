package dao;

import dao.daoexceptions.MapReaderException;
import graph.Affiliation;
import graph.City;
import logic.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.lang.Integer;

public class MapReader {

    private Board board;

    private Scanner sc;
    private int NUMBER_OF_CITIES;
    private List<String[]> tokens;


    public MapReader(Board board, String filePath)
            throws FileNotFoundException, MapReaderException {
        this.board = board;
        File file = new File(filePath);
        this.sc = new Scanner(file);
        this.tokens = readFile();

        spawnBoard();
        board.initAlliances();

    }

    private String[] sanitize(String string) {
        return string.split("\\s+");
    }

    public List<String[]> readFile() throws MapReaderException {
        if (sc.hasNextLine()) {
            NUMBER_OF_CITIES = sc.nextInt();
            sc.nextLine();
        } else {
            throw new MapReaderException("File empty.");
        }

        List<String[]> result = new ArrayList<String[]>();
        while (this.sc.hasNextLine()) {
            result.add(sanitize(sc.nextLine()));
        }
        sc.close();
        return result;
    }

    public void spawnBoard() throws MapReaderException {
        // int counter = 0;
        for (String[] line : tokens) {
            if (line[0].equalsIgnoreCase("V")) {
                int id = Integer.parseInt(line[1]);
                Affiliation affiliation = Affiliation.valueOf(line[2]);
                int x = Integer.parseInt(line[3]);
                int y = Integer.parseInt(line[4]);
                board.addCity(id, new City(id, affiliation, x, y));
            } else if (line[0].equalsIgnoreCase("E")) {
                int c1 = Integer.parseInt(line[1]);
                int c2 = Integer.parseInt(line[2]);
                board.addRoad(c1, c2);
            } else {
                throw new MapReaderException();
            }

        }
    }

}