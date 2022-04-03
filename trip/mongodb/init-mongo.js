db = db.getSiblingDB('trips-db');

db.createCollection('trips');
db.trips.createIndex( { "pickup_datetime": 1 , "dropoff_datetime": 1 } )
db.zones.insertMany([
  {
    "_id": 1,
    "borough": "EWR",
    "zone": "Newark Airport",
    "service_zone": "EWR"
  },
  {
    "_id": 2,
    "borough": "Queens",
    "zone": "Jamaica Bay",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 3,
    "borough": "Bronx",
    "zone": "Allerton/Pelham Gardens",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 4,
    "borough": "Manhattan",
    "zone": "Alphabet City",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 5,
    "borough": "Staten Island",
    "zone": "Arden Heights",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 6,
    "borough": "Staten Island",
    "zone": "Arrochar/Fort Wadsworth",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 7,
    "borough": "Queens",
    "zone": "Astoria",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 8,
    "borough": "Queens",
    "zone": "Astoria Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 9,
    "borough": "Queens",
    "zone": "Auburndale",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 10,
    "borough": "Queens",
    "zone": "Baisley Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 11,
    "borough": "Brooklyn",
    "zone": "Bath Beach",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 12,
    "borough": "Manhattan",
    "zone": "Battery Park",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 13,
    "borough": "Manhattan",
    "zone": "Battery Park City",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 14,
    "borough": "Brooklyn",
    "zone": "Bay Ridge",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 15,
    "borough": "Queens",
    "zone": "Bay Terrace/Fort Totten",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 16,
    "borough": "Queens",
    "zone": "Bayside",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 17,
    "borough": "Brooklyn",
    "zone": "Bedford",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 18,
    "borough": "Bronx",
    "zone": "Bedford Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 19,
    "borough": "Queens",
    "zone": "Bellerose",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 20,
    "borough": "Bronx",
    "zone": "Belmont",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 21,
    "borough": "Brooklyn",
    "zone": "Bensonhurst East",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 22,
    "borough": "Brooklyn",
    "zone": "Bensonhurst West",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 23,
    "borough": "Staten Island",
    "zone": "Bloomfield/Emerson Hill",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 24,
    "borough": "Manhattan",
    "zone": "Bloomingdale",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 25,
    "borough": "Brooklyn",
    "zone": "Boerum Hill",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 26,
    "borough": "Brooklyn",
    "zone": "Borough Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 27,
    "borough": "Queens",
    "zone": "Breezy Point/Fort Tilden/Riis Beach",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 28,
    "borough": "Queens",
    "zone": "Briarwood/Jamaica Hills",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 29,
    "borough": "Brooklyn",
    "zone": "Brighton Beach",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 30,
    "borough": "Queens",
    "zone": "Broad Channel",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 31,
    "borough": "Bronx",
    "zone": "Bronx Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 32,
    "borough": "Bronx",
    "zone": "Bronxdale",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 33,
    "borough": "Brooklyn",
    "zone": "Brooklyn Heights",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 34,
    "borough": "Brooklyn",
    "zone": "Brooklyn Navy Yard",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 35,
    "borough": "Brooklyn",
    "zone": "Brownsville",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 36,
    "borough": "Brooklyn",
    "zone": "Bushwick North",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 37,
    "borough": "Brooklyn",
    "zone": "Bushwick South",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 38,
    "borough": "Queens",
    "zone": "Cambria Heights",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 39,
    "borough": "Brooklyn",
    "zone": "Canarsie",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 40,
    "borough": "Brooklyn",
    "zone": "Carroll Gardens",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 41,
    "borough": "Manhattan",
    "zone": "Central Harlem",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 42,
    "borough": "Manhattan",
    "zone": "Central Harlem North",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 43,
    "borough": "Manhattan",
    "zone": "Central Park",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 44,
    "borough": "Staten Island",
    "zone": "Charleston/Tottenville",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 45,
    "borough": "Manhattan",
    "zone": "Chinatown",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 46,
    "borough": "Bronx",
    "zone": "City Island",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 47,
    "borough": "Bronx",
    "zone": "Claremont/Bathgate",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 48,
    "borough": "Manhattan",
    "zone": "Clinton East",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 49,
    "borough": "Brooklyn",
    "zone": "Clinton Hill",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 50,
    "borough": "Manhattan",
    "zone": "Clinton West",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 51,
    "borough": "Bronx",
    "zone": "Co-Op City",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 52,
    "borough": "Brooklyn",
    "zone": "Cobble Hill",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 53,
    "borough": "Queens",
    "zone": "College Point",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 54,
    "borough": "Brooklyn",
    "zone": "Columbia Street",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 55,
    "borough": "Brooklyn",
    "zone": "Coney Island",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 56,
    "borough": "Queens",
    "zone": "Corona",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 57,
    "borough": "Queens",
    "zone": "Corona",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 58,
    "borough": "Bronx",
    "zone": "Country Club",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 59,
    "borough": "Bronx",
    "zone": "Crotona Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 60,
    "borough": "Bronx",
    "zone": "Crotona Park East",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 61,
    "borough": "Brooklyn",
    "zone": "Crown Heights North",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 62,
    "borough": "Brooklyn",
    "zone": "Crown Heights South",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 63,
    "borough": "Brooklyn",
    "zone": "Cypress Hills",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 64,
    "borough": "Queens",
    "zone": "Douglaston",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 65,
    "borough": "Brooklyn",
    "zone": "Downtown Brooklyn/MetroTech",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 66,
    "borough": "Brooklyn",
    "zone": "DUMBO/Vinegar Hill",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 67,
    "borough": "Brooklyn",
    "zone": "Dyker Heights",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 68,
    "borough": "Manhattan",
    "zone": "East Chelsea",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 69,
    "borough": "Bronx",
    "zone": "East Concourse/Concourse Village",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 70,
    "borough": "Queens",
    "zone": "East Elmhurst",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 71,
    "borough": "Brooklyn",
    "zone": "East Flatbush/Farragut",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 72,
    "borough": "Brooklyn",
    "zone": "East Flatbush/Remsen Village",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 73,
    "borough": "Queens",
    "zone": "East Flushing",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 74,
    "borough": "Manhattan",
    "zone": "East Harlem North",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 75,
    "borough": "Manhattan",
    "zone": "East Harlem South",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 76,
    "borough": "Brooklyn",
    "zone": "East New York",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 77,
    "borough": "Brooklyn",
    "zone": "East New York/Pennsylvania Avenue",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 78,
    "borough": "Bronx",
    "zone": "East Tremont",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 79,
    "borough": "Manhattan",
    "zone": "East Village",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 80,
    "borough": "Brooklyn",
    "zone": "East Williamsburg",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 81,
    "borough": "Bronx",
    "zone": "Eastchester",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 82,
    "borough": "Queens",
    "zone": "Elmhurst",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 83,
    "borough": "Queens",
    "zone": "Elmhurst/Maspeth",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 84,
    "borough": "Staten Island",
    "zone": "Eltingville/Annadale/Prince's Bay",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 85,
    "borough": "Brooklyn",
    "zone": "Erasmus",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 86,
    "borough": "Queens",
    "zone": "Far Rockaway",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 87,
    "borough": "Manhattan",
    "zone": "Financial District North",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 88,
    "borough": "Manhattan",
    "zone": "Financial District South",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 89,
    "borough": "Brooklyn",
    "zone": "Flatbush/Ditmas Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 90,
    "borough": "Manhattan",
    "zone": "Flatiron",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 91,
    "borough": "Brooklyn",
    "zone": "Flatlands",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 92,
    "borough": "Queens",
    "zone": "Flushing",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 93,
    "borough": "Queens",
    "zone": "Flushing Meadows-Corona Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 94,
    "borough": "Bronx",
    "zone": "Fordham South",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 95,
    "borough": "Queens",
    "zone": "Forest Hills",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 96,
    "borough": "Queens",
    "zone": "Forest Park/Highland Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 97,
    "borough": "Brooklyn",
    "zone": "Fort Greene",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 98,
    "borough": "Queens",
    "zone": "Fresh Meadows",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 99,
    "borough": "Staten Island",
    "zone": "Freshkills Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 100,
    "borough": "Manhattan",
    "zone": "Garment District",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 101,
    "borough": "Queens",
    "zone": "Glen Oaks",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 102,
    "borough": "Queens",
    "zone": "Glendale",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 103,
    "borough": "Manhattan",
    "zone": "Governor's Island/Ellis Island/Liberty Island",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 104,
    "borough": "Manhattan",
    "zone": "Governor's Island/Ellis Island/Liberty Island",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 105,
    "borough": "Manhattan",
    "zone": "Governor's Island/Ellis Island/Liberty Island",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 106,
    "borough": "Brooklyn",
    "zone": "Gowanus",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 107,
    "borough": "Manhattan",
    "zone": "Gramercy",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 108,
    "borough": "Brooklyn",
    "zone": "Gravesend",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 109,
    "borough": "Staten Island",
    "zone": "Great Kills",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 110,
    "borough": "Staten Island",
    "zone": "Great Kills Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 111,
    "borough": "Brooklyn",
    "zone": "Green-Wood Cemetery",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 112,
    "borough": "Brooklyn",
    "zone": "Greenpoint",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 113,
    "borough": "Manhattan",
    "zone": "Greenwich Village North",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 114,
    "borough": "Manhattan",
    "zone": "Greenwich Village South",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 115,
    "borough": "Staten Island",
    "zone": "Grymes Hill/Clifton",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 116,
    "borough": "Manhattan",
    "zone": "Hamilton Heights",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 117,
    "borough": "Queens",
    "zone": "Hammels/Arverne",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 118,
    "borough": "Staten Island",
    "zone": "Heartland Village/Todt Hill",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 119,
    "borough": "Bronx",
    "zone": "Highbridge",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 120,
    "borough": "Manhattan",
    "zone": "Highbridge Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 121,
    "borough": "Queens",
    "zone": "Hillcrest/Pomonok",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 122,
    "borough": "Queens",
    "zone": "Hollis",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 123,
    "borough": "Brooklyn",
    "zone": "Homecrest",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 124,
    "borough": "Queens",
    "zone": "Howard Beach",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 125,
    "borough": "Manhattan",
    "zone": "Hudson Sq",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 126,
    "borough": "Bronx",
    "zone": "Hunts Point",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 127,
    "borough": "Manhattan",
    "zone": "Inwood",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 128,
    "borough": "Manhattan",
    "zone": "Inwood Hill Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 129,
    "borough": "Queens",
    "zone": "Jackson Heights",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 130,
    "borough": "Queens",
    "zone": "Jamaica",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 131,
    "borough": "Queens",
    "zone": "Jamaica Estates",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 132,
    "borough": "Queens",
    "zone": "JFK Airport",
    "service_zone": "Airports"
  },
  {
    "_id": 133,
    "borough": "Brooklyn",
    "zone": "Kensington",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 134,
    "borough": "Queens",
    "zone": "Kew Gardens",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 135,
    "borough": "Queens",
    "zone": "Kew Gardens Hills",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 136,
    "borough": "Bronx",
    "zone": "Kingsbridge Heights",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 137,
    "borough": "Manhattan",
    "zone": "Kips Bay",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 138,
    "borough": "Queens",
    "zone": "LaGuardia Airport",
    "service_zone": "Airports"
  },
  {
    "_id": 139,
    "borough": "Queens",
    "zone": "Laurelton",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 140,
    "borough": "Manhattan",
    "zone": "Lenox Hill East",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 141,
    "borough": "Manhattan",
    "zone": "Lenox Hill West",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 142,
    "borough": "Manhattan",
    "zone": "Lincoln Square East",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 143,
    "borough": "Manhattan",
    "zone": "Lincoln Square West",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 144,
    "borough": "Manhattan",
    "zone": "Little Italy/NoLiTa",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 145,
    "borough": "Queens",
    "zone": "Long Island City/Hunters Point",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 146,
    "borough": "Queens",
    "zone": "Long Island City/Queens Plaza",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 147,
    "borough": "Bronx",
    "zone": "Longwood",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 148,
    "borough": "Manhattan",
    "zone": "Lower East Side",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 149,
    "borough": "Brooklyn",
    "zone": "Madison",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 150,
    "borough": "Brooklyn",
    "zone": "Manhattan Beach",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 151,
    "borough": "Manhattan",
    "zone": "Manhattan Valley",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 152,
    "borough": "Manhattan",
    "zone": "Manhattanville",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 153,
    "borough": "Manhattan",
    "zone": "Marble Hill",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 154,
    "borough": "Brooklyn",
    "zone": "Marine Park/Floyd Bennett Field",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 155,
    "borough": "Brooklyn",
    "zone": "Marine Park/Mill Basin",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 156,
    "borough": "Staten Island",
    "zone": "Mariners Harbor",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 157,
    "borough": "Queens",
    "zone": "Maspeth",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 158,
    "borough": "Manhattan",
    "zone": "Meatpacking/West Village West",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 159,
    "borough": "Bronx",
    "zone": "Melrose South",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 160,
    "borough": "Queens",
    "zone": "Middle Village",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 161,
    "borough": "Manhattan",
    "zone": "Midtown Center",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 162,
    "borough": "Manhattan",
    "zone": "Midtown East",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 163,
    "borough": "Manhattan",
    "zone": "Midtown North",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 164,
    "borough": "Manhattan",
    "zone": "Midtown South",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 165,
    "borough": "Brooklyn",
    "zone": "Midwood",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 166,
    "borough": "Manhattan",
    "zone": "Morningside Heights",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 167,
    "borough": "Bronx",
    "zone": "Morrisania/Melrose",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 168,
    "borough": "Bronx",
    "zone": "Mott Haven/Port Morris",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 169,
    "borough": "Bronx",
    "zone": "Mount Hope",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 170,
    "borough": "Manhattan",
    "zone": "Murray Hill",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 171,
    "borough": "Queens",
    "zone": "Murray Hill-Queens",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 172,
    "borough": "Staten Island",
    "zone": "New Dorp/Midland Beach",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 173,
    "borough": "Queens",
    "zone": "North Corona",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 174,
    "borough": "Bronx",
    "zone": "Norwood",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 175,
    "borough": "Queens",
    "zone": "Oakland Gardens",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 176,
    "borough": "Staten Island",
    "zone": "Oakwood",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 177,
    "borough": "Brooklyn",
    "zone": "Ocean Hill",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 178,
    "borough": "Brooklyn",
    "zone": "Ocean Parkway South",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 179,
    "borough": "Queens",
    "zone": "Old Astoria",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 180,
    "borough": "Queens",
    "zone": "Ozone Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 181,
    "borough": "Brooklyn",
    "zone": "Park Slope",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 182,
    "borough": "Bronx",
    "zone": "Parkchester",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 183,
    "borough": "Bronx",
    "zone": "Pelham Bay",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 184,
    "borough": "Bronx",
    "zone": "Pelham Bay Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 185,
    "borough": "Bronx",
    "zone": "Pelham Parkway",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 186,
    "borough": "Manhattan",
    "zone": "Penn Station/Madison Sq West",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 187,
    "borough": "Staten Island",
    "zone": "Port Richmond",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 188,
    "borough": "Brooklyn",
    "zone": "Prospect-Lefferts Gardens",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 189,
    "borough": "Brooklyn",
    "zone": "Prospect Heights",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 190,
    "borough": "Brooklyn",
    "zone": "Prospect Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 191,
    "borough": "Queens",
    "zone": "Queens Village",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 192,
    "borough": "Queens",
    "zone": "Queensboro Hill",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 193,
    "borough": "Queens",
    "zone": "Queensbridge/Ravenswood",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 194,
    "borough": "Manhattan",
    "zone": "Randalls Island",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 195,
    "borough": "Brooklyn",
    "zone": "Red Hook",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 196,
    "borough": "Queens",
    "zone": "Rego Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 197,
    "borough": "Queens",
    "zone": "Richmond Hill",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 198,
    "borough": "Queens",
    "zone": "Ridgewood",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 199,
    "borough": "Bronx",
    "zone": "Rikers Island",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 200,
    "borough": "Bronx",
    "zone": "Riverdale/North Riverdale/Fieldston",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 201,
    "borough": "Queens",
    "zone": "Rockaway Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 202,
    "borough": "Manhattan",
    "zone": "Roosevelt Island",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 203,
    "borough": "Queens",
    "zone": "Rosedale",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 204,
    "borough": "Staten Island",
    "zone": "Rossville/Woodrow",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 205,
    "borough": "Queens",
    "zone": "Saint Albans",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 206,
    "borough": "Staten Island",
    "zone": "Saint George/New Brighton",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 207,
    "borough": "Queens",
    "zone": "Saint Michaels Cemetery/Woodside",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 208,
    "borough": "Bronx",
    "zone": "Schuylerville/Edgewater Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 209,
    "borough": "Manhattan",
    "zone": "Seaport",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 210,
    "borough": "Brooklyn",
    "zone": "Sheepshead Bay",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 211,
    "borough": "Manhattan",
    "zone": "SoHo",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 212,
    "borough": "Bronx",
    "zone": "Soundview/Bruckner",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 213,
    "borough": "Bronx",
    "zone": "Soundview/Castle Hill",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 214,
    "borough": "Staten Island",
    "zone": "South Beach/Dongan Hills",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 215,
    "borough": "Queens",
    "zone": "South Jamaica",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 216,
    "borough": "Queens",
    "zone": "South Ozone Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 217,
    "borough": "Brooklyn",
    "zone": "South Williamsburg",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 218,
    "borough": "Queens",
    "zone": "Springfield Gardens North",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 219,
    "borough": "Queens",
    "zone": "Springfield Gardens South",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 220,
    "borough": "Bronx",
    "zone": "Spuyten Duyvil/Kingsbridge",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 221,
    "borough": "Staten Island",
    "zone": "Stapleton",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 222,
    "borough": "Brooklyn",
    "zone": "Starrett City",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 223,
    "borough": "Queens",
    "zone": "Steinway",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 224,
    "borough": "Manhattan",
    "zone": "Stuy Town/Peter Cooper Village",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 225,
    "borough": "Brooklyn",
    "zone": "Stuyvesant Heights",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 226,
    "borough": "Queens",
    "zone": "Sunnyside",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 227,
    "borough": "Brooklyn",
    "zone": "Sunset Park East",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 228,
    "borough": "Brooklyn",
    "zone": "Sunset Park West",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 229,
    "borough": "Manhattan",
    "zone": "Sutton Place/Turtle Bay North",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 230,
    "borough": "Manhattan",
    "zone": "Times Sq/Theatre District",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 231,
    "borough": "Manhattan",
    "zone": "TriBeCa/Civic Center",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 232,
    "borough": "Manhattan",
    "zone": "Two Bridges/Seward Park",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 233,
    "borough": "Manhattan",
    "zone": "UN/Turtle Bay South",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 234,
    "borough": "Manhattan",
    "zone": "Union Sq",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 235,
    "borough": "Bronx",
    "zone": "University Heights/Morris Heights",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 236,
    "borough": "Manhattan",
    "zone": "Upper East Side North",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 237,
    "borough": "Manhattan",
    "zone": "Upper East Side South",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 238,
    "borough": "Manhattan",
    "zone": "Upper West Side North",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 239,
    "borough": "Manhattan",
    "zone": "Upper West Side South",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 240,
    "borough": "Bronx",
    "zone": "Van Cortlandt Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 241,
    "borough": "Bronx",
    "zone": "Van Cortlandt Village",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 242,
    "borough": "Bronx",
    "zone": "Van Nest/Morris Park",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 243,
    "borough": "Manhattan",
    "zone": "Washington Heights North",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 244,
    "borough": "Manhattan",
    "zone": "Washington Heights South",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 245,
    "borough": "Staten Island",
    "zone": "West Brighton",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 246,
    "borough": "Manhattan",
    "zone": "West Chelsea/Hudson Yards",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 247,
    "borough": "Bronx",
    "zone": "West Concourse",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 248,
    "borough": "Bronx",
    "zone": "West Farms/Bronx River",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 249,
    "borough": "Manhattan",
    "zone": "West Village",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 250,
    "borough": "Bronx",
    "zone": "Westchester Village/Unionport",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 251,
    "borough": "Staten Island",
    "zone": "Westerleigh",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 252,
    "borough": "Queens",
    "zone": "Whitestone",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 253,
    "borough": "Queens",
    "zone": "Willets Point",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 254,
    "borough": "Bronx",
    "zone": "Williamsbridge/Olinville",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 255,
    "borough": "Brooklyn",
    "zone": "Williamsburg (North Side)",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 256,
    "borough": "Brooklyn",
    "zone": "Williamsburg (South Side)",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 257,
    "borough": "Brooklyn",
    "zone": "Windsor Terrace",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 258,
    "borough": "Queens",
    "zone": "Woodhaven",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 259,
    "borough": "Bronx",
    "zone": "Woodlawn/Wakefield",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 260,
    "borough": "Queens",
    "zone": "Woodside",
    "service_zone": "Boro Zone"
  },
  {
    "_id": 261,
    "borough": "Manhattan",
    "zone": "World Trade Center",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 262,
    "borough": "Manhattan",
    "zone": "Yorkville East",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 263,
    "borough": "Manhattan",
    "zone": "Yorkville West",
    "service_zone": "Yellow Zone"
  },
  {
    "_id": 264,
    "borough": "Unknown",
    "zone": "NV",
    "service_zone": "N/A"
  },
  {
    "_id": 265,
    "borough": "Unknown",
    "zone": "NA",
    "service_zone": "N/A"
  }
])
