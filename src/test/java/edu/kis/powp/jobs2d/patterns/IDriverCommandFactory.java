package edu.kis.powp.jobs2d.patterns;

import edu.kis.powp.jobs2d.drivers.command.DriverCommand;

public interface IDriverCommandFactory {
    DriverCommand createDrawTriangleCommand(int height, int width);
}
