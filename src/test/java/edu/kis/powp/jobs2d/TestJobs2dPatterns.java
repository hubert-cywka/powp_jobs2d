package edu.kis.powp.jobs2d;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.ILine;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.adapter.DrawPanelControllerDriverAdapter;
import edu.kis.powp.jobs2d.drivers.adapter.LineDrawerAdapter;
import edu.kis.powp.jobs2d.drivers.command.DriverCommand;
import edu.kis.powp.jobs2d.events.SelectFigureFromCommandOptionListener;
import edu.kis.powp.jobs2d.events.SelectScribbleFigureOptionListener;
import edu.kis.powp.jobs2d.events.SelectEnvelopeFigureOptionListener;
import edu.kis.powp.jobs2d.events.SelectSquareFigureOptionListener;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.lines.decorators.LineColorDecorator;
import edu.kis.powp.jobs2d.patterns.DriverCommandFactory;
import edu.kis.powp.jobs2d.patterns.IDriverCommandFactory;

public class TestJobs2dPatterns {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Setup test concerning preset figures in context.
	 * 
	 * @param application Application context.
	 */
	private static void setupPresetTests(Application application) {
		DriverManager driverManager = DriverFeature.getDriverManager();
		application.addTest("Figure Joe 1", new SelectSquareFigureOptionListener(driverManager));
		application.addTest("Figure Joe 2", new SelectScribbleFigureOptionListener(driverManager));
		application.addTest("Figure Jane 1", new SelectEnvelopeFigureOptionListener(driverManager));

		IDriverCommandFactory driverCommandFactory = new DriverCommandFactory(driverManager);
		DriverCommand drawTriangleCommand = driverCommandFactory.createDrawTriangleCommand(200, 200);
		application.addTest("Triangle", new SelectFigureFromCommandOptionListener(drawTriangleCommand));
	}

	/**
	 * Setup driver manager, and set default driver for application.
	 * 
	 * @param application Application context.
	 */
	private static void setupDrivers(Application application) {
		Job2dDriver loggerDriver = new LoggerDriver();
		DriverFeature.addDriver("Logger Driver", loggerDriver);
		DriverFeature.getDriverManager().setCurrentDriver(loggerDriver);

		DrawPanelController controller = DrawerFeature.getDrawerController();

		Job2dDriver testDriver = new DrawPanelControllerDriverAdapter(controller);
		DriverFeature.addDriver("DrawPanel Driver", testDriver);

		ILine specialLine = LineFactory.getSpecialLine();
		Job2dDriver specialLineDriver = new LineDrawerAdapter(controller, specialLine);
		DriverFeature.addDriver("SpecialLine Driver", specialLineDriver);

		ILine specialRedLine = new LineColorDecorator(specialLine, Color.RED);
		Job2dDriver specialRedLineDriver = new LineDrawerAdapter(controller, specialRedLine);
		DriverFeature.addDriver("SpecialRedLine Driver", specialRedLineDriver);

		DriverFeature.updateDriverInfo();
	}

	/**
	 * Setup menu for adjusting logging settings.
	 * 
	 * @param application Application context.
	 */
	private static void setupLogger(Application application) {
		application.addComponentMenu(Logger.class, "Logger", 0);
		application.addComponentMenuElement(Logger.class, "Clear log",
				(ActionEvent e) -> application.flushLoggerOutput());
		application.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> logger.setLevel(Level.FINE));
		application.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> logger.setLevel(Level.INFO));
		application.addComponentMenuElement(Logger.class, "Warning level",
				(ActionEvent e) -> logger.setLevel(Level.WARNING));
		application.addComponentMenuElement(Logger.class, "Severe level",
				(ActionEvent e) -> logger.setLevel(Level.SEVERE));
		application.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> logger.setLevel(Level.OFF));
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Application app = new Application("2d jobs Visio");
				DrawerFeature.setupDrawerPlugin(app);

				DriverFeature.setupDriverPlugin(app);
				setupDrivers(app);
				setupPresetTests(app);
				setupLogger(app);

				app.setVisibility(true);
			}
		});
	}

}
