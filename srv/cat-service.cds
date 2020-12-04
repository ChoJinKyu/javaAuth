using my.bookshop as my from '../db/data-model';

service CatalogService {

    entity Books as projection on my.Books;

    view User as
    select $user as user : String(256)
          ,$user.id  as id : String(256)
          ,$user.locale as locale : String(256)
          /*
          ,$user.name as user_name : String(256)
          ,$user.givenName as user_givenName : String(256)
          ,$user.familyName as user_familyName : String(256)
          ,$user.familemailyName as user_familemailyName : String(256)
          */
    from   Books
    ;



}