# T4 Kioski


````mermaid
sequenceDiagram
    participant main
    participant HKLLaitehallinto
    participant Lataajalaite
    participant Lukijalaite
    participant Kioski
    participant Matkakortti
    
    main ->> HKLLaitehallinto: Konstruktori
    HKLLaitehallinto ->> main: Luotu objekti
    
    main ->> Lataajalaite: Konstruktori
    Lataajalaite ->> main: Luotu objekti
    main ->> Lukijalaite: Konstruktori
    Lukijalaite ->> main: Luotu objekti
    main ->> Lukijalaite: Konstruktori
    Lukijalaite ->> main: Luotu objekti 
    
    main ->> HKLLaitehallinto: lisaaLataaja(rautatientori)
    HKLLaitehallinto ->> main: void 
    main ->> HKLLaitehallinto: lisaaLukija(Lukijalaite)
    HKLLaitehallinto ->> main: void
    main ->> HKLLaitehallinto: lisaaLukija(Lukijalaite)
    HKLLaitehallinto ->> main: void
    
    main ->> Kioski: Konstruktori
    Kioski ->> main: Luotu objekti
    main ->> Kioski: ostaMatkakortti("Arto")
    
    Kioski ->> Matkakortti: Konstruktori
    Matkakortti ->> Kioski: Luotu objekti
    Kioski ->> main: Luotu matkakortti
    
    main ->> Lataajalaite: lataaArvoa(artonKortti, 3)
    Lataajalaite ->> Matkakortti: kasvataArvoa(a)
    Matkakortti ->> Lataajalaite: void
    Lataajalaite ->> main: void
    
    
    
    main ->> Lukijalaite: ostaLippu(artonKortti, 0)
    Lukijalaite ->> Matkakortti: getArvo()
    Matkakortti ->> Lukijalaite: 3
    Lukijalaite ->> Matkakortti: VahennaArvoa(hinta)
    Matkakortti ->> Lukijalaite: void
    Lukijalaite ->> main: true
    
    main ->> Lukijalaite: ostaLippu(artonKortti,2)
    Lukijalaite ->> Matkakortti: getArvo()
    Matkakortti ->> Lukijalaite: 1,5
    Lukijalaite ->> main: false
    
    
````