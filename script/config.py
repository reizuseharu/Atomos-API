from pathlib import Path
from typing import (
    Dict,
    List
)

from utility import (
    SQL_DIR,
    to_camelcase
)

# - Pull configuration from yaml file
DATABASE_CONNECTION: Dict[str, str] = {
    "host": "localhost",
    "user": "postgres",
    "password": "postgres",
    "dbname": "postgres"
}

DATABASE_TABLES: Dict[str, List[str]] = {
    "Atomos": [],
    "Test": []
}

CREATE_DATABASE_FILES: List[Path] = [
    SQL_DIR.joinpath(f"create-database-{to_camelcase(DATABASE)}.sql")
    for DATABASE, _ in DATABASE_TABLES
]
DROP_DATABASE_FILES: List[Path] = [
    SQL_DIR.joinpath(f"drop-database-{to_camelcase(DATABASE)}.sql")
    for DATABASE, _ in DATABASE_TABLES.items()
]


CREATE_TABLES_FILES: Dict[str, Path] = {
    DATABASE: [
        SQL_DIR.joinpath(f"create-table-{to_camelcase(DATABASE)}-{to_camelcase(TABLE)}.sql")
        for TABLE in TABLES
    ]
    for DATABASE, TABLES in DATABASE_TABLES.items()
}

CREATE_TABLES_FILES: Dict[str, Path] = {
    DATABASE: [
        SQL_DIR.joinpath(f"create-table-{to_camelcase(DATABASE)}-{to_camelcase(TABLE)}.sql")
        for TABLE in TABLES
    ]
    for DATABASE, TABLES in DATABASE_TABLES.items()
}

SEED_DATA_FILES: Dict[str, Path]  = {
    DATABASE: [
        SQL_DIR.joinpath(f"load-seed-data-into-{to_camelcase(DATABASE)}-{to_camelcase(TABLE)}.sql")
        for TABLE in TABLES
    ]
    for DATABASE, TABLES in DATABASE_TABLES.items()
}

# For end to end testing
FAKE_DATA_FILES: Dict[str, Path]  = {
    DATABASE: [
        SQL_DIR.joinpath(f"load-fake-data-into-{to_camelcase(DATABASE)}-{to_camelcase(TABLE)}.sql")
        for TABLE in TABLES
    ]
    for DATABASE, TABLES in DATABASE_TABLES.items()
}


TRUNCATE_DATA_FILE: Path = SQL_DIR.joinpath("truncate.sql")
