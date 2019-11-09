CREATE OR REPLACE function calculatecategory1(id IN customerbank.cust_id%type) return  categorybank.cust_class%type is
customername customerbank.cust_name%type;
customerclass categorybank.cust_class%type;
amount number;
none EXCEPTION;
BEGIN
select cust_purchase into amount from customerbank where cust_id=id;
DBMS_OUTPUT.put_line('Total Purchase:-' || amount);
if amount>=10000 and amount<=20000 then customerclass:='Platinum';
select cust_name into customername from customerbank where cust_id=id;
insert into categorybank values(id,customername,customerclass);

elsif amount>=5000 and amount<=9999 then customerclass:='Gold';
select cust_name into customername from customerbank where cust_id=id;
insert into categorybank values(id,customername,customerclass);

elsif amount>=2000 and amount<=4999 then customerclass:='Silver';
select cust_name into customername from customerbank where cust_id=id;
insert into categorybank values(id,customername,customerclass);

else
raise none;

end if;
return customerclass;
EXCEPTION 
when none then
DBMS_OUTPUT.put_line('No category');
return customerclass;

END;
/
DECLARE
id customerbank.cust_id%type;
customerclass categorybank.cust_class%type;
BEGIN
id:=&id;
customerclass:=calculatecategory1(id);
DBMS_OUTPUT.put_line('Category of customer:-' || customerclass);
END;
/
