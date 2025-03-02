#!/bin/bash
set -e

echo "Waiting for PostgreSQL to be ready..."

# Espera hasta que PostgreSQL esté disponible
until psql -U postgres -c '\q' 2>/dev/null; do
  echo "PostgreSQL is unavailable - waiting..."
  sleep 2
done

echo "PostgreSQL is up - executing script..."

DATABASES=("accounts-movements-service" "clients-persons-service")

for DB in "${DATABASES[@]}"; do
  DB_EXISTS=$(psql -U postgres -tAc "SELECT 1 FROM pg_database WHERE datname='$DB'")
  if [ "$DB_EXISTS" != "1" ]; then
    echo "Creating database: $DB"
    psql -U postgres -c "CREATE DATABASE \"$DB\";"
  else
    echo "Database $DB already exists, skipping..."
  fi
done
