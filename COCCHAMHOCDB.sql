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
    [CourseDescription] NTEXT NOT NULL,
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
    ChapterNumber INT NOT NULL,
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
    LessonDescription NTEXT,
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
    Role INT CHECK(Role IN (1, 2, 3)) NOT NULL,    /*1: User      2: Lecturer      3: Admin*/
    DOB DATE,
    Gender BIT,
    PhoneNumber VARCHAR(69),
);
CREATE TABLE [UsersEnroll]
(
    UserId INT FOREIGN KEY REFERENCES dbo.Users(UserID),
    CourseID INT FOREIGN KEY REFERENCES dbo.Courses(CourseID),
    Status VARCHAR(100)  CHECK(Status IN('Learning', 'Complete')) DEFAULT 'Learning',
    PRIMARY KEY (UserId, CourseID)
)
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
    Review NTEXT,
    PRIMARY KEY (
                    UserID,
                    CourseID
                )
);
GO
SET IDENTITY_INSERT [dbo].[Categories] ON 

INSERT [dbo].[Categories] ([CategoryID], [CategoryDescription]) VALUES (1, N'Programing')
INSERT [dbo].[Categories] ([CategoryID], [CategoryDescription]) VALUES (2, N'Design')
INSERT [dbo].[Categories] ([CategoryID], [CategoryDescription]) VALUES (3, N'Marketing')
INSERT [dbo].[Categories] ([CategoryID], [CategoryDescription]) VALUES (4, N'Photography & Video')
INSERT [dbo].[Categories] ([CategoryID], [CategoryDescription]) VALUES (5, N'Teaching&Academi')
SET IDENTITY_INSERT [dbo].[Categories] OFF
GO
SET IDENTITY_INSERT [dbo].[Levels] ON 

INSERT [dbo].[Levels] ([LevelID], [LevelDescription]) VALUES (1, N'Beginner')
INSERT [dbo].[Levels] ([LevelID], [LevelDescription]) VALUES (2, N'Intermediate')
INSERT [dbo].[Levels] ([LevelID], [LevelDescription]) VALUES (3, N'Advanced')
SET IDENTITY_INSERT [dbo].[Levels] OFF
GO
SET IDENTITY_INSERT [dbo].[Courses] ON 

INSERT [dbo].[Courses] ([CourseID], [CourseBannerImage], [Title], [CourseDescription], [PublishDate], [Lecturer], [DurationInSeconds], [LevelID], [CategoryID]) VALUES (16, N'https://www.classcentral.com/report/wp-content/uploads/2022/05/Java-BCG-Banner.png', N'Learn to Program in Java', N'Ready to start your programming journey? Being a software engineer is much more than simply writing code--it requires a strong conceptual understanding of computer science. In this course, which was developed through a combination of academic and industry perspectives, learn not only how to code in Java but also how to break down problems and implement their solutions using some of the most fundamental computer science tools.

Get plenty of hands-on Java coding experience with methods, logic, loops, variables, parameters, returns, and recursion. And write your code using industry-standard tools and practices to help you build strong habits as you grow your development skill set.

Whether you are preparing for advanced university computer science courses, an entry-level software engineering position, or the Advanced Placement Computer Science A exam, get the tools you need to succeed in this practical, self-paced Java course.', CAST(N'2023-06-16' AS Date), N'Admin', 3600, 1, 1)
INSERT [dbo].[Courses] ([CourseID], [CourseBannerImage], [Title], [CourseDescription], [PublishDate], [Lecturer], [DurationInSeconds], [LevelID], [CategoryID]) VALUES (21, N'https://www.freecodecamp.org/news/content/images/size/w2000/2022/02/Banner-10.png', N'Giới thiệu về Python', N'Khóa học này giới thiệu cách sử dụng ngôn ngữ lập trình Python.', CAST(N'2023-01-01' AS Date), N'Nguyễn Văn A', 18000, 1, 1)
INSERT [dbo].[Courses] ([CourseID], [CourseBannerImage], [Title], [CourseDescription], [PublishDate], [Lecturer], [DurationInSeconds], [LevelID], [CategoryID]) VALUES (22, N'https://gisgeography.com/wp-content/uploads/2022/01/Artificial-Intelligence-Courses-Feature.png', N'Trí tuệ nhân tạo cho người mới bắt đầu', N'Khóa học cung cấp kiến thức cơ bản về trí tuệ nhân tạo.', CAST(N'2023-02-01' AS Date), N'Trần Thị B', 25000, 1, 1)
INSERT [dbo].[Courses] ([CourseID], [CourseBannerImage], [Title], [CourseDescription], [PublishDate], [Lecturer], [DurationInSeconds], [LevelID], [CategoryID]) VALUES (23, N'https://www.classcentral.com/report/wp-content/uploads/2022/10/React-JS-BCG-Banner.png', N'Phát triển ứng dụng di động với React Native', N'Học cách xây dựng ứng dụng di động đa nền tảng với React Native.', CAST(N'2023-03-01' AS Date), N'Lê Văn C', 30000, 2, 1)
INSERT [dbo].[Courses] ([CourseID], [CourseBannerImage], [Title], [CourseDescription], [PublishDate], [Lecturer], [DurationInSeconds], [LevelID], [CategoryID]) VALUES (24, N'https://leverageedu.com/blog/wp-content/uploads/2021/08/Best-Blockchain-Courses-800x500.png', N'Giới thiệu về Blockchain', N'Tìm hiểu về công nghệ Blockchain và các ứng dụng của nó.', CAST(N'2023-04-01' AS Date), N'Phạm Thị D', 20000, 1, 1)
INSERT [dbo].[Courses] ([CourseID], [CourseBannerImage], [Title], [CourseDescription], [PublishDate], [Lecturer], [DurationInSeconds], [LevelID], [CategoryID]) VALUES (25, N'https://ccweb.imgix.net/https%3A%2F%2Fwww.classcentral.com%2Freport%2Fwp-content%2Fuploads%2F2017%2F10%2Fjs-and-frameworks-banner.png?auto=format&h=300&ixlib=php-4.1.0&s=4486df73054faf8345d0b8f69a791187', N'Lập trình Web với JavaScript, HTML và CSS', N'Khóa học này giúp bạn nắm vững kiến thức cơ bản về lập trình web.', CAST(N'2023-05-01' AS Date), N'Đặng Văn E', 27000, 1, 1)
INSERT [dbo].[Courses] ([CourseID], [CourseBannerImage], [Title], [CourseDescription], [PublishDate], [Lecturer], [DurationInSeconds], [LevelID], [CategoryID]) VALUES (26, N'https://www.freecodecamp.org/news/content/images/2022/01/machine-learning-banner-2.png', N'Machine Learning cơ bản', N'Khóa học giúp bạn tìm hiểu về machine learning và các thuật toán cơ bản.', CAST(N'2023-06-01' AS Date), N'Huỳnh Văn F', 32000, 2, 1)
INSERT [dbo].[Courses] ([CourseID], [CourseBannerImage], [Title], [CourseDescription], [PublishDate], [Lecturer], [DurationInSeconds], [LevelID], [CategoryID]) VALUES (27, N'https://i.ytimg.com/vi/2yXfUPwlZTw/maxresdefault.jpg', N'Phát triển ứng dụng Web với Django', N'Học cách xây dựng ứng dụng web mạnh mẽ với Django.', CAST(N'2023-07-01' AS Date), N'Ngô Thị G', 35000, 2, 1)
INSERT [dbo].[Courses] ([CourseID], [CourseBannerImage], [Title], [CourseDescription], [PublishDate], [Lecturer], [DurationInSeconds], [LevelID], [CategoryID]) VALUES (29, N'https://www.classcentral.com/report/wp-content/uploads/2022/06/SQL-BCG-Banner-2.png', N'Tối ưu hóa SQL', N'Tìm hiểu về cách tối ưu hóa truy vấn SQL và cải thiện hiệu suất.', CAST(N'2023-09-01' AS Date), N'Lý Thị I', 22000, 3, 1)
INSERT [dbo].[Courses] ([CourseID], [CourseBannerImage], [Title], [CourseDescription], [PublishDate], [Lecturer], [DurationInSeconds], [LevelID], [CategoryID]) VALUES (30, N'https://img-c.udemycdn.com/course/750x422/1210008_6859.jpg', N'Lập trình game với Unity', N'Khóa học này giúp bạn nắm vững kiến thức cơ bản về lập trình game với Unity.', CAST(N'2023-10-01' AS Date), N'Phan Văn J', 40000, 1, 1)
INSERT [dbo].[Courses] ([CourseID], [CourseBannerImage], [Title], [CourseDescription], [PublishDate], [Lecturer], [DurationInSeconds], [LevelID], [CategoryID]) VALUES (31, N'https://images.careerbuilder.vn/content/images/ui-ux-careerbuilder.jpg', N'UI/UX Design cơ bản', N'Khóa học giúp bạn tìm hiểu về thiết kế giao diện và trải nghiệm người dùng.', CAST(N'2023-11-01' AS Date), N'Mai Văn K', 24000, 1, 2)
INSERT [dbo].[Courses] ([CourseID], [CourseBannerImage], [Title], [CourseDescription], [PublishDate], [Lecturer], [DurationInSeconds], [LevelID], [CategoryID]) VALUES (32, N'https://www.classcentral.com/report/wp-content/uploads/2022/05/Adobe-Photoshop-BCG-Banner-1.png', N'Thiết kế đồ họa với Adobe Photoshop', N'Học cách sử dụng Adobe Photoshop để tạo ra các sản phẩm đồ họa chuyên nghiệp.', CAST(N'2023-12-01' AS Date), N'Bùi Thị L', 30000, 1, 2)
INSERT [dbo].[Courses] ([CourseID], [CourseBannerImage], [Title], [CourseDescription], [PublishDate], [Lecturer], [DurationInSeconds], [LevelID], [CategoryID]) VALUES (33, N'https://www.classcentral.com/report/wp-content/uploads/2022/05/Sketch-Banner.png', N'Thiết kế sản phẩm với Sketch', N'Khóa học giới thiệu về Sketch và cách sử dụng công cụ này để thiết kế sản phẩm.', CAST(N'2024-01-01' AS Date), N'Vũ Văn M', 27000, 2, 2)
INSERT [dbo].[Courses] ([CourseID], [CourseBannerImage], [Title], [CourseDescription], [PublishDate], [Lecturer], [DurationInSeconds], [LevelID], [CategoryID]) VALUES (34, N'https://www.classcentral.com/report/wp-content/uploads/2022/05/Figma-Banner-2.png', N'Thiết kế Web với Figma', N'Tìm hiểu về cách thiết kế giao diện web chuyên nghiệp với Figma.', CAST(N'2024-02-01' AS Date), N'Phạm Thị N', 26000, 2, 2)
INSERT [dbo].[Courses] ([CourseID], [CourseBannerImage], [Title], [CourseDescription], [PublishDate], [Lecturer], [DurationInSeconds], [LevelID], [CategoryID]) VALUES (35, N'https://www.classcentral.com/report/wp-content/uploads/2022/06/Adobe-Illustrator-BCG-Banner.png', N'Thiết kế đồ họa với Adobe Illustrator', N'Khóa học này giúp bạn nắm vững kiến thức cơ bản về thiết kế đồ họa với Adobe Illustrator.', CAST(N'2024-03-01' AS Date), N'Đặng Văn O', 32000, 1, 2)
SET IDENTITY_INSERT [dbo].[Courses] OFF
GO
SET IDENTITY_INSERT [dbo].[Chapters] ON 

INSERT [dbo].[Chapters] ([ChapterID], [ChapterNumber], [CourseID], [ChapterName]) VALUES (4, 1, 16, N'Module 1 - Java Basics')
INSERT [dbo].[Chapters] ([ChapterID], [ChapterNumber], [CourseID], [ChapterName]) VALUES (5, 2, 16, N'Module 2 - Control Structures')
INSERT [dbo].[Chapters] ([ChapterID], [ChapterNumber], [CourseID], [ChapterName]) VALUES (6, 3, 16, N'Module 3 - Data Flow')
SET IDENTITY_INSERT [dbo].[Chapters] OFF
GO
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([UserID], [UserName], [Email], [Password], [Role], [DOB], [Gender], [PhoneNumber]) VALUES (1, N'devadmin', N'admin@gmail.com', N'6a5aeb1ea832832a9969a562357994ba', 3, NULL, NULL, NULL)
INSERT [dbo].[Users] ([UserID], [UserName], [Email], [Password], [Role], [DOB], [Gender], [PhoneNumber]) VALUES (2, N'devdesigner', N'designer@gmail.com', N'6a5aeb1ea832832a9969a562357994ba', 2, NULL, NULL, NULL)
INSERT [dbo].[Users] ([UserID], [UserName], [Email], [Password], [Role], [DOB], [Gender], [PhoneNumber]) VALUES (3, N'devuser', N'user@gmail.com', N'6a5aeb1ea832832a9969a562357994ba', 1, NULL, NULL, NULL)
INSERT [dbo].[Users] ([UserID], [UserName], [Email], [Password], [Role], [DOB], [Gender], [PhoneNumber]) VALUES (4, N'Phuockingboy', N'Phuockingboy@gmail.com', N'6a5aeb1ea832832a9969a562357994ba', 3, NULL, 0, NULL)
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (1, 4, N'Java 01. Why learn Java programming?', N'https://www.youtube.com/embed/xfOp0izFnu0', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (1, 5, N'Java 07. How to import data from key sales', N'https://www.youtube.com/embed/ymFKMQSeodQ', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (1, 6, N'Java 13. Conditional Operators in Java', N'https://www.youtube.com/embed/CQDWaJKynqs', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (2, 4, N'Java 02. Install JDK and Eclipse', N'https://www.youtube.com/embed/ayA1Lz2qEZo', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (2, 5, N'Java 08. Basic math operations in Java', N'https://www.youtube.com/embed/-F8_zsyfs2I', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (2, 6, N'Java 14. Conditional statement if…else in Java', N'https://www.youtube.com/embed/HUtHP5Iz0BQ', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (3, 4, N'Java 03. Structure of a Java class', N'https://www.youtube.com/embed/6Gbxt2Sox7k', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (3, 5, N'Java 09. Unary operators in Java programming', N'https://www.youtube.com/embed/AHF2sxWTGR4', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (3, 6, N'Java 15 . Solving quadratic equation ax2 bx c=0 in Java', N'https://www.youtube.com/embed/qUuUJXkeGgk', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (4, 4, N'Java 04. How to declare variables in Java', N'https://www.youtube.com/embed/zEbraQ5vIaU', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (4, 5, N'Java 10. How to assign data in Java', N'https://www.youtube.com/embed/77vlH6uD32E', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (4, 6, N'Java 16 . Switch…case statement in Java programming', N'https://www.youtube.com/embed/kRSD0jWQSGQ', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (5, 4, N'Java 05 . How to take notes in Java', N'https://www.youtube.com/embed/jgzgkUbK35M', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (5, 5, N'Java 11. Comparison and conditional operations in Java', N'https://www.youtube.com/embed/LuPiDFcHWoU', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (5, 6, N'Java 17.  Exercises to check the number of days of the month in Java', N'https://www.youtube.com/embed/XPO2QR4K5Y4', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (6, 4, N'Java 06. How to check and handle compilation errors', N'https://www.youtube.com/embed/2Zu17CS3288', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (6, 5, N'Java 12. Math class and math functions in Java', N'https://www.youtube.com/embed/7FoJA49E0zE', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (6, 6, N'Java 18.  How to use for loop in Java programming', N'https://www.youtube.com/embed/1NyJ0Nk5DAQ', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (7, 6, N'Java 19. Looping to print multiplication table in Java', N'https://www.youtube.com/embed/x0N_89T4dhk', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (8, 6, N'Java 20. How to use while loop in Java programming', N'https://www.youtube.com/embed/LRfESV4rmFE', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (9, 6, N'Java 21. Convert numbers from decimal to binary', N'https://www.youtube.com/embed/V71kcbhNfT8', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (10, 6, N'Java 22. Do while loop in Java iterator', N'https://www.youtube.com/embed/Y8IYMoq4LmE', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (11, 6, N'Java 23. How to use break, continue and return statements in Java', N'https://www.youtube.com/embed/-9TwINgJOUM', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (12, 6, N'Java 24 . How to catch exceptions with try catch in Java programming', N'https://www.youtube.com/embed/nQXE89sy8QQ', N'')
INSERT [dbo].[Lessons] ([LessonNumber], [ChapterID], [LessonName], [LessonVideo], [LessonDescription]) VALUES (13, 6, N'Java 25. Introduction to arrays in Java programming', N'https://www.youtube.com/embed/ph_RfyQP5cE', N'')
GO

-- Generate default accounts
-- Pass: 12345678
insert into Users(Username, Email, Password, Role)
values ('devadmin', 'admin@gmail.com', '6a5aeb1ea832832a9969a562357994ba', 3)

insert into Users(Username, Email, Password, Role)
values ('devdesigner', 'designer@gmail.com', '6a5aeb1ea832832a9969a562357994ba', 2)

insert into Users(Username, Email, Password, Role)
values ('devuser', 'user@gmail.com', '6a5aeb1ea832832a9969a562357994ba', 1)