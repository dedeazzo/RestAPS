
----------------------------------------

Part 1: Design

The API Specification using Swagger found in the file "Swagger_API_specification.yaml" defines two endpoints:

 1. Retrieve Accounts by Customer ID:
   - Endpoint: GET /api/accounts
   - Description: Retrieves a list of account summaries belonging to a customer by customer ID (account number, account name, account type, and currency).
   - Request Parameters:
     - customerId: The ID of the customer whose accounts to retrieve. (Query Parameter)
   - Responses:
     - 200 OK: Returns a list of account summaries.
     - 404 Not Found: Customer not found.
 2. Retrieve Account Details by Account Number:
   - Endpoint: GET /api/accounts/{accountNumber}
   - Description: Retrieves the details of an account by its account number (account number, account name, account type, customer id, book balance, available balance, blocked, closed, currency, iban, opening date, and last modified date).
   - Path Parameters:
      - accountNumber: The account number of the account to retrieve. (Path Parameter)
   - Responses:
     - 200 OK: Returns the details of the account.
     - 404 Not Found: Account not found.

Sample cURL Requests (in terminal):
 1. Retrieve Accounts by Customer ID:
    
        % curl -u {USERNAME}:{PASSWORD} 'http://localhost:8080/api/accounts?customerId={CUSTOMER_ID}&apiKey={API_KEY}'

        e.g. % curl -u root:1234 'http://localhost:8080/api/accounts?customerId=1&apiKey=api-key1'

 2. Retrieve Account Details by Account Number:

        % curl -u {USERNAME}:{PASSWORD} 'http://localhost:8080/api/accounts/{ACCOUNT_ID}}&apiKey={API_KEY}'

        e.g. % curl -u root:1234 'http://localhost:8080/api/accounts/1001?apiKey=api-key1'

----------------------------------------

Part 2: Implementation

Instructions to Compile and Run:
 1. Clone the repository.
 2. Navigate to the project directory.
 3. Build and run the Docker containers:

        % docker-compose up --build

Notes:
 - The implementation uses Spring Boot for building REST APIs.
 - Data persistence is managed using Spring Data JPA with a MySQL database. The MySQL database is configured using Docker.
 - The API keys for authentication and rate limiting are provided as query parameters (apiKey) in the cURL requests. This, alongside the APIs only allowing data to be read and not written or deleted makes the API marginally more reliable, and malicious data alterations less likely.
 - The Docker setup includes two containers: one for the API application and one for the MySQL database.
 - Ensure that Docker is installed and running on your machine before running the Docker setup.

Sample cURL Requests: Same as in Part 1.

----------------------------------------

----------------------------------------

Appendix: Further Technical Debugging Notes

Running MySQL in terminal without Docker and creating database:

    % /usr/local/mysql/bin/mysql -u root -p

    > CREATE DATABASE db_aps;
    > CREATE USER 'db_username'@'localhost' IDENTIFIED BY 'db_password';
    > GRANT ALL PRIVILEGES ON db_aps.* TO 'db_username'@'localhost';

    > USE db_aps
    > ALTER TABLE accounts MODIFY COLUMN id INT DEFAULT 1001;
    > ALTER TABLE accounts MODIFY COLUMN id INT AUTO_INCREMENT;
    > INSERT INTO accounts (account_number, account_name, account_type, customer_id, book_balance, available_balance, blocked, closed, currency, iban, opening_date, last_modified_date) VALUES (1001, 'Savings', 'Savings Account', 1, 1000.0, 900.0, FALSE, FALSE, 'USD', 'MT84MALT011000087654MTLCAST001S', '2024-01-01', '2024-01-01');

Generating docker (in project directory):

    % mvn clean package spring-boot:repackage
    % docker-compose build
    % docker-compose up

To kill port processes (Apple):
1. Database:

       % sudo lsof -i :3306
       % sudo kill {PID}

2. API:

       % lsof -i :8080
       % kill {PID}

In case of the API docker (restaps-api-1) being unable to connect to the database docker (restaps-mysql-1) ensure that the ip inside the application.properties file spring.datasource.url property is correctly pointing towards the Database Docker.
 - Example property in application.properties file:

       spring.datasource.url=jdbc:mysql://<container_ip_or_hostname>:3306/db_aps 

To find the IP address or hostname of your Docker container do the following in terminal:
1. Inspect the MySQL Docker ('container_id_or_name' in this case is 'restaps-mysql-1')

       % docker inspect <container_id_or_name>

2. Look for the "IPAddress" field in the output to find the IP address.

----------------------------------------