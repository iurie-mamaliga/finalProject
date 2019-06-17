-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS app_user CASCADE;

CREATE TABLE app_user (
  id SERIAL PRIMARY KEY,
  first_Name varchar(64) NOT NULL,
  last_name varchar (64) NOT NULL,
  user_name varchar(32) NOT NULL UNIQUE,
  password varchar(32) NOT NULL,
  role varchar(32),
  salt varchar(255) NOT NULL,
  reset_key varchar(255) NOT NULL,
  phone varchar(16) NOT NULL,
  email varchar(64) NOT NULL UNIQUE
);

COMMIT;

--EVENT TABLE
DROP TABLE IF EXISTS event CASCADE;

CREATE TABLE event (
  event_id SERIAL PRIMARY KEY,
  event_name varchar(64) NOT NULL,
  description varchar(255) NOT NULL,
  start_date TIMESTAMP NOT NULL,
  end_date TIMESTAMP NOT NULL,
  mandatory boolean
);

COMMIT;

--USER EVENT TABLE
DROP TABLE IF EXISTS user_event CASCADE;

CREATE TABLE user_event (
  user_id int,
  event_id int
);

COMMIT;

ALTER TABLE user_event 
ADD FOREIGN KEY (user_id) REFERENCES app_user (id),
ADD FOREIGN KEY (event_id) REFERENCES event (event_id);

COMMIT;

--MATCHMAKING tABLE

DROP TABLE IF EXISTS matchmaking_company CASCADE;

CREATE TABLE matchmaking_company (
  company_id SERIAL PRIMARY KEY,
  company_name varchar(64) NOT NULL
);

COMMIT;

--USER MATCHMAKING TABLE
DROP TABLE IF EXISTS user_matchmaking CASCADE;

CREATE TABLE user_matchmaking (
  id SERIAL PRIMARY KEY,
  user_id int,
  company_id int
);

COMMIT;

ALTER TABLE user_matchmaking 
ADD FOREIGN KEY (user_id) REFERENCES app_user (id),
ADD FOREIGN KEY (company_id) REFERENCES matchmaking_company (company_id);

COMMIT;

--COMPANY ROLE TABLE
DROP TABLE IF EXISTS company_role CASCADE;

CREATE TABLE company_role (
  role_id SERIAL PRIMARY KEY,
  company_id int NOT NULL,
  job_title varchar(255) NOT NULL
);

COMMIT;

ALTER TABLE company_role 
ADD FOREIGN KEY (company_id) REFERENCES matchmaking_company (company_id);

COMMIT;

--COMPANY REPRESENTATIVE TABLE
DROP TABLE IF EXISTS company_rep CASCADE;

CREATE TABLE company_rep (
  rep_id SERIAL PRIMARY KEY,
  company_id INT NOT NULL,
  representative_name varchar(64) NOT NULL,
  representative_email varchar(128)
);

COMMIT;
ALTER TABLE company_rep 
ADD FOREIGN KEY (company_id) REFERENCES matchmaking_company (company_id);

COMMIT;

--QUOTES TABLE

DROP TABLE IF EXISTS quotes CASCADE;

CREATE TABLE quotes (
  id SERIAL PRIMARY KEY,
  category varchar(64),
  quotes varchar(255) NOT NULL,
  quote_source varchar(128)
);

COMMIT;

INSERT INTO matchmaking_company 
(company_name) VALUES 
('CGI'),
('PNC Infrastructure'),
('PNC DevOps'),
('AEO'),
('BNY Mellon'),
('Plus Consulting'),
('Highmark'),
('Open Arc'),
('FedEx'),
('Giant Eagle'),
('Dick''s Sporting Goods');

COMMIT;


INSERT INTO company_role (company_id, job_title) VALUES
(1, 'Developer'),
(1, 'Business Data analyst'),
(2, 'LOB Risk Lead'),
(3, 'DevOps'),
(4, 'Quality Engineer'),
(4, 'Developer'),
(5, 'Lead Developer'),
(6, 'Salseforce Consultant'),
(6, 'Salesforce Developer'),
(7, 'Developer'),
(7, 'Business System Analyst'),
(7, 'Associate Project Manager'),
(8, 'Developer'),
(9, 'Software Developer I'),
(10, 'Developer'),
(11, 'Developer'),
(11, 'Business Data Analyst');

COMMIT;

INSERT INTO company_rep (company_id, representative_name, representative_email) VALUES
(1, 'Aaron Brown', 'a.brown@cgi.info'),
(2, 'Maria Wells', 'mariawells@pnc.org'),
(2, 'Chris Woods', 'chriswoods@pnc.org'),
(3, 'Jason Jones', 'jasonjones@pnc.org'),
(3, 'Sarah Wills', 'sarahwills@pnc.org'),
(4, 'John Williams', 'j.williams@aeo.gov'),
(4, 'Kelley White', 'k.white@aeo.gov'),
(5, 'Mike Rice', 'm.rice@bny.com'),
(5, 'Anita Bush', 'a.bush@bny.com'),
(6, 'Bill McLeash', 'b.mcleash@plusconsulting.org'),
(6, 'Tina Wilson', 't.wilson@plusconsulting.org'),
(7, 'Jim Gates', 'james.gates@highmark.info'),
(8, 'Amber McDonald', 'a.mcdonald@openarc.com'),
(8, 'Linda Paterson', 'l.paterson@openarc.com'),
(9, 'Michael Lisbon', 'michaellisbon@fedex.net'),
(9, 'George Sharma', 'georgesharma@fedex.net'),
(10, 'Matt Woodsworth', 'mathew.woodsworth@ge.com'),
(10, 'Marie Baker', 'marie.baker@ge.com'),
(11, 'Craig Black', 'craigblack@dicks.com'),
(11, 'Kathelene woods', 'kathelenewoods@dicks.com');

COMMIT;

INSERT INTO event (event_name, description, start_date, end_date, mandatory) VALUES
('Strength Finder Activity', 'You will take part in an activity that would help you to find your strength and compare your findings with your friends and instructors.', '2018-09-18 13:00:00-00', '2018-09-18 14:00:00-00',TRUE),
('Pathway Program Overview presentation', 'Pathway Program Director presents your Pathway Program and reviews the events schedule.', '2018-09-19 13:00:00-00', '2018-09-19 14:00:00-00', TRUE),
('Student Headshots', 'Time to take pictures for your Linkedin photo. Remember to wear professional clothing. These photos will be taken outside.', '2018-09-20 12:00:00-00', '2018-09-20 13:00:00-00', TRUE),
('Crafting Your narrative: Elevator Pitch', 'Crafting your narrative for the job search, -Practicing your elevator pitch, -How to talk about your background and experiences in a way that makes sense, especially those with no programming experience.', '2019-07-24 13:00:00-00', '2019-07-24 14:00:00-00', TRUE),
('Elevator Pitch Workshop', 'Practice saying your pitch in front of your Pathway Director and Campus Director in the elevate space.', '2019-09-26 13:00:00-00', '2019-09-26 14:00:00-00', FALSE)
;

COMMIT;

INSERT INTO quotes (category, quotes, quote_source) VALUES
('Motivational', 'Good, better, the best. Never let it rest. Until your good is better and your better is the best.', '-St. Jerome'),
('Happiness', 'Don''t cry because it''s over, smile because it happened.', '-Dr. Seuss'),
('Sad', 'The word "HAPPY" would lose its meaning if it were not balanced by sadness.', '-Carl Jung'),
('Motivational', 'All our dreams can come true, if we have the courage to pursue them.', '-Walt Disney'),
('Sad', 'Never make decision when you are upset, sad, jealous or in love.', '-Mario Teguh'),
('Happiness', 'Happiness is when what you think, what you say and what you do are in harmony.', '-Mahatma Gahdhi');

COMMIT;

INSERT INTO app_user (first_name, last_name, user_name, password, role, salt, reset_key, phone, email) VALUES
('George', 'Wright','studentuser', 'qNWYxrfRCx98/Hq3n2utwA==', 'student', 'mvl0cE54QC4hWWCU8tdg4TwpDW72FOMlxhTGqEZXBOsiG9AqJM9JnT/u7DPsFebtaNPlTKEmMLn+fZMxmsUHeFflkNGOI3IXwcoWP4Qo2swjgOo3Qci6Z7LGH7AutCPUuL3QDPWjySzF18bjh/pGRt84R476TMKW0MP1EFotpfQ=', 'E54QC4hWWCU8tdg4TwpDW72FOMl', '412-680-2222', 'george.wright@gmail.com'),
('Anna', 'Thompson', 'guestuser', 'rIfb6p5LFSBm1Y5rprvC3A==', 'guest', 'BUTfPxj1gDXI0MB8z3rcvaTmIyHgDZKCPzvr0MZpreCNM3x/couF0B07vkPz9ddFc4ZLYMlrjz5W1z22WLSNCPRop82i70LWsG/FuhTGAyXguTzCgNbZXoGmSixpSHy0omLxd9S5f36XMOMSXire9al6nYv15STmRGfx0inXiBg=', '5f36XMOMSXire9al6nYv15STmRGfx0inX', '412-680-1234', 'anna.thompson18@hotmail.com'),
('Margrett', 'McKelley','adminuser', 'pje6L9+6il/K2K5QuMYiaw==', 'admin', '11k1McHagjPPhB9PMf/92UeUUH37Ooq/a1wInKeo/AQhuRR20FKSPoifQHI1Vk78JqxOG/8ocFZiUWXcLN4zsduPvFodp51mnSM+uYwxBvOjUwWM9v1/EZni+HSrUqv5X3S3oMyBaGuMWG3cOEmOLuZj1nQBYLshO72Efm4by30=', 'S3oMyBaGuMWG3cOEmOLuZj1nQBYLsh', '412-680-1222', 'm.mckelley2000@ymail.com');

COMMIT;

