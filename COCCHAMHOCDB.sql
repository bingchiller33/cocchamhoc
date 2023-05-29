USE master;

IF EXISTS
(
    SELECT name
    FROM master.dbo.sysdatabases
    WHERE name = N'COC_CHAM_HOC'
)
BEGIN
    ALTER DATABASE [COC_CHAM_HOC] SET OFFLINE WITH ROLLBACK IMMEDIATE;
    ALTER DATABASE [COC_CHAM_HOC] SET ONLINE;
    DROP DATABASE [COC_CHAM_HOC];
END;
GO

CREATE DATABASE COC_CHAM_HOC;
GO

USE [COC_CHAM_HOC];
GO
CREATE TABLE Categories
(
    CategoryID INT IDENTITY(1, 1) PRIMARY KEY,
    [CategoryDescription] NVARCHAR(69) NOT NULL,
);
GO
CREATE TABLE Levels
(
    LevelID INT IDENTITY(1, 1) PRIMARY KEY,
    [LevelDescription] NVARCHAR(69) NOT NULL,
);
GO
CREATE TABLE Courses
(
    CourseID INT IDENTITY(1, 1) PRIMARY KEY,
    [CourseBannerImage] VARCHAR(420),
    [Title] NVARCHAR(69) NOT NULL,
    [CourseDescription] TEXT NOT NULL,
    PublishDate DATE DEFAULT NULL,
    Lecturer NVARCHAR(69) NOT NULL,
    DurationInSeconds INT NOT NULL,
    LevelID INT
        FOREIGN KEY REFERENCES dbo.Levels (LevelID),
    CategoryID INT
        FOREIGN KEY REFERENCES dbo.Categories (CategoryID),
);
GO
CREATE TABLE Chapters
(
	ChapterID INT IDENTITY(1,1) PRIMARY KEY,
    ChapterNumber INT UNIQUE NOT NULL,
    CourseID INT
        FOREIGN KEY REFERENCES dbo.Courses (CourseID),
    ChapterName NVARCHAR(69) NOT NULL,
);
GO
CREATE TABLE Lessons
(
    LessonNumber INT NOT NULL,
    ChapterID INT
        FOREIGN KEY REFERENCES dbo.Chapters (ChapterID),
    LessonName NVARCHAR(69) NOT NULL,
    LessonVideo VARCHAR(512) NOT NULL,
    LessonDescription TEXT,
    PRIMARY KEY (
                    LessonNumber,
                    ChapterID
                )
);
GO
CREATE TABLE Exams
(
    ExamID INT IDENTITY(1, 1) PRIMARY KEY,
	ExamName NVARCHAR(420),
    CourseID INT
        FOREIGN KEY REFERENCES dbo.Courses (CourseID),
    Duration TIME NOT NULL,
);
GO
CREATE TABLE Questions
(
    QuestionID INT IDENTITY(1, 1) PRIMARY KEY,
    QuestionDetail NVARCHAR(420) NOT NULL,
    ExamID INT
        FOREIGN KEY REFERENCES dbo.Exams (ExamID)
);
GO
CREATE TABLE Choices
(
    ChoiceID INT IDENTITY(1, 1) PRIMARY KEY,
    QuestionID INT
        FOREIGN KEY REFERENCES dbo.Questions (QuestionID),
    IsTrueAnswer BIT
        DEFAULT 0,
);
GO
CREATE TABLE [Users]
(
    UserID INT IDENTITY(1, 1) PRIMARY KEY,
    UserName NVARCHAR(420) NOT NULL,
    Email VARCHAR(420) NOT NULL,
    Password VARCHAR(69) NOT NULL,
    IsAdmin BIT NOT NULL,
    DOB DATE,
    Gender BIT,
    PhoneNumber VARCHAR(69),
);
GO
CREATE TABLE ExamPapers
(
    PaperID INT IDENTITY(1, 1) PRIMARY KEY,
    UserID INT
        FOREIGN KEY REFERENCES dbo.[Users] (UserID),
    ExamID INT
        FOREIGN KEY REFERENCES dbo.Exams (ExamID),
    TimeStart DATETIME
        DEFAULT GETDATE(),
    TimeEnd DATETIME,
    Score INT
);
GO
CREATE TABLE UserAnswers
(
    AnswerID INT IDENTITY(1, 1),
    PaperID INT
        FOREIGN KEY REFERENCES dbo.ExamPapers (PaperID),
    ChoiceID INT
		FOREIGN KEY REFERENCES dbo.Choices(ChoiceID),
    PRIMARY KEY (
                    AnswerID,
                    ChoiceID
                )
);
GO
CREATE TABLE Certificates
(
    UserID INT
        FOREIGN KEY REFERENCES dbo.Users (UserID),
    CourseID INT
        FOREIGN KEY REFERENCES dbo.Courses (CourseID),
    IssueDate DATE NOT NULL,
);
CREATE TABLE Ratings
(
    UserID INT
        FOREIGN KEY REFERENCES dbo.Users (UserID),
    CourseID INT
        FOREIGN KEY REFERENCES dbo.Courses (CourseID),
    Rating INT CHECK (Rating >= 1
                      AND Rating <= 5
                     ),
    RateTime DATETIME
        DEFAULT GETDATE(),
    Review TEXT,
    PRIMARY KEY (
                    UserID,
                    CourseID
                )
);
SELECT * FROM dbo.Courses
