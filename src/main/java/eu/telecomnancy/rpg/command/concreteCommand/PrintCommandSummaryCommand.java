package eu.telecomnancy.rpg.command.concreteCommand;

import eu.telecomnancy.rpg.App.GameFacade;
import eu.telecomnancy.rpg.command.Command;
import eu.telecomnancy.rpg.command.CommandParameters;

import java.io.*;

public class PrintCommandSummaryCommand implements Command {
    @Override
    public void execute(GameFacade gameFacade, CommandParameters parameters) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("CommandSummary.txt");
            if (inputStream == null) {
                throw new FileNotFoundException("CommandSummary.txt not found!");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void undo(GameFacade gameFacade, CommandParameters parameters) {
        // nothing
    }

    @Override
    public String toString() {
        return "";
    }
}
