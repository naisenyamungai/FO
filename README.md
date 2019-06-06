## Project Name
           Site Maintenance

## Author
            [Naisenya Mungai](https://github.com/naisenyamungai)
            Student - Full Stack Developer(Java & Android)


## Project Description
> An app for a Site Maintenance Manager. The Site Maintenance Manager should be able to add a list of the maintenance engineers, and for each engineer, add the sites that the engineer maintains.

## Project Overview
>- As a Manager, I need to be able to see a list of all our engineers.
>- As a Manager, I need to be able to select an engineer, see their details, and see a list of all the sites that they maintain.
>- As a Manager, I need to add new engineers to our system when they are hired.
>- As a Manager, I need to be able to add new sites to a specific engineer.
>- As a Manager, I need to be able to update an engineer's details.
>- As a Manager, I need to be able to update a site's details.
>- As a Manager, I need to be able to delete an engineer if they're no longer employed here.
>- As a Manager, I need to be able to delete a site if it is decommissioned.


## Technologies Used
>The application was created using below technologies;
>- Spark Java
>- HTML
>- CSS
>- BootStrap


## Known Bugs
> No known Bugs at the moment

## SetUp Instructions
>- Internet Connection is required for the following instructions to be executed
>- Open Chrome, Mozilla or any browser in your disposal.
>- Internet Connection is a must.
>- Search naisenyamungai on github.
>- Search on the active link on Github user name.
>- No other downloads or plug-ins are required.
>- Type in the github username, see details appear.
>- For any feedback on bugs and errors contact naisenyamungai@gmail.com or 0721635386.
>- Alternatively you can clone the project onto your local machine to have a sneak peak at the code used.


## Database installation instructions
>-Make sure you have postgres Db installed locally in your machine and follow below commands.

>-Creating a role and credentials
postgres=# create username and password

>-Creating database
postgres=# create database site_maintenance;

>-connecting into the created database
postgres=# \c site_maintenance;

>-Creating Engineers table.
site_maintenance=# create table engineers (id int PRIMARY KEY, name varchar, ek_Number varchar, site_id varchar);

>-Creating sites table
site_maintenance=# create table sites (id int PRIMARY KEY, name varchar, engineer_id varchar, site_Number varchare);


## Support and contact details
> naisenyamungai@gmail.com

## To access this webpage open below link
> https://still-garden-10480.herokuapp.com/

## [License](https://github.com/naisenyamungai/Github-Search/LICENSE.md)

MIT © [Naisenya Mungai ](https://github.com/naisenyamungai)