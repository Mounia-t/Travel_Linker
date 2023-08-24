/* CODE POUR LA NAVBAR */

$(document).ready(function () {
  const menuBtn = $(".menu-btn");
  const navigation = $(".navigation");
  const header = $("header");
  const headerHeight = header.outerHeight();

  menuBtn.click(function () {
    menuBtn.toggleClass("active");
    navigation.toggleClass("active");
  });

  $(window).on("scroll", function () {
    if ($(window).scrollTop() > headerHeight) {
      header.addClass("scroll");
    } else {
      header.removeClass("scroll");
    }
  });
});

/* CODE POUR L'ANIMATION HOME */

document.addEventListener("DOMContentLoaded", function () {
  const textAnimation = document.querySelector(".text-animation");
  const imageAnimation = document.querySelector(".image-animation");

  setTimeout(function () {
    textAnimation.classList.add("active");
    imageAnimation.classList.add("active");
  }, 100);
});
