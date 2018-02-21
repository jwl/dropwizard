## Dropwizard basic CRUD (core System)

A simple project to get to know Dropwizard framework deeper.
Dropwizard is a framework with out-of-the-box tools which are most commonly used in Java, deploy-ready.

There are a couple of things we need to understand,

- Resources are what are commonly known as controllers, they specify an entrypoint between the world and the applications.
- DAO are objects which interact with the persistence unit of choice, also known as services or data layers.
- Domain is the domain entities of your problem which have a mapping into your database, they represent PJO that are being persisted.


### How does Dropwizard work ?

The main thing we need to understand about Dropwizard is the main Application file, in our case `Server.java`, which does most of the work. This tasks include:

- Load the specified configuration.
- Configure endpoints, resources, databases, cache, etc.
- Set health checks.
- Most importantly, run the application.

In order for the application to work, it needs to map a configuration file to a class which will be loaded from this `.yml` file. In our case, just the database is needed. An example config file would be:

```yml
database:
  driverClass: com.mysql.jdbc.Driver
  user: <yourUser>
  password: <yourPassword>
  url: jdbc:mysql://<host>:<port>/<databaseName>
  maxWaitForConnection: 1s
  validationQuery: "/* MyService Health Check */ SELECT 1"
  minSize: 8
  maxSize: 32
  checkConnectionWhileIdle: false
  evictionInterval: 10s
  minIdleTime: 1 minute
  properties:
    hibernate.dialect: org.hibernate.dialect.H2Dialect
    hibernate.show_sql: true
    hibernate.generate_statistics: false
    hibernate.hbm2ddl.auto: validate # validates schema when service is started
```


### How to run it.

To run this specific project correctly, there are a few commands we need to specify.
- `mvn package` in order to create our `.jar` file with every compiled library.
- `java -jar target/<jarGeneratedName> db migrate <pathToConfigFile>` which will migrate your database, database needs to be created though. Migrations can be found in `src/main/resources/*`.
- `java -jar target/<jarGeneratedName> server <pathToConfigFile>` will run your app.

After that, there is an executable you can run just by `./run.sh`.

### Usage

Hit any of the crud endpoints with `/users`.
Made with :heart:
