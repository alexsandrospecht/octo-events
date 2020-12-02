# octo-events

Clojure playground. Project to receive Github Webhook calls.

#### Usage

- Run the project:

    `$ docker-compose up`

- Run Ngrok:

    `sudo ngrok http 3000`

- Create a new Webhook:

    https://developer.github.com/webhooks/creating/
    
- Create/Update/Close an issue in the repository:

    https://github.com/youruser/yourrepo/issues

- Or call POST `http://localhost:3000/events` with the json provided in `example.json`

- Call `http://localhost:3000/issues/:issue-id/events` to show all the events for the given `:issue-id`

