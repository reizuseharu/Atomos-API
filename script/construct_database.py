"""
Removes old databases and then creates databases
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
from utility import execute_scripts


if __name__ == "__main__":
    with psy.connect(**DATABASE_CONNECTION) as connection:
        with connection.cursor() as cursor:
            execute_scripts(cursor, DROP_DATABASE_FILES)
            execute_scripts(cursor, CREATE_DATABASE_FILES)