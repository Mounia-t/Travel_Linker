$(document).ready(function () {
    $('.carousel').carousel({
        padding: 200
    });

    autoplay();
    function autoplay() {
        $('.carousel').carousel('next');
        setTimeout(autoplay, 4500);
    }
});
