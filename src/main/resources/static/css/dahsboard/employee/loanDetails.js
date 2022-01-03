let table = document.querySelector('.tableData');
for (var i = 0, row; row = table.rows[i]; i++) {
   status = table.rows[i].cells[7].innerHTML;
   console.log(status);
 	if(status = 'Paid'){
 		let btn = table.rows[i].cells[8].getElementsByTagName('form')[0];
 		console.log(btn);
 	}
}
