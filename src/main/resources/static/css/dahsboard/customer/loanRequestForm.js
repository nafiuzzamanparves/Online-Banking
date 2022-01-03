function changeInterestRate() {
	let loanTypee = document.querySelector('#loanType');
	let interestRatee = document.querySelector('#irate');
	if (loanTypee.value == 'Personal') {
		interestRatee.value = 9;
	} else if (loanTypee.value == 'Home') {
		interestRatee.value = 9.5;
	} else if (loanTypee.value == 'Auto') {
		interestRatee.value = 10;
	} else if (loanTypee.value == 'Agriculture') {
		interestRatee.value = 5;
	}

	let loanDuration = document.querySelector('#loanDuration').value;
	let amount = document.querySelector('#amount').value;
	if (amount == '') {
		amount = 0;
	}
	let interestRate = document.querySelector('#irate').value;
	let pow = Math.pow((1 + (interestRate / 100)), loanDuration);
	let interest = amount * (pow - 1);
	let totalPayable = parseFloat(amount) + interest;
	document.querySelector('#total').value = totalPayable;
	let mPay = totalPayable / (loanDuration * 12);
	document.querySelector('#mPay').value = mPay;

	console.log(`${loanDuration} years loan of ${amount} at an interest rate of ${interestRate}% that compound total so interest is ${interest}`);
	console.log('Total Payable amount is ' + totalPayable);
	console.log('Monthly installment is ' + mPay);
}



function emiTwo() {
	let loanDuration = document.querySelector('#loanDuration').value;
	let amount = document.querySelector('#amount').value;
	if (amount == '') {
		amount = 0;
	}
	let interestRate = document.querySelector('#irate').value;
	let pow = Math.pow((1 + (interestRate / 100)), loanDuration);
	let interest = amount * (pow - 1);
	let totalPayable = parseFloat(amount) + interest;
	document.querySelector('#total').value = totalPayable;
	let mPay = totalPayable / (loanDuration * 12);
	document.querySelector('#mPay').value = mPay;

	console.log(`${loanDuration} years loan of ${amount} at an interest rate of ${interestRate}% that compound total so interest is ${interest}`);
	console.log('Total Payable amount is ' + totalPayable);
	console.log('Monthly installment is ' + mPay);
}


function emi() {
	let loanDuration = document.querySelector('#loanDuration').value;
	let amount = document.querySelector('#amount').value;
	let interestRate = document.querySelector('#irate').value;
	let pow = Math.pow((1 + (interestRate / 100)), loanDuration);
	let interest = amount * (pow - 1);
	let totalPayable = parseFloat(amount) + interest;
	document.querySelector('#total').value = totalPayable;
	let mPay = totalPayable / (loanDuration * 12);
	document.querySelector('#mPay').value = mPay;
	
	console.log(`${loanDuration} years loan of ${amount} at an interest rate of ${interestRate}% that compound total so interest is ${interest}`);
	console.log('Total Payable amount is ' + totalPayable);
	console.log('Monthly installment is ' + mPay);
}