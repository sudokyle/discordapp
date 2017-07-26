# Discord Shenanigans
Discord Bot For our server

## Architecture

### Command Structure
Commands are split into "Modules"
<br>
<br>
For every command C, it must implement the Command interface.
For every command C, there must also be a factory for it and it’s subcommand(s).
For every sub command S of C it must override the `execute` and `interpret` commands.

Command C should provide general purpose functions that can be used by all of it’s sub commands.
If the C is to not have any subcommands, the core functionality should be implemented in command C itself.
