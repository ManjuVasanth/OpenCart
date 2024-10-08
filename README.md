# OpenCart Test Automation Framework

This repository contains a comprehensive **Test Automation Framework** for the **OpenCart** e-commerce platform. The framework utilizes **Page Object Model (POM)** for better code organization and **Selenium WebDriver** for interacting with the web UI. It integrates **Cucumber** for **BDD (Behavior Driven Development)**, **TestNG** as the test runner, and **Maven** for build automation and dependency management.

## Table of Contents

- [Project Overview](#project-overview)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Setup and Installation](#setup-and-installation)
- [How to Run Tests](#how-to-run-tests)
- [Test Approach](#test-approach)
  - [POM (Page Object Model)](#pom-page-object-model)
  - [BDD (Behavior Driven Development)](#bdd-behavior-driven-development)
- [Reporting](#reporting)
- [Contributing](#contributing)
- [License](#license)

---

## Project Overview

This **OpenCart Test Automation Framework** is designed to validate key functionalities of the OpenCart e-commerce application. The framework is built on **POM** principles for ease of maintenance, scalability, and reusability of code. It follows **BDD** principles with **Cucumber** to write human-readable feature files. **TestNG** is used as the test execution framework, supporting parallel execution, grouping, and custom configurations.

Key features automated include:

- User Registration
- Product Search
- Shopping Cart Management
- Checkout Process
- Admin Panel functionalities

## Technologies Used

- **Java**: Programming language for the automation code.
- **Selenium WebDriver**: Browser automation tool to simulate user actions.
- **Cucumber**: BDD tool for defining user scenarios.
- **TestNG**: Test runner framework.
- **Maven**: For dependency management and build automation.
- **Extent Reports**: For generating detailed test reports.
- **WebDriverManager**: For automatic driver setup for different browsers.

## Project Structure

```
src
│
├── main
│   └── java
│       └── pageObjects        # Page classes for POM
│       └── utilities          # Helper classes and utilities
│
├── test
│   └── java
│       └── stepDefinitions    # Cucumber step definitions
│       └── features           # BDD feature files
│       └── testRunners        # Test runners for execution
│
└── README.md                  # Project documentation
```

## Setup and Installation

### Prerequisites

Ensure you have the following installed before setting up the project:

- **Java 8 or above**
- **Maven**
- **Chrome/Firefox WebDriver** installed or **WebDriverManager** setup

### Installation Steps

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/ManjuVasanth/OpenCart.git
   ```

2. Navigate to the project directory:

   ```bash
   cd OpenCart
   ```

3. Install the required dependencies using Maven:

   ```bash
   mvn clean install
   ```

## How to Run Tests

### Run All Tests

You can execute all the tests in the project using Maven:

```bash
mvn test
```

### Running Specific Tests

To run tests tagged with a specific **Cucumber** tag, use:

```bash
mvn test -Dcucumber.options="--tags @smoke"
```

This will execute only the tests tagged with `@smoke`.

### TestNG Test Execution

You can also run tests defined in a **TestNG XML** file by executing:

```bash
mvn test -DsuiteXmlFile=testng.xml
```

## Test Approach

### POM (Page Object Model)

The framework follows **Page Object Model (POM)** to create reusable and maintainable web interaction logic. Each page of the OpenCart application is represented by a corresponding class, and interactions with the elements (such as clicking buttons, entering text) are encapsulated in methods.

Example pages include:

- **LoginPage**: Handles user login actions.
- **HomePage**: Interacts with the homepage and product search functionality.
- **ProductPage**: Manages product details and adding items to the cart.
- **CheckoutPage**: Handles the checkout process.

### BDD (Behavior Driven Development)

The framework uses **Cucumber** to follow the **BDD** approach, where test scenarios are written in a human-readable format (Gherkin). These feature files serve as documentation and executable specifications for the behavior of the application.

Example feature file:

```gherkin
Feature: Product Search

  Scenario: Search for a product and add to cart
    Given User is on the OpenCart home page
    When User searches for "Laptop"
    And User selects a product from the search results
    Then The product should be added to the cart
```

Each step in the feature file has corresponding code (step definitions) written in Java that performs the actual operations on the web application.

## Reporting

The project is integrated with **Extent Reports** to generate detailed reports of test execution. The reports include:

- Test status (Pass/Fail)
- Test execution time
- Screenshots for failed tests
- Detailed logs for each test step

After running the tests, reports can be found in the `test-output` directory.

## Contributing

Contributions to improve the framework are welcome. Here’s how you can contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit them (`git commit -m "Add new feature"`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a Pull Request to the `main` branch.

## License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for more information.

---

Feel free to modify this `README.md` based on your project's specific requirements.
