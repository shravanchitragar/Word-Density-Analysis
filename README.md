# Word-Density-Analysis
How to Execute:

	java -jar assignment.jar <URL>
	
Example: 
1.	http://www.amazon.com/Cuisinart-CPT-122-Compact-2-Slice-Toaster/dp/B009GQ034C/ref=sr_1_1?s=kitchen&ie=UTF8&qid=1431620315&sr=1-1&keywords=toaster


2.	http://blog.rei.com/camp/how-to-introduce-your-indoorsy-friend-to-the-outdoors/


3.	http://www.cnn.com/2013/06/10/politics/edward-snowden-profile/



Descriptions:

	This program can extract keywords from any website. It takes url as an input and produce 5 or more keywords as the output based on the settings in the code. The approach that I used is as follow:
1.	Download the given webpage.
2.	Extract key words from the webpage using Jsoup library, i.e.
1.	Removing all punctuations
2.	checking if the word is stop word
3.	checking if word is correct words (words like "   " or includes special characters are incorrect words)
3.	Adding all the extracted key words into data structure(Trie) along with their frequencies.
4.	Getting top k (k is set to 5 in my program) most frequencies of keywords




DATA STRUCTURE:
I used the Trie and Node data structure to implement this system. Word Node has three fields: value: it is the word value being stored. The type is String. Degree: it is the frequency of the word being stored, children: it is a list, which store the children Nodes of the current Node.

KEYWORD EXTRACTION:
After getting the Document from the URL, the document is parsed entirely and put all words into the Scanner. Each time, one word is read out from Scanner. After determining the word is not stop word and it is candidate word, the word will be added to the Word Trie. 
For example: the sentence is: 
"Man, behind NSA leaks says he did it to safeguard privacy and liberty"
The key word parsing would look like: 
"Man": wordTrie.add("Man"); StopWord "behind"; "NSA": wordTrie.add("NSA"); "leaks": wordTrie.add("leaks"); StopWord "says": wordTrie.add("NSA","leaks"); StopWord "he" StopWord "did" StopWord "it" StopWord "to" "safeguard": wordTrie.add("safeguard"); "privacy": wordTrie.add("privacy"); StopWord "and": wordTrie("safeguard","privacy"); "liberty": wordTrie.add("liberty");
And the Trie would look like:
["Man", 1] ["NSA", 1] --> ("leaks", 1) ["leaks", 1] ["safeguard", 1] --> ["privacy", 1] ["privacy", 1]

Architecture:

	There are 5 major parts: 
1.	PageParser Module: Responsible for web crawling

2.	KeyParser Module: Responsible for parsing key words

3.	WordNode Module: Responsible for creating nodes which contains key words and their frequencies

4.	Trie Module: Responsible for creating Tree with word nodes and extracting top k frequency words

5.	Main Program: Responsible for taking input and perform program execution.

1.	PageParser Class: 

1.	validateURL: This method accepts string(url) as argument and validates if the provided url is correct or not using MalformedURLException.

2.	getDoc: This method connects to the webpage using Jsoup which grabs the content and writes to the document. This method also handles exceptions when it’s not possible to get the document given the input URL.

2.	KeyParser Class: 

1.	Constructor: This will take the document obtained from PageParser class, initializes the hashset and Trie data structure.

2.	startParsingKeyWords: This method calls parseStopWords() and parseKeyWords() methods

3.	parseStopWords: Reads stopword.txt file and adds each word into hashset

4.	parseKeyWords: Reads document and iterates over each word. This method helps in removing all punctuations, checking if the word is stop word, checking if word is correct words (words like "   " or includes special characters are incorrect words)


3.	WordNode Class:

1.	Constructor: Initializes the value, degree and children for the node

2.	setVal: Sets the value for a node

3.	setDegree: Sets degree for a node

4.	getVal: Returns value of a node

5.	getDegree: Returns degree of a node

6.	getChildren: Returns children of a node



4.	Trie Class:

1.	addWord(String word): This method adds one word to tree along with its frequency. 
2.	
3.	addWord(String[] words): This method iterates over each word and adds it to the tree by scanning it to find the right place.
4.	
5.	getKeyWordsOfTopK: This method takes “k” as input and returns k most frequent words by scanning each node of the tree.

5.	Main Class: In this part we do 4 steps above in the Description part to produce the result.





Exception Handling: MalformedURLException and IOException have been used to handle exceptions.
