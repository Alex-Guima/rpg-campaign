# RPG Campaign Tracker

A REST API for game masters to keep track of tabletop RPG campaigns. It stores:

- **Campaigns**: a title and a description.
- **Players**: the people playing at the table.
- **Character classes**: such as Wizard or Warrior, with a description.
- **Characters**: each belongs to a campaign, and is either
  - **non-playable** (NPCs run by the game master), or
  - **playable** (owned by a player and given a character class).

Built with [Quarkus](https://quarkus.io/) 3.34, Java 25, Hibernate ORM with Panache, and PostgreSQL.

## Project layout

The code follows a clean architecture split under `src/main/java/org/alex/guima/rpg/tracker/`:

| Package | Contents |
|---|---|
| `domain/model` | Domain objects: `Campaign`, `Player`, `CharacterClass`, `Character` and its two subtypes |
| `domain/repository` | Repository interfaces the use cases depend on |
| `domain/exception` | Not-found errors, returned as HTTP 404 |
| `usecase` | One class per operation, such as `CreatePlayableCharacterUseCase` |
| `infrastructure/persistence` | JPA entities, mappers and Panache-backed repository implementations |
| `infrastructure/web` | REST resources and request/response DTOs |

Use cases only see the domain interfaces, so their unit tests run against in-memory fakes in `src/test/.../usecase`.

## Requirements

- JDK 25
- Docker or Podman, for the PostgreSQL database started automatically in dev mode and tests

The Gradle wrapper (`./gradlew`) downloads Gradle itself, so you don't need to install it.

## Running in dev mode

```shell
./gradlew quarkusDev
```

Quarkus Dev Services starts a PostgreSQL container and creates the tables on startup. You don't need to configure anything.

- API: <http://localhost:8080>
- Dev UI: <http://localhost:8080/q/dev/>

Code changes reload live while dev mode is running.

## Running the tests

```shell
./gradlew test
```

The test suite has two parts:

- **Unit tests:** cover the use cases, using in-memory fakes.
- **`@QuarkusTest` classes:** call the REST API against a real PostgreSQL container. They need Docker or Podman running.

By default the tests listen on port 8081. If that port is taken, run them on a random free port:

```shell
QUARKUS_HTTP_TEST_PORT=0 ./gradlew test
```

## Building and running a package

```shell
./gradlew build
java -jar build/quarkus-app/quarkus-run.jar
```

Outside dev mode, Dev Services doesn't start a database, so you must point the app at your own PostgreSQL instance:

```shell
export QUARKUS_DATASOURCE_JDBC_URL=jdbc:postgresql://localhost:5432/rpg
export QUARKUS_DATASOURCE_USERNAME=rpg
export QUARKUS_DATASOURCE_PASSWORD=secret
java -jar build/quarkus-app/quarkus-run.jar
```

The packaged app doesn't create tables. Create the schema yourself, or let Hibernate create it by also setting `QUARKUS_HIBERNATE_ORM_SCHEMA_MANAGEMENT_STRATEGY=update`.

## API

All bodies are JSON. Invalid input returns `400` with a report of the failing fields. Unknown IDs return `404`.

| Method | Path | Description |
|---|---|---|
| `POST` | `/campaign` | Create a campaign |
| `GET` | `/campaign/{id}` | Get a campaign |
| `PUT` | `/campaign/{id}` | Update title and description |
| `POST` | `/player` | Create a player |
| `GET` | `/player` | List players |
| `GET` | `/player/{id}` | Get a player |
| `POST` | `/character-class` | Create a character class |
| `GET` | `/character-class` | List character classes |
| `GET` | `/character-class/{id}` | Get a character class |
| `POST` | `/character/non-playable` | Create an NPC in a campaign |
| `POST` | `/character/playable` | Create a player character in a campaign |
| `GET` | `/character?campaignId={id}` | List a campaign's characters |
| `GET` | `/character/{id}` | Get a character |
| `PUT` | `/character/{id}` | Update name and description |

Validation rules:

- **Titles and names:** required, at most 64 characters.
- **Descriptions:** optional, at most 255 characters.
- **Reference IDs** (`campaignId`, `playerId`, `characterClassId`): required.

### Example: create a playable character

This example uses [`jq`](https://jqlang.org/) to read the IDs from each response.

```shell
CAMPAIGN=$(curl -s -X POST localhost:8080/campaign -H 'Content-Type: application/json' \
  -d '{"title":"Lost Mine of Phandelver","description":"Starter adventure"}' | jq -r .id)
PLAYER=$(curl -s -X POST localhost:8080/player -H 'Content-Type: application/json' \
  -d '{"name":"Alex"}' | jq -r .id)
CLASS=$(curl -s -X POST localhost:8080/character-class -H 'Content-Type: application/json' \
  -d '{"name":"Wizard","description":"Casts arcane spells"}' | jq -r .id)

curl -s -X POST localhost:8080/character/playable -H 'Content-Type: application/json' \
  -d "{\"name\":\"Gale\",\"description\":\"Wizard of Waterdeep\",\"campaignId\":\"$CAMPAIGN\",\"playerId\":\"$PLAYER\",\"characterClassId\":\"$CLASS\"}"
```

Response:

```json
{"id":"…","type":"PLAYABLE","name":"Gale","description":"Wizard of Waterdeep","campaignId":"…","playerId":"…","characterClassId":"…"}
```

## Continuous integration

GitHub Actions (`.github/workflows/ci.yml`) runs `./gradlew build` on JDK 25 for:

- pushes to `main` and `dev`
- pull requests into `main`
