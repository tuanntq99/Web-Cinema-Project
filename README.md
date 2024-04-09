# Cinema Project

This project is a web application implementation of a cinema WebApp using Java Servlet API.
In a nutshell, there are movies and their sessions, which you can search for using filters, then select needed one, choose seats and buy tickets. But first, you should sign up/in.
After choose a movie with seat, room, cinema and food you can pay on VNPAY. After purchase, web send a ticket to your email, you can find your tickets in your email. 
Admin can add/remove new movies and movie, food and seat sessions as well.

## Table of Contents
* [Introduction](#introduction)
* [Changelog](#changelog)
* [Technologies](#technologies)
* [Functions](#functions)

## Introduction
The system is an online showcase of a single-screen cinema.
##### There are 3 roles:
+ Authorized user
+ Moderator
+ Administrator

##### The system includes:
+ An user can see the session schedule,foods and available seats for current movies, and has the opportunity to sign up. The user should be able to sort the movie sessions' schedule by 
    1) the feature movie;
    2) the cinema; 
    3) the date/time of the session (by default); as well as filter available movies (due to empty seats) in the schedule.
+ A signed-up user has to be able to purchase a ticket for the selected movie session.
+ User can sign up/in with confirm email. Also can forgot, reset and change password.
+ The administrator can add a new movie to the schedule, cancel the movie, and review the attendance of the hall, the administator also can CRUD foods, seats, room and cinema. And only administrator can change role user.

## Changelog
08 April 2024:
- The entire project was upgraded with lombok annotation.
- The logic has been moved from the controller to the service.
- Added email to payment method after choose movie.
- Added logout system.
- Much more fixes, corrections, upgrades to clean code.

## Technologies
* Java
* Spring Boot
* Spring Security
* Hibernate
* MySQL
* VNPAY
* JWT
* Postman and SwaggerUI
#### Used tools:
* Maven
* Lombok
* ModelMapper
  
## Functions
- Everyone can browse the application without logging into the system.
- Admin can add, edit and delete the day and time when a particular movie will be playing in the cinema.
- Non logged in users can register and login to the application.
- User registration validation: each field must be entered correctly.
- User must confirm his registration to the e-mail adress he provided.
- Logged in users can reserveSeatConfiguration seats.
- User create choose a movie with seats, schedule, cinema and food attached. After that create a bill and user can pay on VNPAY. And after that bill will send to user's email.
