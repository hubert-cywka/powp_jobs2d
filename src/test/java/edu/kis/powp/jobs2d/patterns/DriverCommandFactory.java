package edu.kis.powp.jobs2d.patterns;

import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.command.ComplexCommand;
import edu.kis.powp.jobs2d.drivers.command.DriverCommand;
import edu.kis.powp.jobs2d.drivers.command.OperateToCommand;
import edu.kis.powp.jobs2d.drivers.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.List;

public class DriverCommandFactory implements IDriverCommandFactory {
    private final DriverManager driverManager;

    public DriverCommandFactory(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public DriverCommand createDrawTriangleCommand(int height, int width) {
        List<DriverCommand> commands = new ArrayList<>();

        commands.add(new SetPositionCommand(this.driverManager, 0, -height / 2));
        commands.add(new OperateToCommand(this.driverManager, width / 2, height / 2));
        commands.add(new OperateToCommand(this.driverManager, -width / 2, height / 2));
        commands.add(new OperateToCommand(this.driverManager, 0, -height / 2));

        return new ComplexCommand(commands);
    }
}
