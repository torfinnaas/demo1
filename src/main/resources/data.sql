
insert into tur_pakke (kode, navn) values
('BC', 'Backpack Cal'),
('CC', 'California Calm'),
('CH', 'California Hot springs'),
('CY', 'Cycle California'),
('DS', 'From Desert to Sea'),
('KC', 'Kids California'),
('NW', 'Nature Watch'),
('SC', 'Snowboard Cali'),
('TC', 'Taste of California');

insert into tur (tur_pakke_kode, tittel, beskrivelse, grad, pris) values
  (
    'BC',
    'Big Sur Retreat',
    'Big Sur is big country. The Big Sur Retreat takes you to the most majestic part of the Pacific Coast and show you the secret trails.',
   'Easy',
    '750'
  );

insert into tur (tur_pakke_kode, tittel, beskrivelse, grad, pris) values
  (
   'BC',
    'In the Steps of John Muir',
    'Follow in the steps on John Muir, famous naturalist and founder of the Sierra Club, and walk the same trails he helped blaze in and around Yosemite National Park.',
   'Difficult',
    '600'
  );


insert into tur (tur_pakke_kode, tittel, beskrivelse, grad, pris) values
  (
   'BC',
    'The Death Valley Survivor''s Trek',
    'Hot stuff? Need more of a challenge? Take this tour to the hottest place in North America: Death Valley.',
   'Difficult',
    '250'
  );

insert into tur (tur_pakke_kode, tittel, beskrivelse, grad, pris) values
  (
   'BC',
    'The Mt. Whitney Climbers Tour',
    'Climb to the sky! The Mt. Whitney Climbers Tour takes you to the top of this 14,000 ft. of mountain in 4 days- our longest and most strenuous backpacking tour.',
   'Difficult',
    '650'
  ),
  (
   'BC',
    'Channel Islands Excursion',
    'The chain known as the Channel Islands offer some of the most diverse and unique landscape on the Pacific coast. No motor vehicles are allowed on the islands, which makes this daytrip hiking package the best and most interesting way to visit.',
   'Easy',
    '150'
  );
insert into tur (tur_pakke_kode, tittel, beskrivelse, grad, pris) values
  (
    'CC',
    'Day Spa Package',
    'Trying to pack some serious relaxation into a short timeframe? Try our Day Spa Package, a full 8 hour immersive experience in seductive the Ojai Valley that will ease away months''worth of tension.',
   'Easy',
    '550'
  ),
  (
    'CC',
    'Restoration Package',
    'The Restoration Package is a 2 day balm for the tired soul. Choose from among three destinations that uniquely combine world-class spa services and attention to the spirit, in locations of unparalleled natural beauty.',
   'Easy',
    '900'
  ),
  (
    'CC',
    'Huntington Library and Pasadena Retreat Tour',
    'This package is perfect for those who want to be pampered, but don''t just want to rest on their laurels all day. Stimulate your mind visiting the Huntington Library''s art and gardens and then settle back for some dining and primping at the Beauty Bar.',
   'Easy',
    '225'
  );

insert into tur (tur_pakke_kode, tittel, beskrivelse, grad, pris) values
  (
    'CH',
    'Avila Beach Hot springs',
    'Spend a weekend in a rustic log cabin, within walking distance of the historic Avila Hot Springs and minutes from the beach.',
   'Easy',
    '1000'
  );


insert into tur (tur_pakke_kode, tittel, beskrivelse, grad, pris) values
  (
    'CH',
    'Matilija Hot springs',
    'Visit the Matilija Sanctuary, located above the tranquil Ojai Valley, and indulge in the waters that flow from the natural sulfur springs.',
   'Easy',
    '1000'
  );




insert into tur_rating (tur_id, customer_id, score, comment) values
  (1, 4, 5, 'I loved it'),
  (2, 100, 5, 'I really thought it could have been better');

