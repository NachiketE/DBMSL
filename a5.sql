DECLARE
Paid_Exception EXCEPTION;
fine number;
id number;
daydiff number;
nameOfScheme varchar2(20);
cur_date date;
c_id customer.cust_id%type;
c_name customer.name%type;
c_date customer.dateofpayment%type;
c_status customer.status%type;

BEGIN
id:=&id;
nameOfScheme:=&nameOfSchema;
DBMS_OUTPUT.put_line('ID entered:-' || id);
DBMS_OUTPUT.put_line('Name of Schema entered:-' || nameOfScheme);
select name into c_name
from customer
where cust_id=id;
DBMS_OUTPUT.put_line('customer name:-' || c_name);
select dateofpayment into c_date
from customer
where cust_id=id;
DBMS_OUTPUT.put_line('Date of payment:-' || c_date);
select status into c_status
from customer
where cust_id=id;
DBMS_OUTPUT.put_line('Status:-' || c_status);
if c_status IN ('p')
then raise Paid_Exception;
else
	DBMS_OUTPUT.put_line('In N block of code');
	cur_date:=SYSDATE;
	DBMS_OUTPUT.put_line('Current date:-' || cur_date);
	select to_date(cur_date,'DD-MM-YY')-to_date(c_date,'DD-MM-YY') into daydiff from DUAL;
	DBMS_OUTPUT.put_line('Difference of days=' || daydiff);
	if daydiff>=15 and daydiff<30
	then fine:=(daydiff-15)*5;
	DBMS_OUTPUT.put_line('Fine calculated:-' || fine);
	elsif daydiff>=30
	then fine:=(daydiff-30)*50+15*5;
	DBMS_OUTPUT.put_line('Fine calculated:-' || fine);
	end if;
	insert into fine1 values(id,c_date,fine);
	update customer set status='p' where cust_id=id;	
end if;
EXCEPTION
when Paid_Exception then
DBMS_OUTPUT.put_line('Payment done!!');
END;
/
