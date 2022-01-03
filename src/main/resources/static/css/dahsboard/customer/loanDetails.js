let msg = document.querySelector('#msg').innerHTML;
let tableData = document.querySelector('#tableData');
if(msg == 'You have not been given any loan'){
	tableData.style.display = 'none';
}