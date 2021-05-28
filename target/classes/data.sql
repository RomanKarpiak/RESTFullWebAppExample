insert into customer(name, phone, email, country, city, street)
values('Alex','123','alex@gmail.com', 'Russia', 'Peterburg', 'Nevsky 2');

insert into customer(name, phone, email, country, city, street)
values('Petya','2323','petya@gmail.com','Russia', 'Kurgan', 'Lenina 90');

insert into customer(name, phone, email, country, city, street)
values('Lindoro','5555','lindoro@gmail.com', 'Belarus', 'Minsk', 'Kupala 2');



insert into product(name, description, price)
values('iPhone','expensive phone', 70000);

insert into product(name, description, price)
values('Samsung','good phone', 10000);

insert into product(name, description, price)
values('LG','good TV', 19000);


insert into product_photo(url,product_id)
values('iphone_photo', 1);

insert into product_photo(url,product_id)
values('samsung_photo', 2);

insert into product_photo(url,product_id)
values('LG_TV_photo', 3);


insert into cart(customer_id) values (1),(2),(3);


insert into cart_product(cart_id, product_id)
values (1, 1), (1,2), (2,3), (2,1), (3,2);