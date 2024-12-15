package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.kis.powp.jobs2d.drivers.command.DriverCommand;

public class SelectFigureFromCommandOptionListener implements ActionListener {

    private final DriverCommand command;

    public SelectFigureFromCommandOptionListener(DriverCommand command) {
        this.command = command;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.command.execute();
    }
}
