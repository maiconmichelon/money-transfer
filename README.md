### CREATE ACCOUNT

`curl -X POST \
  http://localhost:4567/api/account \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 235c389a-5a24-47e5-b80a-0e75097f0e9b' \
  -d '{
	"initialBalance": 100
}'
`

### GET BALANCE

`curl -X GET \
  http://localhost:4567/api/206b4264-7323-418e-9990-e3a83e940f1a/balance \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 197dc125-dcc2-4ac6-8d93-dd7bb3d56b5e' \
  -d '{
	"initialBalance": 123
}'
`
### CREATE TRANSACTION

`curl -X POST \
  http://localhost:4567/api/transaction \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 1665b1be-d858-4570-879f-b154a7176a60' \
  -d '{
	"sourceAccountId": "206b4264-7323-418e-9990-e3a83e940f1a",
	"targetAccountId": "6edefee5-2618-459b-a593-1a5b466e8590",
	"value": 12
}'`
