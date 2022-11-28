
# Arkkitehtuuri

Message eli viesti ei ole vielä tehty, ajattelin että se tulee näyttämään joltain tältä
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