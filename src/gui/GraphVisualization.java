package gui;

import dao.Road;
import logic.Board;

import java.awt.Dimension;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GraphVisualization {

    private Board board;

    private JButton[] cities;
    private RoadLines roads;
    private Dimension size;
    private int buttonSize;

    public GraphVisualization(Board board) {
        this.board = board;

        // initialize buttons array
        this.cities = new JButton[board.getNumberOfCities()];
        for (int i = 0; i < cities.length; i++) {
            cities[i] = new JButton();
        }

        // convert Roads from Board into drawable lines
        this.roads = new RoadLines();
        for (Road r : board.getRoads()) {
            roads.addLine(r.c1.getX(),
                    r.c1.getY(),
                    r.c2.getX()
                    ,                          r.c2.getY());
        }

        // this controls the window size
        this.size = new Dimension(600, 600);
        // this controls the button size
        this.buttonSize = 60;
    }


    // this method should probably be split into two
    private void setUpButtons() {
        for (int i = 0; i < cities.length; i++) {
            cities[i].setText(board.getCity(i).getId() + " " +
                    board.getCity(i).getAffiliation().name());

            cities[i].setBounds(board.getCity(i).getX() - buttonSize/2,
                    board.getCity(i).getY() - buttonSize/2,
                    buttonSize,
                    buttonSize);
        }
    }

    private void addComponentsToPane(Container pane) {
        pane.setLayout(null);

        //add text and bounds to buttons
        setUpButtons();
        //add them to the frame one by one
        for (int i = 0; i < cities.length; i++) {
            pane.add(cities[i]);
        }

        //set window size for the line component
        roads.setSize(size);
        //add the lines to the frame
        pane.add(roads);

    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Rome and Carthage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Size and display the window.
        frame.setSize(size);
        frame.setVisible(true);
    }

}