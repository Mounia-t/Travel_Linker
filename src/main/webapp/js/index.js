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

  //Javacript pour le slide des vidéos de navigation
  const btns = document.querySelectorAll(".nav-btn");
  const slides = document.querySelectorAll(".video-slide");
  const contents = document.querySelectorAll(".content");

  var sliderNav = function (manual) {
    btns.forEach((btn) => {
      btn.classList.remove("active");
    });

    slides.forEach((slide) => {
      slide.classList.remove("active");
    });

    contents.forEach((content) => {
      content.classList.remove("active");
    });

    btns[manual].classList.add("active");
    slides[manual].classList.add("active");
    contents[manual].classList.add("active");
  };

  btns.forEach((btn, i) => {
    btn.addEventListener("click", () => {
      sliderNav(i);
    });
  });

  // Javascript pour le slide du carousel
  document.getElementById("next").onclick = function () {
    let lists = document.querySelectorAll(".item-package");
    document.getElementById("slide-package").appendChild(lists[0]);
  };
  document.getElementById("prev").onclick = function () {
    let lists = document.querySelectorAll(".item-package");
    document.getElementById("slide-package").prepend(lists[lists.length - 1]);
  };
});

   // Code pour gérer la visibilité des boutons en fonction de la connexion
    window.addEventListener('DOMContentLoaded', (event) => {
        var connexionButton = document.getElementById("connexionButton");
        var deconnexionButton = document.getElementById("deconnexionButton");
        var loggedIn = "#{loginControllerBean.loggedIn}";

        if (loggedIn) {
            connexionButton.style.display = "none";
            deconnexionButton.style.display = "inline";
        } else {
            connexionButton.style.display = "inline";
            deconnexionButton.style.display = "none";
        }
    });
