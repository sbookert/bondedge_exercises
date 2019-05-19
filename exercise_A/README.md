# Solution

## Assumptions
The budget given to the company leads to using an open source RDMS. Given a tight deadline, no time is available to research the most suitable DB for the job, so the team chooses MySQL and MySQL Workbench as they already have them set up on their machines.

## Requirements

### "total number of candidates running can be no more than 10"
A check condition is enforced using a trigger on insert and update along with a stored procedure.
- Triggers can be seen here: [TRIGGERS](https://github.com/sbookert/bondedge_exercises/blob/fb137b1bb74b24a260c8b00e47fb6102a18a5d05/exercise_A/BondedgeDDL.sql#L42)
- Stored procedure is here: [PROCEDURE](https://github.com/sbookert/bondedge_exercises/blob/f1d557d15d924276add26cd317f8daf927615348/exercise_A/BondedgeDDL.sql#L98)

### "Assume there is a UI that another team will develop to support whatever model you design."
Additional conversations would have to take place with the UI team to identify what application metadata would have to be stored. An example of such data would be authorized voting locations, etc...

### "A citizen who votes can only vote once"
Every citizen eligible to vote is in table Citizen and has a CitizenId. Each CitizenId can exist only once in the Vote table.
- See the Primary Key defined, which are by default unique, here: [Unique](https://github.com/sbookert/bondedge_exercises/blob/91400691e490a7a31e1c85e1ca2a5ba566c14e66/exercise_A/BondedgeDDL.sql#L70)

### "ignore votes cast before 6am on Election Day, and after 8pm of the same day when the polls close."
Votes that reach the database before 6am and after 8pm are prevented from being inserted in the Vote table using a check condition. We are assuming that voters in different time zones may submit their votes using their local times.
- Triggers can be seen here: [TRIGGERS](https://github.com/sbookert/bondedge_exercises/blob/91400691e490a7a31e1c85e1ca2a5ba566c14e66/exercise_A/BondedgeDDL.sql#L77)
- Stored procedure is here: [PROCEDURE](https://github.com/sbookert/bondedge_exercises/blob/91400691e490a7a31e1c85e1ca2a5ba566c14e66/exercise_A/BondedgeDDL.sql#L108)

### "your DB design should be easily queryable to determine the result."
An application layer should be created with an API which executes the query. Creating such a layer is out of scope of this exercise.

