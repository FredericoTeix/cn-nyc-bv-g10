db.createUser(
  {
    user: "rhythmfishing",
    pwd: "Jw8HsxQtQHFPzDQj7W8b",
    roles: [
      {
        role: "readWrite",
        db: "trips-db"
      }
    ]
  }
)

db = db.getSiblingDB('trips-db');

db.createCollection('trips');
db.trips.createIndex( { "pickup_datetime": 1 , "dropoff_datetime": 1 } )
