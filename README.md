# Discord Shenanigans
Discord Bot For our server

## Architecture

### Command Structure
Commands are split into "Modules"
<br>
<br>
For each module a top level class must be 
defined which implements the `Command` Interface. Further,
<br> 
#### Sub Commands
Any Sub command of you command must be defined in you command module and 
extend your command class.
#### Flags and inputs for you command and it's subcommands
a filter chain manager class must be defined which implements the 
`CommandFilterChainManager`.
<br>
