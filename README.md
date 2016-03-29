# CREATE A NEW CARD
curl -i -X POST -H "Content-Type: application/json" -d '{"embossingLine1":"CHRISTIAN MUELLER", "cardNumber":"4567890123456789", "expirationDate":"07/2021", "cvv":"123"}' http://localhost:8080/techforum-2016-arquillian/v1/cards

# GET ALL CARDS
curl -i -X GET -H "Accept: application/json" http://localhost:8080/techforum-2016-arquillian/v1/cards

# GET A SPECIFIC CARD
curl -i -X GET -H "Accept: application/json" http://localhost:8080/techforum-2016-arquillian/v1/cards/{id}

# UPDATE A SPECIFIC CARD
curl -i -X PUT -H "Content-Type: application/json" -d '{"embossingLine1":"CHRISTIAN MUELLER", "cardNumber":"4567890123456789", "expirationDate":"12/2022", "cvv":"123"}' http://localhost:8080/techforum-2016-arquillian/v1/cards/{id}

# DELETE A CARD
curl -i -X DELETE http://localhost:8080/techforum-2016-arquillian/v1/cards/{id}