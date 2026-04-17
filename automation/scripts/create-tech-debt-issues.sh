#!/bin/sh

for file in $(dirname "$0")/../techdebt/*.md; do
    if [ -f "$file" ]; then
        title="TechDebt: $(basename "$file" .md | tr '-' ' ')"
        body=$(cat "$file")

        echo "Processing file: $file"
        echo "Title: $title"

        echo "Checking for existing issue..."
        existing=$(gh issue list --search "in:title \"$title\"" --json title 2>&1)
        echo "GH response: $existing"

        if echo "$existing" | grep -q "$title"; then
            echo "Issue for '$title' already exists. Skipping."
        else
            echo "Creating issue..."
            gh issue create --title "$title" --body "$body" --label "tech-debt,bug" 2>&1
            echo "Exit code: $?"
        fi
    fi
done