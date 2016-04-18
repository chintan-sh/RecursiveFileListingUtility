# filelister
A file listing utility for linux machines. Equivalent to ls -l that outputs list as text, JSON or YAML - written in java.


I<b>nstructions : </b>

It accepts following CLI flags:<br>

--help  <print help> <br>
--path=<path to folder, required><br>
--recursive  (when set, list files recursively.  default is off)<br>
--output=<json|yaml|text, default is text><br>

<b>Examples : </b>

Run program to list help options :<br>
sh filelister --help<br>

Run program to list files recursively and print in JSON format :<br>
sh filelister --path=/home/ --output=json --recursive<br>

Run program to list files and print in YAML format :<br>
sh filelister --path=/home/ --output=json <br>

Run program to list files recursively and print in TEXT format :<br>
sh filelister --path=/home/ --recursive
