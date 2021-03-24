package dev.triumphteam.core.test.objects

import dev.triumphteam.core.internal.CommandBase
import dev.triumphteam.core.internal.CommandManager
import dev.triumphteam.core.internal.command.CommandData


class TestCommandManager : CommandManager() {

    override fun registerCommand(command: CommandBase) {
        val data = CommandData.process(command)

        val coreCommand = TestCommand()

        register(data.commandName, coreCommand)
        data.aliases.forEach { register(it, coreCommand) }
    }

}