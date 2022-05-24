keysDb = db.getSiblingDB('business')
testDb = db.getSiblingDB('business_test')

let colName = "business"
let dbs = [keysDb, testDb]

dbs.forEach(db => {
    db.createCollection(colName, {capped: false});
    db[colName].insert(
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
    
    db[colName].createIndex({location: "2dsphere"})
    
    db[colName].find().forEach( place =>
        db.businesses.deleteOne(place)
    )
})