#Database
PostgresQL database
## Installation
* Start docker
* Open a command line
* Run: 
```bash
docker run --name pgsql -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres
```
## Start application
Use the 'dev' profile to run the application
## Testing
The integration tests start with the 'tests' profile which will start a h2 database
