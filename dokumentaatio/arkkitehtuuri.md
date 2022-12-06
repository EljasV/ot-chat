
# Arkkitehtuuri


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
    end
    subgraph dao
        UserDao
        MessageDao
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
````