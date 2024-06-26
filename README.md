# Task Manager

This project is a practical task manager implemented with Java Spring Boot, Spring Data/JPA, and MySQL Server. 
It allows users to create tasks with descriptions and due dates, offering convenient filtering and search capabilities based on descriptions, due dates, and completion status. 
Additionally, users can delete tasks, with data persistence ensured through MySQL server storage.

A demo SQLfile is also included with this project named: TaskManagerData.sql

![Screenshot 2](./Image-assets/Taskmanagerscreen.png)

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)

## Installation

This project was developed using Eclipse; however, it should be compatible with other modern IDEs as long as the necessary application dependencies and settings are configured appropriately.

Step 1: Download project from Github

- Git clone the project to a local repository.
- Import the project to your IDE of choice.
- If using eclipse, make sure that when running project you do the following:
    - Make sure to change the MySQL User Name and Password to the ones matching your local MySQL Server.
        - Find the application.properties file and make the necessary changes:
        - ![Screenshot 1](./Image-assets/Mysql-settings.png)
    - To Run application: Enter "<b>mvn spring-boot:run</B>" into the terminal within the project location (/web-based-task-manager-4.1) / (Git Bash recommended)
    - Alternative Option: Find and right click on the "WebBasedTaskManager4Application.java" file and Run As > Spring Boot App.
    - All potential errors should appear on the console.
    - In your browser of choice, enter in the URL: <b>http://localhost:8080/</b>
    - The project should run on your chosen browser now.

## Usage

- Once the project is running on the browser it show display the following:
    - Users will be able to create tasks with descriptions and due dates.
    - Users will also be able to search for specific tasks using the filter options. The can look for tasks based on descriptions, due dates and status.
- Users will be able to mark a task as Done or Not Done by clicking on the button. Users will also be able to delete the tasks at any point.
