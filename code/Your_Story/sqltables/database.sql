-- phpMyAdmin SQL Dump
-- version 3.5.5
-- http://www.phpmyadmin.net
--
-- Host: sql7.freemysqlhosting.net
-- Generation Time: Dec 20, 2016 at 07:24 PM
-- Server version: 5.5.49-0ubuntu0.14.04.1
-- PHP Version: 5.3.28

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `sql7149730`
--

-- --------------------------------------------------------

--
-- Table structure for table `charac`
--

CREATE TABLE IF NOT EXISTS `charac` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `story` int(10) unsigned NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Dumping data for table `charac`
--

INSERT INTO `charac` (`id`, `story`, `name`, `description`) VALUES
(1, 1, 'Monster', 'Huge one'),
(2, 1, 'Hulk', 'Green one'),
(3, 1, 'Demon', 'So scary'),
(4, 2, 'Human', 'Very weaky'),
(5, 2, 'Elf', 'Dark elf'),
(6, 3, 'Karma', 'Stupid'),
(7, 3, 'Rodeo', 'Big one'),
(8, 3, 'Spider-Man', 'Young'),
(9, 3, 'Black Widow', 'Scarlett Johanson'),
(10, 3, 'Archer', 'Good'),
(11, 4, 'Thriel Lyndress', 'A girl in her twenties. His father, the king of Relfaé, kept her identity secret since because of the tradition: The princesses in Ralfaé cannot sit on the throne.  As he feared something might happen to her, before he gave his last breath, he made a promise with his maid, the daughter he keeps as a secret from everyone. “Promise me, you will not get involved with Ralfaé’s problems.” Was his last words. \n\nRight now, this orphan princess has no power except her beauty. She accidentally heard a rumor of invasion, in a tavern which she works after she was thrown out from the castle.'),
(12, 4, 'Ryan Prusan', 'A single elder man in his fifties. A respected knight, before he was thrown out from the army due to his alcohol problem. He tried to suicide, after the death of his king, who he loved more than anyone. \r\n\r\nEven if he is alcoholic, he still remembers the exact words coming from the guy’s mouth. “Hear me, there will be an invasion!” while he was drinking in a tavern. \r\n'),
(13, 4, 'Uthan Eldies', 'A beloved man in his forties. In the public, everybody knows him as the “merchant of peace”.  He sells accessories, silver, and house tools. He is popular, since he is honest and trustworthy, which are unseen features for a merchant.  \r\n\r\nOnce in a month, he likes to drink after he is done with his payments. In that particular day, he also heard the exact words coming from the crying guy’s mouth. “Believe me Thriel! They are coming, right now!”  '),
(14, 4, 'Pedre Manglobe', 'An unkown guy in his twenties. He was a prisoner in Ralfaé, after he supported the wrong cause in the inner war. While waiting for his death, he was bought by an old man. For a short time, we was the only slave of this man, who is a demoted soldier in Gratina. Pedre did not scared nor yield from his tremendously hard life. In his mind, there was only one thing. He wanted go to his country, and die for it. \r\n\r\nAs they are preparing for a war, he did not know who will be their target. He heard the tragedy, when the drunk man spilled the beans to his friends. He waited for the right time to escape. In the next day, he stole his owner’s horse, and went directly to his country, Ralfaé. It took more than he expected. A full day passed without enough food. Another day passed after the horse collapsed. Another day passed under the storm. When he reached the city, he was now a traitor, and a thief. Without owning anything, he reached to his only friend, an ex-maid who is working in a tavern now. \r\n\r\nWhen he shouted the truth, he did not know there are more listeners… '),
(15, 4, 'Altazan Kreithen', 'An aggresive man in his forties. He thinks he is aggresive because of the retarted soldiers  that he commands. If he wasn’t a hero in his country due to his success in his old wars, in the inner war they would have hang him. Now, he keeps his army passive, since he doesn’t want to lose men in this unneccessary fight. \r\nBut that doesn’t mean he is happy. His unloyal soldiers keep leaving one by one because of the lack of work, and there is nothing he can do about it. Except drinking, of course. \r\nIn his daily routine of midnight drinking, he heard the rumors. In this lucky day, he found a cause after all this time. '),
(16, 4, 'Rudy Galoc', 'A shy guy in his twenties. He is a cleaner in a tavern for living, but he has more day-offs than other workers, due to his sickness. Actually, it is a cover up reason that he made up to convince the tavern owner. He works in a tavern, to hide his real job from the others. \r\nThey call him as the “silent killer”. He is an assassin who kills his targets for money. He always kept his promises, except once. \r\nActually, he killed somebody, so we can’t say he did not kept his promise. He killed the customer, who is asking to kill an innocent maid from him. Sometimes, his conscience makes his job unprofessional, yet he doesn’t care.  Because it is easy to guard an innocent princess, who is hiding as a maid. \r\nRight now, he changed his working place, because his “target” is no longer a maid but a waiter. Since he is obsessed with his job, he eavesdropped the rumors that are told to his target. '),
(17, 5, 'Lassandre', ' An orphan maid in the Emperor’s palace, who has known the world as four sided walls\r\nand locked black windows since the day she was born. In this 17 years, she lived her days in pain,\r\nfearing her and her mother’s death. The only person she loved, who taught her how to obey and\r\nsurvive in this disgusting place was put under the sword for a ridiculous reason last year. She\r\ncouldn’t even mourn her death to respect her mother’s sacrifice. Yet, she also knew about the\r\nimpaled angel by her owner, commander of Bahadrur’s Army, who liked to drink alcohol so much.'),
(18, 5, 'Seraph’im', ' A miraculous entity, an angel from the heavens whose beauty is far more superior than\r\nthe human kind. She has no gender, but has the look of a female. Her strength comes from the\r\nhope, hope of her surroundings. The first time she descended to the city, she had a little power. It\r\nwas the first time that she felt such despair coming from the citizens. It was an impossible task for\r\nher to win with this power. Thus, she had to make people believe in her and hope for a new era.\r\nFor this single reason, at first she concealed herself from everyone. Within her reach, she single\r\nhandedly showed people her true self to make them believe in her. Days passed, she grew more\r\npowerful. Her followers showed unquestionable loyalty and believed that she can overthrown King\r\nBahadrur. Yet, Bahadrur had eyes and ears everywhere. At first he only heard some rumors.\r\nWithout being able to locate this “traitor” his anger grew more and more. More days passed and\r\nhe finally found a chance. In a private meeting for the final plan of coup d’état , his soldiers\r\ninvaded. After capturing and killing every single traitors with their family, he finally found a way to\r\npurge the Seraph’im. In his royal palace, he had a gift from hell. This gift was a necklace, which\r\nactivates when somebody wears it. The moment he put this necklace on Seraph’im’s neck, a\r\nthousand pikes impaled her body. Seraph’im, who lost all of her followers also lost her will to fight\r\nanymore. Without any power nor will, she shat herself and closed her mind in this room of\r\nretribution.'),
(19, 5, 'Ryoal the Front', ' Ryoal was only eight years old when he was recuited as a soldier in King\r\nBahadrur’s army. He did not know what happened to his family and he could not remember their\r\nfaces anymore. Even though he was in his twenties, he participated in three wars which he was a\r\nfrontline soldier in all of them. He got his title “the Front” by fighting and not dying in frontline. For\r\nyears, he relentlessly killed his enemies. He didn’t accept any promotions, and continued to fight\r\nwith all his might. Other soldiers tought that he had a murderous intent that kept him killing more\r\nand more but nobody knew the real reason. He, who had no wish to live, just wanted to die in war.\r\nFor this reason he volunteered to fight in front lines. Because he did not have the courage to kill\r\nhimself, this method was suitable for him. Yet, he survived all of his clashes miraculously. He had\r\nonly some slight injuries after he came to the city.\r\nIn one day, he was called by King Bahadrur himself. He had no idea about what he did, and he had\r\nno fear of what would’ve happened to him. When Bahadrur talked with him, it wasn’t because he\r\ndid a bad thing. The king wanted him to guard a secret place, the room of retribution since he was\r\nloyal and can be trusted. Before this conversation, he had no idea about Seraph’im. The more he\r\nlearned about what he was going to guard, he became more interested. 30 years ago, a traitor died\r\nin this room with a thousand pikes impaled his body. He learned it was possible by a magical\r\nnecklace, which curses the person who wore it. In his new position, days passed by guarding the\r\nroom. His curiosity grew more and more, but he was not allowed to enter the room. On one day,\r\nwhen the other guard was sick, he guarded the room himself in night. He wanted to know who was\r\nhe, since he should be the most courageous person in the world as he can riot to Bahadrur. Ryoal\r\nentered the room when nobody was around. In the pure darkness, he held a torch to see the\r\ntraitors face. And everything happened at that moment.\r\nShe was not a traitor. She was a real angel, a miraculous entity. The moment he saw her, he knew\r\nhe had to do something. “She should not be here, she does not deserve this.” was the thing in his\r\nmind. He knew that if he wanted to rescue her, there was only a single thing that can be done.\r\nWithout any hesitation, he pulled the necklace from her neck, but nothing happened. He tried to\r\npull the pikes himself, but nothing happened. Then, he realised what to do. His unimportant life\r\nwould got a meaning if he can use it for her. Thus, he put the necklace on his neck. A thousand\r\npikes, moved from her to him.\r\nHe was impaled by a thousand pikes, and Seraph’im opened her eyes, again.\r\n');

-- --------------------------------------------------------

--
-- Table structure for table `lobby`
--

CREATE TABLE IF NOT EXISTS `lobby` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `state` tinyint(4) NOT NULL,
  `story` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `lobby`
--

INSERT INTO `lobby` (`id`, `name`, `state`, `story`) VALUES
(9, 'BestLobby', 1, 3),
(10, 'Baris''s Lobby', 1, 1),
(11, 'Come In Bro', 0, 2),
(12, 'Free Lobby For All', 0, 3),
(13, 'FreeStyle', 0, 4),
(14, 'BBC', 0, 5);

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE IF NOT EXISTS `message` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user` int(11) NOT NULL,
  `lobby` int(10) unsigned NOT NULL,
  `body` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=57 ;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`id`, `user`, `lobby`, `body`) VALUES
(1, 5, 9, 'Hey'),
(2, 6, 9, 'Wassup'),
(3, 5, 9, 'This is a new message'),
(5, 6, 9, 'this is for trying u?'),
(6, 6, 9, 'This is for tirial'),
(7, 5, 9, 'hi guyssss!'),
(8, 6, 9, 'this is sparta!!!!'),
(9, 5, 9, 'how it is going?==?!'),
(10, 6, 9, 'its goinfg good'),
(11, 9, 9, 'naber gencler'),
(12, 6, 9, 'iyi genc what are you doing now'),
(13, 6, 9, 'jdskhajafhkjc'),
(14, 6, 9, 'kes fazla konusma'),
(15, 9, 9, 'sdhkfhjksdahfck'),
(16, 9, 9, 'abi naber'),
(17, 9, 9, 'fdvjfdfvfk?cdj?'),
(18, 9, 9, 'sdakjfnjkadc'),
(19, 9, 9, 'jdfvjkfhfsdvr'),
(20, 9, 9, 'jfvdhsjkhfk'),
(21, 9, 9, 'kgkfsjgklv'),
(22, 7, 9, 'hi guys'),
(23, 7, 9, 'hi all Im new here'),
(24, 7, 9, 'I will kill sensei'),
(25, 7, 9, 'Im karma im not bitch\n'),
(26, 7, 9, 'What does the fox say????'),
(27, 7, 9, 'hello guys'),
(28, 7, 9, 'ju5785\n'),
(29, 7, 9, '"""éç?\n'),
(30, 7, 9, '?oöüç?\n?'),
(31, 7, 9, 'why you stealing my name?'),
(32, 7, 11, 'wtf are those characters?'),
(33, 7, 10, 'hey bro'),
(34, 7, 10, 'where r we meeting\n'),
(35, 7, 9, 'asdasdasd'),
(36, 7, 9, 'Guys we need to finish this GUI thing'),
(37, 8, 9, 'hi'),
(38, 8, 10, 'hey'),
(39, 8, 10, 'try 1 2\n'),
(40, 8, 10, ''),
(41, 8, 10, '234'),
(42, 7, 10, 'Why suddenly im spiderman>? I want to be Karma Akabane aka AK"""Karma"'),
(43, 8, 10, 'hellop  spider\n'),
(44, 7, 10, 'I forgot that you can''t delete here'),
(45, 8, 10, 'yeah yeah\n'),
(46, 7, 10, 'you are doomed to fail. Like a calculator xD]\n\n'),
(47, 8, 10, 'hah! at leatsfuck canmt dealet a simngle shit\n'),
(48, 7, 10, 'btw did you check that ongoing lobbies thing I told u on wp?'),
(49, 7, 10, 'Ertugrul is that u?\n'),
(50, 8, 10, 'yeah'),
(51, 7, 10, 'let me go offline and see if it shows'),
(52, 7, 10, 'write me on wp'),
(53, 8, 10, 'kk \n'),
(54, 8, 10, 'kk'),
(55, 5, 10, 'hi guys\n'),
(56, 6, 9, 'heyyyyy');

-- --------------------------------------------------------

--
-- Table structure for table `profile`
--

CREATE TABLE IF NOT EXISTS `profile` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user` int(10) unsigned NOT NULL,
  `description` tinytext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user` (`user`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `profile`
--

INSERT INTO `profile` (`id`, `user`, `description`) VALUES
(1, 5, ''),
(2, 6, ''),
(3, 7, ''),
(4, 8, ''),
(5, 9, ''),
(6, 10, '1234'),
(7, 11, '');

-- --------------------------------------------------------

--
-- Table structure for table `seat`
--

CREATE TABLE IF NOT EXISTS `seat` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `lobby` int(10) unsigned NOT NULL,
  `charac` int(10) unsigned DEFAULT NULL,
  `user` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=34 ;

--
-- Dumping data for table `seat`
--

INSERT INTO `seat` (`id`, `lobby`, `charac`, `user`) VALUES
(10, 9, 7, 5),
(11, 9, 9, 6),
(12, 9, 6, 7),
(13, 9, 8, 8),
(14, 9, 10, 9),
(15, 10, 6, 5),
(16, 10, 7, 8),
(17, 10, 8, 7),
(18, 11, NULL, 6),
(19, 11, NULL, NULL),
(20, 12, NULL, 7),
(21, 12, NULL, 8),
(22, 12, NULL, 9),
(23, 12, NULL, NULL),
(24, 12, NULL, NULL),
(25, 13, NULL, 10),
(26, 13, NULL, 11),
(27, 13, NULL, NULL),
(28, 13, NULL, NULL),
(29, 13, NULL, NULL),
(30, 13, NULL, NULL),
(31, 14, NULL, NULL),
(32, 14, NULL, NULL),
(33, 14, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `story`
--

CREATE TABLE IF NOT EXISTS `story` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `timeline` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `quota` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `story`
--

INSERT INTO `story` (`id`, `timeline`, `description`, `quota`) VALUES
(1, '1920', 'Some description', 3),
(2, '1763', 'More description', 2),
(3, '3251', 'Description nnn', 5),
(4, 'Middle Ages', 'Background Story: A war is rising again between kingdoms! \n\nThe Gratina Empire has created a special frontline force to invade the Kingdom Relfaé, which is still struggling with an inner war since the king without a son deceased. As the greedy King of Gratina wants to obtain the fertile lands of Relfaé, he works behind the scenes to prevent any causalities from his side. \n\nHis frontline force consists of demoted soldiers, blood-thirsty hunters and silent assassins. If they win, they are promised with a portion of land and prosperity of Ralfaé but they do not know the name of the nation that they will attack. Since they do not carry a flag, nobody knows which country they are from, except these six people: A powerless princes, a suicidal ex-knight, an experienced merchant, a nationalist traitor, a wild commander, and finally an unsuccessful assassin. \n\nIn this tavern, these 6 people know about the incoming danger. What will they do? How will they act? It is your story now!', 6),
(5, '3000 BC', 'Place​: Baghdad, Iraq\nCharacters:​ Seraph’im, Lassendre, Ryoal the Front\nBackground Story:\n-The Beginning of an Old TaleFor\nhundreds of years, humans lived with wealth and prosperity in the fertile lands of Baghdad. As\nthe center of the greatest wonders, Baghdad has treacherous eyes, filled with hatred and agony,\non it to take over the goods for themselves. Years passed, the city had fallen into the most\ndangerous human’s hand, Bahadrur. After the destruction of Bahadrur’s rivals, he ruled his city\nruthlessly for 20 years.\nIn the moment when all of the hope had gone, and beauties had forgotten, a new miracle\nappeared from nowhere. A white angel, whose existence can only be described by poems and\nsongs, came to the rescue of this city. But, times had already changed. She, who came with good\nand beauty, impaled with a thousand pikes, in the room of retribution. The immortal angel,\nSeraph’im, had lost her will and closed her mind. She, who was the symbol of hope, became a cold,\nweeping statue. For 30 years, the city had entered a worse state than ever. Not a single soul had\nyet to think about freedom, instead of saving his head. But even in a state like this, it was not really\nimpossible for a new miracle to be happened soon.\n\nSeraph’im is resurrected again. Guards are alarted after a huge sound came from the room. What\nwill Seraph’im is going to do? What happened to Ryoal? It is your story​ now!', 3);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `online` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `online`) VALUES
(5, 'baris', 'yilmaz', 1),
(6, 'e3', 't3', 0),
(7, 'a', 'a', 0),
(8, 'ali', 'ali', 0),
(9, 'Kaxell', 'this is not actual pw', 1),
(10, 'erin', 'erin', 0),
(11, 'ohsweet', '123', 1);

-- --------------------------------------------------------

--
-- Table structure for table `vote`
--

CREATE TABLE IF NOT EXISTS `vote` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `voting` int(10) unsigned NOT NULL,
  `value` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `voting`
--

CREATE TABLE IF NOT EXISTS `voting` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `lobby` int(11) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `target` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
