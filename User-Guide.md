# ABOUT THIS GUIDE

### Purpose

This guide provides comprehensive guidelines and step-by-step instructions 
in utilising PIXEList, the powerful to-do list.

### Who should read this guide

This guide is intended for end-users of PIXEList. 

# ABOUT PIXEList

Pixelist is a simple yet powerful to-do list to help better manage your daily tasks. 
It relies heavily on keyboard inputs and is aimed at users who prefers keyboard over mouse. 
Users can add, remove or clear tasks upon completion easily and quickly with this to-do list.
Deadlines can be set for tasks along with the priority level for each task.

# Getting Started
### Installation
This program requires at least Java Platform, Standard Edition Development Kit(JDK 6) 
and a 32bit operating system. Upon downloading, extract the files from the ZIP file and 
run the .jar file.

### Basic tasks manipulations
#### Add a new task. 
User input in the command bar: `add|a` and what ever information they want to add a task into the list, for example `add hello_world` adds a task named "hello_world", with its date, time, priority and description unset.

#### Update/edit a task
User input in the command bar: `name <s/n> <name to update>` to update a task's name.

User input in the command bar: `date <s/n> <date to update>` to update a task's date.

User input in the command bar: `time <s/n> <name to update>` to update a task's time.

User input in the command bar: `prio <s/n> <priority to update>` to update a task's priority.

User input in the command bar: `desc <s/n> <description to update>` to update a task's description.

#### Delete a task
Normal delete: User input in the command bar: `delete|d <s/n>` to delete a task.

Delete plus: User input in the command bar: `delete|d` followed by keyword or combination of keywords to delete all tasks with the given keyword(s).

### View tasks 
Tasks are shown in all tasks window in the order of time and priority by default.

User input in the command bar `list|ls time` to view tasks in the order of date and time in the result window.

User input in the command bar `list|ls prio` to view tasks in the order of priority (high to low) in the result window.

User input in the command bar `list|ls week` to view tasks due in the result window.

# For Power Users - Extra feature 
This would allow users to be able to search for specific tasks and their information(deadlines)
### Power Search
User do searches by add keywords to command `list|ls`, existing tasks with the given keyword or keywords will be shown in the result window

# Cheatsheet
Command | Description
--------| ------------
`add|a <task name>` | add new task by name
`add|a <task name> <Task date>` | add new task by name and date
`add|a <task name> <Task date> <Task time>` | add new task by name, date and time
`add|a <task name> <Task date> <task time> <priority>` | add new task by name, date, time and priority
`add|a <task name> <Task date> <Task time> <priority> <description>` | add new task with all the details 
`name <s/n> <name to update>` | update name of existing task
`date <s/n> <date to update>` | update date of existing task
`time <s/n> <time to update>` | update time of existing task
`prio <s/n> <priority to update>` | update priority of existing task
`desc <s/n> <description to update>` | update description of existing task
`delete|d <s/n>` | delete task
`delete|d <keyword>` | delete tasks with the keyword
`delete|d <keyword1> <keyword2> ...` | delete tasks with the keyword
`delete all` | delete all tasks
`list|ls ` | list all existing tasks
`list|ls <keyword>` | list search results based on the keyword
`list|ls <keyword1> <keyword2> ...` | list search results based on the combination of keywords
`list|ls time` | show all tasks in the order of time
`list|ls prio` | show all tasks in the order of prio
`list|ls week` | show all tasks due this week
`man <s/n>` | show all the information about a task, including description
`man` | show all valid commands
`undo` | undo the last operation
`quit|q` | quit PIXEList

