
# OT-Chat
Oletko halunnut aina chattailla samalla koneella olevien kanssa? Tässä on uusi mahdollisuutesi!
Älä kuitenkaan hauku muita, sillä chattia voidaan moderoida.

## Julkaisut
[1. julkaisu viikolla 5](https://github.com/EljasV/ot-harjoitustyo/releases/tag/viikko5)

[2. julkaisu viikolla 6](https://github.com/EljasV/ot-harjoitustyo/releases/tag/viikko6)

## Ohjelman suorittaminen
Ohjelma voidaan kääntää ja suorittaa seuraavalla komennolla:

```mvn compile exec:java -Dexec.mainClass=veijalainen.eljas.otchat.Main```

## Ohjelman testaaminen
Ohjelman testit voidaan suorittaa komennolla:

```mvn test```


Ohjelman testikattavuusraportti luodaan seuraavalla komennolla:

```mvn test jacoco:report```

Testikattavuusraportti luodaan tiedostoon [./target/site/jacoco/index.html](./target/site/jacoco/index.html)

## Checkstyle
Ohjelman tyyli voidaan tarkistaa checkstylen avulla

```mvn jxr:jxr checkstyle:checkstyle```

Checkstyle -raportti luodaan tiedostoon [./target/site/checkstyle.html](./target/site/checkstyle.html)

## .jar-tiedosto
.jar-tiedosto kasataan seuraavalla komennolla

```mvn package```

Tiedosto ilmestyy kansioon [./target](./target)

Tiedosto voidaan suorittaa komennolla:

```java -jar ot-chat-3.0.0.jar```

Jos olet projektin juurikansiossa, suoritus tehdään komennolla

(Windows)``java -jar target\ot-chat-3.0.0.jar``

(Linux)``java -jar target/ot-chat-3.0.0.jar``


Huomioi myös tiedostonimessä oleva versio 

## Javadoc
Projektin javadoc voidaan generoida komennolla
```mvn javadoc:javadoc```

## Dokumentaatio
[Vaatimusmäärittely](./dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](./dokumentaatio/tuntikirjanpito.md)

[Changelog](./dokumentaatio/changelog.md)

[Arkkitehtuuri](./dokumentaatio/arkkitehtuuri.md)

[Käyttöohje](./dokumentaatio/kayttoohje.md)