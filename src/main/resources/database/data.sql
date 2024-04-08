INSERT INTO Role (code, roleName)
VALUES ('Admin', 'ROLE_ADMIN');
INSERT INTO Role (code, roleName)
VALUES ('Mod', 'ROLE_MODERATOR');
INSERT INTO Role (code, roleName)
VALUES ('User', 'ROLE_USER');

INSERT INTO UserStatus (code, name)
VALUES ('False', 'InActive');
INSERT INTO UserStatus (code, name)
VALUES ('True', 'Active');

INSERT INTO BillStatus (name)
VALUES ('InActive');
INSERT INTO BillStatus (name)
VALUES ('Active');

INSERT INTO SeatType (nameType)
VALUES ('Standard');
INSERT INTO SeatType (nameType)
VALUES ('VIP');

INSERT INTO SeatStatus (code, nameStatus)
VALUES ('False', 'Occupied');
INSERT INTO SeatStatus (code, nameStatus)
VALUES ('True', 'Available');

INSERT INTO Rate (description, code)
VALUES ('1 star', 'Bad');
INSERT INTO Rate (description, code)
VALUES ('2 star', 'Good');
INSERT INTO Rate (description, code)
VALUES ('3 star', 'Great');



INSERT INTO rankcustomer (point, description, name, isActive)
VALUES (20, 'Bronze Rank', 'Bronze', true);
INSERT INTO rankcustomer (point, description, name, isActive)
VALUES (40, 'Silver Rank', 'Silver', true);
INSERT INTO rankcustomer (point, description, name, isActive)
VALUES (60, 'Gold Rank', 'Gold', true);
INSERT INTO rankcustomer (point, description, name, isActive)
VALUES (80, 'Diamond Rank', 'Diamond', true);

INSERT INTO promotion (percent, quantity, type, startTime, EndTime, description, name, isActive, rankCustomerId)
VALUES (5, 3, 'Bronze', '2024-03-21', '2024-04-22', 'Bronze member', 'coupon 5%', true, 1);
INSERT INTO promotion (percent, quantity, type, startTime, EndTime, description, name, isActive, rankCustomerId)
VALUES (10, 2, 'Silver', '2024-03-21', '2024-04-22', 'Silver member', 'coupon 10%', true, 2);
INSERT INTO promotion (percent, quantity, type, startTime, EndTime, description, name, isActive, rankCustomerId)
VALUES (15, 1, 'Gold', '2024-03-21', '2024-04-22', 'Gold member', 'coupon 15%', true, 3);
INSERT INTO promotion (percent, quantity, type, startTime, EndTime, description, name, isActive, rankCustomerId)
VALUES (20, 5, 'Diamond', '2024-03-21', '2024-04-22', 'Diamond member', 'coupon 20%', true, 4);

INSERT INTO food (price, description, image, nameOfFood, isActive)
VALUES (30000, 'Snack', 'src', 'PopCorn', true);
INSERT INTO food (price, description, image, nameOfFood, isActive)
VALUES (20000, 'Drink', 'src', 'Pepsi', true);
INSERT INTO food (price, description, image, nameOfFood, isActive)
VALUES (20000, 'Drink', 'src', 'Coca', true);
INSERT INTO food (price, description, image, nameOfFood, isActive)
VALUES (25000, 'Snack', 'src', 'Chip', true);

INSERT INTO movieType (movieTypeName, isActive)
VALUES ('Comedy', true);
INSERT INTO movieType (movieTypeName, isActive)
VALUES ('Horror', true);
INSERT INTO movieType (movieTypeName, isActive)
VALUES ('Romance', true);
INSERT INTO movieType (movieTypeName, isActive)
VALUES ('Drama', true);
INSERT INTO movieType (movieTypeName, isActive)
VALUES ('Fantasy', true);

INSERT INTO movie (description, director, endtime, heroimage, image, isactive, language, movieduration, name,
                   premieredate, trailer, movietypeid, rateid)
VALUES ('description comedy type', 'Rajkumar Hirani', '2024-04-20', 'src', 'src', true, 'English', 100, '3 idiots',
        '2024-03-23', 'url', 1, 3);
INSERT INTO movie (description, director, endtime, heroimage, image, isactive, language, movieduration, name,
                   premieredate, trailer, movietypeid, rateid)
VALUES ('description horror type', 'James Wan', '2024-04-20', 'src', 'src', true, 'English', 100, 'Insidious',
        '2024-03-23', 'url', 2, 2);
INSERT INTO movie (description, director, endtime, heroimage, image, isactive, language, movieduration, name,
                   premieredate, trailer, movietypeid, rateid)
VALUES ('description fantasy type', 'Christopher Nolan', '2024-04-20', 'src', 'src', true, 'English', 100,
        'Interstellar', '2024-03-23', 'url', 5, 3);
INSERT INTO movie (description, director, endtime, heroimage, image, isactive, language, movieduration, name,
                   premieredate, trailer, movietypeid, rateid)
VALUES ('description fantasy type', 'Ridley Scott', '2024-04-20', 'src', 'src', true, 'English', 100, 'The Martian',
        '2024-03-23', 'url', 5, 2);
INSERT INTO movie (description, director, endtime, heroimage, image, isactive, language, movieduration, name,
                   premieredate, trailer, movietypeid, rateid)
VALUES ('description romance type', 'James Cameron', '2024-04-20', 'src', 'src', true, 'English', 100, 'Titanic',
        '2024-03-23', 'url', 3, 2);

INSERT INTO cinema (Address, Code, Description, IsActive, NameOfCinema)
VALUES ('3 Nguyen Trai', 'NTC', 'standard cinema', true, 'Nguyen Trai Cinema');
INSERT INTO cinema (Address, Code, Description, IsActive, NameOfCinema)
VALUES ('4 Le Loi', 'LLC', 'VIP cinema', true, 'Le Loi Cinema');
INSERT INTO cinema (Address, Code, Description, IsActive, NameOfCinema)
VALUES ('5 Quang Trung', 'QTC', 'standard cinema', true, 'Quang Trung Cinema');

INSERT INTO room (Type, Capacity, Code, Description, IsActive, Name, CinemaId)
VALUES (100, 50, 'NTC-1', '2D', true, 'Standard Room', 1);
INSERT INTO room (Type, Capacity, Code, Description, IsActive, Name, CinemaId)
VALUES (100, 50, 'LLC-1', '2D', true, 'VIP Room', 2);
INSERT INTO room (Type, Capacity, Code, Description, IsActive, Name, CinemaId)
VALUES (100, 50, 'QTC-1', 'imax', true, 'Standard Room', 3);

INSERT INTO schedule (Code, EndAt, IsActive, Name, Price, StartAt, MovieId, RoomId)
VALUES ('NTC-2303-1', '2024-03-30', true, 'Earlier', 60000, '2024-03-23', 1, 1);
INSERT INTO schedule (Code, EndAt, IsActive, Name, Price, StartAt, MovieId, RoomId)
VALUES ('NTC-2303-3', '2024-03-30', true, 'Earlier', 60000, '2024-03-23', 3, 1);
INSERT INTO schedule (Code, EndAt, IsActive, Name, Price, StartAt, MovieId, RoomId)
VALUES ('LLC-2303-1', '2024-03-30', true, 'Earlier', 60000, '2024-03-23', 1, 2);
INSERT INTO schedule (Code, EndAt, IsActive, Name, Price, StartAt, MovieId, RoomId)
VALUES ('LLC-2303-5', '2024-03-30', true, 'Earlier', 60000, '2024-03-23', 5, 2);
INSERT INTO schedule (Code, EndAt, IsActive, Name, Price, StartAt, MovieId, RoomId)
VALUES ('LLC-0104-4', '2024-04-08', true, 'Standard', 50000, '2024-04-01', 4, 2);
INSERT INTO schedule (Code, EndAt, IsActive, Name, Price, StartAt, MovieId, RoomId)
VALUES ('QTC-2303-2', '2024-03-30', true, 'Earlier', 60000, '2024-03-23', 2, 3);

INSERT INTO seat (Number, IsActive, Line, RoomId, SeatStatusId, SeatTypeId)
VALUES (10, true, 'A', 1, 2, 1);
INSERT INTO seat (Number, IsActive, Line, RoomId, SeatStatusId, SeatTypeId)
VALUES (10, true, 'B', 2, 2, 2);
INSERT INTO seat (Number, IsActive, Line, RoomId, SeatStatusId, SeatTypeId)
VALUES (10, true, 'C', 3, 2, 1);

INSERT INTO user (Email, IsActive, Name, Password, PhoneNumber, Point, UserName, RankCustomerId, RoleId, UserStatusId)
VALUES ('anhtran@gmail.com', true, 'Anh', '$2a$12$IMIsn9Pnw4GfgPN.Azl1eOPzoPwKBm2jPp5JcF.GoQyGeCmKoBR0O', '0912004595',
        40, 'Tran Anh', 2, 1, 1);
INSERT INTO user (Email, IsActive, Name, Password, PhoneNumber, Point, UserName, RankCustomerId, RoleId, UserStatusId)
VALUES ('anhquoc@gmail.com', true, 'Anh', '$2a$12$uNSG4ljyLKwvy95tdjHZjOeMx.jtmdN9UPC0OaySUPzhAhtnO.w6W', '0912004596',
        60, 'Quoc Anh', 3, 2, 1);
INSERT INTO user (Email, IsActive, Name, Password, PhoneNumber, Point, UserName, RankCustomerId, RoleId, UserStatusId)
VALUES ('vanlam@gmail.com', true, 'Lam', '$2a$12$LXBNn/Vz0Z2YVqQSD2KwXuJKkPNjYqOJ.bc3oFlqoLXXTPM1rtEV6', '0912004597',
        40, 'Van Lam', 2, 2, 1);

INSERT INTO bill (CreateTime, IsActive, Name, TotalMoney, TradingCode, UpdateTime, BillStatusId, PromotionId,
                  CustomerId)
VALUES ('2024-03-24 18:05:13', true, 'Bill Tran Anh', 148500, 'BTA-2403', '2024-03-24 18:08:13', 2, 2, 1);
INSERT INTO bill (CreateTime, IsActive, Name, TotalMoney, TradingCode, UpdateTime, BillStatusId, PromotionId,
                  CustomerId)
VALUES ('2024-03-24 17:03:31', true, 'Bill Quoc Anh', 255000, 'QTA-2403', '2024-03-24 17:08:53', 2, 3, 1);
INSERT INTO bill (CreateTime, IsActive, Name, TotalMoney, TradingCode, UpdateTime, BillStatusId, PromotionId,
                  CustomerId)
VALUES ('2024-03-26 15:08:02', true, 'Bill Tran Anh', 108000, 'BTA-2603', '2024-03-26 15:11:19', 2, 2, 1);
INSERT INTO bill (CreateTime, IsActive, Name, TotalMoney, TradingCode, UpdateTime, BillStatusId, PromotionId,
                  CustomerId)
VALUES ('2024-03-26 15:08:02', true, 'Bill Van Lam', 216000, 'BVL-2603', '2024-03-26 15:11:19', 2, 2, 3);

INSERT INTO billFood (Quantity, BillId, FoodId)
VALUES (1, 1, 3);
INSERT INTO billFood (Quantity, BillId, FoodId)
VALUES (1, 1, 4);
INSERT INTO billFood (Quantity, BillId, FoodId)
VALUES (1, 2, 2);
INSERT INTO billFood (Quantity, BillId, FoodId)
VALUES (2, 2, 3);
INSERT INTO billFood (Quantity, BillId, FoodId)
VALUES (2, 2, 1);

INSERT INTO ticket (Code, IsActive, PriceTicket, ScheduleId, SeatId)
VALUES ('NTC-1-2-1', true, 60000, 2, 1);
INSERT INTO ticket (Code, IsActive, PriceTicket, ScheduleId, SeatId)
VALUES ('LLC-1-3-2', true, 60000, 3, 2);
INSERT INTO ticket (Code, IsActive, PriceTicket, ScheduleId, SeatId)
VALUES ('QTC-1-6-3', true, 60000, 6, 3);
INSERT INTO ticket (Code, IsActive, PriceTicket, ScheduleId, SeatId)
VALUES ('LLC-1-4-2', true, 60000, 4, 2);

INSERT INTO billTicket (Quantity, BillId, TicketId)
VALUES (2, 1, 1);
INSERT INTO billTicket (Quantity, BillId, TicketId)
VALUES (3, 2, 2);
INSERT INTO billTicket (Quantity, BillId, TicketId)
VALUES (2, 3, 3);
INSERT INTO billTicket (Quantity, BillId, TicketId)
VALUES (4, 4, 4);


