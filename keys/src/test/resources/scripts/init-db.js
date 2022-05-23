keysDb = db.getSiblingDB('api_keys')
testDb = db.getSiblingDB('api_keys_test')

// 123e4567-e89b-42d3-a456-556642440000
adminKey = {
    contact: "admin@mail.com",
    quota: 43690,
    key: "5d962bcc577343eff09091f902ca52d125ecaab8f3fe3cd2e92227dd916be1d3",
    used: 0,
    scope: 'ADMIN'
}

// 123ee89b-4567-a456-42d3-440000556642
userKey = {
    contact: "user@mail.com",
    quota: 10,
    key: "316ead37f44194a1fba644f22db6e38734716e33bfca7ceec858fc80498abb77",
    used: 0,
    scope: 'CONSUMER'
}

keysDb.keys.createIndex({"key": 1}, {unique: true})
keysDb.keys.insertOne(adminKey)

testDb.keys.createIndex({"key": 1}, {unique: true})
testDb.keys.insertOne(adminKey)
testDb.keys.insertOne(userKey)
