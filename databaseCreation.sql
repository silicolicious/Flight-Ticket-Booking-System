create database flight_booking_system;
use flight_booking_system;

create table User(
	userId int primary key auto_increment,
    username varchar(25) not null,
    password varchar(12) not null,
    emailAddress varchar(25) not null
);

create table Customer(
	id int primary key,
    firstName varchar(25) not null,
    lastName varchar(25) not null,
    address varchar(255) not null,
    mobileNo varchar(10) not null,
    foreign key (id) references User(userId)
);

create table Agent(
	id int primary key,
    roleName enum('Head', 'Manager','Superviser') not null,
    foreign key (id) references User(userId)
);

create table Flight(
	flightNo int primary key,
    departureCity varchar(25) not null,
    arrivalCity varchar(25) not null
);

create table Schedule(
	scheduleId int primary key auto_increment,
    flightNo int not null,
    startTime datetime not null,
    endTime datetime not null,
    availableSeats int default 60,
    foreign key (flightNo) references Flight(flightNo)
);

create table Booking(
	bookingId int primary key auto_increment,
    userId int not null,
    scheduleId int not null,
    noOfTickets int not null,
    foreign key (userId) references User(userId),
    foreign key (scheduleId) references Schedule(scheduleId)
);