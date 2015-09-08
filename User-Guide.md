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
User input in the command bar: `add <task name> <due dates> <priority> <recurring?>` to add a task into the list.
#### Update/edit a task
User input in the cmmmand bar: `update <s/n> <area to edit> <content to update>` to update a task's property specified by `<area to edit>` to `<content to update>`
#### Delete a task
User input in the command bar: `delete <s/n>` to delete a task

### View tasks 
Users are able to view the tasks according to their preference as seen below
###### Sort by priority
###### Sort by due dates

# For Power Users - Extra feature 
This would allow users to be able to search for specific tasks and their information(deadlines)
### Power Search
User do searches by add keywords to command "list|ls", existing tasks with the given keyword or keywords will be shown in the result window

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
`prio <s/n> <date to update>` | update priority of existing task
`desc <s/n> <date to update>` | update description of existing task
`delete|d <s/n>` | delete task
`delete|d <keyword>` | delete tasks with the keyword
`delete|d <keyword1> <keyword2> ...` | delete tasks with the keyword
`deleteall` | delete all tasks
`list|ls ` | list all existing tasks
`list|ls <keyword>` | list search results based on the keyword
`list|ls <keyword1> <keyword2> ...` | list search results based on the combination of keywords
`man <s/n>` | show all the information about a task, including description
`man` | show all valid commands
`undo` | undo the last operation
`quit|q` | quit PIXEList
