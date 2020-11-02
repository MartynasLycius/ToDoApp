(function ($) {
    var getCsrf = function () {
        return $('[name=_csrf]').val();
    };
    var generateFormSubmitParams = function (context) {
        var _this = $(context),
            data = {};
        _this.find('[name]').each(function (index, value) {
            var _this = $(this),
                name = _this.attr('name'),
                value = _this.val();
            data[name] = value;
        });
        var params = {};
        params["data"] = JSON.stringify(data);
        console.log(params["data"]);
        return params;
    };

    var bindFormSubmits = function (formName, onSuccess, onError) {
        $('form.' + formName).on('submit', function () {
            var params = generateFormSubmitParams(this);
            var $form = $(this);
            $.ajax({
                url: $form.attr('action'),
                type: $form.attr('method'),
                contentType: "application/json",
                dataType: "json",
                data: params["data"],
                beforeSend: function (request) {
                    request.setRequestHeader("X-CSRF-TOKEN", getCsrf());
                    request.setRequestHeader("Accept", "application/json");
                },
                success: onSuccess,
                error: onError
            });
            return false;
        })
    };

    bindFormSubmits('createTodo', function (response) {
        window.location.replace("/");
    }, function (xhr) {
        var responseError = JSON.parse(xhr.responseText);
        alert(xhr.status + " " + responseError);
    });

    bindFormSubmits('editTodo', function (response) {
        window.location.replace("/");
    }, function (xhr) {
        alert(xhr.status + " " + JSON.stringify(xhr));
    });

})(jQuery);