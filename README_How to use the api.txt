On UPVNET/vrroman account

Open command prompt (as this account with admin)

java -Dcore.nlp="C:\Users\vrroman\recsysRESTApi\Resources\corenlpServer" -Dpython.querypath="C:\Users\vrroman\recsysRESTApi\Resources\FinalVersion-SearchForAQuery_R\SearchForAQuery-master\SearchForQuery.py" -Dpython.queryfolderpath="C:\Users\vrroman\recsysRESTApi\Resources\FinalVersion-SearchForAQuery_R\SearchForAQuery-master" -Dpython.logfile="C:\Users\vrroman\recsysRESTApi\queriesLog.txt" -Dvideos.upvjson="C:\Users\vrroman\recsysRESTApi\Resources\FinalVersionModules-Method-to-Classify_R\videos_upv.json" -Dnormal.querypath="C:\Users\vrroman\recsysRESTApi\Resources\scraper\scraper.py" -jar C:\Users\vrroman\recsysRESTApi\recsys-restapi-0.0.1-SNAPSHOT.jar 

!!Note: for this to work, you have to have Anaconda installed, conda added to path environment variables, and an environment called
recenv that contains all needed dependencies. Use pipreqs to find all needed dependencies. There is also a requirements.txt inside project

This will start extracting essential info from the upvvideos.json.
Also it will start a visible cli containing Stanford CoreNLP
There will be a hidden process starting the python part of this program (SearchForQuery.py)

After this you have to wait a few minutes(or more,depending on data size)
until you see that data is done uploading in the coreNLP CLI. If the coreNLP does not load any data(only a few lines present in CLI
regarding the start of corenlp) after 5 or 10 minutes, restart the program.

After all data has been loaded into core nlp you can access the start page on http://localhost:8080/ and 
swagger on http://localhost:8080/swagger-ui/index.html

!!!important - consider changing the database from h2(in memory) to a persistent database!!!

If you want to process the data from queriesLog.txt, you must know that it is in form:
{...},{...},{...}, 
and some have users and data about users, some not