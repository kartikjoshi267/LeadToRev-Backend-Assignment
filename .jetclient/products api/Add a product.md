```toml
name = 'Add a product'
method = 'POST'
url = '{{url}}/'
sortWeight = 1000000
id = '9230aff3-65ce-4146-9317-9fe0698b3d96'

[body]
type = 'JSON'
raw = '''
{
  "name": "Wireless Headphones",
  "description": "High-quality wireless headphones with noise-cancellation and long battery life.",
  "price": 299,
  "categories": ["Electronics", "Audio", "Gadgets"],
  "attributes": {
    "brand": "TechSound",
    "color": "Black",
    "batteryLife": "30 hours",
    "connectivity": "Bluetooth 5.2"
  },
  "availability": {
    "inStock": true,
    "quantity": 150
  },
  "ratings": [
    {
      "userId": "user123",
      "rating": 5,
      "comment": "Excellent sound quality and very comfortable to wear!"
    },
    {
      "userId": "user456",
      "rating": 4,
      "comment": "Good value for money, but could use better noise cancellation."
    },
    {
      "userId": "user789",
      "rating": 3
    }
  ]
}
'''
```
