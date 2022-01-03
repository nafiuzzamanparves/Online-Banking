let status = document.querySelector('#tableData').rows[10].cells[1].innerHTML;
let giveBtn = document.querySelector('#giveBtn');
let rejectBtn = document.querySelector('#rejectBtn');
if(status == 'Rejected'){
	giveBtn.style.display = 'none';
	rejectBtn.style.display = 'none';
}else if(status == 'Approved'){
	giveBtn.style.display = 'none';
	rejectBtn.style.display = 'none';
}
