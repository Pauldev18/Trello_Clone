-- Create the database
create database trello_clone;
use trello_clone;

-- Users table
create table users
(
    gmail varchar(255) primary key not null,
    user_name varchar(255) not null,
    pass_word varchar(255) not null,
    sdt varchar(30),
    dia_chi varchar(255),
    created_at datetime default current_timestamp
);

-- Projects table
create table projects
(
    project_id int auto_increment primary key,
    project_name varchar(255) not null,
    description varchar(255),
    start_date datetime,
    end_date datetime,
    created_at datetime default current_timestamp,
    updated_at datetime default current_timestamp on update current_timestamp,
    owner_id varchar(255) not null,
    foreign key(owner_id) references users(gmail) on delete cascade
);

-- UserProjects table (for many-to-many relationships between users and projects)
create table user_projects
(
    user_project_id int auto_increment primary key,
    user_id varchar(255) not null,
    project_id int not null,
    role varchar(255),
    foreign key(user_id) references users(gmail) on delete cascade,
    foreign key(project_id) references projects(project_id) on delete cascade
);

-- Boards table
create table boards
(
    board_id int auto_increment primary key,
    board_name varchar(255) not null,
    description varchar(255),
    created_at datetime default current_timestamp,
    project_id int not null,
    foreign key(project_id) references projects(project_id) on delete cascade
);

-- Tasks table
create table tasks
(
    task_id int auto_increment primary key,
    task_name varchar(255) not null,
    description varchar(255),
    due_date datetime,
    created_at datetime default current_timestamp,
    updated_at datetime default current_timestamp on update current_timestamp,
    status varchar(255) not null,
    user_id varchar(255),
    board_id int not null,
    foreign key(user_id) references users(gmail) on delete set null,
    foreign key(board_id) references boards(board_id)
);
