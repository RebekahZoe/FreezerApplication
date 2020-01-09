"use strict";
function getQueryVariable(variable)
{
       let query = window.location.search.substring(1);
       let vars = query.split("&");
       for (let i=0;i<vars.length;i++) {
               let pair = vars[i].split("=");
               if(pair[0] == variable){return pair[1];}
       }
       return(false);
}

function setTitle(){
    let title = document.getElementById("freezerName");
    let newTitle = document.createElement("h1");
    let titleText = getQueryVariable("name");
    newTitle.innerHTML = decodeURI(titleText);
    title.appendChild(newTitle);

}
let fId = getQueryVariable("id");

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
    let itemHeader = document.createElement("th");
    let quantityHeader = document.createElement("th");

    itemHeader.innerHTML = "Items";
    quantityHeader.innerHTML = "Quantity";

    headerRow.appendChild(itemHeader);
    headerRow.appendChild(quantityHeader);
    itemList.appendChild(headerRow);

    console.log(items);
for (let item of items){
    let newRow= document.createElement("tr");
    let newItem = document.createElement("td");
    let newQuantity = document.createElement("td");
    newItem.innerHTML = item.itemName;
    newQuantity.innerHTML = item.quantity;
    newRow.appendChild(newItem);
    newRow.appendChild(newQuantity);

    itemList.appendChild(newRow);
}
}

function createNewItem(){
    
    let hasNumber = /\d/;
    let format = /[!@#$%^*()_+\-=\[\]{};':"\\|,.<>\/?]+/;

    let item = document.getElementById("itemNameAdd").value.toLowerCase();
    let  quantity = document.getElementById("quantityAdd").value.toLowerCase();
    let message;
    
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
                    location.reload();
                })
                
            }
            else{
                alert(message);
            }
            
        }
        
    }
    else{
        alert("Please enter a valid item name and quantity");
    }


}

function quantityValidation(quantity){
    let letters = /^[A-Za-z]+$/;
    let format = /[!@#$%&^*()_+\-=\[\]{};':"\\|,.<>\/?]+/;
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
    
    let hasNumber = /\d/;
    let format = /[!@#$%^*()_+\-=\[\]{};':"\\|,.<>\/?]+/;

    let item = document.getElementById("itemNameEdit").value.toLowerCase();
    let quantity = document.getElementById("quantityEdit").value.toLowerCase();
    let message;
    
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
                    location.reload();
                })
                
            }
            else{
                alert(message);
            }
        }
        
        
    }location.reload();

}

function deleteItem(){
    let hasNumber = /\d/;
    let format = /[!@#$%^*()_+\-=\[\]{};':"\\|,.<>\/?]+/;

    let item = document.getElementById("itemNameDelete").value.toLowerCase();
    let message;
    
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
            if (confirm("Are you sure you want to delete this item?")){
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
       
            location.reload();
        }
        
    
    else{
        alert("Please enter a valid item name");
    }
}


