
## Branch Manager Address Book API

###Problem Statement
As a Reece Branch Manager
I would like an address book application
So that I can keep track of my customer contacts

###Acceptance Criteria
Address book will hold name and phone numbers of
contact entries. Create a REST API which will have endpoints for the
following -

* Users should be able to add new contact entries
* Users should be able to remove existing contact
entries
* Users should be able to print all contacts in an
address book
* Users should be able to maintain multiple address
books
* Users should be able to print a unique set of all
contacts across multiple address books
## What's inside

 * SprintBoot Service source code covering endpoints for all ACs
 * Gradle build and wrapper
 * Docker file
 * In memory H2 DB config(properties)
 * Integration tests
 * Unit tests
 * Postman collection spec

## Building and deploying the application

### Building the application

The project uses [Gradle](https://gradle.org) as a build tool. It already contains
`./gradlew` wrapper script, so there's no need to install gradle.

To build the project execute the following command:

```bash
  ./gradlew build
```

### Running the application

Start the application by executing the following command:

```bash
  ./gradlew bootRun
```