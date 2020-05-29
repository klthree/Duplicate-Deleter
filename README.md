# Duplicate Deleter

Traverses a provided directory and its subdirectories, identifying duplicate files in each and deleting all but one.
Will not identify duplicates in different directories. Will change write permissions on files that are determined to 
be duplicates. Only use absolute paths - does not support relative paths as arguments.

Current usage: java checker abs/path/to/directory

Be careful; this code comes with no guarantees of safety or effectiveness.
