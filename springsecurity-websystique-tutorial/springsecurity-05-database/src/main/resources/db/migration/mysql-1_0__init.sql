USE spring;

DROP TABLE app_user_user_profile;
DROP TABLE app_user;
DROP TABLE user_profile;

/*All User's are stored in APP_USER table*/
CREATE TABLE app_user (
  id         BIGINT       NOT NULL AUTO_INCREMENT,
  sso_id     VARCHAR(30)  NOT NULL,
  password   VARCHAR(100) NOT NULL,
  first_name VARCHAR(30)  NOT NULL,
  last_name  VARCHAR(30)  NOT NULL,
  email      VARCHAR(30)  NOT NULL,
  state      VARCHAR(30)  NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (sso_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/* USER_PROFILE table contains all possible roles */
CREATE TABLE user_profile (
  id   BIGINT      NOT NULL AUTO_INCREMENT,
  type VARCHAR(30) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (type)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/* JOIN TABLE for MANY-TO-MANY relationship*/
CREATE TABLE app_user_user_profile (
  user_id         BIGINT NOT NULL,
  user_profile_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, user_profile_id),
  CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES app_user (id),
  CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES user_profile (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/* Populate USER_PROFILE Table */
INSERT INTO user_profile (type)
VALUES ('USER');

INSERT INTO user_profile (type)
VALUES ('ADMIN');

INSERT INTO user_profile (type)
VALUES ('DBA');

/* Populate APP_USER Table */
INSERT INTO app_user (sso_id, password, first_name, last_name, email, state)
VALUES ('bill', 'abc123', 'Bill', 'Watcher', 'bill@xyz.com', 'Active');

INSERT INTO app_user (sso_id, password, first_name, last_name, email, state)
VALUES ('danny', 'abc124', 'Danny', 'Theys', 'danny@xyz.com', 'Active');

INSERT INTO app_user (sso_id, password, first_name, last_name, email, state)
VALUES ('sam', 'abc125', 'Sam', 'Smith', 'samy@xyz.com', 'Active');

INSERT INTO app_user (sso_id, password, first_name, last_name, email, state)
VALUES ('nicole', 'abc126', 'Nicole', 'warner', 'nicloe@xyz.com', 'Active');

INSERT INTO app_user (sso_id, password, first_name, last_name, email, state)
VALUES ('kenny', 'abc127', 'Kenny', 'Roger', 'kenny@xyz.com', 'Active');

/* Populate JOIN Table */
INSERT INTO app_user_user_profile (user_id, user_profile_id)
  SELECT
    user.id,
    profile.id
  FROM app_user user, user_profile profile
  WHERE user.sso_id = 'bill' AND profile.type = 'USER';

INSERT INTO app_user_user_profile (user_id, user_profile_id)
  SELECT
    user.id,
    profile.id
  FROM app_user user, user_profile profile
  WHERE user.sso_id = 'danny' AND profile.type = 'USER';

INSERT INTO app_user_user_profile (user_id, user_profile_id)
  SELECT
    user.id,
    profile.id
  FROM app_user user, user_profile profile
  WHERE user.sso_id = 'sam' AND profile.type = 'ADMIN';

INSERT INTO app_user_user_profile (user_id, user_profile_id)
  SELECT
    user.id,
    profile.id
  FROM app_user user, user_profile profile
  WHERE user.sso_id = 'nicole' AND profile.type = 'DBA';

INSERT INTO app_user_user_profile (user_id, user_profile_id)
  SELECT
    user.id,
    profile.id
  FROM app_user user, user_profile profile
  WHERE user.sso_id = 'kenny' AND profile.type = 'ADMIN';

INSERT INTO app_user_user_profile (user_id, user_profile_id)
  SELECT
    user.id,
    profile.id
  FROM app_user user, user_profile profile
  WHERE user.sso_id = 'kenny' AND profile.type = 'DBA';