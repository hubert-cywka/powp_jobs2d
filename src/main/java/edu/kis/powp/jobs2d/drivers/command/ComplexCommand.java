package edu.kis.powp.jobs2d.drivers.command;

import java.util.List;

public class ComplexCommand implements DriverCommand {
    private final List<DriverCommand> commands;

    public ComplexCommand(List<DriverCommand> commands) {
        this.commands = commands;
    }

    public void execute() {
        this.commands.forEach(DriverCommand::execute);
    }
}
