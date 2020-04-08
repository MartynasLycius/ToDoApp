/**
 * Created by Murad on 9/21/2017.
 */



/*******Vant  validation*************/

/*******Form Submit validation*************/


function validationCheckToDO() {
    var itemName = $('#itemName').val().trim();
    var startDateString = $('#startDateString').val().trim();
    var endDateString=$('#endDateString').val();



    //Error
    var itemName_error = $('#itemName_error');
    var startDateString_error = $('#startDateString_error');
    var endDateString_error=$('#endDateString_error');



    //Msg Error
    var msgError = "Empty Field";
    var msgSelect = "NONE Selected";



//
    if (!itemName || !startDateString ||!endDateString) {

        if (!itemName) {

            itemName_error.text(msgError);
            itemName_error.show();
        }




        if (!startDateString) {

            startDateString_error.text(msgError);
            startDateString_error.show();
        }

        if (!endDateString) {

            endDateString_error.text(msgError);
            endDateString_error.show();
        }


        return false;
    } else {

        if (itemName.length < 2) {

            alert("length");
            itemName_error.text("size must be between 2 and 30");
            itemName_error.show();
            return false;
        }





        return true;

    }
}


/*******Code On Change validation*************/
function startDateStringOnChange() {
    var startDateString = $('#startDateString').val().trim();
    //Error
    var startDateString_error = $('#startDateString_error');
    //Error Msg
    var msgError = "Empty Field";

    if (!startDateString) {
        startDateString_error.text(msgError);
        startDateString_error.show();
    } else {

        startDateString_error.hide();
        }



}




/******* itemName On Change validation*************/
function itemNameOnChange() {
    var itemName = $('#itemName').val().trim();
    //Error
    var itemName_error = $('#itemName_error');
    //Error Msg
    var msgError = "Empty Field";

    if (!itemName) {
        itemName_error.text(msgError);
        itemName_error.show();
    } else {

        if (itemName.length < 2) {
            itemName_error.text("size must be between 2 and 30");
            itemName_error.show();

        } else {
            itemName_error.hide();
        }
    }

}

/*******Organization validation*************/


function endDateStringOnChange () {
    var endDateString=$('#endDateString').val();
    //Error
    var endDateString_error=$('#endDateString_error');
    //Error Msg
    var msgSelect="NONE Selected";

    if(endDateString==0){
        endDateString_error.text(msgSelect);
        endDateString_error.show();
    }else{

        endDateString_error.hide();

    }

}




