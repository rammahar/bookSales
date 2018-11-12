# BookSales
Book Sales Example by command line inputs

Steps to run this program in local -

After compilation by "javac com/prima/seller/BookSales.java" in src folder

Run the program by
### java com.prima.seller.BookSales --books=</path/to/book/list/csv/file> --sales=</path/to/sales/list/csv/file> --top_selling_books=\<n\> --top_customers=\<n> --sales_on_date=\<date yyyy-MM-dd format>

Following arguments will be required

--books=/path/to/books.list	(required)	
	
--sales=/path/to/sales.list	(required)	
	
--top_selling_books=<count>		(optional)	output	the	top	<count>	best	selling	books	in	terms	
of	sale	value	
	
--top_customers=<count>		(optional)	output	the	top	<count>	customers	in	terms	of	value	of	
purchases	they've	made.	
	
--sales_on_date=<date>	(optional)	output	the	total	sales	amount	on	the	date