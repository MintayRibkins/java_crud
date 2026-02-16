# Java CRUD (User)

Simple Spring Boot CRUD application with H2 in-memory database.

## User fields
- `name`
- `surname`
- `email`
- `address`

## Run

### Option 1: from IDE
Run `main` method in `src/main/java/Service/CrudApplication.java`.

### Option 2: Maven
```bash
mvn spring-boot:run
```

App port is configured in `src/main/resources/application.properties`:
- `http://localhost:9090`

## API

### Get users (with optional filters)
```bash
curl "http://localhost:9090/api/get_all_users"
curl "http://localhost:9090/api/get_all_users?name=John"
curl "http://localhost:9090/api/get_all_users?surname=Doe"
curl "http://localhost:9090/api/get_all_users?name=John&surname=Doe"
```

### Add user
```bash
curl -X POST "http://localhost:9090/api/add_user" \
  -d "name=John" \
  -d "surname=Doe" \
  -d "email=john@example.com" \
  -d "address=Main Street 1"
```

### Update user (current implementation updates `name` and `surname`)
```bash
curl -X PUT "http://localhost:9090/api/update_user" \
  -d "id=1" \
  -d "name=Johnny" \
  -d "surname=Doe" \
  -d "email=johnny@example.com" \
  -d "address=Main Street 2"
```

### Remove user
```bash
curl -X DELETE "http://localhost:9090/api/remove_user?id=1"
```

### Import users from CSV
CSV format:
```csv
name,surname,email,address
Anna,Stone,anna@example.com,Green Street 3
Bob,River,bob@example.com,Blue Street 5
```

Request:
```bash
curl -X POST "http://localhost:9090/api/import_users_csv" \
  -F "file=@/home/dmytro/IdeaProjects/CRUD/users.csv"
```

Important: endpoint expects `multipart/form-data`.

## Database

H2 database is initialized from:
- `src/main/resources/init.sql`

JDBC connection configuration:
- `src/main/java/db/DBUtils.java`
