var budgerContoller=(function() {

})();


var UIController=(function(){

})();


var contoroller=(function(budgetCtrl,UItrl){
    var ctrlAddItem=function(){

        console.log('it works')
    }
    
    

document.querySelector('.add__btn').addEventListener('click', ctrlAddItem); 
document.addEventListener('keypress',function(event){
if (event.keycode===13 || event.which===13){
    ctrlAddItem();

}
    });

})(budgerContoller,UIController);