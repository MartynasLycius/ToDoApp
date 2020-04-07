/**
 * Created by murad on 3/5/17.
 */
$.noConflict();
var _ctx = $("meta[name='context-path']").attr("content");
jQuery(document).ready(function ($) {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $('.parent').livequery('change', function() {
        var dt = $(this).val();

        $(this).nextAll('.parent').remove();
        $(this).nextAll('.brr').remove();
        if($(this).val() == 0) {
            $.ajax({
                type: 'POST',
                url: _ctx+ 'user/setID',
                data :{ "parid" : 0},
                beforeSend: function(xhr) {
                    xhr.setRequestHeader(header, token);
                }
            });
            return false;
        }

        $.ajax({
            type: 'POST',
            url: _ctx+ 'user/setID',
            data :{ "parid" : dt},
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            }
        });

        $.ajax({
            type: 'POST',
            url: _ctx+ 'location/dropData',
            data :{ "pid" : $(this).val()},
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            success : function(callback){
                var ddown = '<select name="sub_category" class="parent">' +
                    '<option value='+dt+' selected="selected">-- Sub Category --</option>';

                $.each( JSON.parse(callback), function( key, value ) {
                    ddown = ddown + '<option value='+value.locationId+'>'+value.name+'</option>';
                })

                ddown = ddown + '</select>';

                if(JSON.parse(callback).length != 0) {
                    $('#show_sub_categories').append('<br class="brr"/>');
                    $('#show_sub_categories').append(ddown);
                }

            },
            error : function(){
                alert("error");
            }
        });

        return false;
    });

});