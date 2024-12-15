package edu.kis.powp.jobs2d.patterns;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.command.ComplexCommand;
import edu.kis.powp.jobs2d.drivers.command.DriverCommand;
import edu.kis.powp.jobs2d.drivers.command.OperateToCommand;
import edu.kis.powp.jobs2d.drivers.command.SetPositionCommand;

import java.util.ArrayList;
import java.util.List;

public class DriverCommandFactory {
    private final Job2dDriver driver;

    public DriverCommandFactory(Job2dDriver driver) {
        this.driver = driver;
    }

    public DriverCommand createDrawSquareCommand(int size) {
        List<DriverCommand> commands = new ArrayList<>();

        int a = size / 2;

        commands.add(new SetPositionCommand(this.driver, -a, -a));
        commands.add(new OperateToCommand(this.driver, -a, a));
        commands.add(new OperateToCommand(this.driver, a, a));
        commands.add(new OperateToCommand(this.driver, a, -a));
        commands.add(new OperateToCommand(this.driver, -a, -a));

        return new ComplexCommand(commands);
    }

    public DriverCommand createDrawTriangleCommand(int height, int width) {
        List<DriverCommand> commands = new ArrayList<>();

        commands.add(new SetPositionCommand(this.driver, 0, height));
        commands.add(new OperateToCommand(this.driver, width / 2, 0));
        commands.add(new OperateToCommand(this.driver, -width / 2, 0));
        commands.add(new OperateToCommand(this.driver, 0, height / 2));

        return new ComplexCommand(commands);
    }
}
