# RSM

To set up the project you can just run:
> mvn clean install -DskipTests

To run the tests for all tasks execute:
> mvn test

## Task 1: FooBar

### Package: org/rsm/foobar

### Description:

Write a function called FooBar that
takes input string with a list of integer values, comma separated, and prints all the numbers from 1 to n in one line,
comma separated.

If the number is divisible by 3 then print "foo", if the number is divisible by 5 then print "bar" and if the number is
divisible by both 3 and 5, print "foobar".

Otherwise, just print the number. If the number is repeated print the suffix “-copy” at the end of the string:
For example FooBar(15) should print as follows:

- Input: 1,2,3,4,5,6,45 -> Output: 1,2,foo,4,bar, foo,foobar
- Input: 1,2,3,1,2,3,1,1 -> Output: 1,2,foo,1-copy,2-copy,foo-copy,1-copy,1-copy
- Input: 1,1,3,3,5,5,45,45 -> Output: 1,1-copy,foo,foo-copy,bar,bar-copy,foobar,foobar-copy

AddJUnit tests covering the code

## Task 2: Selenium

### Package: org/rsm/selenium

### Description:

Use Java, Selenium, and Chrome to Automate the following:

Navigate to amazon.co.uk - verify the page is correct and loaded

Search in the books section for a book named "Harry Potter and the Cursed Child", 1 & 2

- verify that the name of the first entry in the list has the same title,
- then verify it has paperback version and verify it has selling price
- could you think of more checks here?

From the list with the books editions click on the paperback version

- check it has the expected title, the same price as on the previous page and it is paperback edition
- could you think of more checks here?

Add it to the basket and mark it as a gift - check it was added as a gift in the basket

- Check the basket content - open the basket and check that the title is the same, it is paperback edition and the price
  is the same as shown on the first search screen
- check it is the only item in the basket
- could you think of more checks here?

## Task 2: Threads

### Package: org/rsm/threads

### Description:

You have 2 HIGH CAPACITY remote services Upload and Download. For simplicity's sake, no authentification is required.

Download service exposes API:
List<DownloadInfo> getDownloadInfos(long packageId);

where:

public interface DownloadInfo{
public int getSize(); //bytes
public String getOriginalFileName();
public String getFileKey();
public String getDownloadURL();
}

Upload service exposes api:

void doUpload(String key, InputStream data, int size);
And throws an exception in case of failure.

Implement the method that, based on provided packageId, downloads data from one and uploads it to another service(the
code should be optimized for performance).

The following restrictions should be enforced(violation leads to total operation failure):

- combined files downloaded at the same time size should not exceed 100M
- file names should be unique
- file extensions: cmd, com, dll, dmg, exe, iso, jar, js are not allowed

The method should return a report containing the following info:

- For every file: name, time to upload, success or failure.
- For the entire operation: total time, number of successes, number of failures.
