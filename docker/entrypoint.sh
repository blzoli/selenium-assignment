#!/usr/bin/env bash
set -euo pipefail

REMOTE_URL="${SELENIUM_REMOTE_URL:-http://selenium:4444/wd/hub}"

# Selenium 4 exposes status on /status; /wd/hub/status is also commonly available.
STATUS_URL_1="${REMOTE_URL%/wd/hub}/status"
STATUS_URL_2="${REMOTE_URL%/}/status"
STATUS_URL_3="${REMOTE_URL%/}/wd/hub/status"

echo "Waiting for Selenium at: $REMOTE_URL"
for i in $(seq 1 60); do
  if curl -fsS "$STATUS_URL_1" >/dev/null 2>&1 || \
     curl -fsS "$STATUS_URL_2" >/dev/null 2>&1 || \
     curl -fsS "$STATUS_URL_3" >/dev/null 2>&1; then
    echo "Selenium is up."
    break
  fi

  if [[ "$i" -eq 60 ]]; then
    echo "Timed out waiting for Selenium." >&2
    echo "Tried: $STATUS_URL_1" >&2
    echo "Tried: $STATUS_URL_2" >&2
    echo "Tried: $STATUS_URL_3" >&2
    exit 1
  fi

  sleep 1
done

exec "$@"
