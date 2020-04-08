/**
 * Created by  on 3/22/17.
 */

/*******User validation*************/

/*******Form Submit validation*************/

function validtionCheck() {

    var user_name=$('#user_name').val().trim();
    var pasword=$('#pasword').val().trim();
    var re_pasword=$('#re_pasword').val().trim();
    var hr_employee_name=$('#hr_employee_name').val();
    var location_name=$('#location_name').val();
    var location_name_edit=$('#location_name_edit').val();
    //Error
    var user_name_error=$('#user_name_error');
    var dealer_name_error=$('#dealer_name_error');
    var pasword_error=$('#pasword_error');
    var re_pasword_error=$('#re_pasword_error');
    var hr_employee_name_error=$('#hr_employee_name_error');
    var location_name_error=$('#location_name_error');
    var location_name_edit_error=$('#location_name_edit_error');
    var role_error=$('#role_error');
    var location_error=$('#location_error');
    //Msg Error
    var msgError="Empty Field";
    var msgSelect="NONE Selected";
    var msgMismatch ="Password Mismatch";

    // if (hr_employee_name==0) {
    //
    //     if ($('#employeeType').is(':checked')) {
    //         if (hr_employee_name == 0) {
    //             hr_employee_name_error.text(msgSelect);
    //             hr_employee_name_error.show();
    //             return false;
    //         }
    //     }
    //
    //
    // }

    if(!user_name||!pasword||!re_pasword ||hr_employee_name == 0) {

        if(!user_name){
            user_name_error.text(msgError);
            user_name_error.show();
        }

        if(!pasword){
            pasword_error.text(msgError);
            pasword_error.show();
        }

        if(!re_pasword){
            re_pasword_error.text(msgError);
            re_pasword_error.show();
        }
        // if (location_name == 0) {
        //     location_name_error.text(msgSelect);
        //     location_name_error.show();
        //
        // }
        if (hr_employee_name == 0) {
            hr_employee_name_error.text(msgSelect);
            hr_employee_name_error.show();
            return false;
        }

        return false;
    }else{

        if(pasword != re_pasword) {
            re_pasword_error.text(msgMismatch);
            re_pasword_error.show();
            return false;
        }

        if(user_name.length<2){
            user_name_error.text("size must be between 2 and 30");
            user_name_error.show();
            return false;
        }

        if(pasword.length<2){
            pasword_error.text("size must be between 2 and 10");
            pasword_error.show();
            return false;
        }
        if(re_pasword.length<2){
            re_pasword_error.text("size must be between 2 and 10");
            re_pasword_error.show();
            return false;
        }

            return true;

    }
}

function validtionEditCheck() {

    var user_name=$('#user_name').val().trim();
    var pasword=$('#pasword').val().trim();
    var re_pasword=$('#re_pasword').val().trim();
    var hr_employee_name=$('#hr_employee_name').val();
    //Error
    var user_name_error=$('#user_name_error');
    var pasword_error=$('#pasword_error');
    var re_pasword_error=$('#re_pasword_error');
    var hr_employee_name_error=$('#hr_employee_name_error');
    var role_error=$('#role_error');
    //Msg Error
    var msgError="Empty Field";
    var msgSelect="NONE Selected";
    var msgMismatch ="Password Mismatch";

    if(!user_name){

        if(!user_name){
            user_name_error.text(msgError);
            user_name_error.show();
        }



        return false;
    }else{

        if(user_name.length<2){
            user_name_error.text("size must be between 2 and 30");
            user_name_error.show();
            return false;
        }
        if(pasword != re_pasword) {
            re_pasword_error.text(msgMismatch);
            re_pasword_error.show();
            return false;
        }

        return true;

    }
}


/*******Role on Change validation*************/
function roleOnChange () {
    var role=$('#role').val();
    //Error
    var role_error=$('#role_error');
    //Error Msg
    var msgSelect="NONE Selected";

    if(role==0){
        role_error.text(msgSelect);
        role_error.show();
    }else{

        role_error.hide();

    }

}

//location_name_edit

/*******Hr Employee Name on Change validation*************/
function hrEmployeeNameOnChangeUser() {
    var hr_employee_name=$('#hr_employee_name').val();
    //Error
    var hr_employee_name_error=$('#hr_employee_name_error');
    //Error Msg
    var msgSelect="NONE Selected";

    if(hr_employee_name==0){
        hr_employee_name_error.text(msgSelect);
        hr_employee_name_error.show();
    }else{

        hr_employee_name_error.hide();

    }

}

/*******Dealer Name on Change validation*************/
function locationNameOnChanges() {
    var location_name=$('#location_name').val();
    //Error
    var location_name_error=$('#location_name_error');
    //Error Msg
    var msgSelect="NONE Selected";

    if(location_name==0){
        location_name_error.text(msgSelect);
        location_name_error.show();
    }else{

        location_name_error.hide();

    }

}

/*******Re-Password On Change validation*************/
function rePasswordOnChange() {
    var re_pasword=$('#re_pasword').val().trim();
    //Error
    var re_pasword_error=$('#re_pasword_error');
    //Error Msg
    var msgError="Empty Field";

    if(!re_pasword){
        re_pasword_error.text(msgError);
        re_pasword_error.show();
    }else{
        if(re_pasword.length<2){
            re_pasword_error.text("size must be between 2 and 20");
            re_pasword_error.show();

        }else {
            re_pasword_error.hide();
        }

    }

}

/*******Password On Change validation*************/
function passwordOnChange() {
    var pasword=$('#pasword').val().trim();
    //Error
    var pasword_error=$('#pasword_error');
    //Error Msg
    var msgError="Empty Field";

    if(!pasword){
        pasword_error.text(msgError);
        pasword_error.show();
    }else{
        if(pasword.length<2){
            pasword_error.text("size must be between 2 and 20");
            pasword_error.show();

        }else {
            pasword_error.hide();
        }

    }

}

function passwordEditOnChange() {
    var pasword=$('#pasword').val().trim();
    var re_pasword=$('#re_pasword').val().trim();
    var msgMismatch ="Password Mismatch";
    var pasword_error=$('#pasword_error');
    //Error Msg
    var msgError="Empty Field";
    var re_pasword_error=$('#re_pasword_error');
    if(!pasword){

    }else{
        if(pasword.length<2){
            pasword_error.text("size must be between 2 and 20");
            pasword_error.show();

        }else {
            pasword_error.hide();
        }
    }

    if(pasword != re_pasword) {
        re_pasword_error.text(msgMismatch);
        re_pasword_error.show();
    }else {
        re_pasword_error.hide();
    }

}
/******* User Name On Change validation*************/
function userNameOnChange () {
    var user_name=$('#user_name').val().trim();
    //Error
    var user_name_error=$('#user_name_error');
    //Error Msg
    var msgError="Empty Field";

    if(!user_name){
        user_name_error.text(msgError);
        user_name_error.show();
    }else{

        if(user_name.length<2){
            user_name_error.text("size must be between 2 and 30");
            user_name_error.show();

        }else{
            user_name_error.hide();
        }
    }

}



