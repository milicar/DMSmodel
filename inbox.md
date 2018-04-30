## Inbox



### TODOs

remove .idea files from repo! and update .gitignore

id generators return constant id.

revise "remove descriptors" for both types and documents..

should getActivityDocumentTypeList be public? currently it's only used in a test

separate isLoggedIn(user) from isAuthorised(user) checks 

destatic-ise services?

I think services should deal with exceptions, not rethrow them 

### Dilemmas

check User logged in? Singleton or passed with servlet request?   
** (maybe not allow guests any further than UI, which would break user authentication to logged in at the top, role which is checked at business level - each so, and company which is checked where?  

is user interface/view a control mechanism?   
Pros: 
* if employee doesn't have an option (eg button) to create process, for example,
should there even be a precondition check isAuthorised(user)?
* faster view generation
   
Cons: 
* what about UI changes? maybe some future UI shows "add a process" button to employee..
* how much processing should be left to client machine?

If all updates to a record/object basically go through a constructor, then there's no need for setters.
This is tied to user interface, how broken down the controls are - is it "fill all fields and click create/update" 
or are there buttons to change one field at a time. 

These service classes look like static utility classes

isComplex check for processes in ActivityService talks directly to instance of Process. 
Should it go through ProcessService?  
 
### Decisions

At this point, document service has only "create document" with adding descriptors within it (so user cannot create a document without filling in descriptor values)  
users will click on create doc, and fill everything including descriptors  
same is for document type - descriptors are added at the creation or update time  
BUT what about removing descriptors? It should also be within create/update, but still isn't


## System operations requirements

Logging in (admin and employee)  
Logging out (admin and employee)   

Create company (admin)  
Find/show company (admin)  
Update company (admin)  
Delete company (admin)  

Create company contact list (admin)  
Add new contact to contact list (admin)  
Find/show contact (admin and employee)  
Update contact (admin)  
Delete contact (admin)  

~~Create process (admin) DONE: ProcessService~~  
Find/show process (admin and employee)  
Update process (admin)  
Delete process (admin)  

~~Create activity (admin) DONE: ActivityService~~  
Find/show activity (admin and employee?)  
Update activity (admin)  
Delete activity (admin)  

~~Create document type (admin) DONE: DocumentTypeService~~  
Find document type (admin and employee)  
Update document type (admin)  
Delete document type (admin)  

~~Create document (employee and admin) DONE: DocumentService~~   
Find/show document (employee and admin)  
Update document (employee and admin)  
Delete document (employee and admin)  

~~Add document tags (employee and admin) DONE: DocumentService~~

Send document to contact/business partner (employee and admin)
