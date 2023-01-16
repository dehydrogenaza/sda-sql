1 przygotuj nowy projekt na maven
dodaj zależność ze sterownikami do wybranej przez siebie bazy danych (najlepiej MySQL lub Postgresql)
przygotuj pustą bazę danych - komenda SQL:  CREATE DATABASE …
W nowej klasie uzyskaj obiekt Connection przy wykorzystaniu odpowiedniej z metod statycznych klasy DriverManager
należy podać odpowiedni port, nazwę bazy danych, typ oraz login i hasło
pamiętaj o obsłudze wyjątku SQL Exception
sprawdź czy program uruchamia się poprawnie


2. Przygotuj klasę Game, gry mają mieć takie cechy jak: id, tytuł, cena i platforma (np. PC).
   Wykorzystując PreparedStatement stwórz w main i przetestuj następujące zapytania:

stwórz tabelę SQL dla obiektów gier
napisz instrukcję pozwalającą dodać jedną grę
napisz instrukcję pozwalającą pobrać wszystkie gry dla danej platformy
napisz instrukcję pozwalającą zmodyfikować grę o danym ID zmieniając platformę
napisz instrukcję pozwalającą skasować wskazaną grę


3. Setup hibernate:
   dodaj odpowiednie dependency (hibernate wersja 5)
   dodaj plik konfiguracyjny do pakietu resources
   uaktualnij plik konfiguracyjny o swoje dane
   stwórz obiekt konfiguracji (z pakietu hibernate!) i skonfiguruj metodą configure
   uzyskaj obiekt SessionFactory testując jednocześnie połączenie z bazą danych

4. setup klasy Game:
   jeśli nie masz to przygotuj klasę Game z poprzedniego zadania
   dodaj do niej adnotację @Entity
   przy konfiguracji wykorzystaj metodę addAnnotatedClass aby wskazać klasę Game
   oznacz pole id adnotacją @Id
   oznacz pole id adnotacją @GeneratedValue
   udostępnij bezparametrowy konstruktor
   zadbaj o wskazanie odpowiednich nazw tabeli i kolumn poprzez adnotacje @Table i @Column


5. CRUD:

Uwaga:nie zapomnij o uzyskaniu obiektu sesji, otwarciu transakcji a po każdej operacji CRUD zrobieniu commit transakcji i zamknięciu sesji

przetestuj zapis metodą save()
przetestuj pobieranie po id metodą get() i load()
przetestuj update metodą save()
zmodyfikuj cenę gry
przetestuj kasowanie metodą delete()
przetestuj pobieranie wielu elementów metodą createQuery()
pobierz tylko gry z wybranej platformy
