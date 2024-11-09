package backend.academy.logic;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommandLineArgs {
    @Parameter(names = "--path", description = "Path to the logs", required = true)
    private String path;

    @Parameter(names = "--from", description = "Start date (format: yyyy/MM/dd)", required = true)
    private String from;

    @Parameter(names = "--to", description = "End date (format: yyyy/MM/dd)")
    private String to;

    public static CommandLineArgs parseArguments(String[] args) {
        CommandLineArgs cmdArgs = new CommandLineArgs();
        JCommander jCommander = JCommander.newBuilder()
            .addObject(cmdArgs)
            .build();

        jCommander.parse(args);
        return cmdArgs;
    }
}
