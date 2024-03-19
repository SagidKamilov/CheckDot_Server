[![Build Status](https://travis-ci.com/coma123/Spring-Boot-Blog-REST-API.svg?branch=development)](https://travis-ci.com/coma123/Spring-Boot-Blog-REST-API) [![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=coma123_Spring-Boot-Blog-REST-API&metric=alert_status)](https://sonarcloud.io/dashboard?id=coma123_Spring-Boot-Blog-REST-API) [![CII Best Practices](https://bestpractices.coreinfrastructure.org/projects/3706/badge)](https://bestpractices.coreinfrastructure.org/projects/3706)

# Spring Boot, PostgreSQL JPA, Rest API

Серверное приложение + API, разработанное на Spring Boot.
Это backend часть для системы фиксирования деффектов городской среды.

## Шаги настройки

**1. Склонировать репозиторий**

```bash
git clone https://github.com/coma123/Spring-Boot-Blog-REST-API.git
```

**2. Изменить переменные среды для вашего пользования**
```bash
Откройте Dockerfile и замените переменные среды, указанные в Dockerfile на ваши значениея
```

**3. Запустите сборку приложения
```bash
docker compose up/docker-compose up
```

**4. Ваше приложение будет собрано. Docker запустит контейнеры с приложением и базой.
Для более комфортной работы я советую установить плагин docker в вашу среду разработки. Так вы сможете отслеживать повдеение и легко управлять docker контейнерами 

приложение будет доступно по адресу:
```bash
http://localhost:8080
```

## Об эндпоинтах Rest APIs

В приложении определены следующие CRUD APIs.

### Auth

| Метод  | Url | Описание | Пример валидного тела запроса | Пример тела ответа | 
| ------ | --- | ---------- | --------------------------- | ------------------ |
| POST   | /api/v1/user/singup | Регистрация пользователя | [JSON](#signup) | |
| POST   | /api/v1/user/signin | Авторизация пользователя | [JSON](#signin) | |

### User

| Метод | Url | Описание | Пример валидного тела запроса | Пример тела ответа |
| ------ | --- | ----------- | ------------------------- | ------------------ |
| GET    | /api/v1/user/all | Получить список всех пользователей | | |
| DELETE | /api/v1/user/{userId} | Удалить пользователя | | |

### Claim

| Метод | Url | Описание | Пример валидного тела запроса | Пример тела ответа |
| ------ | --- | ----------- | ------------------------- | ------------------ |
| GET    | /api/v1/claim/all | Получить отсортированный по рейтингу список всех претензий | | |
| GET    | /api/v1/claim/{claimId} | Получить претензию | | |
| GET    | /api/v1/claim/{userId}/all | Получить список всех претензий пользователя | | |
| POST   | /api/v1/claim/{userId} | Создать претензию пользователя | [JSON](#claimcreate) | |
| PATCH  | /api/v1/claim/{claimId}/uprating | Поднять рейтинг претензии | | |
| PATCH  | /api/v1/claim/{claimId}/downrating | Понизить рейтинг претензии | | |
| PUT    | /api/v1/claim/{claimId} | Обновить претензию | [JSON](#claimupdate) | |
| DELETE | /api/v1/claim/{claimId} | Удалить претензию | | |

##### <a id="signup">Sign Up -> /api/v1/user/singup</a>
```json
{
    "name": "Имя пользователя",
    "password": "ПаРоЛьПоЛьЗоВаТеЛя"
}
```

##### <a id="signin">Sign In -> /api/v1/user/signin</a>
```json
{
    "name": "Имя пользователя",
    "password": "ПаРоЛьПоЛьЗоВаТеЛя"
}
```

##### <a id="claimcreate">Создание претензии -> /api/v1/claim/{userId}</a>
```json
{
    "heading":"Заголовок претензии",
    "description":"Описание претензии",
    "address": "адресс расположения объекта для фиксации",
    "path_image":"url к изображению",
    "rating": 0
}
```

##### <a id="claimupdate">Обновление претензии -> /api/v1/claim/{claimId}</a>
```json
{
    "heading":"Заголовок претензии", | Option
    "description":"Описание претензии", | Option
    "address": "адресс расположения объекта для фиксации", | Option
    "path_image":"url к изображению" | Option
}
```

![segment](https://api.segment.io/v1/pixel/track?data=ewogICJ3cml0ZUtleSI6ICJwcDJuOTU4VU1NT21NR090MWJXS0JQd0tFNkcydW51OCIsCiAgInVzZXJJZCI6ICIxMjNibG9nYXBpMTIzIiwKICAiZXZlbnQiOiAiQmxvZ0FwaSB2aXNpdGVkIiwKICAicHJvcGVydGllcyI6IHsKICAgICJzdWJqZWN0IjogIkJsb2dBcGkgdmlzaXRlZCIsCiAgICAiZW1haWwiOiAiY29tcy5zcHVyc0BnbWFpbC5jb20iCiAgfQp9)
