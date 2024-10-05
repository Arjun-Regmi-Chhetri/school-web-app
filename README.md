# School - Spring 2024

School portal


## Introduction

This project is a school portal application built using Spring Boot. It provides RESTful APIs for managing contacts within the school system.

## Technologies

- Java
- Spring Boot
- Maven
- SQL
- JavaScript

## Setup

1. Clone the repository:
    ```sh
    git clone https://github.com/Arjun-Regmi-Chhetri/school.git
    cd school
    ```

2. Build the project using Maven:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn spring-boot:run
    ```
## 🚀 Features

**User Account Management**
- Login/Signup: 🚪 Users can create an account or log in to an existing one.
- Update Profile/Password: 🔐 Users can update their profile information and change their passwords.

**Contact Management**
- Save Contact: 💾 Users can save contact information.
- Delete Contact: 🗑️ Users can delete contact information.
- Close Contact: 🔒 Users can close contact information.
- Update Contact: ✏️ Users can update contact information.

**API Endpoints**
- RESTful APIs: 🌐 Provides endpoints for managing contacts within the school system.

**Admin Features**
- Dashboard: 🖥️ Admins have access to a dedicated dashboard for managing contacts and user data.





![School](https://raw.githubusercontent.com/Arjun-Regmi-Chhetri/image/main/admin.png)

<table>

  <tr>
    <td><img src="https://raw.githubusercontent.com/Arjun-Regmi-Chhetri/schoo-image/refs/heads/main/dark%20enable.png" /> </td>
    <td><img src="https://raw.githubusercontent.com/Arjun-Regmi-Chhetri/schoo-image/refs/heads/main/home%20page.png" /> </td>
  </tr>
  <tr>
    <td><img src="https://raw.githubusercontent.com/Arjun-Regmi-Chhetri/schoo-image/refs/heads/main/student%20dashboard.png" /> </td>
    <td><img src="https://raw.githubusercontent.com/Arjun-Regmi-Chhetri/schoo-image/refs/heads/main/login%20page.png" /> </td>
  </tr>
</table>

## Usage

Once the application is running, you can access the APIs using a tool like Postman or cURL.

## API Endpoints Example

### Save Contact

- **URL:** `/saveContact`
- **Method:** `POST`
- **Request Body:**
    ```json
    {
        "contactId": "1",
        "name": "John Doe",
        "email": "john.doe@example.com",
        "message": "Hello, this is a test message."
    }
    ```
- **Response:**
    ```json
    {
        "statusCode": "200",
        "statusMsg": "Message saved successfully"
    }
    ```


## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any improvements.

## License


This project is licensed under the MIT [License](./LICENSE).
.
