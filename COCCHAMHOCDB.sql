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
        FOREIGN KEY REFERENCES dbo.Courses (CourseID) ON DELETE CASCADE,
    ChapterName NVARCHAR(69) NOT NULL,
);
GO
CREATE TABLE Lessons
(
    LessonNumber INT NOT NULL,
    ChapterID INT
        FOREIGN KEY REFERENCES dbo.Chapters (ChapterID) ON DELETE CASCADE,
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
        FOREIGN KEY REFERENCES dbo.Courses (CourseID) ON DELETE CASCADE,
    Duration TIME NOT NULL,
);
GO
CREATE TABLE Questions
(
    QuestionID INT IDENTITY(1, 1) PRIMARY KEY,
    QuestionDetail NVARCHAR(420) NOT NULL,
    ExamID INT
        FOREIGN KEY REFERENCES dbo.Exams (ExamID) ON DELETE CASCADE
);
GO
CREATE TABLE Choices
(
    ChoiceID INT IDENTITY(1, 1) PRIMARY KEY,
    QuestionID INT
        FOREIGN KEY REFERENCES dbo.Questions (QuestionID) ON DELETE CASCADE,
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
        FOREIGN KEY REFERENCES dbo.[Users] (UserID) ON DELETE CASCADE,
    ExamID INT
        FOREIGN KEY REFERENCES dbo.Exams (ExamID) ON DELETE CASCADE,
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
        FOREIGN KEY REFERENCES dbo.ExamPapers (PaperID) ON DELETE CASCADE,
    ChoiceID INT
		FOREIGN KEY REFERENCES dbo.Choices(ChoiceID) ON DELETE CASCADE,
    PRIMARY KEY (
                    AnswerID,
                    ChoiceID
                )
);
GO
CREATE TABLE Certificates
(
    UserID INT
        FOREIGN KEY REFERENCES dbo.Users (UserID) ON DELETE CASCADE,
    CourseID INT
        FOREIGN KEY REFERENCES dbo.Courses (CourseID) ON DELETE CASCADE,
    IssueDate DATE NOT NULL,
);
CREATE TABLE Ratings
(
    UserID INT
        FOREIGN KEY REFERENCES dbo.Users (UserID) ON DELETE CASCADE,
    CourseID INT
        FOREIGN KEY REFERENCES dbo.Courses (CourseID) ON DELETE CASCADE,
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


Insert into Categories(CategoryDescription)
VALUES('Programing'),('Design'),('Marketing'),('Photography & Video'),('Teaching&Academi')


Insert into Levels(LevelDescription)
VALUES('Beginner'),('Intermediate'),('Advanced')

INSERT INTO Courses (CourseBannerImage, Title, CourseDescription, PublishDate, Lecturer, DurationInSeconds, LevelID, CategoryID)
VALUES 
('https://example.com/image1.jpg', N'Giới thiệu về Python', N'Khóa học này giới thiệu cách sử dụng ngôn ngữ lập trình Python.', '2023-01-01', N'Nguyễn Văn A', 18000, 1, 1),
('https://example.com/image2.jpg', N'Trí tuệ nhân tạo cho người mới bắt đầu', N'Khóa học cung cấp kiến thức cơ bản về trí tuệ nhân tạo.', '2023-02-01', N'Trần Thị B', 25000, 1, 1),
('https://example.com/image3.jpg', N'Phát triển ứng dụng di động với React Native', N'Học cách xây dựng ứng dụng di động đa nền tảng với React Native.', '2023-03-01', N'Lê Văn C', 30000, 2, 1),
('https://example.com/image4.jpg', N'Giới thiệu về Blockchain', N'Tìm hiểu về công nghệ Blockchain và các ứng dụng của nó.', '2023-04-01', N'Phạm Thị D', 20000, 1, 1),
('https://example.com/image5.jpg', N'Lập trình Web với JavaScript, HTML và CSS', N'Khóa học này giúp bạn nắm vững kiến thức cơ bản về lập trình web.', '2023-05-01', N'Đặng Văn E', 27000, 1, 1),
('https://example.com/image6.jpg', N'Machine Learning cơ bản', N'Khóa học giúp bạn tìm hiểu về machine learning và các thuật toán cơ bản.', '2023-06-01', N'Huỳnh Văn F', 32000, 2, 1),
('https://example.com/image7.jpg', N'Phát triển ứng dụng Web với Django', N'Học cách xây dựng ứng dụng web mạnh mẽ với Django.', '2023-07-01', N'Ngô Thị G', 35000, 2, 1),
('https://example.com/image8.jpg', N'Thực hành DevOps', N'Khóa học giới thiệu về DevOps và các công cụ liên quan.', '2023-08-01', N'Trương Văn H', 28000, 3, 1),
('https://example.com/image9.jpg', N'Tối ưu hóa SQL', N'Tìm hiểu về cách tối ưu hóa truy vấn SQL và cải thiện hiệu suất.', '2023-09-01', N'Lý Thị I', 22000, 3, 1),
('https://example.com/image10.jpg', N'Lập trình game với Unity', N'Khóa học này giúp bạn nắm vững kiến thức cơ bản về lập trình game với Unity.', '2023-10-01', N'Phan Văn J', 40000, 1, 1),
('https://example.com/image11.jpg', N'UI/UX Design cơ bản', N'Khóa học giúp bạn tìm hiểu về thiết kế giao diện và trải nghiệm người dùng.', '2023-11-01', N'Mai Văn K', 24000, 1, 2),
('https://example.com/image12.jpg', N'Thiết kế đồ họa với Adobe Photoshop', N'Học cách sử dụng Adobe Photoshop để tạo ra các sản phẩm đồ họa chuyên nghiệp.', '2023-12-01', N'Bùi Thị L', 30000, 1, 2),
('https://example.com/image13.jpg', N'Thiết kế sản phẩm với Sketch', N'Khóa học giới thiệu về Sketch và cách sử dụng công cụ này để thiết kế sản phẩm.', '2024-01-01', N'Vũ Văn M', 27000, 2, 2),
('https://example.com/image14.jpg', N'Thiết kế Web với Figma', N'Tìm hiểu về cách thiết kế giao diện web chuyên nghiệp với Figma.', '2024-02-01', N'Phạm Thị N', 26000, 2, 2),
('https://example.com/image15.jpg', N'Thiết kế đồ họa với Adobe Illustrator', N'Khóa học này giúp bạn nắm vững kiến thức cơ bản về thiết kế đồ họa với Adobe Illustrator.', '2024-03-01', N'Đặng Văn O', 32000, 1, 2);
