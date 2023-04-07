# **Receipt API Application**

This project is a Receipt API Application built using Spring Boot. The application calculates the receipt for a shopping cart, applying tax and coupons.

**`Prerequisites:`**

1. JDK 11 or later
2. Maven 3.6.x or later
3. An IDE of your choice (IntelliJ, Eclipse, etc.)

###### **`Project Setup`**
1. Clone/download the repository to your local machine.
2. Import the project into your IDE as a Maven project.
3. Configure your IDE to use JDK 11 or later as the project SDK.

**`Running the Application`**

1. Start the application by running the main method in the Application.java class from your IDE.
2. Alternatively, you can build and run the application using Maven:

bash

    2.1 cd /path/to/your/project (ex: cd IdeaProjects\grocery.demo)
    2.2 mvn clean install
    2.3 java -jar target/grocery.demo-0.0.1-SNAPSHOT.jar


**`API Endpoints`**

The application exposes the following RESTful API endpoints: make sure the application is running on 8080, if running on a different port replace the port number in the URL with the correct port

  1.`Create Receipt by sending any cart.json using post `

    URL: http://localhost:8080/api/receipts
    Method: POST
    Request Body: JSON payload with the shopping cart
    Response: Calculated receipt in JSON format
`   you can use tools like postman, select request type as POST, under Body, select raw and paste the valid cart.json file, select JSON from the dropdown and send the request.in the response body you would see receipt as output

  2. `Create Receipt from cart.json loaded in the resources folder(provided for demo)`

    URL: http://localhost:8080/api/receipts/generate-receipt
    Method: GET
    Response: Calculated receipt in JSON format based on the cart.json file located in the resources folder, no addional input needed from user

 
`**Testing the API**`

      You can test the API using tools like Postman or Curl. Alternatively, you can run test cases using JUnit and Mockito for unit and integration testing.

**`**Troubleshooting**`**

If you face any issues while setting up or running the project, please make sure you have the correct JDK and Maven versions installed. Additionally, ensure that your IDE is configured correctly with the project SDK and Maven settings.

If you still face issues, please email me at balamaddy03@gmail.com with details.