INSERT INTO player(nick,level,points,benefit,loss) values ('zzenek',1,10,5,3);
INSERT INTO player(nick,level,points,benefit,loss) values ('bbenek',1,10,5,3);
INSERT INTO player(nick,level,points,benefit,loss) values ('ggienek',3,10,5,3);
INSERT INTO player(nick,level,points,benefit,loss) values ('kkaja',8,10,5,3);
INSERT INTO player(nick,level,points,benefit,loss) values ('mmaja',1,10,5,3);
INSERT INTO player(nick,level,points,benefit,loss) values ('ggaja',2,10,5,3);

INSERT INTO profile(name,surname,life_motto,about_me) values ('Zenek','Zenkowski','co mnie nie zabije to mnie wzmocni','fajny jestem');
INSERT INTO profile(name,surname,life_motto,about_me) values ('Benek','Benkowski','idę wolno, ale nie cofam się nigdy','fajny jestem');
INSERT INTO profile(name,surname,life_motto,about_me) values ('Gienek','Gienkowski','myślenie ryje głowę','fajny jestem');
INSERT INTO profile(name,surname,life_motto,about_me) values ('Kaja','Kajkowska','rób, to co lubisz, ale najpierw spróbuj wszystkiego','fajna jestem');
INSERT INTO profile(name,surname,life_motto,about_me) values ('Maja','Majkowska','myślenie ryje głowę','fajna jestem');
INSERT INTO profile(name,surname,life_motto,about_me) values ('Gaja','Gajkowska','nigdy nie jest tak źle żeby nie mogło być gorzej','fajna jestem');

INSERT INTO user(email,password,player_id,profile_id) values ('zenek@ipsario.pl','fajny_zenek',1,1);
INSERT INTO user(email,password,player_id,profile_id) values ('benek@ipsario.pl','fajny_benek',2,2);
INSERT INTO user(email,password,player_id,profile_id) values ('gienek@ipsario.pl','fajny_gienek',3,3);
INSERT INTO user(email,password,player_id,profile_id) values ('kaja@ipsario.pl','fajna_kaja',4,4);
INSERT INTO user(email,password,player_id,profile_id) values ('maja@ipsario.pl','fajna_maja',5,5);
INSERT INTO user(email,password,player_id,profile_id) values ('gaja@ipsario.pl','fajna_gaja',6,6);

INSERT INTO challenge(challenge_status,id_player_id,id_opponent_id) values('ACCEPT',1,2);
INSERT INTO challenge(challenge_status,id_player_id,id_opponent_id) values('ACCEPT',1,3);
INSERT INTO challenge(challenge_status,id_player_id,id_opponent_id) values('DECLINE',4,5);
INSERT INTO challenge(challenge_status,id_player_id,id_opponent_id) values('RECEIVED',5,6);
INSERT INTO challenge(challenge_status,id_player_id,id_opponent_id) values('DECLINE',6,5);
INSERT INTO challenge(challenge_status,id_player_id,id_opponent_id) values('DECLINE',2,5);