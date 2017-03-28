# Filelister
A file listing utility for linux. Provides recursive list of files and directories in TEXT, JSON or YAML format. It's written in java (Still under works) for CLI lookup.


## Instructions : 

It accepts following CLI flags:<br>

--help  <print help> <br>
--path=<path to folder, required><br>
--recursive  (when set, list files recursively.  default is off)<br>
--output=<json|yaml|text, default is text><br>

## Examples : 

Run program to list help options :<br>
sh filelister --help<br>

Run program to list files recursively and print in JSON format :<br>
sh filelister --path=/home/ --output=json --recursive<br>

Run program to list files and print in YAML format :<br>
sh filelister --path=/home/ --output=json <br>

Run program to list files recursively and print in TEXT format :<br>
sh filelister --path=/home/ --recursive


---
## Text
![alt tag](http://chintansh.com/img/portfolio/w3p2.png)

---
## YAML
![alt tag](http://chintansh.com/img/portfolio/w3p3.png)

---
## JSON
![alt tag](http://chintansh.com/img/portfolio/w3p4.png)
