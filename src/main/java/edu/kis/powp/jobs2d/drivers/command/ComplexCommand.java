package edu.kis.powp.jobs2d.drivers.command;

import java.util.Collection;

public class ComplexCommand implements DriverCommand {
    private final Collection<DriverCommand> commands;

    public ComplexCommand(Collection<DriverCommand> commands) {
        this.commands = commands;
    }

    public void execute() {
        this.commands.forEach(DriverCommand::execute);
    }
}
