
CREATE TABLE user (
    user_id VARCHAR(20) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(10) NOT NULL UNIQUE,
    review DECIMAL(3,1) DEFAULT 3.0
);

CREATE TABLE user_review (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    reviewer VARCHAR(10) NOT NULL,
    reviewee VARCHAR(10) NOT NULL,
    score DECIMAL(3,1) NOT NULL,  -- CHECK는 MySQL에 따라 무시될 수 있음

    CONSTRAINT fk_reviewer FOREIGN KEY (reviewer) REFERENCES user(nickname)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_reviewee FOREIGN KEY (reviewee) REFERENCES user(nickname)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE moim (
    moim_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(30) NOT NULL,
    is_online BOOLEAN DEFAULT FALSE,
    max_people INT,  -- CHECK (max_people <= 100)는 애플리케이션에서 처리 권장
    expiration_date DATE,
    even_date DATE,
    location VARCHAR(25),
    represent_img VARCHAR(255),
    category VARCHAR(40) NOT NULL
);

CREATE TABLE moim_detail (
    moim_id INT NOT NULL PRIMARY KEY,
    content LONGTEXT,
    min_people INT,
    CONSTRAINT fk_moim_id FOREIGN KEY (moim_id) REFERENCES moim(moim_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE moim_review (
    moim_id INT NOT NULL,
    reviewer_nickname VARCHAR(10) NOT NULL,
    score DECIMAL(3,1) NOT NULL,
    comment TEXT,

    PRIMARY KEY (moim_id, reviewer_nickname),
    FOREIGN KEY (moim_id) REFERENCES moim(moim_id) ON DELETE CASCADE,
    FOREIGN KEY (reviewer_nickname) REFERENCES user(nickname) ON DELETE CASCADE
);

CREATE TABLE moim_favorite (
    moim_id INT NOT NULL,
    user_id VARCHAR(20) NOT NULL,

    PRIMARY KEY (moim_id, user_id),

    CONSTRAINT fk_dibs_moim_moim FOREIGN KEY (moim_id)
        REFERENCES moim(moim_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_dibs_moim_user FOREIGN KEY (user_id)
        REFERENCES user(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
