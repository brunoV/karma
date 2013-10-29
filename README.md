
     /$$   /$$                                             /$$
    | $$  /$$/                                            | $$
    | $$ /$$/   /$$$$$$   /$$$$$$  /$$$$$$/$$$$   /$$$$$$ | $$
    | $$$$$/   |____  $$ /$$__  $$| $$_  $$_  $$ |____  $$| $$
    | $$  $$    /$$$$$$$| $$  \__/| $$ \ $$ \ $$  /$$$$$$$|__/
    | $$\  $$  /$$__  $$| $$      | $$ | $$ | $$ /$$__  $$
    | $$ \  $$|  $$$$$$$| $$      | $$ | $$ | $$|  $$$$$$$ /$$
    |__/  \__/ \_______/|__/      |__/ |__/ |__/ \_______/|__/

                      It's all that matters

A web service to track the karma of things.

## Build

    mvn package

## Run

    java -jar target/karma-*.jar server karma.yml

The karma service will be running on localhost, port 8080. Admin port is on port 8081.
Requires Java 7.

## Basic API endpoints

Get the karma for a thing:

    GET karma/{thing}

Bump or decrease the karma of a thing:

    POST karma/{thing}/bump
    POST karma/{thing}/down

Get the top or bottom `n` things according to their karma:

    GET karma/top/{n}
    GET karma/bottom/{n}

## Database

Karma uses SQLite as its backend by default. At startup, it will create a
database file on the service folder called `karma.db`.

You can change the storage engine in the `database` section of the `karma.yml`
config file; simply pick a diferent JDBC URL string and driver.
