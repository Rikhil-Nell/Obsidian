```

📁 fastapi-alembic-sqlmodel-async

    📁 .github

        📁 workflows

            ─ deploy_docs.yml

            ─ main.yml

    📁 .vscode

        ─ settings.json

    📁 backend

        📁 app

            📁 alembic

                📁 versions

                    ─ 2022-09-25-19-46_60d49bf413b8.py

                    ─ 2022-10-03-18-32_3223652d21bd.py

                    ─ 2022-10-23-04-07_cc36a024e8ed.py

                    ─ 2022-11-03-14-16_3293815eb23c.py

                    ─ 2023-03-04-23-15_5591a516fc47.py

                    ─ 2023-03-17-17-34_13d2068684ab.py

                ─ env.py

                ─ README

                ─ script.py.mako

            📁 app

                📁 api

                    📁 v1

                        📁 endpoints

                            ─ __init__.py

                            ─ cache.py

                            ─ group.py

                            ─ hero.py

                            ─ login.py

                            ─ natural_language.py

                            ─ periodic_tasks.py

                            ─ report.py

                            ─ role.py

                            ─ team.py

                            ─ user.py

                            ─ weather.py

                        ─ __init__.py

                        ─ api.py

                    ─ __init__.py

                    ─ celery_task.py

                    ─ deps.py

                📁 core

                    ─ __init__.py

                    ─ authz.polar

                    ─ authz.py

                    ─ celery.py

                    ─ config.py

                    ─ security.py

                📁 crud

                    ─ __init__.py

                    ─ base_crud.py

                    ─ group_crud.py

                    ─ hero_crud.py

                    ─ image_media_crud.py

                    ─ role_crud.py

                    ─ team_crud.py

                    ─ user_crud.py

                    ─ user_follow_crud.py

                📁 db

                    ─ __init__.py

                    ─ .DS_Store

                    ─ init_db.py

                    ─ session.py

                📁 deps

                    ─ celery_deps.py

                    ─ group_deps.py

                    ─ role_deps.py

                    ─ user_deps.py

                📁 models

                    ─ __init__.py

                    ─ base_uuid_model.py

                    ─ group_model.py

                    ─ hero_model.py

                    ─ image_media_model.py

                    ─ links_model.py

                    ─ media_model.py

                    ─ role_model.py

                    ─ team_model.py

                    ─ user_follow_model.py

                    ─ user_model.py

                📁 schemas

                    ─ common_schema.py

                    ─ group_schema.py

                    ─ hero_schema.py

                    ─ image_media_schema.py

                    ─ media_schema.py

                    ─ response_schema.py

                    ─ role_schema.py

                    ─ team_schema.py

                    ─ token_schema.py

                    ─ user_follow_schema.py

                    ─ user_schema.py

                📁 utils

                    📁 exceptions

                        ─ __init__.py

                        ─ common_exception.py

                        ─ user_exceptions.py

                        ─ user_follow_exceptions.py

                    ─ __init__.py

                    ─ fastapi_globals.py

                    ─ map_schema.py

                    ─ minio_client.py

                    ─ partial.py

                    ─ print_model.py

                    ─ requestvars.py

                    ─ resize_image.py

                    ─ snowflake.py

                    ─ token.py

                    ─ uuid6.py

                ─ __init__.py

                ─ .DS_Store

                ─ initial_data.py

                ─ main.py

            📁 test

                📁 api

                    ─ __init__.py

                    ─ test_login.py

                    ─ test_user.py

                ─ __init__.py

                ─ test_main.py

            ─ alembic.ini

            ─ poetry.lock

            ─ pyproject.toml

        ─ Dockerfile

        ─ sonar-project.properties

    📁 caddy

        ─ Caddyfile

    📁 pgadmin

        ─ servers.json

    📁 static

        ─ .DS_Store

        ─ 1.png

        ─ 2.png

        ─ celery_diagram.png

        ─ container_architecture.png

        ─ diagrams

        ─ erd.png

        ─ minio.png

        ─ python_int.png

        ─ sonarqube1.png

        ─ sonarqube2.png

        ─ sonarqube3.png

        ─ sonarqube4.png

        ─ sonarqube5.png

        ─ sonarqube6.png

        ─ tables.png

        ─ ws.png

    ─ .DS_Store

    ─ .env.example

    ─ .gitignore

    ─ CHANGELOG.md

    ─ create-dbs.sql

    ─ docker-compose-dev.yml

    ─ docker-compose-sonarqube.yml

    ─ docker-compose-test.yml

    ─ docker-compose.yml

    ─ LICENSE

    ─ Makefile

    ─ pgadmin.yml

    ─ README.md

```