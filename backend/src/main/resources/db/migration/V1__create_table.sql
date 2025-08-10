
CREATE TABLE user (
    user_id VARCHAR(20) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(10) NOT NULL UNIQUE,
    review DECIMAL(3,1) DEFAULT 3.0,
    Intro LONGTEXT NULL,
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
    moim_id VARCHAR(20) PRIMARY KEY,
    title VARCHAR(30) NOT NULL,
    is_online BOOLEAN DEFAULT FALSE,
    max_people INT,  -- CHECK (max_people <= 100)는 애플리케이션에서 처리 권장
    expiration_date DATE,
    even_date DATE,
    location VARCHAR(25),
    represent_img VARCHAR(255),
    category VARCHAR(40) NOT NULL,
    organizer VARCHAR(50) NOT NULL,
    description VARCHAR(50) NOT NULL,  -- 쉼표 누락 수정
    tag JSON,
    category_detail VARCHAR(40) NOT NULL,  -- 카멜케이스 → 스네이크케이스 변경 추천
    approval BOOLEAN DEFAULT FALSE         -- 쉼표 제거 (마지막 컬럼 뒤 쉼표 안 됨)
);

CREATE TABLE moim_approval (
  moim_id VARCHAR(20) NOT NULL,
  join_member VARCHAR(255),
  approval_members TEXT,
  CONSTRAINT fk_moim_id FOREIGN KEY (moim_id) REFERENCES moim(moim_id)
          ON DELETE CASCADE
          ON UPDATE CASCADE
); --신규 테이블 추가

CREATE TABLE moim_detail (
    moimdetailId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    moim_id VARCHAR(20) NOT NULL,
    content LONGTEXT,
    min_people INT,
    Pay DECIMAL(15, 2),
    CONSTRAINT fk_moim_id FOREIGN KEY (moim_id) REFERENCES moim(moim_id)
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
    FOREIGN KEY (reviewer_nickname) REFERENCES user(nickname) ON DELETE CASCADE
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
        REFERENCES user(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
