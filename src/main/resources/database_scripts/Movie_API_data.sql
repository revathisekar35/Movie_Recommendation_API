INSERT INTO `moviedetails`.`movie` (`id`, `certification`, `country`, `description`, `genre`, `language`, `name`, `release_date`) VALUES ('1', '1', '0', 'The film\'s story focuses on Peter Rabbit as he deals with new problems', '3', '0', 'Peter Rabbit', '2018-03-16');
INSERT INTO `moviedetails`.`person` (`id`, `name`, `role`) VALUES ('1', 'Will Gluck', '1');
INSERT INTO `moviedetails`.`person` (`id`, `name`, `role`) VALUES ('2', 'Rose Byrne', '0');
INSERT INTO `moviedetails`.`movie_person` (`movie_id`, `person_id`) VALUES ('1', '1');
INSERT INTO `moviedetails`.`movie_person` (`movie_id`, `person_id`) VALUES ('1', '2');
INSERT INTO `moviedetails`.`rating` (`id`, `dislikes`, `likes`, `total_rating`, `movie_id`) VALUES ('1', '10', '90', '6.6', '1');

INSERT INTO `moviedetails`.`movie` (`id`, `certification`, `country`, `description`, `genre`, `language`, `name`, `release_date`) VALUES ('2', '1', '1', 'Annie is a 2014 American musical comedy film directed by Will Gluck, produced by Village Roadshow Pictures and James Lassiter\'s Overbrook Entertainment and distributed by Sony Pictures Releasing\'s Columbia Pictures', '3', '0', 'Annie', '2014-12-19');
INSERT INTO `moviedetails`.`person` (`id`, `name`, `role`) VALUES ('3', 'Jamie Foxx', '0');
INSERT INTO `moviedetails`.`movie_person` (`movie_id`, `person_id`) VALUES ('2', '1');
INSERT INTO `moviedetails`.`movie_person` (`movie_id`, `person_id`) VALUES ('2', '3');
INSERT INTO `moviedetails`.`rating` (`id`, `dislikes`, `likes`, `total_rating`, `movie_id`) VALUES ('2', '30', '78', '5.3', '2');

INSERT INTO `moviedetails`.`movie` (`id`, `certification`, `country`, `description`, `genre`, `language`, `name`, `release_date`) VALUES ('3', '1', '1', 'The Boss Baby is a 2017 American computer-animated comedy film produced by DreamWorks Animation and distributed by 20th Century Fox', '1', '0', 'The Boss Baby', '2017-12-03');
INSERT INTO `moviedetails`.`person` (`id`, `name`, `role`) VALUES ('4', 'Tom McGrath', '1');
INSERT INTO `moviedetails`.`person` (`id`, `name`, `role`) VALUES ('5', 'Alec Baldwin', '0');
INSERT INTO `moviedetails`.`movie_person` (`movie_id`, `person_id`) VALUES ('3', '4');
INSERT INTO `moviedetails`.`movie_person` (`movie_id`, `person_id`) VALUES ('3', '5');
INSERT INTO `moviedetails`.`rating` (`id`, `dislikes`, `likes`, `total_rating`, `movie_id`) VALUES ('3', '20', '80', '6.3', '3');

INSERT INTO `moviedetails`.`user` (`id`, `age`, `email`, `is_admin`, `name`) VALUES ('1', '30', 'george@gmail.com', FALSE, 'George');
INSERT INTO `moviedetails`.`user` (`id`, `age`, `email`, `is_admin`, `name`)  VALUES ('2', '34', 'jack@gmail.com', FALSE, 'Jack');
INSERT INTO `moviedetails`.`user` (`id`, `age`, `email`, `is_admin`, `name`)  VALUES ('3', '20', 'harry@gmail.com', FALSE, 'Harry');
INSERT INTO `moviedetails`.`review` (`id`, `comment`, `created_date`, `movie_id`, `user_id`) VALUES ('1', 'An absolutely fantastic film. I really enjoy watching both Peter Rabbit films', '2020-02-03', '1', '1');
INSERT INTO `moviedetails`.`review` (`id`, `comment`, `created_date`, `movie_id`, `user_id`) VALUES ('2', 'This is a review for Annie', '2022-02-03', '2', '2');
INSERT INTO `moviedetails`.`review` (`id`, `comment`, `created_date`, `movie_id`, `user_id`) VALUES ('3', 'This is a review for Boss Baby', '2021-12-03', '3', '3');

