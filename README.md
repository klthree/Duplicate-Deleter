# duplicatedeleter

Traverses a provided directory and its subdirectories, identifying duplicate files in each and deleting all but one.
Will not identify duplicates in different directories. Will change write permissions on files that are determined to 
be duplicates.

Be careful; this code comes with no guarantees of safety or effectiveness.
