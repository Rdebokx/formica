Contributing to the Formica Project
===================================
Contributions to the Formica project are very much appreciated. Below you can find instructions on how to run the Formica project on your local machine, as well as instructions
on how to contribute to the Formica project.

Cheers!

## Setting up your environment
Formica runs on Java 8 so please make sure you have Java 8 or higher installed on your machine.
Technically, Java 8 would be the only prerequisite for running and contributing to Formica, but it is advised to use Maven to streamline your development cycle.

## Tests
Formica uses a JUnit test suite that can be run independently, e.g. in your IDE, or through Maven by using `mvn test` to run the entire test suite.
Please make sure that if you change any code in the Formica code base as part of your contribution, affected JUnit tests are updated accordingly to verify these changes.
Also when adding functionalities to the Formica module, please make sure that your changes are accompanied by JUnit tests that verify the validity of these added functionalities.

## Feature Requests
Do you have a feature request for the Formica project or do you have some other suggestion? That's awesome!
Please file this request through an issue at the [Formica Github Repository](https://github.com/Rdebokx/formica/issues), accompanied by an explanation why you think
this should be added to Formica.

## Bug Reports
If you happen to find a bug in the Formica software, please submit it through an issue at the [Formica Github Repository](https://github.com/Rdebokx/formica/issues), 
your bug reports are very much appreciated. Please make sure that your bug report is accompanied by any relevant information, so it will be easier to reproduce. 
At a minimum, the following information should be included in the bug report:
* A detailed description of the observed behavior.
* A detailed description of the expected behavior.
* System information of the machine this was run on, eg. Operating System, Java version, IDE used (if relevant), etc.

## Code contributions
Code contributions are our favorite contributions. Please submit any code contributions to the Formica through pull requests to the Formica Github repo so they can be reviewed accordingly.
Please make sure that any code contributions are accompanied by JUnit tests that verify these changes.

## Future work
In the short-term development is aimed at:
* Publishing Formica to a central repository
* Support a variety of stopping criteria
* Support different data types to be stored in DataPoints
* Support some more Distance Metrics, including Distance Metric that can deal with DataPoints that contain various data types.
* Providing a better interface for running custom jobs through the command line. 
