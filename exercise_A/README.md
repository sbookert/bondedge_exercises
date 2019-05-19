# Solution
## Assumptions
The budget given to the company leads to using an open source RDMS. Given a tight deadline, no time is available to research the most suitable DB for the job, so the team chooses MySQL and MySQL Workbench as they already have them set up on their machines.
## Requirements
### "total number of candidates running can be no more than 10"
A check condition is enforced using a trigger on insert and update along with a stored procedure.
- Triggers can be seen here: [TRIGGERS](https://github.com/sbookert/bondedge_exercises/blob/fb137b1bb74b24a260c8b00e47fb6102a18a5d05/exercise_A/BondedgeDDL.sql#L42)
- Stored procedure is here: [PROCEDURE] (https://github.com/sbookert/bondedge_exercises/blob/f1d557d15d924276add26cd317f8daf927615348/exercise_A/BondedgeDDL.sql#L98)
