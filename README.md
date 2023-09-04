Credit Application Processing

This Java project, created during the Udemy Java course, is designed to process credit applications, calculate scores, and make decisions on whether they should be accepted or rejected. It accepts JSON files containing application data and automatically processes them. Once the processing is complete, it generates an output file containing both the application information and the decision.
Table of Contents

## Table of Contents

- [Usage](#usage)
- [Requirements](#requirements)
- [Installation](#installation)
- [Running the Application](#running-the-application)


## Usage

This application is intended for processing credit applications in JSON format. To use it:

    Prepare a JSON file containing the credit application data you want to process.
    Run the application.
    Place the JSON file in the project's input directory.
    The application will process the file, make a decision, and create an output file in the output directory.
    You can keep putting JSON files and it will keep processing them.

## Requirements

To run this project, you need the following:

    Java Development Kit (JDK) 8 or higher
    Maven (for building and managing dependencies)

## Installation

Clone this repository to your local machine:

    git clone https://github.com/mikolajp2137/CreditApplication.git

Navigate to the project directory:


    cd CreditApplication

Build the project using Maven:


    mvn clean install

## Running the application

You can run this application in two modes, if you pass no arguments it will keep watching for JSON files to appear in C:\CreditAppOutput and if it finds them it will start processing the application they contain, you can keep adding those files and it will keep processing them until you stop the app.
You can also run this application with two arguments: 1st being a id of an existing application (looking like this - dce5d364-a5bc-4072-96d4-64afdb2b70d5) and either a PESEL preceded by N- or NIP preceded by S-. Then the app will display dce5d364-a5bc-4072-96d4-64afdb2b70d5.dat file if it exists.