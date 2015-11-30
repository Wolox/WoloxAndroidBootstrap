# Wolox Android Bootstrap
This is a basic template to kickoff Android projects that includes some common usage libraries and a basic build variant configuration with two flavors for stage and production.

## Usage
1. Clone this repository
2. Rename the "WoloxAndroidBootstrap" directory to match your project name
3. Change the **applicationId** in the *app/build.gradle* file
4. Change the ***WoloxApplication*** class name
5. Setup the API endpoints (if neccesary) in the *Configuration*/*BaseConfiguration* classes
6. Change the launcher icons under *main*/*stage* directories
7. Setup the production keystore credentials: create *app/keystore.gradle* file:
```gradle
ext.key_alias='...'
ext.key_password='...'
ext.store_password='...'
```