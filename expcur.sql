DECLARE
empid number;
empname varchar2(20);
empsal number;
empcount number;
cursor mycursor is select * from oldemp;
BEGIN
open mycursor;
DBMS_OUTPUT.put_line('Explicit cursor');
LOOP
	fetch mycursor into empid,empname,empsal;
	if mycursor%NOTFOUND then
	DBMS_OUTPUT.put_line('No data present.all rows completed');
	EXIT;
	elsif mycursor%FOUND then 
	DBMS_OUTPUT.put_line('Fetched id=' || empid || ' Fetched name=' || empname || ' Fetched salary=' || empsal);
	end if;
	select count(*) into empcount from newemp where newe_id=empid;
	if empcount=0 then
	insert into newemp values(empid,empname,empsal);
	DBMS_OUTPUT.put_line('Data inserted');
	else
	DBMS_OUTPUT.put_line('Data already present');
	end if;
end LOOP;
close mycursor;
END;
