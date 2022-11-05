package ru.mipt.bit.platformer.commands;

import java.util.Collection;

public interface ICommandGenerator {
    Collection<ICommand> generateCommands();
}
