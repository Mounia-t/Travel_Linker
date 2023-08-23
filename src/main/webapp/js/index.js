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

 const connexionButton = document.getElementById("connexionButton");
  const deconnexionButton = document.getElementById("deconnexionButton");

  connexionButton.addEventListener("click", function(event) {
    // Handle your login logic here
    // You can toggle classes, show/hide elements, etc.
    // Example: Show navigation when logged in
    navigation.classList.add("active");
    // Hide the Connexion button and show the Déconnexion button
    connexionButton.style.display = "none";
    deconnexionButton.style.display = "inline";
    event.preventDefault(); // Prevent default link behavior
  });

  deconnexionButton.addEventListener("click", function(event) {
    // Handle your logout logic here
    // Example: Hide navigation when logged out
    navigation.classList.remove("active");
    // Show the Connexion button and hide the Déconnexion button
    connexionButton.style.display = "inline";
    deconnexionButton.style.display = "none";
    event.preventDefault(); // Prevent default link behavior
  });

const connexionButton = document.getElementById("connexionButtonPlaceholder");
  const deconnexionButton = document.getElementById("deconnexionButtonPlaceholder");

  // Récupérer la valeur booléenne de l'attribut data-connected
  const userConnected = JSON.parse(connexionButton.getAttribute("data-connected"));

  if (userConnected) {
    // L'utilisateur est connecté, afficher le bouton Déconnexion et masquer le bouton Connexion
    deconnexionButton.style.display = "inline";
    connexionButton.style.display = "none";
  } else {
    // L'utilisateur n'est pas connecté, afficher le bouton Connexion et masquer le bouton Déconnexion
    connexionButton.style.display = "inline";
    deconnexionButton.style.display = "none";
  }

  // Gérer la logique de déconnexion lorsque le bouton de déconnexion est cliqué
  deconnexionButton.addEventListener("click", function(event) {
    // Gérer la déconnexion ici, par exemple en redirigeant vers l'action de déconnexion dans votre code Java
    // Assurez-vous d'invalider la session dans votre code Java
    event.preventDefault();
  });





