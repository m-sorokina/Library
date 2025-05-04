package menu;

import exceptions.CommandCancelException;

@FunctionalInterface
public interface IMenuCommand {
    public boolean get() throws CommandCancelException;
}
