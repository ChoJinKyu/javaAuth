using my.bookshop as my from '../db/data-model';

service CatalogService {

    entity Books as projection on my.Books;
    entity Books2 as projection on my.Books;


    view BooksView  @(restrict: [
        { grant: 'READ', where: 'language_cd = $user.language' }
    ])as 
    select ID
          ,title
          ,stock
          ,language_cd
    from my.Books
    ;
/*
    view BooksView2 as 
    select ID
          ,title
          ,stock
          ,language_cd
          ,(select title
            from Books2
            where ID = 1) as title2 : my.Books.title
    from Books
    ;
*/    

/*
    view BooksView2 as 
    select ID
          ,title
          ,stock
          ,language_cd
    from my.Books
    where language_cd = $user.language
    ;
*/

    view User as
    select $user as user : String(256)
          ,$user.id  as id : String(256)
          ,$user.locale as locale : String(256)
          //,$user.language as language : String(256)
          /*
          ,$user.name as user_name : String(256)
          ,$user.givenName as user_givenName : String(256)
          ,$user.familyName as user_familyName : String(256)
          ,$user.familemailyName as user_familemailyName : String(256)
          */
    from   Books
    ;



}