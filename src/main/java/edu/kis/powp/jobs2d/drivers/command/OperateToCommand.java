package edu.kis.powp.jobs2d.drivers.command;

import edu.kis.powp.jobs2d.drivers.DriverManager;

public class OperateToCommand implements DriverCommand {
    private final DriverManager driverManager;
    private final int x;
    private final int y;

    public OperateToCommand(DriverManager driverManager, int x, int y) {
        this.driverManager = driverManager;
        this.x = x;
        this.y = y;
    }

    public void execute() {
        this.driverManager.getCurrentDriver().operateTo(this.x, this.y);
    }
}
