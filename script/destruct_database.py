"""
Drops databases
"""
import psycopg2 as psy

from config import (
    DATABASE_CONNECTION,
    DROP_DATABASE_FILES,
)
from utility import execute_scripts


if __name__ == "__main__":
    with psy.connect(**DATABASE_CONNECTION) as connection:
        with connection.cursor() as cursor:
            execute_scripts(cursor, DROP_DATABASE_FILES)
