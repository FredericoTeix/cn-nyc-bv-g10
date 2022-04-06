keysDb = db.getSiblingDB('api_keys')
testDb = db.getSiblingDB('api_keys_test')

adminKey = {
    contact: "admin@mail.com",
    quota: 43690,
    key: "5d962bcc577343eff09091f902ca52d125ecaab8f3fe3cd2e92227dd916be1d3",
    used: 0,
    scope: 'ADMIN'
}

keysDb.keys.createIndex({"key": 1})
keysDb.keys.insertOne(adminKey)

testDb.keys.createIndex({"key": 1})
testDb.keys.insertOne(adminKey)
