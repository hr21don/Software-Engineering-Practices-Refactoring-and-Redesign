# Software Engineering practise

## Software Engineering Practices, Refactoring and Redesign

Your team is developing Tinee, a simple text-based Ticket System for in-house use by your organisation.Users can create tickets to log various issues (e.g., bugs to fix, or Todos), which they or others can updatewith further information as the issue is resolved. Tinee is intended to be a lightweight utility that can bequickly integrated into other development projects, e.g., by running it from shell scripts, embedding it intoother applications, and so forth.

Tinee uses a basic client-server architecture:
* The server records a database of tickets, identified by tags.
* A ticket is essentially a list of tines (a portmanteau of “ticket message line”).Each tine contains the username of its author, and a single line of text as the message body.
* A client connected to a server can, e.g., read the existing tines for a tag, and add new tines.

Your teammates have developed a server in Java and, notably, message classes for the client-servercommunication. They have specified the above concepts (“tag”, “tine”, etc.) in more detail in the associatedJavadoc; e.g., see the Javadoc for the sep.tinee.server and the sep.tinee.message packages and theirclasses.To test the base framework and the server, they also wrote a makeshift client in Java. This client supportssome basic functionality, but it is buggy, incomplete and lacking any real design. By this, we mean softwaredesign – application architecture, design patterns, code structure, and development processes – as opposedto “user interface design” (also important, but not the topic of this module).

<img width="382" alt="TineeApplication" src="https://user-images.githubusercontent.com/91548582/137599718-90e736a6-054b-43d5-a88c-3eeefb05f9b4.PNG">

Your organisation has also identified these features/extensions as requirements:
* Undo of (appropriate) commands in drafting mode.
* Support for internationalisation (i18n); e.g., UI messages in alternative languages.

The command line client must support all existing and future functionality with gracefulhandling of user input errors (e.g., mistyped commands or invalid arguments). The UI should report usageerrors and continue running as expected, i.e., without crashing the session.

<!--
------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

* PROJECT TITLE:
* PURPOSE OF PROJECT:
* VERSION or DATE:
* HOW TO START THIS PROJECT:
* AUTHORS:
* USER INSTRUCTIONS:
-->
