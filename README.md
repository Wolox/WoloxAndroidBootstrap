# Wolox Android Bootstrap
This is a basic template to kickoff Android projects that includes some common usage libraries and a basic build variant configuration with two flavors for stage and production.

## Usage
1. Clone this repository
2. Change the **applicationId** in the *app/build.gradle* file
3. Change the ***WoloxApplication*** class name
4. Setup the API endpoints (if neccesary) in the *Configuration*/*BaseConfiguration* classes
5. Change the launcher icons under *main*/*stage* directories
6. Setup the production keystore credentials: run `keystore.sh` or create *app/keystore.gradle* file manually:
```gradle
ext.release_keystore=file('...')
ext.key_alias='...'
ext.key_password='...'
ext.store_password='...'
```
