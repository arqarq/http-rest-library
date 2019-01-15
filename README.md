Zaimplementuj serwis RESTowy obsługi biblioteki. Biblioteka przechowuje listę książek. Książka powinna być reprezentowana przez klasę Book, która ma następujące pola:
* name (String) – tytuł książki
* author (Author) – autor (osobna klasa z polami String firstName i String lastName)
* available (boolean) – informacja czy książka jest dostępna

Dodaj następujące funkcjonalności:

1. wyświetlenie listy książek (GET)
2. dodanie nowej książki (PUT) – nowa książka powinna być wysłana jako JSON
3. zmiana statusu dostępności wybranej książki (PATCH)
4. zmiana autora wybranej książki (PATCH) – nowy autor powinien być wysłany jako JSON
5. usunięcie wybranej książki (DELETE)

Spróbuj wywołać poszczególne metody przy użyciu Postmana i curl-a. Listę wszystkich książek spróbuj pobrać również poleceniem wget.

Na stronie https://www.baeldung.com/spring-response-header zobacz jak można dodać własny header do odpowiedzi z serwisu. Spróbuj dodać jakiś przykładowy header do odpowiedzi na request wyświetlenia listy książek. Sprawdź (np. narzędziem Live HTTP Headers) czy Twój header znajduje się w odpowiedzi.