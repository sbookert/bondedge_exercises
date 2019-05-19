# Solution
## Assumptions
The budget given to the company leads to using an open source RDMS. Given a tight deadline, no time is available to research the most suitable DB for the job, so the team chooses MySQL and MySQL Workbench as they already have them set up on their machines.
## Requirements
### "total number of candidates running can be no more than 10"
A check condition is enforced using a trigger on insert and update along with a stored procedure.
- Trigger can be seen here:
- Stored procedure is here: 
