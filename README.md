# -INVERTED-INDEX-BY-USING-HASH-TABLES
An inverted index is an index data structure, which is used to map all documents with their
content. It keeps a word and all documents containing this word. There are two types of inverted
indexes:
• Record-level inverted index contains a list of references to documents for each word.
• Word-level inverted index contains the positions of each word within a document.
Inverted index allows fast full text searches and is the most popular data structure used in
document retrieval systems, used on a large scale for example in search engines. Its
disadvantage is large storage overhead and high maintenance costs on update, delete, and insert.
In this assignment, you are expected to implement a record-level inverted index structure using
your own hash table implementation in Java programming language. Your index structure will
be used to find all documents that contain a particular word (e.g., return all documents in which
"computer" occurs).
