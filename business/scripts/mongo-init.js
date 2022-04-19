db = new Mongo().getDB("businesses");
db.createCollection("businesses", {capped: false});

let id = db.businesses.insert(
    {
        "name": "Shopping",
        "city": "New York",
        "address": "Some Address nº 12",
        "location": {
            "type": "Point",
            "coordinates": [70.12314, 40.123123]
        }
    }
);

db.businesses.createIndex({location: "2dsphere"})

db.businesses.find().forEach( place =>
    db.businesses.deleteOne(place)
)