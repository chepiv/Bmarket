new WOW().init();

$('.loading-icon').click(function() {
    var $this = $(this);
    $this.html('<i class="fas fa-circle-notch fa-spin"></i>');
});