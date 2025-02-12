Project Overview – In this in class assignment we use map reduce and hadoop to read a movie script and output the most frequently spoken words, each character's dialogue length, and the unique words from each character.

Approach and Implementation – Each of the three above outputs has a map and reduce function that first splits dialogue lines before tokenizing the dialogue and lowercasing all words for easier processing, then sorting depending on the function before reducing, which combines the results back together

Execution Steps – We used similar terminal commands to in class assignment 3, opening the github into vs code, then using docker compose to configure the hadoop cluster. After the java code is finished, we use maven to combine them all into one jar file that can then be run through HDFS for results

Challenges Faced & Solutions – I gott stuck up on hash set and had to look up some uides and ask GPT about it a bit (still getting the hang of that), i also got hung up thinking that dialogue length mapper needed a sring tokenizer, not realizing that you can count length without the need of one. Other than that I primaril looked at examples from the slides and in class 3 with the help of some guides.
