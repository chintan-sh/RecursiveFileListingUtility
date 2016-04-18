# filelister
A file listing utility for linux machines. Equivalent to ls -l that outputs list as text, JSON or YAML - written in java.


I<b>nstructions : </b>

It accepts following CLI flags:

--help  <print help>
--path=<path to folder, required>
--recursive  (when set, list files recursively.  default is off)
--output=<json|yaml|text, default is text>

<b>Examples : </b>

Run program to list help options :
sh filelister --help

Run program to list files recursively and print in JSON format :
sh filelister --path=/home/ --output=json --recursive

Run program to list files and print in YAML format :
sh filelister --path=/home/ --output=json 

Run program to list files recursively and print in TEXT format :
sh filelister --path=/home/ --recursive
