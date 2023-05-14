let Flower = {
	name: "",
	main_color: "",
	family: "",
	height: 0,
	price: 0,
	idInDatabase:0
}
function sendEditedFlower(){
	let xhrEdit = new XMLHttpRequest();
	Flower.name=document.getElementById("name").value;
	Flower.main_color=document.getElementById("main_color").value;
	Flower.family=document.getElementById("family").value;
	Flower.height=document.getElementById("height").value;
	Flower.price=document.getElementById("price").value;
	Flower.idInDatabase=location.search.split("=")[1];
	let flowerJson = JSON.stringify(Flower);
	console.log(Flower);
	xhrEdit.open("POST", "saveChanges",true);
	xhrEdit.setRequestHeader('Content-Type', 'application/json');
    xhrEdit.send(flowerJson);
    location.href="read"
}