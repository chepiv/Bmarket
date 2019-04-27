var postRegister = {
    DATABASE_ERROR: {
        title: 'Rejestracja nie powiodła się',
        message: 'Użytkownik znajduje się już w bazie danych',
        style: 'error'
    },
    SUCCESS:{
        title: 'Użytkownik zarejestrowany',
        message: 'Można się teraz zalogować',
        style: 'success'
    },
    PASSWORDS_NOT_MATCH:{
        title: 'Rejestracja nie powiodła się',
        message: 'Hasła nie zgadzają się',
        style: 'error'
    }
};

var postLogin = {
    DATABASE_ERROR: {
        title: 'Logowanie nie powiodło się',
        message: 'Błąd bazy danych',
        style: 'error'
    },
    SUCCESS:{
        title: '',
        message: '',
        style: 'none'
    }
};

let type = $('#alertContainer').attr('type');
let data = $('#alertContainer').attr('data');
let message;
let title;
let style;
if(type == "postRegister"){
    title = postRegister[data]['title'];
    message = postRegister[data]['message'];
    style = postRegister[data]['style'];
}
else if(type == "postLogin"){
    title = postLogin[data]['title'];
    message = postLogin[data]['message'];
    style = postLogin[data]['style'];
}
if(title != undefined) {
    if (style == "warning" || style == "error" || style == "success") {
        let alert_id = 'alert';//+alerts.length.toString();
        $('#alertContainer').html('<div class="modal fade" id="' + alert_id + '" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">\n' +
            '    <div class="modal-dialog modal-dialog-centered modal-md" role="document">\n' +
            '        <div class="modal-content">\n' +
            '            <div class="modal-header">\n' +
            '                <h4 class="modal-title w-100 text-' + style + '" id="myModalLabel"> ' + title + ' </h4>\n' +
            '                <button type="button" class="close" data-dismiss="modal" aria-label="Close">\n' +
            '                    <span aria-hidden="true">&times;</span>\n' +
            '                </button>\n' +
            '            </div>\n' +
            '            <div class="modal-body">\n' +
            '                ' + message + '\n' +
            '            </div>\n' +
            '            <div class="modal-footer">\n' +
            '                <button type="button" class="btn btn-' + style + ' btn-sm" data-dismiss="modal">Zamknij</button>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>');
        $('#' + alert_id).modal('show');
    } else {
        console.error("unrecognized alert style")
    }
}


