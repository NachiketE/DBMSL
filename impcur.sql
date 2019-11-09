/*declaring the variables*/
DECLARE
empid number;
empname varchar2(20);
empsalary number;
empcount number;
/*Defining the cursor to initailize the memory*/
cursor mycursor is select * from oldemployee;

BEGIN
DBMS_OUTPUT.put_line('Implicit cursor with for loop');
for i in mycursor
LOOP
	empid:=i.olde_id;
	empname:=i.olde_name;
	empsalary:=i.olde_salary;
	if mycursor%NOTFOUND then
	DBMS_OUTPUT.put_line('No data present.All rows completed!');
	EXIT;
	elsif mycursor%FOUND then
	DBMS_OUTPUT.put_line('Fetched id: '|| empid ||' Fetched name: '|| empname ||' Fetched salary: '|| empsalary);
	end if;
	select count(*) into empcount from newemployee where newe_id=empid;
	if empcount=0 then
	insert into newemployee values(empid,empname,empsalary);
	DBMS_OUTPUT.put_line('Data inserted');
	else
	DBMS_OUTPUT.put_line('Data already present');
	end if;
end LOOP;
END;

/

