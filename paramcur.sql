DECLARE
empid number;
empname varchar2(20);
empsalary number;
empcount number;
/*create cursor for memory initialization*/
cursor mycursor(compareid number :=&id) is select * from oldemployee where olde_id=compareid;
BEGIN
/*open cursor to allocate memory*/
open mycursor;
DBMS_OUTPUT.put_line('Parametrized cursor');
/*fetch data from cursor*/
fetch mycursor into empid,empname,empsalary;
if mycursor%NOTFOUND then
DBMS_OUTPUT.put_line('Data of given id not present');
elsif mycursor%FOUND then
DBMS_OUTPUT.put_line('Fetched id: '|| empid||' Fetched name: '|| empname || ' Fetched salary: ' || empsalary);
	select count(*) into empcount from newemployee where newe_id=empid;
	if empcount=0 then
	insert into newemployee values(empid,empname,empsalary);
	DBMS_OUTPUT.put_line('Data inserted');
	else
	DBMS_OUTPUT.put_line('Data already present');
	end if;
end if;
close mycursor;
END;

/	

