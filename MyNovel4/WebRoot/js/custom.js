$(document).ready(function() {
    //sponsors
    $(".sponsors").owlCarousel({
      autoPlay: 3000, //Set AutoPlay to 3 seconds
      items : 4,    
      nav : true,
      transitionStyle:"fade",
      itemsDesktop : [1199,3],
      itemsDesktopSmall : [979,3]

    });
    
    ///smooth scroll
    $(function() {
      $('.scroll').click(function() {
        if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {
          var target = $(this.hash);
          target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
          if (target.length) {
            $('html, body').animate({
              scrollTop: target.offset().top - 69
            }, 800);
            return false;
          }
        }
      });
    });
});    