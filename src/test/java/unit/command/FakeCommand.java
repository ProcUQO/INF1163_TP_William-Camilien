package unit.command;

import command.Command;

public class FakeCommand implements Command {

    public boolean executed = false;
    public boolean undone = false;

    @Override
    public void execute() {
        executed = true;
    }

    @Override
    public void undo() {
        undone = true;
    }
}
