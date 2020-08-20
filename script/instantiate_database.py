"""
Create databases and tables, then loads data
"""
import psycopg2 as psy

from config import (
    CREATE_DATABASE_FILES,
    CREATE_TABLES_FILES,
    DATABASE_CONNECTION,
    DATABASE_TABLES,
    DROP_DATABASE_FILES,
    FAKE_DATA_FILES,
    SEED_DATA_FILES
)


if __name__ == "__main__":
    with psy.connect(**DATABASE_CONNECTION) as connection:
        with connection.cursor() as cursor:
            for DROP_DATABASE_FILE in DROP_DATABASE_FILES:
                with DROP_DATABASE_FILE.open(mode="r") as ddf:
                    try:
                        cursor.execute(ddf.read())
                    except:
                        continue
            for CREATE_DATABASE_FILE in CREATE_DATABASE_FILES:
                with CREATE_DATABASE_FILE.open(mode="r") as cdf:
                    cursor.execute(cdf.read())

    for DATABASE, _ in DATABASE_TABLES:
        DATABASE_CONNECTION["dbname"] = DATABASE

        with psy.connect(**DATABASE_CONNECTION) as connection:
            with connection.cursor() as cursor:
                for CREATE_TABLE_FILE in CREATE_TABLES_FILES[DATABASE]:
                    with CREATE_TABLE_FILE.open(mode="r") as ctf:
                        cursor.execute(ctf.read())
                for SEED_DATA_FILE in SEED_DATA_FILES[DATABASE]:
                    with SEED_DATA_FILE.open(mode="r") as sdf:
                        cursor.execute(sdf.read())
                for FAKE_DATA_FILE in FAKE_DATA_FILES[DATABASE]:
                    with FAKE_DATA_FILE.open(mode="r") as fdf:
                        cursor.execute(fdf.read())
