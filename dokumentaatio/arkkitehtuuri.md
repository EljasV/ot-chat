
# Arkkitehtuuri

Sekvenssikaavio, joka näyttää mitä tapahtuu, kun käyttäjä laittaa viestin toiselle käyttäjälle
````mermaid
sequenceDiagram
    Käyttäjä ->> UI: painaa 'send'- painiketta
    UI ->> ChatService: sendMessage(message)
    ChatService ->> MessageDao: sendMessage(message)
    MessageDao -->> MessageFileDao: 
    MessageFileDao ->> MessageFileDao: save()
    MessageFileDao ->> File: Data
    File ->> MessageFileDao: 
    MessageFileDao -->> MessageDao: 
    MessageDao ->> ChatService: 
    ChatService ->> UI: 
    UI ->> UI: Uudelleenlataus
    UI ->> Käyttäjä: 
    
````


## Rakenne
Koko sovelluksen rakenne näyttää tältä
````mermaid
flowchart
    subgraph ui
        UiApp
    end
    subgraph domain
        ChatService
        Session
        User
        Message
        Moderator
    end
    subgraph dao
        UserDao
        MessageDao
        ConfigDao
    end
    UiApp --> ChatService
    UiApp --> Session
    UiApp --> User
    ChatService -.-> dao
    User -.- UserDao
    ChatService -.- User
    UiApp -.-> Message
    Message -.- MessageDao
    ChatService -->Message
    ChatService -.- Moderator
    UiApp -.- Moderator
    Moderator -.- ConfigDao
````