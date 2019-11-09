CREATE OR REPLACE TRIGGER row_level_stud_2
BEFORE UPDATE OR DELETE
ON student
FOR EACH ROW
BEGIN
	if(:old.s_status='P' and (EXTRACT(YEAR FROM SYSDATE)-EXTRACT(YEAR FROM :old.s_dateadmin)>=4)) then
	insert into alumini values(:old.s_roll,:old.s_name,:old.s_dateadmin,:old.s_branch,:old.s_percent,:old.s_status);
	DBMS_OUTPUT.put_line('Row level trigger executed');
	end if;
END;
