CREATE TABLE users (
    userId VARCHAR(50) PRIMARY KEY,
    Password VARCHAR(100) NOT NULL,
    Nickname VARCHAR(50) NOT NULL UNIQUE,
    Review DECIMAL(3,1) DEFAULT 3.0,
    Provider VARCHAR(20) DEFAULT 'DEFAULT',
    Intro LONGTEXT NOT NULL,
    Profileimg VARCHAR(100) NOT NULL,
    Birthday DATE DEFAULT (CURRENT_DATE),
    Gender VARCHAR(20) DEFAULT 'MALE',
    Type VARCHAR(50) DEFAULT 'MEMBER',
    Address VARCHAR(255) NOT NULL,
    AddressDetail VARCHAR(255) NOT NULL,
    Interests VARCHAR(255) NOT NULL
);

CREATE TABLE user_review (
    Reviews_id INT AUTO_INCREMENT PRIMARY KEY,
    Reviewer VARCHAR(50) NOT NULL,
    Reviewee VARCHAR(50) NOT NULL,
    Score DECIMAL(3,1) NOT NULL,

    CONSTRAINT fk_reviewer FOREIGN KEY (Reviewer) REFERENCES users(Nickname)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_reviewee FOREIGN KEY (Reviewee) REFERENCES users(Nickname)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE category (
    Category_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    Category_name VARCHAR(50) NOT NULL
);

CREATE TABLE category_detail (
    CategoryDetail_id VARCHAR(50) PRIMARY KEY,
    Category_id INTEGER NOT NULL,
    Category_detail_name VARCHAR(20),

    CONSTRAINT fk_category FOREIGN KEY (Category_id)
        REFERENCES category(Category_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE moim (
    Moim_id VARCHAR(50) PRIMARY KEY,
    Title VARCHAR(30) NOT NULL,
    Is_online BOOLEAN DEFAULT FALSE,
    Max_people INT,
    Expiration_Date DATE,
    Even_Date DATE,
    Location VARCHAR(25),
    Represent_img VARCHAR(255),
    Organizer VARCHAR(50) NOT NULL,
    Description VARCHAR(100) NOT NULL,
    Tag JSON,
    CateGoryDetail_id VARCHAR(50) NOT NULL,

    CONSTRAINT fk_CateGoryDetail_id FOREIGN KEY (CateGoryDetail_id)
            REFERENCES category_detail(CategoryDetail_id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT fk_CateGoryDetail_users FOREIGN KEY (Organizer)
                REFERENCES users(Nickname)
                ON DELETE CASCADE
                ON UPDATE CASCADE
);

CREATE TABLE moim_detail (
    Moim_id VARCHAR(50) PRIMARY KEY,
    Content LONGTEXT NOT NULL,
    Min_people INT,
    Pay DECIMAL(15, 2),
    Approval BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_moim_detail_moim FOREIGN KEY (Moim_id)
        REFERENCES moim(Moim_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE moim_review (
    Moim_id VARCHAR(50) NOT NULL,
    Reviewer_nickname VARCHAR(50) NOT NULL,
    Score DECIMAL(3,1) NOT NULL,
    Comment TEXT,

    PRIMARY KEY (Moim_id, Reviewer_nickname),
    FOREIGN KEY (Moim_id) REFERENCES moim(Moim_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (Reviewer_nickname) REFERENCES users(Nickname) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE moim_approval (
  Approval_id INT AUTO_INCREMENT PRIMARY KEY,
  Moim_id VARCHAR(50) NOT NULL,
  userId VARCHAR(50) NOT NULL,
  Status VARCHAR(50) DEFAULT 'Pended',
  Requested_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  Approval_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT fk_moim_approval_users FOREIGN KEY (userId) REFERENCES users(userId)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
  CONSTRAINT fk_moim_approval_moim FOREIGN KEY (Moim_id) REFERENCES moim(Moim_id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
);

CREATE TABLE moim_favorite (
    FavoriteId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Moim_id VARCHAR(50) NOT NULL,
    userId VARCHAR(50) NOT NULL,

    CONSTRAINT fk_dibs_moim_moim FOREIGN KEY (Moim_id)
        REFERENCES moim(Moim_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_dibs_moim_user FOREIGN KEY (userId)
        REFERENCES users(userId)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE consult_category (
  Consult_categoryId INTEGER AUTO_INCREMENT PRIMARY KEY,
  ConsultType VARCHAR(20) NOT NULL
);

CREATE TABLE moim_consult (
    Moims_consultId VARCHAR(50) PRIMARY KEY,
    Nickname VARCHAR(50) NOT NULL,
    Title VARCHAR(50) NOT NULL,
    Consult_categoryId INT NOT NULL,
    Consult_Comment LONGTEXT NOT NULL,
    Create_Date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 작성일, 자동 입력

    CONSTRAINT fk_moim_consult_users FOREIGN KEY (Nickname) REFERENCES users(Nickname)
          ON DELETE CASCADE
          ON UPDATE CASCADE,
    CONSTRAINT fk_moim_consult_consult_category FOREIGN KEY (Consult_categoryId) REFERENCES consult_category(Consult_categoryId)
              ON DELETE CASCADE
              ON UPDATE CASCADE
);

CREATE TABLE moim_consult_comment (
    Moims_ConsultCommentId VARCHAR(50) PRIMARY KEY,
    Moims_consultId VARCHAR(50),
    Nickname VARCHAR(50) NOT NULL,
    Password VARCHAR(50) NOT NULL,
    Comments VARCHAR(100) NOT NULL,
    Profileimg VARCHAR(100) NOT NULL,
    Update_Date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 작성일, 자동 입력
    CONSTRAINT fk_moim_consult_comment_consult FOREIGN KEY (Moims_consultId)
             REFERENCES moim_consult(Moims_consultId)
             ON DELETE CASCADE
             ON UPDATE CASCADE,
    CONSTRAINT fk_moim_consult_comment_user FOREIGN KEY (Nickname) REFERENCES users(Nickname)
             ON DELETE CASCADE
             ON UPDATE CASCADE
);