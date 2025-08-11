CREATE TABLE `users` (
    user_id VARCHAR(20) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(10) NOT NULL UNIQUE,
    review DECIMAL(3,1) DEFAULT 3.0,
    Intro LONGTEXT NULL
);

CREATE TABLE user_review (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    reviewer VARCHAR(10) NOT NULL,
    reviewee VARCHAR(10) NOT NULL,
    score DECIMAL(3,1) NOT NULL,

    CONSTRAINT fk_reviewer FOREIGN KEY (reviewer) REFERENCES users(nickname)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_reviewee FOREIGN KEY (reviewee) REFERENCES users(nickname)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE moim (
    moim_id VARCHAR(20) PRIMARY KEY,
    title VARCHAR(30) NOT NULL,
    is_online BOOLEAN DEFAULT FALSE,
    max_people INT,
    expiration_date DATE,
    even_date DATE,
    location VARCHAR(25),
    represent_img VARCHAR(255),
    category VARCHAR(40) NOT NULL,
    organizer VARCHAR(50) NOT NULL,
    description VARCHAR(50) NOT NULL,
    tag JSON,
    category_detail VARCHAR(40) NOT NULL,
    approval BOOLEAN DEFAULT FALSE
);

CREATE TABLE moim_approval (
  moim_id VARCHAR(20) PRIMARY KEY,
  join_member JSON,
  approval_members JSON,
  CONSTRAINT fk_moim_approval_moim FOREIGN KEY (moim_id) REFERENCES moim(moim_id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
);

CREATE TABLE moim_detail (
    moim_id VARCHAR(20) PRIMARY KEY,
    content LONGTEXT NOT NULL,
    min_people INT,
    pay DECIMAL(15, 2),
    member JSON,
    CONSTRAINT fk_moim_detail_moim FOREIGN KEY (moim_id)
        REFERENCES moim(moim_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE moim_review (
    moim_id VARCHAR(20) NOT NULL,
    reviewer_nickname VARCHAR(10) NOT NULL,
    score DECIMAL(3,1) NOT NULL,
    comment TEXT,

    PRIMARY KEY (moim_id, reviewer_nickname),
    FOREIGN KEY (moim_id) REFERENCES moim(moim_id) ON DELETE CASCADE,
    FOREIGN KEY (reviewer_nickname) REFERENCES users(nickname) ON DELETE CASCADE
);

CREATE TABLE moim_favorite (
    favoriteId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    moim_id VARCHAR(20) NOT NULL,
    user_id VARCHAR(20) NOT NULL,

    CONSTRAINT fk_dibs_moim_moim FOREIGN KEY (moim_id)
        REFERENCES moim(moim_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_dibs_moim_user FOREIGN KEY (user_id)
        REFERENCES users(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
