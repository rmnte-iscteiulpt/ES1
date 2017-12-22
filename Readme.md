Class: LIGE-PL

Group 113

Members:
Rui Tomé, Nº 73553, Developer

Project Variant: AntiSpamConfigurationForLeisureMailbox

Youtube software showcase: https://youtu.be/TprnuuMNcMk

Software Observations:
When compiling the results, namely the .eps and .r file, the program exports the results into the location of the .jar file, or the root of the classpath.
The export and import works on .cfg files, instead of writing a new rules.cf file. The rules.cf file is only used to list the rules.
When the software generates the configuration through the algorithm, it blocks the interface as there's no separate threads making the algorithm run.
The jUnitTests are located inside the src folder.
Every time the manual table is updated on the interface, it doesn't update the table, it creates a new one from scratch to hold the configurations set by the main interface.