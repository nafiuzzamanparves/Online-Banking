function check() {
	let newPass = document.querySelector('#newPass').value;
	let confirmPass = document.querySelector('#confirmPass').value;

	if (newPass == confirmPass) {
		document.getElementById("newPass").style.borderColor = "green";
		document.getElementById("newPass").style.borderWidth = "3px";
		document.getElementById("confirmPass").style.borderColor = "green";
		document.getElementById("confirmPass").style.borderWidth = "3px";
		document.getElementById("btndis").disabled = false;
		document.getElementById("span").innerHTML = "";
	} else {
		document.getElementById("span").innerHTML = "Password doesn't match";
		document.getElementById("btndis").disabled = true;
	}
}