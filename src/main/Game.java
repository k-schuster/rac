package main;

import dao.MapReader;
import dao.Road;
import graph.City;
import gui.GraphVisualization;
import logic.Board;

import java.util.Scanner;

public class Game {


    public static void main(String[] args) {

        Board board = new Board();

        try {
            Scanner sc = new Scanner(System.in);
            String file;
            System.out.println("Please enter path to map file: ");
            file = sc.nextLine();
            sc.close();


            MapReader mr = new MapReader(board, file);

        } catch (Exception e) {
            System.err.println("Error.");
        }

        // testing println
        for (City c : board.getCities()) {
            System.out.println(c);
        }


        GraphVisualization graph = new GraphVisualization(board);

        graph.createAndShowGUI();

        /*
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gv.createAndShowGUI();
            }
        });
        */

    }

}