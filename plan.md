library.Library
administration of library activities:
1. Add new book to library (possibility to add by list)
2. Remove old book (possibility to do by list)
3. library.Book catalog (id, name of book, author, year, status, place (several books with same name), genre)
4. library.Book searching in catalog
5. library.Book taking
6. Return of book
7. library.Author catalog (id, fist name, last name), searching
8. Enter new client
9. library.Client list (id, active, passive, has book on hand, book in due)?
10. Term of book keeping, check if is not in due
11. Reports: popularity by genre, by author, by name, by term of keeping
12. Users: administrators, clients (free book searching only)?

Program structure:
1. menu.Main: call menu
2. menu.Menu: menu sections, menu items, commands, user choice
3. library.Author: id, name, actions: add, edit, remove, search?
4. library.Book: id, name, author, genre, year, status, actions: add, remove, search, take, return
5. library.Client: id, name, date of taking, date of book return, actions: add, edit, remove, search, take book, return book
6. library.Library: list of book, list of clients, list of authors
7. Report
9. Storage: structure (rows, shelves, places, section)


ItemList:
1. add
2. print all
3. print one item
4. remove
5. update
6. find by id
7. find by part of the name
8. get
9. set


Item:
add name

Add:
1. ~~class Authors~~
2. ~~class Book~~

1. ~~Add to deserializeLib linking Authors from file to authors~~
2. ~~Genre (input by id)~~
3. ~~correct to string for printing books and authors~~
4. ~~Finish book commands~~


1. ~~rewrite EditItem (MenuCommands)~~
1. ~~enter - return to menu without saving~~
1. ~~Year checking~~
4. ~~Exception for Enter~~
5. ~~Exception for wrong value~~


1. Add checking that such book is already added?
1. ~~Add listTitle to ItemsList~~
1. ~~Add listTitle to printAll (MenuCommands)~~
2. ~~Add menuTitle to menu~~
3. ~~Add menuTitle above menu~~
2. Add map of authors to book

1. Repeat the cause of exception only
2. ~~Books copies~~
3. Work with readers

   ~~class Reader {id, name, last name, date of birth}~~
   ~~class BookLogsRecord {id, reader, bookcopy, date, take/return action}~~
   menu items 
   {Reader list: add, remove(!), update, print all}
   ~~{Books log: take, return, print all by reader, print all readers}~~
   {Find book: by name (wrong values), by id (wrong values), by author, show all books available}
   {Report: show all "on hand"(!), show all readers in due}
   
4. ~~Add book new copies to BookCommand~~
5. Check exception message in linking method
6. ~~rewrite add copies to separate class extends MenuCommands~~
7. ~~think about find book and print it - if it is better to make general method~~
8. ~~Remove book copy item~~
9. ~~Remove book with all copies~~
10. ~~Add exceptions for interruption~~
11. ~~Moving find from ItemList to Menu - for BookCoomands, AuthorCommands, BookCopies~~
12. Add abstract checking during entering data in Menu
13. ~~Update in BookCommands with new find~~
14. Add unit tests to project
15. Add default meaning to items in case of wrong data values
16. ~~Reader: write addItem, editItem~~
17. ~~To String BookLogs add reader id~~
18. Check menu titles



