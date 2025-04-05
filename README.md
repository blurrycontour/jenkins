# Setup
- Create ssh keys `ssh-keygen -t ed25519 -f ./.ssh/id_ed25519`
- Create .env file from template `cp .env.template .env` and add public key for `JENKINS_AGENT_SSH_PUBLIC_KEY`
- Run docker compose `docker compose up -d`
- Jenkins controller setup
    - Use generated master key
    - Install recommened plugins
    - Add credential of type `ssh private key with username`
    - Add a node using this credential, username `jenkins`, workspace `/home/jenkins/ws`, hostname `agent` and optionally hostkey of the agent.
