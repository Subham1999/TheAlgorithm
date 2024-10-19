


EMPL
id
name
salary
location
doj


SELECT TOP 5 * FROM EMPL e WHERE DATEDIFF(year, e.doj, getdate()) >= 3 AND trim(e.location)=N'bangalore' ORDER BY e.salary DESC
