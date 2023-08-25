$(document).ready(function () {
  /* CODE BARRE DE NAVIGATION */
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

  /* CODE CAROUSEL OFFRE */
  const rightImages = Array.from(
    document.getElementsByClassName("imgCarousel-right")
  );
  const rightMainPhoto = document.getElementById("mainPhoto-right");

  const leftImages = Array.from(
    document.getElementsByClassName("imgCarousel-left")
  );
  const leftMainPhoto = document.getElementById("mainPhoto-left");

  const rightMainName = rightMainPhoto.getAttribute("name-right");
  const leftMainName = leftMainPhoto.getAttribute("name-left");

  updateCaption(rightMainName, "right");
  updateCaption(leftMainName, "left");

  function updateRightImage(event) {
    const image = event.target;
    const name = image.getAttribute("name-right");

    rightMainPhoto.src = image.src;
    updateCaption(name, "right");
  }

  function updateLeftImage(event) {
    const image = event.target;
    const name = image.getAttribute("name-left");

    leftMainPhoto.src = image.src;
    updateCaption(name, "left");
  }

  function updateCaption(name, side) {
    const caption = document.getElementById(`caption-${side}`);
    caption.innerHTML = name;
  }

  rightImages.forEach(function (image) {
    image.addEventListener("click", updateRightImage);
  });

  leftImages.forEach(function (image) {
    image.addEventListener("click", updateLeftImage);
  });
});
