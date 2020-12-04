namespace my.bookshop;

entity Books {
    key ID : Integer;
    title  : String;
    stock  : Integer;
    name  : String @cds.on.insert: $user;
    user_name  : String @cds.on.insert: $user.name;
    givenName  : String @cds.on.insert: $user.givenName;
    familyName  : String @cds.on.insert: $user.familyName;
    email  : String @cds.on.insert: $user.email;
}
