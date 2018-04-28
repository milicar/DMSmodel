## Inbox



### TODOs

DocumentType and Document id generators return constant id.

check User logged in? Singleton or passed with servlet request?   
** (maybe not allow guests any further than UI, which would break user authentication to logged in at the top, role which is checked at business level - each so, and company which is checked where?  

implementations.DocumentTypeTest is pretty weak, only tests size of descriptor list..

test adding and removing tags for documents

revise "remove descriptors" for both types and documents..

rename to DocumentType.Descriptors maybe?

### Decisions

document service has only "create document" with adding descriptors within it (so user cannot create a document without filling in descriptor values)  
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

Create process (admin)  
Find/show process (admin)  
Update process (admin)  
Delete process (admin)  

Create activity (admin)  
Find/show activity (admin and employee?)  
Update activity (admin)  
Delete activity (admin)  

Create document type (admin)  DONE: DocumentTypeService
Find document type (admin and employee)  
Update document type (admin)  
Delete document type (admin)  

Create document (employee and admin) DONE: DocumentService   
Find/show document (employee and admin)  
Update document (employee and admin)  
Delete document (employee and admin)  

Add document tags (employee and admin)  

Send document to contact/business partner (employee and admin)
