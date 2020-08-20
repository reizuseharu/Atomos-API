from pathlib import Path
from typing import List

import psycopg2 as psy

ROOT_DIR: Path = Path(".").absolute
SCRIPT_DIR: Path = ROOT_DIR.joinpath("script")
SQL_DIR: Path = SCRIPT_DIR.joinpath("sql")


def to_camelcase(word):
    return f"{word[0].lower()}{word[1:]}"


def execute_scripts(cursor: psy.Cursor, database_files: List[Path]):
    for database_file in database_files:
        with database_file.open(mode="r") as ddf:
            try:
                cursor.execute(ddf.read())
            except Exception as e:
                continue
