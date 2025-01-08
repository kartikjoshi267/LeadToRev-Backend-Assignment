# Product Catalogue Backend

This is the backend REST API for product catalogue application. It is built using SpringBoot with MongoDB as the
database.

## Models used

Models used in the application are:

### 1. User (Sample JSON)

```json
{
  "id": "677dd2cdeceb1c7b7e002783",
  "email": "test@test.com",
  "password": "test"
}
```

### 2. Product (Sample JSON)

```json
{
  "id": "677dfb5372730232d0421352",
  "name": "Wireless Mouse",
  "description": "A high-quality wireless mouse with ergonomic design.",
  "price": 29.99,
  "categories": [
    "Electronics",
    "Accessories"
  ],
  "attributes": {
    "Brand": "Logitech",
    "Color": "Black",
    "Connectivity": "Wireless"
  },
  "availability": {
    "inStock": true,
    "quantity": 100
  },
  "ratings": [
    {
      "userId": "677dd2cdeceb1c7b7e002783",
      "rating": 4.3,
      "comment": "Good ear phones"
    }
  ]
}
```

## How to run this on local machine

1. You should have JDK 21 and IntelliJ IDEA installed on your machine.
2. Build the maven dependencies.
3. Create a .env file at the same level of pom.xml with the following data

```
MONGO_URI=your-mongo-uri
PEPPER_SECRET=anySecret
JWT_SECRET=yourSecretKeyyourSecretKeyyourSecretKeyyourSecretKeyyourSecretKey
```

4. Run the application.
5. The application server will be available at [http://localhost:8080](http://localhost:8080).

## API Docs

### Test API

#### 1. Test connection ([http://localhost:8080](http://localhost:8080))

    Method: GET
    Response Body:
        200: Server is running

### User API

#### 1. Signup User (/users/signup)

    Method: POST
    Request Body:
    {
        "email": "test@test.com"
        "password": "test"
    }
    Response Body:
        200: User created successfully
        400: User already exists
        500: User Creation Failed

#### 2. Login User (/users/login)

    Method: POST
    Request Body:
    {
        "email": "test@test.com"
        "password": "test"
    }
    Response Body:
        200: User logged in successfully
            Cookie (token) set
        400: Invalid Credentials
        500: Login Failed

#### 3. Get User (/users/)

    Method: GET
    Cookies:
        token (required)
    Response Body:
        200:
            {
                "email": "test@test.com",
                "id": "677dd2cdeceb1c7b7e002783"
            }
        400: User not found
        500: Failed to fetch user

### Products API

#### 1. Get all products (/products/)

    Method: GET
    Response Body:
        200:
            [
                {
                    "id": "677dfb5372730232d0421352",
                    "name": "Wireless Headphones edited",
                    "description": "A high-quality wireless mouse with ergonomic design.",
                    "price": 29.99,
                    "categories": [
                        "Electronics",
                        "Accessories"
                    ],
                    "attributes": {
                        "Brand": "Logitech",
                        "Color": "Black",
                        "Connectivity": "Wireless"
                    },
                    "availability": {
                        "inStock": true,
                        "quantity": 100
                    },
                    "ratings": [
                    {
                        "userId": "677dd2cdeceb1c7b7e002783",
                        "rating": 4.3,
                        "comment": "Good ear phones"
                    }]
                },
                {
                    "id": "677dfb6c24e1173117d500cf",
                    "name": "Wireless Mouse",
                    "description": "A high-quality wireless mouse with ergonomic design.",
                    "price": 29.99,
                    "categories": [
                        "Electronics",
                        "Accessories"
                    ],
                    "attributes": {
                        "Brand": "Logitech",
                        "Color": "Black",
                        "Connectivity": "Wireless"
                    },
                    "availability": {
                        "inStock": true,
                        "quantity": 100
                    },
                    "ratings": []
                }
            ]

#### 2. Get a product (/products/{id})

    Method: GET
    Response Body:
        200:
            {
                "id": "677dfb5372730232d0421352",
                "name": "Wireless Headphones",
                "description": "A high-quality wireless mouse with ergonomic design.",
                "price": 29.99,
                "categories": [
                    "Electronics",
                    "Accessories"
                ],
                "attributes": {
                    "Brand": "Logitech",
                    "Color": "Black",
                    "Connectivity": "Wireless"
                },
                "availability": {
                    "inStock": true,
                    "quantity": 100
                },
                "ratings": [
                {
                    "userId": "677dd2cdeceb1c7b7e002783",
                    "rating": 4.3,
                    "comment": "Good ear phones"
                }]
            }

#### 3. Add a product (/products/)

    Method: POST
    Request Body:
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
            "ratings": []
        }
    Response Body:
        200: Product successfully created
        500: Failed to create product

#### 4. Update a product (/products/{id})
    
    Method: PUT
    Request Body:
        {
            "name": "Wireless Headphones edited"
        }
    Response Body:
        200:
            {
                "id": "677dfb5372730232d0421352"
                "name": "Wireless Headphones edited",
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
                "ratings": []
            }
        400: Product not found
        500: Failed to update product

#### 5. Delete a product (/products/{id})
    
    Method: DELETE
    Response Body:
        200: Product deleted successfully
        500: Failed to delete product

#### 6. Rate a product (/products/{id})

    Method: PUT
    Cookies:
        token (required)
    Request Body:
        {
            "rating": 3.4,
            "comment": "some comment"
        }
    Response Body:
        200:
            {
                "id": "677dfb5372730232d0421352"
                "name": "Wireless Headphones edited",
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
                        "userId": "677dd2cdeceb1c7b7e002783"
                        "rating": 3.4,
                        "comment": "some comment"
                    }
                ]
            }
        400: Product not found
        500: Rating the product failed