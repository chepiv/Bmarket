new WOW().init();

$('.loading-icon').click(function() {
    var $this = $(this);
    $this.html('<i class="fas fa-circle-notch fa-spin"></i>');
});

function tooltipLoader() {
    $(".title-row a h4").each(function (index) {
        if ($(this)[0].scrollWidth > $(this).innerWidth()) {
            $(this).parent().tooltip('enable')
        }
        else{
            $(this).parent().tooltip('disable')
        }
    });
    $(".title-row h4").each(function (index) {
        if ($(this)[0].scrollWidth > $(this).innerWidth()) {
            $(this).tooltip('enable')
        }
        else{
            $(this).tooltip('disable')
        }
    });
}

$( document ).ready(function() {
    $(function () {
        $('[data-toggle="tooltip"]').tooltip({
            container: 'body',
            boundary: 'window'
        });
    });
    tooltipLoader();
    $( window ).resize(function() {
        tooltipLoader()
    });
});

