/**
 * Created by  on 3/2/17.
 */

var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var _ctx = $("meta[name='context-path']").attr("content");
function sendFromDataProcess() {

    var isError=false;

   // console.log($.trim($('#roleName').val()));

    if(!$.trim($('#email').val())){
        var unitnameErrorField =$("#nameError").text("Empty Field");
        unitnameErrorField.show();
        isError=true;
    }

  if(!isError) {

      var item = {};
      item ["id"] = $('#userId').val();
      item ["email"] = $.trim($('#email').val());
      item ["passwordHash"] = $.trim($('#password').val());
      item ["rePassword"] = $.trim($('#rePassword').val());
      item ["role"] = $('#roleId').val();
      item ["locationId"] = $('#locationId').val();
      item ["hrid"] = $('#empName').val();
      item ["active"] = $('#isActive').val();




      console.log("Rsult sending to server:");
     // console.log(JSON.stringify(item));
      sendDataToserver(item);
  }
}
    
function sendDataToserver(item) {
        $.ajax({
            contentType : 'application/json; charset=utf-8',
            type: 'POST',
            url: _ctx+ 'user/popupEdit',
            dataType : 'json',
            data : JSON.stringify(item),
            beforeSend: function(xhr) {

                xhr.setRequestHeader(header, token);
            },
            success : function(callback) {
                console.log(callback)
                if (callback) {
                    window.location.replace("/user/allUser");
                }else{
                    var unitnameErrorField =$("#nameError").text("Same Name Exist");
                    unitnameErrorField.show();
                }


                // alert("success");
            },
            error : function(){

                alert("error");
            }
        });
}


