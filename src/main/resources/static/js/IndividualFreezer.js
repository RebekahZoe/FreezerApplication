"use strict";
function getQueryVariable(variable)
{
       var query = window.location.search.substring(1);
       var vars = query.split("&");
       for (var i=0;i<vars.length;i++) {
               var pair = vars[i].split("=");
               if(pair[0] == variable){return pair[1];}
       }
       return(false);
}
function setTitle(){
    var title = document.getElementById("freezerName");
    var newTitle = document.createElement("h1");
    var titleText = getQueryVariable("name");
    newTitle.innerHTML = decodeURI(titleText);
    title.appendChild(newTitle);

}
var fId = getQueryVariable("id");

function getItemsFromFreezer(){
    console.log("bloop");
   axios.get("http://localhost:8080/getItemsFromFreezer/"+fId)
   .then((response)=>{
        showItemsFromFreezer(response.data);
        console.log(response);
   })
   .catch((error)=>{
       console.error(error);
   })
}

const headerRow = document.getElementById("headerRow")
const itemList = document.getElementById("itemsInFreezer");
function showItemsFromFreezer(items){
    itemList.innerHTML = " ";
    headerRow.innerHTML =" ";
    var itemHeader = document.createElement("th");
    var quantityHeader = document.createElement("th");

    itemHeader.innerHTML = "Items";
    quantityHeader.innerHTML = "Quantity";

    headerRow.appendChild(itemHeader);
    headerRow.appendChild(quantityHeader);
    itemList.appendChild(headerRow);

    console.log(items);
for (let item of items){
    var newRow= document.createElement("tr");
    var newItem = document.createElement("td");
    var newQuantity = document.createElement("td");
    newItem.innerHTML = item.itemName;
    newQuantity.innerHTML = item.quantity;
    newRow.appendChild(newItem);
    newRow.appendChild(newQuantity);

    itemList.appendChild(newRow);
}
}

function createNewItem(){
    
    var hasNumber = /\d/;
    var format = /[!@#$%^*()_+\-=\[\]{};':"\\|,.<>\/?]+/;

    const item = document.getElementById("itemNameAdd").value;
    const quantity = document.getElementById("quantityAdd").value;
    var message;
    
    if(item !== "" && quantity !==""){
        if (hasNumber.test(item)){
            message = "Please enter a valid item name (No numbers)" + quantityValidation(quantity);
            alert(message);
        } 
        else if (format.test(item)){
            message = "Please enter a valid item name (No special characters (& is allowed))" + quantityValidation(quantity);
            alert(message);
        }
        else if (item.length < 3){
            message = "Please enter a valid item name (must be greater than 3 characters)" + quantityValidation(quantity)
            alert(message);
        }
        else{
            message = quantityValidation(quantity);
            if (message === " "){
                axios.patch("http://localhost:8080/addItem/"+fId,{
                    itemName : item,
                    quantity : quantity
                }).then(()=>{
                    getItemsFromFreezer();
                })
                
            }
            else{
                alert(message);
            }
            item.value = "";
            quantity.value="";
        }
        
    }
    else{
        alert("Please enter a valid item name and quantity");
    }


}

function quantityValidation(quantity){
    var letters = /^[A-Za-z]+$/;
    var format = /[!@#$%&^*()_+\-=\[\]{};':"\\|,.<>\/?]+/;
    if (letters.test(quantity)){
        return " Please enter a valid quantity (no letters)"
    }
    else if (format.test(quantity)){
        return " Please enter a valid quantity (no special characters)"
    }
    else if (quantity === "0"){
        return " Please enter a valid quantity (quantity must be greater than 0)"
    }
    else{
        return " "
    }


}
function editItem(){
    
    var hasNumber = /\d/;
    var format = /[!@#$%^*()_+\-=\[\]{};':"\\|,.<>\/?]+/;

    const item = document.getElementById("itemNameEdit").value;
    const quantity = document.getElementById("quantityEdit").value;
    var message;
    
    if(item !== "" && quantity !==""){
        if (hasNumber.test(item)){
            message = "Please enter a valid item name (No numbers)" + quantityValidation(quantity);
            alert(message);
        } 
        else if (format.test(item)){
            message = "Please enter a valid item name (No special characters (& is allowed))" + quantityValidation(quantity);
            alert(message);
        }
        else if (item.length < 3){
            message = "Please enter a valid item name (must be greater than 3 characters)" + quantityValidation(quantity)
            alert(message);
        } 
        else{
            message = quantityValidation(quantity);
            if (message === " "){
                axios.put("http://localhost:8080/updateItemByName/"+item,{
                    itemName : item,
                    quantity : quantity
                }).then(()=>{
                    getItemsFromFreezer();
                })
                
            }
            else{
                alert(message);
            }
        }
        item.value = "";
        quantity.value="";
        
    }
    else{
        alert("Please enter a valid item name and quantity");
    }
}

function deleteItem(){
    var hasNumber = /\d/;
    var format = /[!@#$%^*()_+\-=\[\]{};':"\\|,.<>\/?]+/;

    const item = document.getElementById("itemNameDelete").value;
    var message;
    
    if(item !== ""){
        if (hasNumber.test(item)){
            message = "Please enter a valid item name (No numbers)";
            alert(message);
        } 
        else if (format.test(item)){
            message = "Please enter a valid item name (No special characters (& is allowed))";
            alert(message);
        }
        else if (item.length < 3){
            message = "Please enter a valid item name (must be greater than 3 characters)"; 
            alert(message);
        }
        else{ 
            if (confirm("Are you sure you want to delete this freezer?")){
                axios.delete("http://localhost:8080/deleteItemFromFreezerByName/"+item+"/"+fId)               
                .then((response)=>{
                    console.log(response);
                    getItemsFromFreezer();
                })
                .catch((error)=>{
                    console.error(error);
                    getItemsFromFreezer();
                })
                
            }
        }
        item.value="";
            
        }
        
    
    else{
        alert("Please enter a valid item name");
    }
}


