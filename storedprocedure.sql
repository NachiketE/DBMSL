CREATE OR REPLACE procedure calculatecategory(id IN customerbank.cust_id%type,customerclass OUT categorybank.cust_class%type) is
amount customerbank.cust_purchase%type;
customername customerbank.cust_name%type;
none EXCEPTION;
BEGIN

select cust_purchase into amount from customerbank where cust_id=id;
DBMS_OUTPUT.put_line('Total amount:-' || amount);

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
EXCEPTION
when none then 
customerclass:='No category';
DBMS_OUTPUT.put_line('No category.data not inserted into category table');
RETURN;
END;
/
DECLARE
id customerbank.cust_id%type;
customerclass categorybank.cust_class%type;
BEGIN
id:=&id;
calculatecategory(id,customerclass);
DBMS_OUTPUT.put_line('Class name is:-' || customerclass);	
END;
/

