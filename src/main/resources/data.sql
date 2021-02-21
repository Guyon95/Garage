
INSERT INTO role(name) VALUES('ROLE_ADMIN');
INSERT INTO role(name) VALUES('ROLE_MECHANIC');
INSERT INTO role(name) VALUES('ROLE_ADMINISTRATOR');
INSERT INTO role(name) VALUES('ROLE_BACKOFFICE');
INSERT INTO role(name) VALUES('ROLE_CASHIER');
INSERT INTO role(name) VALUES('ROLE_USER');

INSERT INTO labor(labor_number,description,price,created,modified) VALUES (101,'Check the car',45.00,NOW(),NOW());
INSERT INTO labor(labor_number,description,price,created,modified) VALUES (102,'Replace brakes',20.50,NOW(),NOW());
INSERT INTO labor(labor_number,description,price,created,modified) VALUES (103,'Replace windshield wipers',19.99,NOW(),NOW());
INSERT INTO labor(labor_number,description,price,created,modified) VALUES (104,'Change motoroil',9.99,NOW(),NOW());
INSERT INTO labor(labor_number,description,price,created,modified) VALUES (105,'Change tyre(s)',25.00,NOW(),NOW());

INSERT INTO part(part_number,description,price,created,modified) VALUES (101,'Windshield wipers',7.50,NOW(),NOW());
INSERT INTO part(part_number,description,price,created,modified) VALUES (102,'Motoroil 1L',19.50,NOW(),NOW());
INSERT INTO part(part_number,description,price,created,modified) VALUES (103,'Tyre',102.99,NOW(),NOW());
INSERT INTO part(part_number,description,price,created,modified) VALUES (104,'Brakes',14.95,NOW(),NOW());
INSERT INTO part(part_number,description,price,created,modified) VALUES (105,'Fuse',1.99,NOW(),NOW());

INSERT INTO customer(first_Name,last_Name,phone_Number,created,modified) VALUES ('Peter','Janssen','0612345678',NOW(),NOW());
INSERT INTO customer(first_Name,last_Name,phone_Number,created,modified) VALUES ('Peter','Hielkema','0687654321',NOW(),NOW());
INSERT INTO customer(first_Name,last_Name,phone_Number,created,modified) VALUES ('Sjaak','Pietersen','0698765431',NOW(),NOW());
INSERT INTO customer(first_Name,last_Name,phone_Number,created,modified) VALUES ('Darryl','Huizen','0633333333',NOW(),NOW());
INSERT INTO customer(first_Name,last_Name,phone_Number,created,modified) VALUES ('Niels','Muller','0645894589',NOW(),NOW());

INSERT INTO vehicle(license_plate,created,modified) VALUES ('AA20GM',NOW(),NOW());
INSERT INTO vehicle(license_plate,created,modified) VALUES ('AA21GM',NOW(),NOW());
INSERT INTO vehicle(license_plate,created,modified) VALUES ('AA22GM',NOW(),NOW());
INSERT INTO vehicle(license_plate,created,modified) VALUES ('AA23GM',NOW(),NOW());
INSERT INTO vehicle(license_plate,created,modified) VALUES ('AA24GM',NOW(),NOW());


INSERT INTO vehicle_customer (customer_id,vehicle_id) VALUES (1,1);
INSERT INTO vehicle_customer (customer_id,vehicle_id) VALUES (2,2);
INSERT INTO vehicle_customer (customer_id,vehicle_id) VALUES (3,3);
INSERT INTO vehicle_customer (customer_id,vehicle_id) VALUES (4,4);
INSERT INTO vehicle_customer (customer_id,vehicle_id) VALUES (5,5);

INSERT INTO workorder(vehicle_id,created,modified,status,wo_number,appointment) VALUES (1,NOW(),NOW(),'CHECK_CAR',11111,'2021-03-18 10:00');

INSERT INTO workorder_row(workorder_id,type,labor_id,amount,created,modified,customer_Agreed) VALUES (1,'LABOR',1,1,NOW(),NOW(),true);