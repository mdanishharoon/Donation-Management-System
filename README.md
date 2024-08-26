

# CareShare Donation Management System

This repository contains the rationale document and source code for the CareShare Donation Management System, a digital solution developed to streamline donation tracking and promote donations effectively.

## Problem Statement

CareShare, a non-profit organization, faces challenges with its current paper-based donation management system:
- **Reliability Issues:** Human errors lead to inaccurate donation records.
- **High Operational Costs:** Hiring staff for manual logbook maintenance is expensive.
- **Inventory Management Problems:** Inaccurate stock tracking leads to shortages and inefficiencies.
- **Low Donation Levels:** Recent trends show a decline in donations.

## Proposed Solution

To address these issues, we proposed an all-digital donation management system:
- **Technology Stack:** Backend developed in Java using Eclipse IDE, leveraging MySQL for database management.
- **Features:** 
  - Separate interfaces for donors and receivers.
  - Real-time inventory updates and donor leaderboards to encourage donations.
  - JavaSwing for user-friendly GUI development.
  - JDBC API for seamless MySQL database integration.

## Success Criteria

To meet client requirements, the system is designed with the following success criteria:
- **Design:** Inbuilt validation checks and error handling for data integrity.
- **Donors:** 
  - Account management (login, sign-up).
  - Donation cost calculation and accurate logging.
  - Top donor leaderboard display.
  - Real-time availability updates for items.
- **Receivers:** 
  - Unique CNIC-based account registration.
  - Login using CNIC and username.
  - Check available items and print donation receipts.

## Installation and Usage

1. Clone the repository:
   ```
   git clone <repository_url>
   ```
2. Open the project in Eclipse IDE.
3. Set up MySQL database and configure JDBC connection details in `config.properties`.
4. Run the application.

For detailed instructions on setting up the project and using the application, refer to the [Installation Guide](installation.md).

## Contributing

Contributions are welcome! Please check the [Contribution Guidelines](CONTRIBUTING.md) for details.

## License

This project is licensed under the [MIT License](LICENSE).

---

This README provides a high-level overview of the project, outlining its purpose, features, installation instructions, and how to contribute. Adjustments can be made based on specific project requirements or additional sections needed.
