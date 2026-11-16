# Money Manager

A JavaFX application developed by Levi Rukundo for CS 3211, Fall 2026.
Users can log in, record income and expenses, and view a transaction log.

## Features

- Login with feedback for incorrect credentials.
- Add income with an amount, date, and source or description.
- Add expenses with an amount, date, description, and category.
- Validate required fields and reject invalid or nonpositive amounts.
- Cancel entry without saving.
- View recorded transactions with dates, types, categories,
  descriptions, and signed amounts.
- Display and update total income.
- Show an empty-log message when no transactions exist.
- Display retrieval-error feedback and allow retry through View Log.

## Login Credentials

| Username | Password | Role |
| --- | --- | --- |
| user | Money123! | User |

The User role can view the log and add income and expenses.
Credentials are hardcoded for this educational demonstration.

## Repository Structure

- `code/MoneyManager/` — Maven project imported and developed in Eclipse,
  including production code, tests, FXML, and Checkstyle configuration.
- `documentation/` — product backlog, use-case diagram, HW4 scenarios,
  UI wireframe, and class diagram.
- `.gitignore` — excludes generated Java and Maven build files.

The project retains the provided sample's Java package prefix:
`edu.westga.comp4420.grocery_list`.

## Requirements

- Eclipse IDE with Maven support.
- JDK 17 or newer, compatible with JavaFX 21.
- Internet access for the first Maven dependency download.

The project uses JavaFX 21, JUnit 5, Checkstyle, and JaCoCo.

## Import into Eclipse

1. Clone or download this repository.
2. Select File > Import > Maven > Existing Maven Projects.
3. Select `code/MoneyManager` as the root directory.
4. Select its `pom.xml` and click Finish.
5. Allow Maven to download dependencies.

## Run the Application

Right-click the project and select Run As > Maven build.

Enter this in Goals:

```text
javafx:run
```

Click Run, then log in using the credentials above.

## Run Tests and Quality Checks

Use Run As > Maven build with:

```text
clean test jacoco:check@jacoco-check
```

This runs Checkstyle, the JUnit tests, and the configured 100% line-coverage
check for the model code. Main/startup and codebehind packages are excluded
by the sample-based coverage configuration.

The coverage report is generated at:

```text
code/MoneyManager/target/site/jacoco/index.html
```

## Design and Testing Notes

Transaction validation and income calculations are implemented in the model
and tested with JUnit. JavaFX codebehind connects the model to the interface.

The class wireframe includes broader MVP features. This implementation
focuses on View Log, Add Income, and Add Expenses, with supporting login.

## Limitations

- Transactions are stored in memory and reset when the application closes.
- Recurring income, expense-breakdown charts, category-detail views,
  projections, and budgeting are not implemented.
- Only one demonstration account and role are provided.
- Log retrieval-error handling is defensive. The current in-memory store
  does not normally produce retrieval failures, and that failure path
  has not been demonstrated through the interface.
