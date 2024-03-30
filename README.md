![Static Badge](https://img.shields.io/badge/3.x-orange?style=flat&logo=Spring%20Boot&logoColor=white&label=Spring%20Boot&labelColor=green)
![Static Badge](https://img.shields.io/badge/%20-%20orange?style=flat&logo=Docker&logoColor=white&label=Docker&labelColor=blue)
![Static Badge](https://img.shields.io/badge/15--alpine-orange?style=flat&logo=PostgreSQL&logoColor=white&label=PostgreSQL&labelColor=blue)



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
Откройте Dockerfile и замените переменные среды, указанные в Dockerfile, на ваши значениея
```

**3. Запустите сборку приложения**
```bash
docker compose up/docker-compose up
```

**4. Ваше приложение будет собрано. Docker запустит контейнеры с приложением и БД.**
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
| GET    | /api/v1/user | Получить список всех пользователей | тела нет | |
| DELETE | /api/v1/user/{userId} | Удалить пользователя | тела нет | |

### Claim

| Метод | Url | Описание | Пример валидного тела запроса | Пример тела ответа |
| ------ | --- | ----------- |-------------------------------| ------------------ |
| GET    | /api/v1/claim | Получить отсортированный по рейтингу список всех претензий | тела нет                      | |
| GET    | /api/v1/claim/{claimId} | Получить претензию | тела нет                      | |
| GET    | /api/v1/claim/{userId} | Получить список всех претензий пользователя | тела нет                      | |
| POST   | /api/v1/claim/{userId} | Создать претензию пользователя | [JSON](#claimcreate)          | |
| PATCH  | /api/v1/claim/{claimId}/uprating | Поднять рейтинг претензии | тела нет                      | |
| PATCH  | /api/v1/claim/{claimId}/downrating | Понизить рейтинг претензии | тела нет                      | |
| PUT    | /api/v1/claim/{claimId} | Обновить претензию | [JSON](#claimupdate)          | |
| DELETE | /api/v1/claim/{claimId} | Удалить претензию | тела нет                      | |

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
